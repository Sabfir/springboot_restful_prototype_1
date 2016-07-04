package ua.opinta.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseStatus;
import ua.opinta.core.Role;
import ua.opinta.core.User;
import ua.opinta.dao.RoleRepository;
import ua.opinta.dao.UserRepository;

import javax.persistence.NoResultException;
import java.util.Collection;

/**
 * The RoleServiceBean encapsulates all business behaviors operating on the
 * Role entity model object.
 *
 * Created by Administrator on 7/2/2016.
 */
@Service
@Transactional(
        propagation = Propagation.SUPPORTS,
        readOnly = true)
public class UserServiceBean implements UserService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * The <code>CounterService</code> captures metrics for Spring Actuator.
     */
    @Autowired
    private CounterService counterService;

    /**
     * The Spring Data repository for Role entities.
     */
    @Autowired
    private UserRepository userRepository;

    @Override
    public Collection<User> findAll() {
        logger.info("> findAll");

        counterService.increment("method.invoked.userServiceBean.findAll");

        Collection<User> users = userRepository.findAll();

        logger.info("< findAll");
        return users;
    }

    @Override
    @Cacheable(
            value = "users",
            key = "#id")
    public User findOne(Long id) {
        logger.info("> findOne id:{}", id);

        counterService.increment("method.invoked.userServiceBean.findOne");

        User user = userRepository.findOne(id);

        logger.info("< findOne id:{}", id);
        return user;
    }

    @Override
    @Transactional(
            propagation = Propagation.REQUIRED,
            readOnly = false)
    @CachePut(
            value = "users",
            key = "#result.id")
    public User create(User user) {
        logger.info("> create");

        counterService.increment("method.invoked.userServiceBean.create");

        validateUser(user.getUsername());

        User savedUser = userRepository.save(user);

        logger.info("< create");
        return savedUser;
    }

    @Override
    @Transactional(
            propagation = Propagation.REQUIRED,
            readOnly = false)
    @CachePut(
            value = "users",
            key = "#user.id")
    public User update(User user) {
        logger.info("> update id:{}", user.getId());

        counterService.increment("method.invoked.userServiceBean.update");

        // Ensure the entity object to be updated exists in the repository to
        // prevent the default behavior of save() which will persist a new
        // entity if the entity matching the id does not exist
        User userToUpdate = findOne(user.getId());
        if (userToUpdate == null) {
            // Cannot update User that hasn't been persisted
            logger.error(
                    "Attempted to update a User, but the entity does not exist.");
            throw new NoResultException("Requested entity not found.");
        }

        userToUpdate.setUsername(user.getUsername());
        User updatedUser = userRepository.save(userToUpdate);

        logger.info("< update id:{}", user.getId());
        return updatedUser;
    }

    @Override
    @Transactional(
            propagation = Propagation.REQUIRED,
            readOnly = false)
    @CacheEvict(
            value = "users",
            key = "#id")
    public void delete(Long id) {
        logger.info("> delete id:{}", id);

        counterService.increment("method.invoked.userServiceBean.delete");

        userRepository.delete(id);

        logger.info("< delete id:{}", id);
    }

    @Override
    @CacheEvict(
            value = "users",
            allEntries = true)
    public void evictCache() {
        logger.info("> evictCache");
        logger.info("< evictCache");
    }

    @Override
    public void validateUser(String name) {
        this.userRepository.findByUsername(name).ifPresent(
                (user) -> {throw new UserAlreadyExistsException(user);});
    }
}

/**
 * Ensure the entity object to be created does NOT exist in the
 * repository. Prevent the default behavior of save() which will update
 * an existing entity if the entity matching the supplied id exists.
 */
@ResponseStatus(HttpStatus.CONFLICT)
class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(User user) {
        super("user already exists: " + user + ".");
    }
}
