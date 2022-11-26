package id.dbs.vmtools.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.dbs.vmtools.models.entities.SurveyTemplate;
import id.dbs.vmtools.models.repos.SurveyTemplateRepo;

@Service
@Transactional
public class SurveyTemplateService {
  @Autowired
  private SurveyTemplateRepo surveyRepo;

  // insert into table
  public SurveyTemplate insert(SurveyTemplate survey){
    return surveyRepo.save(survey);
  }

  // select data by id
  public SurveyTemplate getDataById(Integer id){
    Optional<SurveyTemplate> survey = surveyRepo.findById(id);
    if(!survey.isPresent()){
      return null;
    }
    return survey.get();
  }

  // select all data
  public Iterable<SurveyTemplate> getAll(){
    return surveyRepo.findAll();
  }

  // delete data by id
  public void delete(Integer id){
    surveyRepo.deleteById(id);
  }
}
