package de.neuefische.backend.todo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record NewTodoDTO(
        @NotBlank
        @Size(min = 6, max = 32, message = "Beschreibung muss mindestens 6 Zeichen lang sein und maximal 32 Zeichen!")
        String description,

        TodoStatus status
) {
}
