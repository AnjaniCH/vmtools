package id.dbs.vmtools.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.dbs.vmtools.models.entities.EmailTemplate;
import id.dbs.vmtools.models.repos.EmailTemplateRepo;

@Service
@Transactional
public class EmailTemplateService {
  @Autowired
  private EmailTemplateRepo EmailTemplateRepo;

  // insert / update table
  public EmailTemplate insert(EmailTemplate template){
    return EmailTemplateRepo.save(template);
  }

  // select data by id
  public EmailTemplate getDataById(Integer id){
    Optional<EmailTemplate> template = EmailTemplateRepo.findById(id);
    if(!template.isPresent()){
      return null;
    }
    return template.get();
  }

  // select all data
  public Iterable<EmailTemplate> getAll(){
    return EmailTemplateRepo.findAll();
  }
}
