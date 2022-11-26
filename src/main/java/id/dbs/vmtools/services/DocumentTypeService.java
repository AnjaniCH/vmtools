package id.dbs.vmtools.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.dbs.vmtools.models.entities.DocumentType;
import id.dbs.vmtools.models.repos.DocumentTypeRepo;

@Service
@Transactional
public class DocumentTypeService {
  @Autowired
  private DocumentTypeRepo documentTypeRepo;

  // insert into table
  public DocumentType insert(DocumentType doctype){
    return documentTypeRepo.save(doctype);
  }

  // select data by id
  public DocumentType getDataById(Integer id){
    Optional<DocumentType> doctype = documentTypeRepo.findById(id);
    if(!doctype.isPresent()){
      return null;
    }
    return doctype.get();
  }

  // select all data
  public Iterable<DocumentType> getAll(){
    return documentTypeRepo.findAll();
  }

  // delete data by id
  public void delete(Integer id){
    documentTypeRepo.deleteById(id);
  }
}
