package se.lexicon.todoapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.lexicon.todoapi.exception.ObjectDuplicateException;
import se.lexicon.todoapi.exception.ObjectNotFoundException;
import se.lexicon.todoapi.model.dto.RoleDto;
import se.lexicon.todoapi.service.RoleService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/role")
public class RoleController {

    RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }


    @GetMapping("/")
    public ResponseEntity<List<RoleDto>> getAllRoles() {
        System.out.println("### getAllRoles has been executed!");
        List<RoleDto> roleDtoList = roleService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(roleDtoList);
        //return ResponseEntity.ok(roleDtoList); // 200
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleDto> findRoleById(@PathVariable("id") Integer id) throws ObjectNotFoundException {
        System.out.println("id = " + id);
        RoleDto roleDto = roleService.findById(id);
        return ResponseEntity.ok(roleDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoleById(@PathVariable("id") Integer id) throws ObjectNotFoundException {
        System.out.println("id = " + id);
        roleService.delete(id);
        return ResponseEntity.noContent().build(); //204
        //return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/")
    public ResponseEntity<RoleDto> createRole(@RequestBody RoleDto roleDto) throws ObjectDuplicateException {
        System.out.println("roleDto = " + roleDto);
        RoleDto createdRole = roleService.create(roleDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRole); // 201
    }

    @PutMapping("/")
    public ResponseEntity<Void> updateRoleById(@RequestBody RoleDto roleDto) {
        System.out.println("roleDto = " + roleDto);
        roleService.update(roleDto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); // 204
    }

}
