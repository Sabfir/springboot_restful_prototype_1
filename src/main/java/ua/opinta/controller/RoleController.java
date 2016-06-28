package ua.opinta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ua.opinta.core.Role;
import ua.opinta.dao.RoleRepository;

import java.util.List;

/**
 * Created by Administrator on 6/24/2016.
 */
@RestController
public class RoleController {

    @Autowired
    private RoleRepository roleRepository;

    @RequestMapping(value = "/role/{id}", method = RequestMethod.GET,
            headers = "Accept=application/json", produces = {"application/json"})
    public ResponseEntity<?> getRole(@PathVariable Long id) {
        Role role = roleRepository.findOne(id);

        if (role == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(role, HttpStatus.FOUND);
        }
    }

    @RequestMapping(value = "/role", method = RequestMethod.GET,
            headers = "Accept=application/json", produces = {"application/json"})
    public ResponseEntity<?> getRoles() {
        List<Role> roles = roleRepository.findAll();

        return new ResponseEntity<>(roles, HttpStatus.FOUND);
    }

    @RequestMapping(value = "/role", method = RequestMethod.POST,
            headers = "Accept=application/json", produces = {"application/json"})
    public ResponseEntity<?> saveRole(@RequestBody Role role) {
        validateRole(role.getName());
        Role savedRole = roleRepository.save(role);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedRole.getId()).toUri());
        return new ResponseEntity<>(httpHeaders, HttpStatus.CREATED);
    }

    @RequestMapping(method=RequestMethod.PUT, value="/role/{id}",
            headers = "Accept=application/json", produces = {"application/json"})
    public ResponseEntity<?> updateRole(@PathVariable Long id, @RequestBody Role role) {
        Role update = roleRepository.findOne(id);
        if (update == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        update.setName(role.getName());
        Role savedRole = roleRepository.save(update);

        return new ResponseEntity<>(savedRole, HttpStatus.OK);
    }

    @RequestMapping(value="/role/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<?> deleteRole(@PathVariable Long id) {
        roleRepository.delete(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    private void validateRole(String name) {
        this.roleRepository.findByName(name).ifPresent(
                (role) -> {throw new RoleAlreadyExistsException(role);});
    }
}

@ResponseStatus(HttpStatus.CONFLICT)
class RoleAlreadyExistsException extends RuntimeException {
    public RoleAlreadyExistsException(Role role) {
        super("Role already exists: " + role + ".");
    }
}
