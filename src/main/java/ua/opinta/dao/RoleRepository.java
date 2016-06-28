package ua.opinta.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.opinta.core.Role;

import javax.persistence.Table;
import java.util.Optional;

/**
 * Created by Administrator on 6/25/2016.
 */
@Repository
@Table(name="role")
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
