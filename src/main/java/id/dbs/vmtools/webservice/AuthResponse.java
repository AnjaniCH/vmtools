package id.dbs.vmtools.webservice;

import java.io.Serializable;

public class AuthResponse implements Serializable {
  private final String userId;
  private final String name;
  private final String roleId;
  private final String roleName;
  private final String jwttoken;

  public String getUserId() {
    return this.userId;
  }

  public String getName() {
    return this.name;
  }

  public String getRoleId() {
    return this.roleId;
  }

  public String getRoleName() {
    return this.roleName;
  }

  public String getToken() {
    return this.jwttoken;
  }

  public AuthResponse(String userId, String name, String roleId, String roleName, String jwttoken) {
    this.userId = userId;
    this.name = name;
    this.roleId = roleId;
    this.roleName = roleName;
    this.jwttoken = jwttoken;
  }
}