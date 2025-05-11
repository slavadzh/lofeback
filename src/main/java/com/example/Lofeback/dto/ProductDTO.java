package com.example.Lofeback.dto;

import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductDTO {

    @Size(min = 2, max = 50, message = "Название от 2 до 50")
    String name;

    @Size(min = 5, max = 2000, message = "Описание должно быть от 5 до 2000 элементов")
    String description;

    Double averageRating;
    Long teamId;
    List<FeedbackDTO> feedbacks;
}
