package ua.opinta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ua.opinta.core.Role;
import ua.opinta.dao.RoleRepository;
import ua.opinta.service.RoleService;
import ua.opinta.service.RoleServiceBean;

import java.util.Collection;
import java.util.List;

/**
 * The RoleController class is a RESTful web service controller. The
 * <code>@RestController</code> annotation informs Spring that each
 * <code>@RequestMapping</code> method returns a <code>@ResponseBody</code>
 * which, by default, contains a ResponseEntity converted into JSON with an
 * associated HTTP status code.
 *
 * Created by Administrator on 6/24/2016.
 */
@RestController
public class RoleController extends BaseController {
    /**
     * The RoleService business service.
     */
    @Autowired
    private RoleService roleService;

    /**
     * Web service endpoint to fetch a single Role entity by primary key
     * identifier.
     * <p>
     * If found, the Role is returned as JSON with HTTP status 200.
     * <p>
     * If not found, the service returns an empty response body with HTTP status
     * 404.
     *
     * @param id A Long URL path variable containing the Role primary key
     *           identifier.
     * @return A ResponseEntity containing a single Role object, if found,
     * and a HTTP status code as described in the method comment.
     */
    @RequestMapping(value = "/role/{id}", method = RequestMethod.GET,
            headers = "Accept=application/json", produces = {"application/json"})
    public ResponseEntity<?> getRole(@PathVariable Long id) {
        logger.info("> getRole id:{}", id);

        Role role = roleService.findOne(id);
        if (role == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        logger.info("< getRole id:{}", id);
        return new ResponseEntity<>(role, HttpStatus.FOUND);
    }

    /**
     * Web service endpoint to fetch all Role entities. The service returns
     * the collection of Role entities as JSON.
     *
     * @return A ResponseEntity containing a Collection of Role objects.
     */
    @RequestMapping(value = "/role", method = RequestMethod.GET,
            headers = "Accept=application/json", produces = {"application/json"})
    public ResponseEntity<?> getRoles() {
        logger.info("> getRoles");

        Collection<Role> roles = roleService.findAll();

        logger.info("< getRoles");
        return new ResponseEntity<>(roles, HttpStatus.FOUND);
    }

    /**
     * Web service endpoint to create a single Role entity. The HTTP request
     * body is expected to contain a Role object in JSON format. The
     * Role is persisted in the data repository.
     * <p>
     * If created successfully, the persisted Role is returned as JSON with
     * HTTP status 201.
     * <p>
     * If not created successfully, the service returns an empty response body
     * with HTTP status 500.
     *
     * @param role The Role object to be created.
     * @return A ResponseEntity containing a single Role object, if created
     * successfully, and a HTTP status code as described in the method
     * comment.
     */
    @RequestMapping(value = "/role", method = RequestMethod.POST,
            headers = "Accept=application/json", produces = {"application/json"})
    public ResponseEntity<?> createRole(@RequestBody Role role) {
        logger.info("> createRole");

        roleService.validateRole(role.getName());
        Role savedRole = roleService.create(role);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedRole.getId()).toUri());

        logger.info("< createRole");
        return new ResponseEntity<>(httpHeaders, HttpStatus.CREATED);
    }

    /**
     * Web service endpoint to update a single Role entity. The HTTP request
     * body is expected to contain a Role object in JSON format. The
     * Role is updated in the data repository.
     * <p>
     * If updated successfully, the persisted Role is returned as JSON with
     * HTTP status 200.
     * <p>
     * If not found, the service returns an empty response body and HTTP status
     * 404.
     * <p>
     * If not updated successfully, the service returns an empty response body
     * with HTTP status 500.
     *
     * @param role The Role object to be updated.
     * @return A ResponseEntity containing a single Role object, if updated
     * successfully, and a HTTP status code as described in the method
     * comment.
     */
    @RequestMapping(method = RequestMethod.PUT, value = "/role/{id}",
            headers = "Accept=application/json", produces = {"application/json"})
    public ResponseEntity<?> updateRole(@PathVariable Long id, @RequestBody Role role) {
        logger.info("> updateRole id:{}", role.getId());

        Role updatedRole = roleService.findOne(id);
        if (updatedRole == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        logger.info("< updateRole id:{}", role.getId());
        return new ResponseEntity<>(updatedRole, HttpStatus.OK);
    }

    /**
     * Web service endpoint to delete a single Role entity. The HTTP request
     * body is empty. The primary key identifier of the Role to be deleted
     * is supplied in the URL as a path variable.
     * <p>
     * If deleted successfully, the service returns an empty response body with
     * HTTP status 204.
     * <p>
     * If not deleted successfully, the service returns an empty response body
     * with HTTP status 500.
     *
     * @param id A Long URL path variable containing the Role primary key
     *           identifier.
     * @return A ResponseEntity with an empty response body and a HTTP status
     * code as described in the method comment.
     */
    @RequestMapping(value = "/role/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteRole(@PathVariable Long id) {
        logger.info("> deleteRole id:{}", id);

        roleService.delete(id);

        logger.info("< deleteRole id:{}", id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}