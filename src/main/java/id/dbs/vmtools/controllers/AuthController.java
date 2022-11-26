package id.dbs.vmtools.controllers;

import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// import id.dbs.vmtools.models.entities.Roles;
import id.dbs.vmtools.models.entities.User;
import id.dbs.vmtools.response.ResponseHandler;
import id.dbs.vmtools.security.JwtTokenUtil;
import id.dbs.vmtools.services.UserService;
import id.dbs.vmtools.webservice.AuthRequest;
import id.dbs.vmtools.webservice.AuthResponse;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/auth")
public class AuthController {
  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private JwtTokenUtil jwtTokenUtil;

  @Autowired
  private UserService userService;

  @PostMapping("login")
  public ResponseEntity<Object> createAuthenticationToken(@RequestBody AuthRequest authenticationRequest,
      HttpSession session) throws NullPointerException, Exception {
    User UserEntity = userService.getDataByLoginName(authenticationRequest.getUsername());

    String loginName = UserEntity.getLoginName();

    if (loginName != null && !loginName.isEmpty()) {
      authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

      session.setAttribute("loggedInUser", UserEntity);
      String userId = UserEntity.getUserId();
      String name = UserEntity.getName();
      String roleId = UserEntity.getRoleId();
      // Roles roles = UserEntity.getRoles();
      String roleName = "admin";
      Timestamp lastLoginTime = new Timestamp(System.currentTimeMillis());
      String token = jwtTokenUtil.generateToken(UserEntity);
      Date tokenExpire = jwtTokenUtil.getExpirationDateFromToken(token);

      userService.setLogin(lastLoginTime, token, tokenExpire, loginName);

      return ResponseHandler.generateResponse(true, HttpStatus.OK, null, new AuthResponse(userId, name, roleId, roleName, token));

    } else {
      // return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username or password is wrong");
      return ResponseHandler.generateResponse(false, HttpStatus.BAD_REQUEST, "Username or password is wrong", null);
    }
  }

  @PostMapping("logout")
  public ResponseEntity<?> logout(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
    if (session != null) {
      request.getSession(true).removeAttribute("loggedInUser");
      request.getSession(true).invalidate();

      User userDetails = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      String loginName = userDetails.getLoginName();
      userService.setLogout(loginName);

      return ResponseEntity.status(HttpStatus.OK).body("Logout Succesfully");
    } else {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Logout Failed");
    }
  }

  private void authenticate(String username, String password) throws Exception {
    try {
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    } catch (DisabledException e) {
      throw new Exception("USER_DISABLED", e);
    } catch (BadCredentialsException e) {
      throw new Exception("INVALID_CREDENTIALS", e);
    }
  }
}
