package se.lexicon.todoapi.service;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.todoapi.exception.ObjectDuplicateException;
import se.lexicon.todoapi.exception.ObjectNotFoundException;
import se.lexicon.todoapi.model.dto.RoleDto;
import se.lexicon.todoapi.model.entity.Role;
import se.lexicon.todoapi.repository.RoleRepository;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    RoleRepository roleRepository;
    ModelMapper modelMapper;


    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository, ModelMapper modelMapper) {
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public RoleDto create(RoleDto dto) throws ObjectDuplicateException {
        if (dto == null) throw new IllegalArgumentException("RoleDto was null");
        if (dto.getId() != null) throw new IllegalArgumentException("RoleDto.Id must be null");

        Role convertedToEntity = modelMapper.map(dto, Role.class);
        Role createdRole = roleRepository.save(convertedToEntity);
        RoleDto convertedToDto = modelMapper.map(createdRole, RoleDto.class);
        return convertedToDto;
    }

    @Override
    public void update(RoleDto dto) {
        if (dto == null) throw new IllegalArgumentException("RoleDto was null");
        if (dto.getId() == null) throw new IllegalArgumentException("RoleDto.Id must not be null");
        Role convertedToEntity = modelMapper.map(dto, Role.class);
        roleRepository.save(convertedToEntity);
    }

    @Override
    public List<RoleDto> getAll() {
        List<Role> roleList = roleRepository.findAll();
        List<RoleDto> roleDtoList = modelMapper.map(
                roleList,
                new TypeToken<List<RoleDto>>() {
                }.getType()
        );

        return roleDtoList;
    }

    @Override
    public RoleDto findById(Integer id) throws ObjectNotFoundException {
        if (id == null) throw new IllegalArgumentException("Id was null");
        Role result = roleRepository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException("Role data not found"));

        return modelMapper.map(result, RoleDto.class);
    }

    @Override
    public RoleDto findByName(String name) throws ObjectNotFoundException {
        if (name == null) throw new IllegalArgumentException("name was null");
        Role result = roleRepository.findByName(name).orElseThrow(
                () -> new ObjectNotFoundException("Role data not found"));

        return modelMapper.map(result, RoleDto.class);
    }

    @Override
    public void delete(Integer id) throws ObjectNotFoundException {
        findById(id);
        roleRepository.deleteById(id);
    }
}
