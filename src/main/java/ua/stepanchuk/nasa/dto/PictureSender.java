package ua.stepanchuk.nasa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PictureSender {
    private User user;
    private Picture picture;
}
