package com.test.aziz.photos.clone;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
}
