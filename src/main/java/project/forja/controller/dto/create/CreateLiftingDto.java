package project.forja.controller.dto.create;

import java.util.List;
import java.util.UUID;

public record CreateLiftingDto (UUID exerciseId, String notes,List<CreateSetsDto> sets) {
}
