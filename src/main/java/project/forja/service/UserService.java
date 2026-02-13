package project.forja.service;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import project.forja.controller.dto.create.CreateUserDto;
import project.forja.controller.dto.response.UserResponseDto;
import project.forja.controller.dto.update.UpdateUserDto;
import project.forja.entity.User;
import project.forja.exception.UserNotFoundException;
import project.forja.repository.UserRepository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UUID createUser(CreateUserDto createUserDTO) {
        if (userRepository.existsByEmail(createUserDTO.email())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Este e-mail já está em uso.");
        }
        // DTO -> ENTITY
        var entity = new User(null,
                createUserDTO.name(),
                createUserDTO.email(),
                createUserDTO.password(),
                createUserDTO.currentWeight(),
                createUserDTO.height(),
                createUserDTO.targetWeight(),
                createUserDTO.startDate(),
                createUserDTO.goalType(),
                Instant.now(),
                null);
        var userSaved = userRepository.save(entity);
        return userSaved.getUserId();
    }

    public Optional<User> getUserById(String userId) {
        return userRepository.findById(UUID.fromString(userId));
    }

    public List<UserResponseDto> listUsers() {
        var userEntity = userRepository.findAll();
        return userEntity.stream()
                // Chamada do metodo para converter o Dto
                .map(this::mapToDto)
                .toList();
    }

    public UserResponseDto mapToDto(User user) {
        return new UserResponseDto(
                user.getUserId(),
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                user.getCurrentWeight(),
                user.getHeight(),
                user.getTargetWeight(),
                user.getStartDate(),
                user.getGoalType()
        );
    }

    public void updateUserById(String userId,
                               UpdateUserDto updateUserDTO) {

        var id = UUID.fromString(userId);
        var user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Usuário não existe."));

        if (updateUserDTO.name() != null) {
            user.setName(updateUserDTO.name());
        }
        if (updateUserDTO.email() != null) {
            user.setEmail(updateUserDTO.email());
        }
        if (updateUserDTO.password() != null) {
            user.setPassword(updateUserDTO.password());
        }
        if (updateUserDTO.currentWeight() != null) {
            user.setCurrentWeight(updateUserDTO.currentWeight());
        }
        if (updateUserDTO.height() != null) {
            user.setHeight(updateUserDTO.height());
        }
        if (updateUserDTO.targetWeight() != null) {
            user.setTargetWeight(updateUserDTO.targetWeight());
        }
        if (updateUserDTO.goalType() != null) {
            user.setGoalType(updateUserDTO.goalType());
        }
        userRepository.save(user);
    }

    public void deleteById(String userId) {
        var id = UUID.fromString(userId);
        var user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Usuário não existe."));
        userRepository.deleteById(id);
    }

}
