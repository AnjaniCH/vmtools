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

import id.dbs.vmtools.models.entities.SurveyTemplate;
import id.dbs.vmtools.response.ResponseHandler;
import id.dbs.vmtools.services.SurveyTemplateService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/surveytemplate")
public class SurveyTemplateController {
  @Autowired
  private SurveyTemplateService surveyService;

  @PostMapping
  public ResponseEntity<Object> create(@RequestBody SurveyTemplate survey) {
    try {
      SurveyTemplate result = surveyService.insert(survey);
      return ResponseHandler.generateResponse(true, HttpStatus.OK, null, result);
    } catch (Exception e) {
      return ResponseHandler.generateResponse(false, HttpStatus.MULTI_STATUS, e.getMessage(), null);
    }
  }

  @GetMapping
  public ResponseEntity<Object> getAll() {
    try {
      Iterable<SurveyTemplate> result = surveyService.getAll();
      return ResponseHandler.generateResponse(true, HttpStatus.OK, null, result);
    } catch (Exception e) {
      return ResponseHandler.generateResponse(false, HttpStatus.MULTI_STATUS, e.getMessage(), null);
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<Object> getById(@PathVariable("id") Integer id) {
    try {
      SurveyTemplate result = surveyService.getDataById(id);
      return ResponseHandler.generateResponse(true, HttpStatus.OK, null, result);
    } catch (Exception e) {
      System.out.println(e.getCause());
      return ResponseHandler.generateResponse(false, HttpStatus.NOT_FOUND, e.getMessage(), null);
    }
  }

  @PutMapping
  public ResponseEntity<Object> update(@RequestBody SurveyTemplate survey) {
    try {
      SurveyTemplate result = surveyService.insert(survey);
      return ResponseHandler.generateResponse(true, HttpStatus.OK, null, result);
    } catch (Exception e) {
      return ResponseHandler.generateResponse(false, HttpStatus.MULTI_STATUS, e.getMessage(), null);
    }
  }

}