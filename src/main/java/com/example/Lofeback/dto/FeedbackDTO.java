package com.example.Lofeback.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FeedbackDTO {

    @Size(min = 5, max = 2000, message = "Описание должно быть от 5 до 2000 элементов")
    String description;

    @Min(value = 1, message = "Рейтинг минимум 1")
    @Max(value = 5, message = "Рейтинг максимум 5")
    Integer rating;

    Long productId;
    Long profileId;
}
