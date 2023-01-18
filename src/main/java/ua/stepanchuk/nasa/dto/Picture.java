package ua.stepanchuk.nasa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class Picture {
    private String url;
    private long size;
}
