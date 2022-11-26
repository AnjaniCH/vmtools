package id.dbs.vmtools.models.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SurveyTemplates")
public class SurveyTemplate implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "surveyTemplateId", length = 11, nullable = false)
  private Integer surveyTemplateId;

  @Column(name = "content", nullable = true)
  private String content;

  public SurveyTemplate() {
  }

  public SurveyTemplate(Integer surveyTemplateId, String content) {
    this.surveyTemplateId = surveyTemplateId;
    this.content = content;
  }

  public Integer getSurveyTemplateId() {
    return surveyTemplateId;
  }

  public void setSurveyTemplateId(Integer surveyTemplateId) {
    this.surveyTemplateId = surveyTemplateId;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }
}
