package project.forja.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import project.forja.controller.dto.create.CreateSessionDto;
import project.forja.controller.dto.response.SessionResponseDto;
import project.forja.controller.dto.update.UpdateUserDto;
import project.forja.entity.*;
import project.forja.exception.UserNotFoundException;
import project.forja.repository.CardioLogRepository;
import project.forja.repository.ExerciseCatalogRepository;
import project.forja.repository.SessionRepository;
import project.forja.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class SessionService {
    private final UserRepository userRepository;
    private final SessionRepository sessionRepository;
    private final CardioLogRepository cardioLogRepository;
    private final ExerciseCatalogRepository exerciseCatalogRepository;

    public SessionService(UserRepository userRepository, SessionRepository sessionRepository, CardioLogRepository cardioLogRepository, ExerciseCatalogRepository exerciseCatalogRepository) {
        this.userRepository = userRepository;
        this.sessionRepository = sessionRepository;
        this.cardioLogRepository = cardioLogRepository;
        this.exerciseCatalogRepository = exerciseCatalogRepository;
    }

    public void createSession(CreateSessionDto createSessionDto) {
        // Busca o usuário usando o ID que veio dentro da chamada da função pela controller
        var user = userRepository.findById(createSessionDto.userId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        // DTO -> ENTITY
        var session = new TrainingSession(
                user,
                createSessionDto.dateTime(),
                createSessionDto.durationTime(),
                createSessionDto.intensityRpe(),
                createSessionDto.battleLog(),
                createSessionDto.terrainType()
        );

        // Verifica se o cardio foi passado ao criar uma sessão de treino
        if (createSessionDto.cardio() != null) {
            // Coleta o sub-DTO de cardio referenciado
            var cardioDto = createSessionDto.cardio();

            var cardioLog = new CardioLog(
                    cardioDto.distanceKm(),
                    cardioDto.pace(),
                    cardioDto.elevationGain(),
                    cardioDto.sportsType()
            );
            // Vínculo bidirecional
            cardioLog.setTrainingSession(session);
            session.setCardioLog(cardioLog);
        }
        // Verifica se a musculação foi passado ao criar uma sessão de treino
        // Se tiver, percorre a lista, busca o nome do exercício no catálogo, cria as séries e anexa.
        if (createSessionDto.lifts() != null && !createSessionDto.lifts().isEmpty()) {

            // Se tiver treino de musculação passe por cada exercício
            for (var liftDto : createSessionDto.lifts()) {

                // Cria o registro do exercício
                var liftingLog = new LiftingLog(liftDto.notes());

                // Busca o exercício no catálogo. Necessidade do repository!
                var exerciseCatalog = exerciseCatalogRepository.findById(liftDto.exerciseId())
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

                // Vincula um exercício do catálogo em cada exercício
                liftingLog.setExerciseCatalog(exerciseCatalog);

                int contadorDeSeries = 1;

                if (liftDto.sets() != null) {
                    for (var setDto : liftDto.sets()) {
                        // DTO -> ENTITY
                        var setLog = new SetLog(
                                contadorDeSeries,
                                setDto.reps(),
                                setDto.weightKg()
                        );
                        contadorDeSeries++;
                        liftingLog.addSets(setLog);
                    }
                }
                // Vinculação bidirecional
                liftingLog.setTrainingSession(session);
                session.getLiftingLog().add(liftingLog);
            }
        }
        var sessionCreated = sessionRepository.save(session);
    }

    public List<SessionResponseDto> listSessions() {
        var sessionsEntity = sessionRepository.findAll();
        return sessionsEntity.stream()
                // Chamada do metodo para converter o Dto
                .map(this::mapToDto)
                .toList();
    }

    public List<SessionResponseDto> listSessionsByUserId(UUID userId) {
        List<TrainingSession> sessions = sessionRepository.findByUserUserId(userId);
        return sessions.stream()
                .map(this::mapToDto)
                .toList();
    }

    private SessionResponseDto mapToDto(TrainingSession session) {
        return new SessionResponseDto(
                session.getId(),
                session.getDateTime(),
                session.getDurationTime(),
                session.getIntensityRpe(),
                session.getBattleLog(),
                session.getTerrainType(),
                session.getUser().getUserId()
        );
    }


    public void deleteBySessionId(String sessionId) {
        var id = UUID.fromString(sessionId);
        var sessionExist = sessionRepository.existsById(id);

        if (sessionExist) {
            sessionRepository.deleteById(id);
        }
    }
}
