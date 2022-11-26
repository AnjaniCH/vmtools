package id.dbs.vmtools.controllers;

import java.util.UUID;

import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.dbs.vmtools.models.entities.User;
import id.dbs.vmtools.response.ResponseHandler;
import id.dbs.vmtools.services.UserService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/user")
public class UserController {
  @Autowired
  private UserService userService;

  @Transient
  private UUID userId = UUID.randomUUID();

  @PostMapping
  public ResponseEntity<Object> create(@RequestBody User user) {
    try {
      user.setUserId(userId.toString());
      User result = userService.insert(user);
      return ResponseHandler.generateResponse(true, HttpStatus.OK, null, result);
    } catch (Exception e) {
      return ResponseHandler.generateResponse(false, HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null);
    }
  }

  @GetMapping
  public ResponseEntity<Object> getAll() {
    try {
      Iterable<User> result = userService.getAll();
      return ResponseHandler.generateResponse(true, HttpStatus.OK, null, result);
    } catch (Exception e) {
      return ResponseHandler.generateResponse(false, HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null);
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<Object> getById(@PathVariable("id") String id) {
    try {
      User result = userService.getDataById(id);
      return ResponseHandler.generateResponse(true, HttpStatus.OK, null, result);
    } catch (Exception e) {
      System.out.println(e.getCause());
      return ResponseHandler.generateResponse(false, HttpStatus.NOT_FOUND, e.getMessage(), null);
    }
  }

  @PutMapping
  public ResponseEntity<Object> update(@RequestBody User user) {
    try {
      User result = userService.insert(user);
      return ResponseHandler.generateResponse(true, HttpStatus.OK, null, result);
    } catch (Exception e) {
      return ResponseHandler.generateResponse(false, HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null);
    }
  }

}