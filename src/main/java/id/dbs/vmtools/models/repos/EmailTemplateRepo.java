package id.dbs.vmtools.models.repos;

import org.springframework.data.repository.CrudRepository;

import id.dbs.vmtools.models.entities.EmailTemplate;

public interface EmailTemplateRepo extends CrudRepository<EmailTemplate, Integer> {
  
}
