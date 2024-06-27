package dev.danvega.sb3w.post;

import jakarta.validation.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;

public record Post(
        @Id
        Integer id,
        Integer userId,
        @NotEmpty
        String title,
        String body,
        @Version
        Integer version
) {

}
