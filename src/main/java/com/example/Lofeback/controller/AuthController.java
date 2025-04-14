package com.example.Lofeback.controller;

import com.example.Lofeback.dto.AuthDTO;
import com.example.Lofeback.dto.ProfileDTO;
import com.example.Lofeback.security.JWTUtil;
import com.example.Lofeback.service.ProfileService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private final ProfileService profileService;
    private final JWTUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/regist")
    public Map<String, String> registerProfile(@RequestBody ProfileDTO profileDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return Map.of("error", bindingResult.getFieldError().getDefaultMessage());
        }
        profileService.register(profileDTO);
        String token = jwtUtil.generateToken(profileDTO.getLogin());
        return Map.of("jwt-token", token);
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody AuthDTO authDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return Map.of("error", bindingResult.getFieldError().getDefaultMessage());
        }
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authDTO.getLogin(), authDTO.getPassword()
        ));
        String token = jwtUtil.generateToken(authDTO.getLogin());
        return Map.of("jwt-token", token);
    }
}
