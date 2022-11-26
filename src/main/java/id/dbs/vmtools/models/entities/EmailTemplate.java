package id.dbs.vmtools.models.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "EmailTemplates")
public class EmailTemplate implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "emailTemplateId", length = 11, nullable = false)
  private Integer emailTemplateId;

  @Column(name = "subject", length = 100, nullable = true)
  private String subject;

  @Lob
  @Column(name="body", nullable = true)
  private String body;

  public EmailTemplate() {
  }

  public EmailTemplate(Integer emailTemplateId, String subject, String body) {
    this.emailTemplateId = emailTemplateId;
    this.subject = subject;
    this.body = body;
  }

  public Integer getEmailTemplateId() {
    return emailTemplateId;
  }

  public void setEmailTemplateId(Integer emailTemplateId) {
    this.emailTemplateId = emailTemplateId;
  }

  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }
}
