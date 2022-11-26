package id.dbs.vmtools.services;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.dbs.vmtools.models.entities.User;
import id.dbs.vmtools.models.repos.UserRepo;

@Service
@Transactional
public class UserService {
  @Autowired
  private UserRepo userRepo;

  public UserService(UserRepo userRepo) {
    this.userRepo = userRepo;
  }

  // insert into table
  public User insert(User user){
    return userRepo.save(user);
  }

  // select data by id
  public User getDataById(String id){
    Optional<User> user = userRepo.findById(id);
    if(!user.isPresent()){
      return null;
    }
    return user.get();
  }

  // select all data
  public Iterable<User> getAll(){
    return userRepo.findAll();
  }

  // delete data by id
  public void delete(String id){
    userRepo.deleteById(id);
  }

  // Auth Module
  public User getDataByLoginName(String name){
    Optional<User> user = userRepo.findByLoginName(name);
    if(!user.isPresent()){
      return null;
    }
    return user.get();
  }

  public User setLogin(Timestamp lastLoginTime, String token, Date tokenExpiryTime, String loginName){
    int result = userRepo.setLoginState(lastLoginTime, token, tokenExpiryTime, loginName);
    if(result > 0){
      Optional<User> user = userRepo.findByLoginName(loginName);
      return user.get();
    } else {
      return null;
    }
  }

  public void setLogout(String loginName){
    userRepo.setLogoutState(loginName);
  }
}