package com.skillsystem.controller;

import com.skillsystem.dto.AddUserSkillRequestDTO;
import com.skillsystem.dto.UpdateLevelRequestDTO;
import com.skillsystem.dto.UserSkillDTO;
import com.skillsystem.service.UserSkillService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class UserSkillController {

    private final UserSkillService userSkillService;

    public UserSkillController(UserSkillService userSkillService) {
        this.userSkillService = userSkillService;
    }

    @GetMapping("/users/{userId}/skills")
    public ResponseEntity<List<UserSkillDTO>> listByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(userSkillService.listByUser(userId));
    }

    @PostMapping("/users/{userId}/skills")
    public ResponseEntity<UserSkillDTO> add(@PathVariable Long userId,@RequestBody AddUserSkillRequestDTO req) {
        return ResponseEntity.ok(userSkillService.add(userId, req));
    }

    @PutMapping("/user-skills/{id}")
    public ResponseEntity<UserSkillDTO> updateLevel(@PathVariable Long id,@RequestBody UpdateLevelRequestDTO req) {
        return ResponseEntity.ok(userSkillService.updateLevel(id, req));
    }

    @DeleteMapping("/user-skills/{id}")
    public ResponseEntity<Void> remove(@PathVariable Long id) {
        userSkillService.remove(id);
        return ResponseEntity.noContent().build();
    }
}