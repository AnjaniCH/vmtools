package id.dbs.vmtools.models.repos;

import org.springframework.data.repository.CrudRepository;

import id.dbs.vmtools.models.entities.DocumentType;

public interface DocumentTypeRepo extends CrudRepository<DocumentType, Integer> {
  
}
