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
import ua.opinta.dao.RoleRepository;

import javax.persistence.EntityExistsException;
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
public class RoleServiceBean implements RoleService{

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
    private RoleRepository roleRepository;

    @Override
    public Collection<Role> findAll() {
        logger.info("> findAll");

        counterService.increment("method.invoked.roleServiceBean.findAll");

        Collection<Role> roles = roleRepository.findAll();

        logger.info("< findAll");
        return roles;
    }

    @Override
    @Cacheable(
            value = "roles",
            key = "#id")
    public Role findOne(Long id) {
        logger.info("> findOne id:{}", id);

        counterService.increment("method.invoked.roleServiceBean.findOne");

        Role role = roleRepository.findOne(id);

        logger.info("< findOne id:{}", id);
        return role;
    }

    @Override
    @Transactional(
            propagation = Propagation.REQUIRED,
            readOnly = false)
    @CachePut(
            value = "roles",
            key = "#result.id")
    public Role create(Role role) {
        logger.info("> create");

        counterService.increment("method.invoked.roleServiceBean.create");

        validateRole(role.getName());

        Role savedRole = roleRepository.save(role);

        logger.info("< create");
        return savedRole;
    }

    @Override
    @Transactional(
            propagation = Propagation.REQUIRED,
            readOnly = false)
    @CachePut(
            value = "roles",
            key = "#role.id")
    public Role update(Role role) {
        logger.info("> update id:{}", role.getId());

        counterService.increment("method.invoked.roleServiceBean.update");

        // Ensure the entity object to be updated exists in the repository to
        // prevent the default behavior of save() which will persist a new
        // entity if the entity matching the id does not exist
        Role roleToUpdate = findOne(role.getId());
        if (roleToUpdate == null) {
            // Cannot update Role that hasn't been persisted
            logger.error(
                    "Attempted to update a Role, but the entity does not exist.");
            throw new NoResultException("Requested entity not found.");
        }

        roleToUpdate.setName(role.getName());
        Role updatedRole = roleRepository.save(roleToUpdate);

        logger.info("< update id:{}", role.getId());
        return updatedRole;
    }

    @Override
    @Transactional(
            propagation = Propagation.REQUIRED,
            readOnly = false)
    @CacheEvict(
            value = "roles",
            key = "#id")
    public void delete(Long id) {
        logger.info("> delete id:{}", id);

        counterService.increment("method.invoked.roleServiceBean.delete");

        roleRepository.delete(id);

        logger.info("< delete id:{}", id);
    }

    @Override
    @CacheEvict(
            value = "roles",
            allEntries = true)
    public void evictCache() {
        logger.info("> evictCache");
        logger.info("< evictCache");
    }

    @Override
    public void validateRole(String name) {
        this.roleRepository.findByName(name).ifPresent(
                (role) -> {throw new RoleAlreadyExistsException(role);});
    }

    @Override
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
}

/**
 * Ensure the entity object to be created does NOT exist in the
 * repository. Prevent the default behavior of save() which will update
 * an existing entity if the entity matching the supplied id exists.
 */
@ResponseStatus(HttpStatus.CONFLICT)
class RoleAlreadyExistsException extends RuntimeException {
    public RoleAlreadyExistsException(Role role) {
        super("Role already exists: " + role + ".");
    }
}
