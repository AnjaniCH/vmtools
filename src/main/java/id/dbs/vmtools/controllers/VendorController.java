package id.dbs.vmtools.controllers;

import java.util.Date;
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

import id.dbs.vmtools.models.entities.Vendor;
import id.dbs.vmtools.response.ResponseHandler;
import id.dbs.vmtools.services.VendorService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/vendor")
public class VendorController {
  @Autowired
  private VendorService vendorService;

  @Transient
  private UUID vendorId = UUID.randomUUID();

  @PostMapping
  public ResponseEntity<Object> create(@RequestBody Vendor vendor) {
    try {
      Date dateNow = new Date();  
      vendor.setVendorId(vendorId.toString());
      vendor.setStatus("Expired");
      vendor.setRegistrationStatus("Inactive");
      vendor.setRegistrationDate(dateNow);
      Vendor result = vendorService.insert(vendor);
      return ResponseHandler.generateResponse(true, HttpStatus.OK, null, result);
    } catch (Exception e) {
      return ResponseHandler.generateResponse(false, HttpStatus.MULTI_STATUS, e.getMessage(), null);
    }
  }

  @GetMapping
  public ResponseEntity<Object> getAll() {
    try {
      Iterable<Vendor> result = vendorService.getAll();
      return ResponseHandler.generateResponse(true, HttpStatus.OK, null, result);
    } catch (Exception e) {
      return ResponseHandler.generateResponse(false, HttpStatus.MULTI_STATUS, e.getMessage(), null);
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<Object> getById(@PathVariable("id") String id) {
    try {
      Vendor result = vendorService.getDataById(id);
      return ResponseHandler.generateResponse(true, HttpStatus.OK, null, result);
    } catch (Exception e) {
      System.out.println(e.getCause());
      return ResponseHandler.generateResponse(false, HttpStatus.NOT_FOUND, e.getMessage(), null);
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<Object> update(@PathVariable("id") String id, @RequestBody Vendor vendor) {
    try {
      Vendor prevResult = vendorService.getDataById(id);
      prevResult.setCoupaVendorId(vendor.getCoupaVendorId());
      prevResult.setPsapVendorId(vendor.getPsapVendorId());
      prevResult.setLegalEntity(vendor.getLegalEntity());
      prevResult.setVendorName(vendor.getVendorName());
      prevResult.setLocation(vendor.getLocation());
      prevResult.setAddress(vendor.getAddress());
      prevResult.setCity(vendor.getCity());
      prevResult.setEmail(vendor.getEmail());
      Vendor result = vendorService.insert(prevResult);
      return ResponseHandler.generateResponse(true, HttpStatus.OK, null, result);
    } catch (Exception e) {
      return ResponseHandler.generateResponse(false, HttpStatus.MULTI_STATUS, e.getMessage(), null);
    }
  }
}
