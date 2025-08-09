package com.skillsystem.controller;

import com.skillsystem.dto.SkillDTO;
import com.skillsystem.service.SkillService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/skills")
public class SkillController {

    private final SkillService skillService;

    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    @GetMapping
    public ResponseEntity<List<SkillDTO>> listAll() {
        return ResponseEntity.ok(skillService.listAll());
    }
}