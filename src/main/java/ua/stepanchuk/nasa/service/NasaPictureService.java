package ua.stepanchuk.nasa.service;


import ua.stepanchuk.nasa.dto.Picture;
import ua.stepanchuk.nasa.dto.PictureSender;
import ua.stepanchuk.nasa.dto.User;
import ua.stepanchuk.nasa.dto.response.SuccessResponse;

import java.util.List;

public interface NasaPictureService {
     SuccessResponse checkPicture(PictureSender pictureSender);

     List<User> getUsersFromSystem();
}
