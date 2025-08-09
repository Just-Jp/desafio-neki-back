package com.skillsystem.service;

import com.skillsystem.dto.AddUserSkillRequestDTO;
import com.skillsystem.dto.UpdateLevelRequestDTO;
import com.skillsystem.dto.UserSkillDTO;
import com.skillsystem.entity.Skill;
import com.skillsystem.entity.User;
import com.skillsystem.entity.UserSkill;
import com.skillsystem.exception.BusinessException;
import com.skillsystem.exception.NotFoundException;
import com.skillsystem.repository.SkillRepository;
import com.skillsystem.repository.UserRepository;
import com.skillsystem.repository.UserSkillRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserSkillService {

    private final UserRepository userRepository;
    private final SkillRepository skillRepository;
    private final UserSkillRepository userSkillRepository;

    public UserSkillService(UserRepository userRepository,
                            SkillRepository skillRepository,
                            UserSkillRepository userSkillRepository) {
        this.userRepository = userRepository;
        this.skillRepository = skillRepository;
        this.userSkillRepository = userSkillRepository;
    }

    public List<UserSkillDTO> listByUser(Long userId) {
        User user = getUser(userId);
        return userSkillRepository.findAll().stream()
                .filter(us -> us.getUser().getId().equals(user.getId()))
                .map(this::toDTO)
                .toList();
    }

    @Transactional
    public UserSkillDTO add(Long userId, AddUserSkillRequestDTO req) {
        User user = getUser(userId);
        Skill skill = getSkill(req.getSkillId());

        boolean exists = userSkillRepository.findAll().stream()
                .anyMatch(us -> us.getUser().getId().equals(user.getId())
                        && us.getSkill().getId().equals(skill.getId()));

        if (exists) throw new BusinessException("Skill already associated to user.");

        UserSkill us = new UserSkill(user, skill, req.getLevel());
        UserSkill saved = userSkillRepository.save(us);
        return toDTO(saved);
    }

    @Transactional
    public UserSkillDTO updateLevel(Long userSkillId, UpdateLevelRequestDTO req) {
        UserSkill us = userSkillRepository.findById(userSkillId)
                .orElseThrow(() -> new NotFoundException("UserSkill not found"));

        us.setLevel(req.getLevel());
        return toDTO(userSkillRepository.save(us));
    }

    @Transactional
    public void remove(Long userSkillId) {
        if (!userSkillRepository.existsById(userSkillId)) {
            throw new NotFoundException("UserSkill not found");
        }
        userSkillRepository.deleteById(userSkillId);
    }

    private User getUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));
    }

    private Skill getSkill(Long id) {
        return skillRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Skill not found"));
    }

    private UserSkillDTO toDTO(UserSkill us) {
        return new UserSkillDTO(
                us.getId(),
                us.getSkill().getId(),
                us.getSkill().getName(),
                us.getSkill().getImageUrl(),
                us.getSkill().getDescription(),
                us.getLevel()
        );
    }
}