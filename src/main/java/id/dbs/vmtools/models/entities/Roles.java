package id.dbs.vmtools.models.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Roles")
public class Roles implements Serializable {
  @Id
  @Column(name="roleId", length = 15)
  private String roleId;

  @Column(name="name", length = 100)
  private String name;

  @OneToMany(targetEntity = User.class, mappedBy = "roleId", orphanRemoval = false, fetch = FetchType.LAZY)
  @JsonIgnore
	private List<User> users;

  public Roles() {
  }

  public Roles(String roleId, String name, List<User> users) {
    this.roleId = roleId;
    this.name = name;
    this.users = users;
  }

  public String getRoleId() {
    return roleId;
  }

  public void setRoleId(String roleId) {
    this.roleId = roleId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<User> getUsers() {
    return users;
  }

  public void setUsers(List<User> users) {
    this.users = users;
  }
}
