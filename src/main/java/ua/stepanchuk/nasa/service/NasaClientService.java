package ua.stepanchuk.nasa.service;

import ua.stepanchuk.nasa.dto.Picture;
import ua.stepanchuk.nasa.dto.User;

import java.util.List;

public interface NasaClientService {
    Picture getLargestPicture();

    List<Picture> getAllPictures();

    Picture getSmallestPicture();
}
