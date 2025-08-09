package com.skillsystem.dto;

public class UserSkillDTO {
    private Long id;
    private Long skillId;
    private String skillName;
    private String imageUrl;
    private String description;
    private String level;

    public UserSkillDTO() {
    }

    public UserSkillDTO(Long id, Long skillId, String skillName, String imageUrl, String description, String level) {
        this.id = id;
        this.skillId = skillId;
        this.skillName = skillName;
        this.imageUrl = imageUrl;
        this.description = description;
        this.level = level;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSkillId() {
        return skillId;
    }

    public void setSkillId(Long skillId) {
        this.skillId = skillId;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}