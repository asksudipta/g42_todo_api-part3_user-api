package se.lexicon.todoapi.service;

import se.lexicon.todoapi.exception.ObjectNotFoundException;
import se.lexicon.todoapi.model.dto.UserDto;

public interface UserService {

    UserDto register(UserDto dto) throws ObjectNotFoundException;

    UserDto findByUsername(String username) throws ObjectNotFoundException;

    void disableUserByUsername(String username);

    void enableUserByUsername(String username);
}
