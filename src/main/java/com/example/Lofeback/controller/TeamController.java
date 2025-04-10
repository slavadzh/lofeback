package com.example.Lofeback.controller;

import com.example.Lofeback.dto.TeamDTO;
import com.example.Lofeback.entity.Team;
import com.example.Lofeback.service.TeamService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/team")
@AllArgsConstructor
public class TeamController {

    private final TeamService teamService;

    @GetMapping
    public List<TeamDTO> getAllTeams() {
        return teamService.getAll();
    }

    @PostMapping("/new")
    public ResponseEntity<?> create(@RequestBody TeamDTO teamDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getFieldError().getDefaultMessage());
        }
        teamService.save(teamDTO);
        return ResponseEntity.ok("team created");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody TeamDTO teamDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getFieldError().getDefaultMessage());
        }
        teamService.update(id, teamDTO);
        return ResponseEntity.ok("team updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        teamService.delete(id);
        return ResponseEntity.ok("team deleted");
    }
}
