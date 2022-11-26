package id.dbs.vmtools.controllers;

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

import id.dbs.vmtools.models.entities.DocumentType;
import id.dbs.vmtools.response.ResponseHandler;
import id.dbs.vmtools.services.DocumentTypeService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/documenttype")
public class DocumentTypeController {
  @Autowired
  private DocumentTypeService doctypeService;

  @PostMapping
  public ResponseEntity<Object> create(@RequestBody DocumentType doctype) {
    try {
      DocumentType result = doctypeService.insert(doctype);
      return ResponseHandler.generateResponse(true, HttpStatus.OK, null, result);
    } catch (Exception e) {
      return ResponseHandler.generateResponse(false, HttpStatus.MULTI_STATUS, e.getMessage(), null);
    }
  }

  @GetMapping
  public ResponseEntity<Object> getAll() {
    try {
      Iterable<DocumentType> result = doctypeService.getAll();
      return ResponseHandler.generateResponse(true, HttpStatus.OK, null, result);
    } catch (Exception e) {
      return ResponseHandler.generateResponse(false, HttpStatus.MULTI_STATUS, e.getMessage(), null);
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<Object> getById(@PathVariable("id") Integer id) {
    try {
      DocumentType result = doctypeService.getDataById(id);
      return ResponseHandler.generateResponse(true, HttpStatus.OK, null, result);
    } catch (Exception e) {
      System.out.println(e.getCause());
      return ResponseHandler.generateResponse(false, HttpStatus.NOT_FOUND, e.getMessage(), null);
    }
  }

  @PutMapping
  public ResponseEntity<Object> update(@RequestBody DocumentType doctype) {
    try {
      DocumentType result = doctypeService.insert(doctype);
      return ResponseHandler.generateResponse(true, HttpStatus.OK, null, result);
    } catch (Exception e) {
      return ResponseHandler.generateResponse(false, HttpStatus.MULTI_STATUS, e.getMessage(), null);
    }
  }

}