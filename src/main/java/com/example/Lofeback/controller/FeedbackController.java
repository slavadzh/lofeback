package com.example.Lofeback.controller;

import com.example.Lofeback.dto.FeedbackDTO;
import com.example.Lofeback.entity.Feedback;
import com.example.Lofeback.service.FeedbackService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/feedback")
@AllArgsConstructor
public class FeedbackController {

    private final FeedbackService feedbackService;

    @PostMapping("/new")
    public ResponseEntity<?> create(@RequestBody FeedbackDTO feedbackDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getFieldError().getDefaultMessage());
        }

        feedbackService.save(feedbackDTO);
        return ResponseEntity.ok("feedback created");
    }
}
