package ua.stepanchuk.nasa.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ua.stepanchuk.nasa.dto.Picture;
import ua.stepanchuk.nasa.dto.PictureSender;
import ua.stepanchuk.nasa.dto.User;
import ua.stepanchuk.nasa.dto.response.SuccessResponse;
import ua.stepanchuk.nasa.service.NasaClientService;
import ua.stepanchuk.nasa.service.NasaPictureService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/nasa/pictures")
@RequiredArgsConstructor
public class NasaPictureController {

    private final NasaPictureService nasaPictureService;
    private final NasaClientService nasaClientService;

    @GetMapping("/largest")
    public Picture getLargestPicture() {
        return nasaClientService.getLargestPicture();
    }

    @GetMapping("/smallest")
    public Picture getSmallestPicture() {
        return nasaClientService.getSmallestPicture();
    }

    @GetMapping("/users-in-system")
    public List<User> getUsersFromSystem() {
        return nasaPictureService.getUsersFromSystem();
    }

    @PostMapping
    public SuccessResponse submitForCheckPicture(@RequestBody PictureSender pictureSender) {
        return nasaPictureService.checkPicture(pictureSender);
    }

    @GetMapping()
    public List<Picture> getAllPictures() {
        return nasaClientService.getAllPictures();
    }

    @GetMapping("/view-largest")
    public String ViewLargestPicture() {
        Picture picture = nasaClientService.getLargestPicture();
        return String.format("<img src=\"%s\">", picture.getUrl());
    }

    @GetMapping("/view-smallest")
    public String ViewSmallestPicture() {
        Picture picture = nasaClientService.getSmallestPicture();
        return String.format("<img src=\"%s\">", picture.getUrl());
    }

    @GetMapping("/view-all")
    public String ViewAllPicture() {
        List<Picture> pictures = nasaClientService.getAllPictures();
        return pictures.stream()
                .map(Picture::getUrl)
                .map(picture -> String.format("<img src=\"%s\"><br>\n", picture))
                .collect(Collectors.joining());
    }

}
