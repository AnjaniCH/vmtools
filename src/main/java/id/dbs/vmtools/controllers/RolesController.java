package id.dbs.vmtools.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.dbs.vmtools.models.entities.Roles;
import id.dbs.vmtools.response.ResponseHandler;
import id.dbs.vmtools.services.RolesService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/roles")
public class RolesController {
  @Autowired
  private RolesService rolesService;

  @GetMapping
  public ResponseEntity<Object> getAll() {
    try {
      Iterable<Roles> result = rolesService.getAll();
      return ResponseHandler.generateResponse(true, HttpStatus.OK, "Successfully retrieved data!", result);
    } catch (Exception e) {
      return ResponseHandler.generateResponse(false, HttpStatus.MULTI_STATUS, e.getMessage(), null);
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<Object> getById(@PathVariable("id") String id) {
    try {
      Roles result = rolesService.getDataById(id);
      return ResponseHandler.generateResponse(true, HttpStatus.OK, "Successfully retrieved data!", result);
    } catch (Exception e) {
      System.out.println(e.getCause());
      return ResponseHandler.generateResponse(false, HttpStatus.NOT_FOUND, e.getMessage(), null);
    }
  }
}