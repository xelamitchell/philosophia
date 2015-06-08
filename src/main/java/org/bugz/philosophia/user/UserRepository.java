package org.bugz.philosophia.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author bugz
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
}
