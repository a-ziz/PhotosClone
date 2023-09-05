package com.test.aziz.photos.clone;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.*;

@RestController
public class PhotosController {

  private Map<String, Photo> db = new HashMap<>() {{
    put("1", new Photo("1", "hello.jpg"));
  }};

//  private List<Photo> db = List.of(new Photo("1", "hello.jpg"));

  // mapping it, browser will send HTTP GET request to our server
  // path is "/"
  // there are post, delete mapping as well
  @GetMapping("/")
  public String hello(){
    return "Hello world";
  }

  @GetMapping("/photos")
  public Collection<Photo> get(){
    // type List -> Collection
//    return db;
    return db.values();
  }

  // @PathVariable - in the mapping if param {id} - put the id in the string param
  @GetMapping("/photos/{id}")
  public Photo get(@PathVariable String id){
//    return db;
    Photo photo = db.get(id);
    if (photo == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    return photo;
  }

  @DeleteMapping("/photos/{id}")
  public void delete(@PathVariable String id){
    Photo photo = db.remove(id);
    if (photo == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
  }

  @PostMapping("/photos")
  public Photo create (@RequestPart("data") MultipartFile file) throws IOException {
    Photo photo = new Photo();
    photo.setId(UUID.randomUUID().toString());
    photo.setFileName(file.getOriginalFilename());
    photo.setData(file.getBytes());

    db.put(photo.getId(), photo);
    return photo;
  }

//  public Photo create (@RequestBody @Valid Photo photo){
//    photo.setId(UUID.randomUUID().toString());
//    db.put(photo.getId(), photo);
//    return photo;
//  }
}
