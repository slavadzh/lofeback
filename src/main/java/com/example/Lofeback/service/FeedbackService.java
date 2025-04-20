package com.example.Lofeback.service;

import com.example.Lofeback.dto.FeedbackDTO;
import com.example.Lofeback.entity.Feedback;
import com.example.Lofeback.entity.Profile;
import com.example.Lofeback.repository.FeedbackRepository;
import com.example.Lofeback.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final ProductRepository productRepository;
    private final ProfileService profileService;

    @Transactional
    public void save(FeedbackDTO feedbackDTO) {
        feedbackRepository.save(toEntity(feedbackDTO));
    }

    public Feedback toEntity(FeedbackDTO feedbackDTO) {
        Feedback feedback = new Feedback();
        feedback.setDescription(feedbackDTO.getDescription());
        feedback.setRating(feedbackDTO.getRating());
        feedback.setProduct(productRepository.findById(feedbackDTO.getProductId()).orElse(null));
        feedback.setProfile(profileService.getAuthProfile());
        return feedback;
    }

    public FeedbackDTO toDTO(Feedback feedback) {
        FeedbackDTO feedbackDTO = new FeedbackDTO();
        feedbackDTO.setDescription(feedback.getDescription());
        feedbackDTO.setRating(feedback.getRating());
        feedbackDTO.setProductId(feedback.getProduct().getId());
        feedbackDTO.setProfileId(feedback.getProfile().getId());
        return feedbackDTO;
    }
}
