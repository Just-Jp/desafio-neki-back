package com.skillsystem.service;

import com.skillsystem.dto.SkillDTO;
import com.skillsystem.entity.Skill;
import com.skillsystem.repository.SkillRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillService {

    private final SkillRepository skillRepository;

    public SkillService(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    public List<SkillDTO> listAll() {
        return skillRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    private SkillDTO toDTO(Skill s) {
        return new SkillDTO(s.getId(), s.getName(), s.getDescription(), s.getImageUrl());
    }
}