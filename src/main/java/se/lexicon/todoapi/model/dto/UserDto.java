package se.lexicon.todoapi.model.dto;

import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "password")
public class UserDto {

    private String username; // admin
    private String password; // admin
    private boolean expired; // false
    private List<RoleDto> roles; // [ {id: 1, name:ROLE_ADMIN} , {id: 3, name: ROLE_TEST} ];


}
