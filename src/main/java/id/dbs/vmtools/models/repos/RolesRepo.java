package id.dbs.vmtools.models.repos;

import org.springframework.data.repository.CrudRepository;

import id.dbs.vmtools.models.entities.Roles;

public interface RolesRepo extends CrudRepository<Roles, String>{
  
}