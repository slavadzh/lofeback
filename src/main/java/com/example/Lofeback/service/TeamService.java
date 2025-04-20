package com.example.Lofeback.service;

import com.example.Lofeback.dto.TeamDTO;
import com.example.Lofeback.entity.Profile;
import com.example.Lofeback.entity.Team;
import com.example.Lofeback.repository.ProfileRepository;
import com.example.Lofeback.repository.TeamRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class TeamService {

    private final TeamRepository teamRepository;
    private final ProfileRepository profileRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public void save(TeamDTO teamDTO) {
        teamRepository.save(toEntity(teamDTO));
    }

    public List<TeamDTO> getAll() {
        return teamRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    public Team getById(Long id) {
        return teamRepository.findById(id).orElse(null);
    }

    @Transactional
    public void update(Long id, TeamDTO teamDTO) {
        var team = getById(id);
        team.setName(teamDTO.getName());
        team.setDescription(teamDTO.getDescription());
        teamRepository.save(team);
    }

    @Transactional
    public void delete(Long id) {
        teamRepository.deleteById(id);
    }


    private Team toEntity(TeamDTO teamDTO) {
        var team = modelMapper.map(teamDTO, Team.class);
        if (teamDTO.getProfileIds() != null) {
            Set<Profile> profiles = new HashSet<>(profileRepository.findAllById(teamDTO.getProfileIds()));
            team.setProfiles(profiles);
        }
        return team;
    }

    private TeamDTO toDto(Team team) {
        TeamDTO dto = modelMapper.map(team, TeamDTO.class);
        Set<Long> profileIds = team.getProfiles().stream()
                .map(Profile::getId)
                .collect(Collectors.toSet());
        dto.setProfileIds(profileIds);
        return dto;
    }

    public List<Team> findAllById(Set<Long> teamIds) {
        return teamRepository.findAllById(teamIds);
    }
}
