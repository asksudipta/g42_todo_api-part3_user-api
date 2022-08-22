package se.lexicon.todoapi.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.todoapi.exception.ObjectNotFoundException;
import se.lexicon.todoapi.model.dto.RoleDto;
import se.lexicon.todoapi.model.dto.UserDto;
import se.lexicon.todoapi.model.entity.User;
import se.lexicon.todoapi.repository.RoleRepository;
import se.lexicon.todoapi.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    ModelMapper modelMapper;// it is used for converting dto to entity and voice versa
    UserRepository userRepository;
    RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(ModelMapper modelMapper,
                           UserRepository userRepository,
                           RoleRepository roleRepository) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserDto register(UserDto dto) throws ObjectNotFoundException {
        // check the input data
        if (dto == null) throw new IllegalArgumentException("User Data was null");
        if (dto.getUsername() == null) throw new IllegalArgumentException("Username was null");
        if (dto.getPassword() == null) throw new IllegalArgumentException("Password was null");
        if (dto.isExpired()) throw new IllegalArgumentException("Expired value must be false or null");
        if (dto.getRoles() == null || dto.getRoles().size() == 0)
            throw new IllegalArgumentException("Role list was null");
        // check the user name is duplicate or no
        if (userRepository.existsByUsername(dto.getUsername()))
            throw new IllegalArgumentException("Username was duplicate");
        // check the roles are valid or not
        for (RoleDto roleDto : dto.getRoles()) {
            roleRepository.findById(roleDto.getId()).orElseThrow(() -> new ObjectNotFoundException("Role ID was not valid"));
        }
        // convert dto to entity
        User convertedToEntity = modelMapper.map(dto, User.class);
        // save it on DB
        User createdEntity = userRepository.save(convertedToEntity);
        // convert entity to dto to return the result
        UserDto convertedToDto = modelMapper.map(createdEntity, UserDto.class);
        // return the result
        return convertedToDto;
    }

    @Override
    public UserDto findByUsername(String username) throws ObjectNotFoundException {
        // todo: implement find by username
        return null;
    }

    @Override
    public void disableUserByUsername(String username) {
        // todo: implement disable user by username
    }

    @Override
    public void enableUserByUsername(String username) {
        // todo: implement enable user by username
    }

}
