package ua.opinta.service;

import ua.opinta.core.Role;
import ua.opinta.dao.RoleRepository;

import java.util.Collection;

/**
 * The RoleService interface defines all public business behaviors for
 * operations on the Role entity model.
 *
 * This interface should be injected into RoleService clients, not the
 * implementation bean.
 *
 * Created by Administrator on 7/2/2016.
 */
public interface RoleService {
    /**
     * Find all Role entities.
     * @return A Collection of Role objects.
     */
    Collection<Role> findAll();

    /**
     * Find a single Role entity by primary key identifier.
     * @param id A Long primary key identifier.
     * @return A Role or <code>null</code> if none found.
     */
    Role findOne(Long id);

    /**
     * Persists a Role entity in the data store.
     * @param role A Role object to be persisted.
     * @return The persisted Role entity.
     */
    Role create(Role role);

    /**
     * Updates a previously persisted Role entity in the data store.
     * @param role A Role object to be updated.
     * @return The updated Role entity.
     */
    Role update(Role role);

    /**
     * Removes a previously persisted Role entity from the data store.
     * @param id A Long primary key identifier.
     */
    void delete(Long id);

    /**
     * Evicts all members of the "role" cache.
     */
    void evictCache();

    /**
     * Validates if the Role exists before creating. Throws exception if role already exists
     * @param name A String name of the Role to be validated.
     */
    void validateRole(String name);

    /**
     * Sets the Role repository.
     * @param roleRepository A Role repository to be set.
     */
    void setRoleRepository(RoleRepository roleRepository);
}
