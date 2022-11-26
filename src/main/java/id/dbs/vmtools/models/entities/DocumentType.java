package id.dbs.vmtools.models.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "DocumentTypes")
public class DocumentType implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "documentTypeId", length = 11, nullable = false)
  private Integer documentTypeId;

  @Column(name = "name", length = 100, nullable = true)
  private String name;

  @Column(name = "fileNameFormat", length = 50, nullable = true)
  private String fileNameFormat;

  @Column(name = "mandatoryStatus")
  private String mandatoryStatus;

  @Column(name = "keywords", length = 100, nullable = true)
  private String keywords;

  public DocumentType() {
  }

  public DocumentType(Integer documentTypeId, String name, String fileNameFormat, String mandatoryStatus,
      String keywords, Integer documentId) {
    this.documentTypeId = documentTypeId;
    this.name = name;
    this.fileNameFormat = fileNameFormat;
    this.mandatoryStatus = mandatoryStatus;
    this.keywords = keywords;
  }

  public Integer getDocumentTypeId() {
    return documentTypeId;
  }

  public void setDocumentTypeId(Integer documentTypeId) {
    this.documentTypeId = documentTypeId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getFileNameFormat() {
    return fileNameFormat;
  }

  public void setFileNameFormat(String fileNameFormat) {
    this.fileNameFormat = fileNameFormat;
  }

  public String getMandatoryStatus() {
    return mandatoryStatus;
  }

  public void setMandatoryStatus(String mandatoryStatus) {
    this.mandatoryStatus = mandatoryStatus;
  }

  public String getKeywords() {
    return keywords;
  }

  public void setKeywords(String keywords) {
    this.keywords = keywords;
  }
}
