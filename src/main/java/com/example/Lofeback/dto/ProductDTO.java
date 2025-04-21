package com.example.Lofeback.dto;

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
    String name;
    String description;
    Double averageRating;
    Long teamId;
    List<FeedbackDTO> feedbacks;
}
