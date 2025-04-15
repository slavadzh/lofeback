package com.example.Lofeback.controller;

import com.example.Lofeback.dto.ProfileDTO;
import com.example.Lofeback.entity.Profile;
import com.example.Lofeback.entity.Team;
import com.example.Lofeback.service.ProfileService;
import com.example.Lofeback.service.TeamService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profile")
@AllArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @GetMapping
    public List<ProfileDTO> getAll() {
        return profileService.getAll();
    }

    @PostMapping("/new")
    public ResponseEntity<?> create(@RequestBody ProfileDTO profileDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getFieldError().getDefaultMessage());
        }
        profileService.save(profileDTO);
        return ResponseEntity.ok("Profile created");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody ProfileDTO profileDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getFieldError().getDefaultMessage());
        }
        profileService.update(id, profileDTO);
        return ResponseEntity.ok("Profile updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        profileService.delete(id);
        return ResponseEntity.ok("Profile deleted");
    }

    @GetMapping("/lk")
    public ProfileDTO getLk() {
        return profileService.getLK();
    }

    @PatchMapping("/addTeam/{team_id}")
    public ResponseEntity<?> addTeam(@PathVariable("team_id") Long teamId) {
        profileService.addTeam(teamId);
        return ResponseEntity.ok("Team added");
    }
}
