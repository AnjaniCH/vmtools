package id.dbs.vmtools.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.dbs.vmtools.models.entities.Roles;
import id.dbs.vmtools.models.repos.RolesRepo;

@Service
@Transactional
public class RolesService {
  @Autowired
  private RolesRepo rolesRepo;

  public Roles getDataById(String id){
    return rolesRepo.findById(id).get();
  }

  public Iterable<Roles> getAll(){
    return rolesRepo.findAll();
  }
}
