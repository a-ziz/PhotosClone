package com.test.aziz.photos.clone;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.*;

@RestController
public class PhotosController {

  private final PhotosService photosService;

  // constructor injection
  public PhotosController(PhotosService photosService) {
    this.photosService = photosService;
  }


  // mapping it, browser will send HTTP GET request to our server
  // path is "/"
  // there are post, delete mapping as well
  @GetMapping("/")
  public String hello() {
    return "Hello world";
  }

  @GetMapping("/photos")
  public Collection<Photo> get() {
    return photosService.get();
  }

  // @PathVariable - in the mapping if param {id} - put the id in the string param
  @GetMapping("/photos/{id}")
  public Photo get(@PathVariable String id) {
//    return db;
    Photo photo = photosService.get(id);
    if (photo == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    return photo;
  }

  @DeleteMapping("/photos/{id}")
  public void delete(@PathVariable String id) {
    Photo photo = photosService.remove(id);
    if (photo == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
  }

  @PostMapping("/photos")
  public Photo create(@RequestPart("data") MultipartFile file) throws IOException {

    return photosService.save(file.getOriginalFilename(), file.getBytes());
  }
}