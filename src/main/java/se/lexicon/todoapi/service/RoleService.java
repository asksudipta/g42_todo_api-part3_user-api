package se.lexicon.todoapi.service;

import se.lexicon.todoapi.exception.ObjectDuplicateException;
import se.lexicon.todoapi.exception.ObjectNotFoundException;
import se.lexicon.todoapi.model.dto.RoleDto;

import java.util.List;

public interface RoleService {

    RoleDto create(RoleDto dto) throws ObjectDuplicateException;

    void update(RoleDto dto);

    List<RoleDto> getAll();

    RoleDto findById(Integer id) throws ObjectNotFoundException;

    RoleDto findByName(String name) throws ObjectNotFoundException;

    void delete(Integer id) throws ObjectNotFoundException;
}
