package project.forja.controller;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import project.forja.controller.dto.create.CreateUserDto;
import project.forja.controller.dto.response.UserResponseDto;
import project.forja.controller.dto.update.UpdateUserDto;
import project.forja.entity.User;
import project.forja.service.UserService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody CreateUserDto createUserDTO) {
        var userID = userService.createUser(createUserDTO);
        return ResponseEntity.created(URI.create("/v1/users/" + userID.toString())).build();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable("userId") String userId) {
        var user = userService.getUserById(userId);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping()
    public ResponseEntity<List<UserResponseDto>> listUsers() {
        var users = userService.listUsers();
        return ResponseEntity.ok(users);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Void> updateUserById(@Valid @PathVariable("userId") String userId,
                                               @RequestBody UpdateUserDto updateUserDTO) {
        userService.updateUserById(userId, updateUserDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteById(@PathVariable("userId") String userId) {
        userService.deleteById(userId);
        return ResponseEntity.noContent().build();
    }

    // Tratamento de exceções que faltam o ID na solicitação
    @PutMapping({"", "/"})
    public ResponseEntity<Void> updateWithouId() {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O ID do usuário é obrigatório.");
    }

    @DeleteMapping({"", "/"})
    public ResponseEntity<Void> deleteWithoutId() {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O ID do usuário é obrigatório.");
    }

    @GetMapping({"", "/"})
    public ResponseEntity<User> getUserById() {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O ID do usuário é obrigatório.");
    }
}