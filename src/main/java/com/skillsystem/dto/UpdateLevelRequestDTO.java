package com.skillsystem.dto;

public class UpdateLevelRequestDTO {
    private String level;

    public UpdateLevelRequestDTO() {}

    public UpdateLevelRequestDTO(String level) {
        this.level = level;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}