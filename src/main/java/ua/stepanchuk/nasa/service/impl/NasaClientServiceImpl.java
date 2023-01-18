package ua.stepanchuk.nasa.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ua.stepanchuk.nasa.dto.Picture;
import ua.stepanchuk.nasa.service.NasaClientService;

import java.net.URI;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NasaClientServiceImpl implements NasaClientService {
    private final RestTemplate restTemplate;
    @Value("${nasa.api.url}")
    private String nasaApiUrl;

    @Cacheable("largestPicture")
    @Override
    public Picture getLargestPicture() {
        return getAllPictures()
                .stream()
                .max(Comparator.comparing(Picture::getSize))
                .orElseThrow();
    }

    @Cacheable("allPictures")
    @Override
    public List<Picture> getAllPictures() {
        return restTemplate
                .getForObject(nasaApiUrl, JsonNode.class)
                .findValuesAsText("img_src")
                .stream()
                .map(this::createPicture)
                .collect(Collectors.toList());
    }

    @Cacheable("smallestPicture")
    @Override
    public Picture getSmallestPicture() {
            return getAllPictures()
                    .stream()
                    .min(Comparator.comparing(Picture::getSize))
                    .orElseThrow();
    }

    private Picture createPicture(String pictureUrl) {
        URI pictureLocation = restTemplate.headForHeaders(pictureUrl).getLocation();
        long pictureSize = restTemplate.headForHeaders(pictureLocation).getContentLength();
        return new Picture(pictureUrl, pictureSize);
    }

}
