package se.lexicon.todoapi.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class CustomUserDto {

    private String username;
    private List<RoleDto> roles;
}
