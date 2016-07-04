package ua.opinta.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.opinta.core.User;

import javax.persistence.Table;
import java.util.Optional;

/**
 * Created by Administrator on 6/24/2016.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
