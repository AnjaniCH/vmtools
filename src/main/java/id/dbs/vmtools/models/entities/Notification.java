package id.dbs.vmtools.models.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Notifications")
public class Notification implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "notificationId", length = 11, nullable = false)
  private Integer notificationId;

  @Column(name = "reminderPeriod", length = 11, nullable = false)
  private Integer reminderPeriod;

  @Column(name = "ccEmail", length = 300, nullable = true)
  private String ccEmail;

  public Notification() {
  }

  public Notification(Integer notificationId, Integer reminderPeriod, String ccEmail) {
    this.notificationId = notificationId;
    this.reminderPeriod = reminderPeriod;
    this.ccEmail = ccEmail;
  }

  public Integer getNotificationId() {
    return notificationId;
  }

  public void setNotificationId(Integer notificationId) {
    this.notificationId = notificationId;
  }

  public Integer getReminderPeriod() {
    return reminderPeriod;
  }

  public void setReminderPeriod(Integer reminderPeriod) {
    this.reminderPeriod = reminderPeriod;
  }

  public String getCcEmail() {
    return ccEmail;
  }

  public void setCcEmail(String ccEmail) {
    this.ccEmail = ccEmail;
  }
}
