package se.lexicon.todoapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.lexicon.todoapi.exception.ObjectNotFoundException;
import se.lexicon.todoapi.model.dto.CustomUserDto;
import se.lexicon.todoapi.model.dto.RoleDto;
import se.lexicon.todoapi.model.dto.UserDto;
import se.lexicon.todoapi.model.entity.User;
import se.lexicon.todoapi.repository.UserRepository;
import se.lexicon.todoapi.service.UserService;
import se.lexicon.todoapi.service.UserServiceImpl;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping()
    public ResponseEntity<UserDto> register(@RequestBody UserDto dto) {
        System.out.println("dto = " + dto);
        UserDto result = null;
        try {
            result = userService.register(dto);
            return status(HttpStatus.CREATED).body(result);

        } catch (ObjectNotFoundException e) {
            e.printStackTrace();
            return status(HttpStatus.INTERNAL_SERVER_ERROR).build();

        }

    }


    @GetMapping("/{username}")
    public ResponseEntity<CustomUserDto> findByUsername(@PathVariable("username") String username) throws ObjectNotFoundException {
        System.out.println("Username =" + username);
        CustomUserDto userDto = userService.findByUsername(username);
        return ResponseEntity.ok(userDto);

    }

    @PutMapping("/disable")
    public ResponseEntity<Void> disableUser(@RequestParam("username") String username) throws ObjectNotFoundException {
        userService.disableUserByUsername(username);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/enable")
    public ResponseEntity<Void> enableUser(@RequestParam("username") String username) throws ObjectNotFoundException {
        userService.enableUserByUsername(username);
        return ResponseEntity.noContent().build();
    }

}
