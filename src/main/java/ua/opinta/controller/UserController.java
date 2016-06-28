package ua.opinta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ua.opinta.core.User;
import ua.opinta.dao.UserRepository;

import java.util.List;

/**
 * Created by Administrator on 6/24/2016.
 */
@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET,
            headers = "Accept=application/json", produces = {"application/json"})
    public ResponseEntity<?> getUser(@PathVariable Long id) {
        User user = userRepository.findOne(id);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(user, HttpStatus.FOUND);
        }
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET,
            headers = "Accept=application/json", produces = {"application/json"})
    public ResponseEntity<?> getUsers() {
        List<User> users = userRepository.findAll();

        return new ResponseEntity<>(users, HttpStatus.FOUND);
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST,
            headers = "Accept=application/json", produces = {"application/json"})
    public ResponseEntity<?> saveUser(@RequestBody User user) {
        validateUser(user.getUsername());
        User savedUser = userRepository.save(user);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedUser.getId()).toUri());
        return new ResponseEntity<>(httpHeaders, HttpStatus.CREATED);
    }

    @RequestMapping(value="/user/{id}", method=RequestMethod.PUT,
            headers = "Accept=application/json", produces = {"application/json"})
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody User user) {
        User update = userRepository.findOne(id);
        if (update == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        update.setUsername(user.getUsername());
        update.setPassword(user.getPassword());
        update.setIsActive(user.isActive());
        update.setRoles(user.getRoles());
        User savedUser = userRepository.save(update);

        return new ResponseEntity<>(savedUser, HttpStatus.OK);
    }

    @RequestMapping(value="/user/{id}", method=RequestMethod.DELETE,
            headers = "Accept=application/json", produces = {"application/json"})
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userRepository.delete(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    private void validateUser(String username) {
        this.userRepository.findByUsername(username).ifPresent(
                (user) -> {throw new UserAlreadyExistsException(user);});
    }
}

@ResponseStatus(HttpStatus.CONFLICT)
class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(User user) {
        super("User already exists: " + user + ".");
    }
}
