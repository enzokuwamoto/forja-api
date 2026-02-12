package project.forja.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import project.forja.controller.dto.create.CreateSessionDto;
import project.forja.controller.dto.response.SessionResponseDto;
import project.forja.controller.dto.update.UpdateCatalogDto;
import project.forja.controller.dto.update.UpdateUserDto;
import project.forja.service.SessionService;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/v1")
public class SessionController {

    @Autowired
    private SessionService sessionService;


    @PostMapping("/session")
    public ResponseEntity<Void> createSessionById(@Valid @RequestBody CreateSessionDto createSessionDto) {
        sessionService.createSession(createSessionDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/session")
    public ResponseEntity<List<SessionResponseDto>> listSessions() {
        var sessions = sessionService.listSessions();
        return ResponseEntity.ok(sessions);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<SessionResponseDto>> listSessionByUserId(@PathVariable("userId") String userId) {
        var sessions = sessionService.listSessionsByUserId(UUID.fromString(userId));
        return ResponseEntity.ok(sessions);
    }

    @DeleteMapping("/{training_session_id}")
    public ResponseEntity<Void> deleteById(@PathVariable("training_session_id") String sessionId) {
        sessionService.deleteBySessionId(sessionId);
        return ResponseEntity.noContent().build();
    }

    // Tratamento de exceções que faltam o ID na solicitação

    @GetMapping({"", "/"})
    public ResponseEntity<List<SessionResponseDto>> listSessionWithoutUserId() {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O Id do usuário é obrigatório.");
    }

    @DeleteMapping({"", "/"})
    public ResponseEntity<Void> deleteWithoutId() {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O Id do treinamento é obrigatório.");
    }

}


