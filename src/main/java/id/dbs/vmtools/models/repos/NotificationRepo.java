package id.dbs.vmtools.models.repos;

import org.springframework.data.repository.CrudRepository;

import id.dbs.vmtools.models.entities.Notification;

public interface NotificationRepo extends CrudRepository<Notification, Integer> {
  
}
