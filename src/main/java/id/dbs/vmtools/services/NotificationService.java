package id.dbs.vmtools.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.dbs.vmtools.models.entities.Notification;
import id.dbs.vmtools.models.repos.NotificationRepo;

@Service
@Transactional
public class NotificationService {
  @Autowired
  private NotificationRepo notification;

  // insert into table
  public Notification insert(Notification notif){
    return notification.save(notif);
  }

  // select data by id
  public Notification getDataById(Integer id){
    Optional<Notification> notif = notification.findById(id);
    if(!notif.isPresent()){
      return null;
    }
    return notif.get();
  }

  // select all data
  public Iterable<Notification> getAll(){
    return notification.findAll();
  }

  // delete data by id
  public void delete(Integer id){
    notification.deleteById(id);
  }
}
