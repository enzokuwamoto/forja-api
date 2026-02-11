package project.forja.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import project.forja.controller.dto.create.CreateUserDto;
import project.forja.entity.GoalType;
import project.forja.entity.User;
import project.forja.repository.UserRepository;

import java.time.LocalDate;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Nested
    class createUser {

        @Test
        @DisplayName("Should create a user with success")
        void shouldCreateAUserWithSuccess() {
            // Arrange

            var userEntitySalva = new User(
                    UUID.randomUUID(),
                    "Enzo",
                    "email@gmail.com",
                    "123456",
                    71.0,
                    1.78,
                    80.0,
                    LocalDate.parse("2023-10-25"),
                    GoalType.MAQUINA_HIBRIDA,
                    null,
                    null
            );
            doReturn(null).when(userRepository).save(any());
            var input = new CreateUserDto(
                    "Enzo",
                    "email@gmail.com",
                    "123456",
                    71.0,
                    1.78,
                    80.0,
                    LocalDate.parse("2023-10-25"),
                    GoalType.MAQUINA_HIBRIDA
            );
            // Act
            var output = userService.createUser(input);
            // Assert
        }
    }

}