package com.example.lottiegenerator.controller;


import com.example.lottiegenerator.service.LottieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Controller
public class LottieController {

   private LottieService lottieService;
   @Autowired
    public LottieController(LottieService lottieService) {
        this.lottieService = lottieService;
    }


    @GetMapping("/")
    public String index() {
       return "index";
    }

    @PostMapping("/generate_animation")
    public ResponseEntity<Map<String,Object>> generateAnimation(
           @RequestParam("image1") MultipartFile image1,
           @RequestParam("image2") MultipartFile image2
    ) {
           try {
                Map<String, Object> animationData = lottieService.createLottieJson(image1, image2);
                return ResponseEntity.ok(animationData);
             }
            catch(IOException exception) {
              return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                      .body(Map.of("error", "Error processing images: " + exception.getMessage()));
            }
              catch (IllegalArgumentException exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", exception.getMessage()));
        }
    }
                  }
