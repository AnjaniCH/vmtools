package id.dbs.vmtools.controllers;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import id.dbs.vmtools.models.entities.EmailTemplate;
import id.dbs.vmtools.models.entities.Vendor;
import id.dbs.vmtools.models.entities.VendorDocument;
import id.dbs.vmtools.response.ResponseHandler;
import id.dbs.vmtools.services.DocumentTypeService;
import id.dbs.vmtools.services.EmailTemplateService;
import id.dbs.vmtools.services.VendorDocumentService;
import id.dbs.vmtools.services.VendorService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/vendor/document")
public class VendorDocumentController {
  @Autowired
  private VendorDocumentService documentService;

  @Autowired
  private VendorService vendorService;

  @Autowired
  private EmailTemplateService templateService;

  @Autowired
  private DocumentTypeService doctypeService;

  @Autowired
  private RestTemplate restTemplate;

  @GetMapping("/{vendorId}")
  public ResponseEntity<Object> getById(@PathVariable("vendorId") String vendorId) {
    try {
      List<Map<String, Object>> result = documentService.getDataByVendorId(vendorId);
      return ResponseHandler.generateResponse(true, HttpStatus.OK, null, result);
    } catch (Exception e) {
      System.out.println(e.getCause());
      return ResponseHandler.generateResponse(false, HttpStatus.NOT_FOUND, e.getMessage(), null);
    }
  }

  @GetMapping("/{vendorId}/{documentTypeId}")
  public ResponseEntity<Object> getHistoryDocument(@PathVariable("vendorId") String vendorId,
      @PathVariable("documentTypeId") Integer documentTypeId) {
    try {
      List<Map<String, Object>> result = documentService.getHistory(vendorId, documentTypeId);
      return ResponseHandler.generateResponse(true, HttpStatus.OK, null, result);
    } catch (Exception e) {
      System.out.println(e.getCause());
      return ResponseHandler.generateResponse(false, HttpStatus.NOT_FOUND, e.getMessage(), null);
    }
  }
  // Rest Template
  // Web client

  @PostMapping("/request")
  public ResponseEntity<Object> create(@RequestBody List<VendorDocument> data) {
    String vendorId = data.get(0).getVendorId();
    Vendor vendor = vendorService.getDataById(vendorId);
    EmailTemplate template = templateService.getDataById(1);

    String recipient[] = new String[] { vendor.getEmail() };
    String emailSubject = template.getSubject();
    emailSubject = emailSubject.replace("{requestId}", vendorId);
    String emailBodyString = template.getBody();
    if (emailBodyString.contains("{coupaVendorId}")) {
      emailBodyString = emailBodyString.replace("{coupaVendorId}", vendor.getCoupaVendorId());
    }
    if (emailBodyString.contains("{psapVendorId}")) {
      emailBodyString = emailBodyString.replace("{psapVendorId}", vendor.getPsapVendorId());
    }
    if (emailBodyString.contains("{legalEntity}")) {
      emailBodyString = emailBodyString.replace("{legalEntity}", vendor.getLegalEntity());
    }
    if (emailBodyString.contains("{vendorName}")) {
      emailBodyString = emailBodyString.replace("{vendorName}", vendor.getVendorName());
    }
    if (emailBodyString.contains("{location}")) {
      emailBodyString = emailBodyString.replace("{location}", vendor.getLocation());
    }
    if (emailBodyString.contains("{address}")) {
      emailBodyString = emailBodyString.replace("{address}", vendor.getAddress());
    }
    if (emailBodyString.contains("{city}")) {
      emailBodyString = emailBodyString.replace("{city}", vendor.getCity());
    }
    if (emailBodyString.contains("{email}")) {
      emailBodyString = emailBodyString.replace("{email}", vendor.getEmail());
    }
    if (emailBodyString.contains("{listDocument}")) {
      String listDocHtml = "<ul>";
      for (VendorDocument item : data) {
        String docName = doctypeService.getDataById(item.getDocumentTypeId()).getName();
        listDocHtml = listDocHtml + "<li>" + docName + "</li>";
        item.setRequestTime(new Timestamp(System.currentTimeMillis()));
        item.setStatus("Pending");
      }
      listDocHtml = listDocHtml + "</ul>";
      emailBodyString = emailBodyString.replace("{listDocument}", listDocHtml);
    }
    emailBodyString = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\"><html xmlns=\"http://www.w3.org/1999/xhtml\"> <head> <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"/> <title>VMTools DBS - Document Request</title> <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"/></head><body>"
        + emailBodyString + "</body></html>";

    Date date = new Date();
    long time = date.getTime();
    for (VendorDocument item : data) {
      item.setRequestTime(new Timestamp(time));
      item.setStatus("Pending");
    }

    // create a map for post parameters
    Map<String, Object> map = new HashMap<>();
    map.put("recipients", recipient);
    map.put("cc", new String[] {});
    map.put("subject", emailSubject);
    map.put("body", emailBodyString);
    map.put("fileName", new String[] {});

    // create headers
    HttpHeaders headers = new HttpHeaders();
    // set `content-type` header
    headers.setContentType(MediaType.APPLICATION_JSON);
    // set `accept` header
    headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

    // build the request
    HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map, headers);

    // send POST request
    ResponseEntity<String> response = restTemplate.postForEntity("http://156.67.219.250:7070/email-sender/email-sender", entity, String.class);

    // check response status code
    if (response.getStatusCode() == HttpStatus.OK) {
      try {
        List<VendorDocument> result = documentService.insertBatch(data);
        return ResponseHandler.generateResponse(true, HttpStatus.OK, null, result);
      } catch (Exception e) {
        return ResponseHandler.generateResponse(false, HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null);
      }
    } else {
      return ResponseHandler.generateResponse(false, HttpStatus.INTERNAL_SERVER_ERROR, "Send email failed", null);
    }
  }
}
