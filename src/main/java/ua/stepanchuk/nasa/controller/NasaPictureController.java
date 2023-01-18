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

@RestController
@RequestMapping("/pictures")
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
        System.out.println(pictureSender);
        return nasaPictureService.checkPicture(pictureSender);
    }

    @GetMapping("/users")
    public List<Picture> getAllPictures() {
        return nasaClientService.getAllPictures();
    }

}
