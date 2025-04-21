package com.example.Lofeback.service;

import com.example.Lofeback.dto.FeedbackDTO;
import com.example.Lofeback.entity.Feedback;
import com.example.Lofeback.entity.Profile;
import com.example.Lofeback.repository.FeedbackRepository;
import com.example.Lofeback.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<FeedbackDTO> findAll() {
        return  feedbackRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    @Transactional
    public void update(Long id, FeedbackDTO feedbackDTO) {
        Feedback feedback = feedbackRepository.findById(id).orElse(null);
        feedback.setDescription(feedbackDTO.getDescription());
        feedback.setRating(feedbackDTO.getRating());
        feedbackRepository.save(feedback);
    }

    @Transactional
    public void delete(Long id) {
        feedbackRepository.deleteById(id);
    }

    public Feedback toEntity(FeedbackDTO feedbackDTO) {
        var currProfile = profileService.getAuthProfile();
        var currProduct = productRepository.findById(feedbackDTO.getProductId()).orElse(null);
        Feedback feedback = new Feedback();
        if(currProfile.getTeams().stream().anyMatch(team ->
                team.getId().equals(currProduct.getTeam().getId()))) {
            feedback.setDescription(feedbackDTO.getDescription());
            feedback.setRating(feedbackDTO.getRating());
            feedback.setProduct(productRepository.findById(feedbackDTO.getProductId()).orElse(null));
            feedback.setProfile(profileService.getAuthProfile());
        }
        else {
            throw new RuntimeException("пользователь и данный продукт не в одной комадне");
        }
        return feedback;
    }

    public FeedbackDTO toDto(Feedback feedback) {
        FeedbackDTO feedbackDTO = new FeedbackDTO();
        feedbackDTO.setDescription(feedback.getDescription());
        feedbackDTO.setRating(feedback.getRating());
        feedbackDTO.setProductId(feedback.getProduct().getId());
        feedbackDTO.setProfileId(feedback.getProfile().getId());
        return feedbackDTO;
    }
}
