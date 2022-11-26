package id.dbs.vmtools.models.repos;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import id.dbs.vmtools.models.entities.User;

@Repository
public interface UserRepo extends JpaRepository<User, String> {
  Optional<User> findByLoginName(String loginName);

  @Modifying(clearAutomatically=true, flushAutomatically = true)
  @Transactional
  @Query("UPDATE User u SET u.onlineStatus = 1, u.lastLoginTime = :lastLoginTime, u.token = :token, u.tokenExpiryTime = :tokenExpiryTime WHERE u.loginName = :loginName")
  int setLoginState(@Param("lastLoginTime") Timestamp lastLoginTime, @Param("token") String token, @Param("tokenExpiryTime") Date tokenExpiryTime, @Param("loginName") String loginName);

  @Modifying(clearAutomatically=true, flushAutomatically = true)
  @Transactional
  @Query("UPDATE User u SET u.onlineStatus = 0, u.token = null, u.tokenExpiryTime = null WHERE u.loginName = :loginName")
  int setLogoutState(@Param("loginName") String loginName);
}