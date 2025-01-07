package com.example.lottiegenerator.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

@Service
public class LottieService {

  public Map<String, Object> createLottieJson(MultipartFile image1, MultipartFile image2) throws IOException {

    if (image1 == null || image1.isEmpty() || image2 == null || image2.isEmpty()) {
            throw new IllegalArgumentException("Both image files must be provided.");
        }

    BufferedImage bufferedImage1, bufferedImage2;

      try (InputStream inputStream1 = image1.getInputStream();
           InputStream inputStream2 = image2.getInputStream()) {

        bufferedImage1 = ImageIO.read(inputStream1);
        bufferedImage2 = ImageIO.read(inputStream2);

    } catch(Exception e) {
        throw new IOException("Error reading image files: " + e.getMessage());
    }


    int width = bufferedImage1.getWidth();
    int height = bufferedImage1.getHeight();
    int width2 = bufferedImage2.getWidth();
    int height2 = bufferedImage2.getHeight();

     if (width != width2 || height != height2) {
        throw new IllegalArgumentException("Images must be the same size.");
     }


    int frameRate = 1;
    int totalFrames = 2;


    Map<String, Object> animationData = Map.of(
            "v", "5.10.2",
            "fr", frameRate,
            "ip", 0,
            "op", totalFrames,
            "w", width,
            "h", height,
           "assets", List.of(
             Map.of(
                   "id", "img_1",
                   "w", width,
                   "h", height,
                    "u", "",
                   "p", "image1.png",
                     "e", 1
               ),
              Map.of(
                    "id", "img_2",
                    "w", width,
                    "h", height,
                    "u", "",
                    "p", "image2.png",
                     "e", 1
                )
          ),
            "layers", List.of(
              Map.of(
                      "ty", 2,
                      "nm", "image_1",
                      "refId", "img_1",
                      "ind", 1,
                       "ip", 0,
                       "op", frameRate,
                         "ks", Map.of(
                                   "o",Map.of("a", 0, "k", 100, "ix", 11),
                                   "r",Map.of("a", 0, "k", 0, "ix", 10),
                                   "p",Map.of("a", 0, "k", List.of(width/2,height/2),"ix",2),
                                   "s",Map.of("a", 0, "k", List.of(100,100), "ix", 6),
                                   "a",Map.of("a", 0, "k", List.of(0,0), "ix", 1)
                            )
                   ),
               Map.of(
                  "ty", 2,
                      "nm", "image_2",
                      "refId", "img_2",
                      "ind", 2,
                      "ip", frameRate,
                       "op", totalFrames,
                       "ks", Map.of(
                           "o",Map.of("a", 0, "k", 100, "ix", 11),
                            "r",Map.of("a", 0, "k", 0, "ix", 10),
                            "p",Map.of("a", 0, "k", List.of(width/2,height/2), "ix", 2),
                           "s",Map.of("a", 0, "k", List.of(100,100), "ix", 6),
                            "a",Map.of("a", 0, "k", List.of(0,0), "ix", 1)
                       )
               )
          )
       );
   return animationData;
}
      }
