package com.test.aziz.photos.clone;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PhotosController {

  // mapping it, browser will send HTTP GET request to our server
  // path is "/"
  // there are post, delete mapping as well
  @GetMapping("/")
  public String hello(){
    return "Hello world";
  }
}
