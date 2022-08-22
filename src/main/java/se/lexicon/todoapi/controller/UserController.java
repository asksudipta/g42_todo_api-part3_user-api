package se.lexicon.todoapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.lexicon.todoapi.exception.ObjectNotFoundException;
import se.lexicon.todoapi.model.dto.UserDto;
import se.lexicon.todoapi.service.UserService;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping()
    public ResponseEntity<UserDto> register(@RequestBody UserDto dto){
        System.out.println("dto = " + dto);
        UserDto result = null;
        try {
            result = userService.register(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(result);

        } catch (ObjectNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

        }

    }


    @GetMapping("/{username}")
    public ResponseEntity<UserDto> findByUsername(@PathVariable("username") String username){
        // todo: implement rest full api for find by username
        return null;
    }


    @PutMapping("/disable")
    public ResponseEntity<Void> disableUser(@RequestParam("username") String username){
        // todo: implement disable user by username api
        return null;
    }


    @PutMapping("/enable")
    public ResponseEntity<Void> enableUser(@RequestParam("username") String username){
        // todo: implement enable user by username api
        return null;
    }

}
