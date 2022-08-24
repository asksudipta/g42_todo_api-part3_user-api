package se.lexicon.todoapi.service;

import se.lexicon.todoapi.exception.ObjectNotFoundException;
import se.lexicon.todoapi.model.dto.CustomUserDto;
import se.lexicon.todoapi.model.dto.UserDto;

public interface UserService {

    UserDto register(UserDto dto) throws ObjectNotFoundException;

    CustomUserDto findByUsername(String username) throws ObjectNotFoundException;

    void disableUserByUsername(String username) throws ObjectNotFoundException;

    void enableUserByUsername(String username) throws ObjectNotFoundException;
}
