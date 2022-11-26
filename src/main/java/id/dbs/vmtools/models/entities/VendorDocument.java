package id.dbs.vmtools.models.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "VendorDocuments")
public class VendorDocument implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "documentId", length = 11, nullable = false)
  private Integer documentId;

  @Column(name = "vendorId", length = 36, nullable = false)
  private String vendorId;

  @Column(name = "documentTypeId", length = 11, nullable = false)
  private Integer documentTypeId;

  @Column(name = "fileName", length = 100, nullable = true)
  private String fileName;

  @Column(name = "expiryDate", nullable = true)
  @JsonFormat(pattern = "yyyy-MM-dd")
  private Date expiryDate;

  @Column(name = "requestTime", nullable = false)
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Timestamp requestTime;

  @Column(name = "receivedTime", nullable = true)
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Timestamp receivedTime;

  @Column(name = "verificationTime", nullable = true)
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Timestamp verificationTime;

  @Column(name = "approvalTime", nullable = true)
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Timestamp approvalTime;

  @Column(name = "verifier", length = 36, nullable = true)
  private String verifier;

  @Column(name = "approver", length = 36, nullable = true)
  private Integer approver;

  @Column(name = "status")
  private String status;

  public VendorDocument() {
  }

  public VendorDocument(Integer documentId, String vendorId, Integer documentTypeId, String fileName, Date expiryDate,
      Timestamp requestTime, Timestamp receivedTime, Timestamp verificationTime, Timestamp approvalTime, String verifier, Integer approver,
      String status) {
    this.documentId = documentId;
    this.vendorId = vendorId;
    this.documentTypeId = documentTypeId;
    this.fileName = fileName;
    this.expiryDate = expiryDate;
    this.requestTime = requestTime;
    this.receivedTime = receivedTime;
    this.verificationTime = verificationTime;
    this.approvalTime = approvalTime;
    this.verifier = verifier;
    this.approver = approver;
    this.status = status;
  }

  public Integer getDocumentId() {
    return documentId;
  }

  public void setDocumentId(Integer documentId) {
    this.documentId = documentId;
  }

  public String getVendorId() {
    return vendorId;
  }

  public void setVendorId(String vendorId) {
    this.vendorId = vendorId;
  }

  public Integer getDocumentTypeId() {
    return documentTypeId;
  }

  public void setDocumentTypeId(Integer documentTypeId) {
    this.documentTypeId = documentTypeId;
  }

  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public Date getExpiryDate() {
    return expiryDate;
  }

  public void setExpiryDate(Date expiryDate) {
    this.expiryDate = expiryDate;
  }

  public Timestamp getRequestTime() {
    return requestTime;
  }

  public void setRequestTime(Timestamp requestTime) {
    this.requestTime = requestTime;
  }

  public Timestamp getReceivedTime() {
    return receivedTime;
  }

  public void setReceivedTime(Timestamp receivedTime) {
    this.receivedTime = receivedTime;
  }

  public Timestamp getVerificationTime() {
    return verificationTime;
  }

  public void setVerificationTime(Timestamp verificationTime) {
    this.verificationTime = verificationTime;
  }

  public Timestamp getApprovalTime() {
    return approvalTime;
  }

  public void setApprovalTime(Timestamp approvalTime) {
    this.approvalTime = approvalTime;
  }

  public String getVerifier() {
    return verifier;
  }

  public void setVerifier(String verifier) {
    this.verifier = verifier;
  }

  public Integer getApprover() {
    return approver;
  }

  public void setApprover(Integer approver) {
    this.approver = approver;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }
}