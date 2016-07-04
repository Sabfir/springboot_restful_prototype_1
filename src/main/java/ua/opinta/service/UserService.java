package ua.opinta.service;

import ua.opinta.core.Role;
import ua.opinta.core.User;

import java.util.Collection;

/**
 * The UserService interface defines all public business behaviors for
 * operations on the User entity model.
 *
 * This interface should be injected into UserService clients, not the
 * implementation bean.
 *
 * Created by Administrator on 7/2/2016.
 */
public interface UserService {
    /**
     * Find all User entities.
     * @return A Collection of User objects.
     */
    Collection<User> findAll();

    /**
     * Find a single User entity by primary key identifier.
     * @param id A Long primary key identifier.
     * @return A User or <code>null</code> if none found.
     */
    User findOne(Long id);

    /**
     * Persists a User entity in the data store.
     * @param user A User object to be persisted.
     * @return The persisted User entity.
     */
    User create(User user);

    /**
     * Updates a previously persisted User entity in the data store.
     * @param user A User object to be updated.
     * @return The updated User entity.
     */
    User update(User user);

    /**
     * Removes a previously persisted User entity from the data store.
     * @param id A Long primary key identifier.
     */
    void delete(Long id);

    /**
     * Evicts all members of the "user" cache.
     */
    void evictCache();

    void validateUser(String name);
}
