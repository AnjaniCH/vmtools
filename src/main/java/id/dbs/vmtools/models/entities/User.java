package id.dbs.vmtools.models.entities;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
// import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "Users")
public class User implements Serializable {
  @Id
  @Column(name = "userId", length = 36, nullable = false)
  private String userId;

  @Column(unique = true, name = "loginName", length = 15, nullable = false)
  private String loginName;

  @Column(name = "name", length = 100, nullable = false)
  private String name;

  @Column(name = "roleId", length = 15, nullable = false)
  private String roleId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "roleId", insertable = false, updatable = false)
  @Fetch(FetchMode.JOIN)
  private Roles roles;

  @Column(name = "lockStatus", length = 4, nullable = true)
  private Integer lockStatus;

  @Column(name = "onlineStatus", length = 4, nullable = true)
  private Integer onlineStatus;

  @Column(name = "registrationTime", nullable = false)
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Timestamp registrationTime;

  @Column(name = "lastLoginTime", nullable = true)
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Timestamp lastLoginTime;

  @Column(name = "token", length = 100, nullable = true)
  private String token;

  @Column(name = "tokenExpiryTime", nullable = true)
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Timestamp tokenExpiryTime;

  public User() {
  }

  public User(String userId, String loginName, String name, String roleId, Roles roles, Integer lockStatus,
      Integer onlineStatus, Timestamp registrationTime, Timestamp lastLoginTime, String token,
      Timestamp tokenExpiryTime) {
    this.userId = userId;
    this.loginName = loginName;
    this.name = name;
    this.roleId = roleId;
    this.roles = roles;
    this.lockStatus = lockStatus;
    this.onlineStatus = onlineStatus;
    this.registrationTime = registrationTime;
    this.lastLoginTime = lastLoginTime;
    this.token = token;
    this.tokenExpiryTime = tokenExpiryTime;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getLoginName() {
    return loginName;
  }

  public void setLoginName(String loginName) {
    this.loginName = loginName;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getRoleId() {
    return roleId;
  }

  public void setRoleId(String roleId) {
    this.roleId = roleId;
  }

  public Roles getRoles() {
    return roles;
  }

  public void setRoles(Roles roles) {
    this.roles = roles;
  }

  public Integer getLockStatus() {
    return lockStatus;
  }

  public void setLockStatus(Integer lockStatus) {
    this.lockStatus = lockStatus;
  }

  public Integer getOnlineStatus() {
    return onlineStatus;
  }

  public Integer setOnlineStatus(Integer onlineStatus) {
    this.onlineStatus = onlineStatus;
    return onlineStatus;
  }

  public Timestamp getRegistrationTime() {
    return registrationTime;
  }

  public void setRegistrationTime(Timestamp registrationTime) {
    this.registrationTime = registrationTime;
  }

  public Timestamp getLastLoginTime() {
    return lastLoginTime;
  }

  public void setLastLoginTime(Timestamp lastLoginTime) {
    this.lastLoginTime = lastLoginTime;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public Timestamp getTokenExpiryTime() {
    return tokenExpiryTime;
  }

  public void setTokenExpiryTime(Timestamp tokenExpiryTime) {
    this.tokenExpiryTime = tokenExpiryTime;
  }
}