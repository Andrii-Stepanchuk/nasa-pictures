package ua.stepanchuk.nasa;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Slf4j
@EnableCaching
@EnableScheduling
@SpringBootApplication
public class NasaPicturesSpringBootApp {

    public static void main(String[] args) {
        SpringApplication.run(NasaPicturesSpringBootApp.class, args);
    }

    @Scheduled(fixedDelay = 5 * 60 * 1000)
    @CacheEvict(value = {"largestPicture", "smallestPicture", "allPictures"})
    public void refreshCache() {
        log.info("Refreshing NASA cache...");
    }

}
