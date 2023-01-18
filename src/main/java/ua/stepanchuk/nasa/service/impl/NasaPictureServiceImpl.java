package ua.stepanchuk.nasa.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import ua.stepanchuk.nasa.dto.Picture;
import ua.stepanchuk.nasa.dto.PictureSender;
import ua.stepanchuk.nasa.dto.User;
import ua.stepanchuk.nasa.dto.response.SuccessResponse;
import ua.stepanchuk.nasa.exception.IncorrectPictureException;
import ua.stepanchuk.nasa.exception.InvalidUserDataException;
import ua.stepanchuk.nasa.exception.PictureAlreadySubmittedException;
import ua.stepanchuk.nasa.service.NasaClientService;
import ua.stepanchuk.nasa.service.NasaPictureService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NasaPictureServiceImpl implements NasaPictureService {

    private Map<User, Picture> users = new ConcurrentHashMap<>();

    private final NasaClientService nasaClientService;

    @Override
    public SuccessResponse checkPicture(PictureSender pictureSender) {
        validateUserAlreadySubmitted(pictureSender);
        validateUserData(pictureSender);
        validatePicture(pictureSender);
        return new SuccessResponse("This photo is confirmed to be from NASA");
    }

    @Override
    public List<User> getUsersFromSystem() {
        return new ArrayList<>(users.keySet());
    }

    private void validatePicture(PictureSender pictureSender) {
        Picture picture = pictureSender.getPicture();
        if (!nasaClientService.getAllPictures().contains(picture))
            throw new IncorrectPictureException();

        users.put(pictureSender.getUser(), picture);
    }

    private void validateUserAlreadySubmitted(PictureSender pictureSender) {
        if (users.containsKey(pictureSender.getUser()))
            throw new PictureAlreadySubmittedException();
    }

    private void validateUserData(PictureSender pictureSender) {
        User user = pictureSender.getUser();
        if (ObjectUtils.isEmpty(user.getFirstName()) || ObjectUtils.isEmpty(user.getLastName()))
            throw new InvalidUserDataException();
    }
}
