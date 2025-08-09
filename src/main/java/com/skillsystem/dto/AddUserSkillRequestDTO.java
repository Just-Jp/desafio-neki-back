package com.skillsystem.dto;

public class AddUserSkillRequestDTO {
    private Long skillId;
    private String level;

    public AddUserSkillRequestDTO() {}
    public AddUserSkillRequestDTO(Long skillId, String level) {
        this.skillId = skillId;
        this.level = level;
    }

    public Long getSkillId() {
        return skillId;
    }

    public void setSkillId(Long skillId) {
        this.skillId = skillId;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}