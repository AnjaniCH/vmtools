package id.dbs.vmtools.models.repos;

import org.springframework.data.repository.CrudRepository;

import id.dbs.vmtools.models.entities.SurveyTemplate;

public interface SurveyTemplateRepo extends CrudRepository<SurveyTemplate, Integer> {
  
}
