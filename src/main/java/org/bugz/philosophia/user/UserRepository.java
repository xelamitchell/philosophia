package org.bugz.philosophia.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author bugz
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    /**
     * Retrieves a user based on its username.
     * 
     * @param username must not be null
     * @return 
     */
    @Query("FROM User u WHERE u.username = :username AND u.status NOT LIKE 'DELETED'")
    User findOne(@Param("username") String username);
    
    /**
     * Returns whether a user with the given username exists.
     * 
     * @param username must not be null
     * @return 
     */
    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN 'true' ELSE 'false' END FROM User u WHERE u.username = :username")
    Boolean exists(@Param("username") String username);
    
}
