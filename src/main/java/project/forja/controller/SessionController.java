package project.forja.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.forja.controller.dto.create.CreateSessionDto;
import project.forja.controller.dto.response.SessionResponseDto;
import project.forja.service.SessionService;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/v1")
public class SessionController {

    private SessionService sessionService;

    // Injeção de dependência via construtor
    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @PostMapping("/session")
    public ResponseEntity<Void> createSessionById(@RequestBody CreateSessionDto createSessionDto) {
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
}


