package com.example.smartLibrary.dto;

public class MemberRequestDTO {
    private String name;
    private String email;
    public MemberRequestDTO(String name, String email) {
        this.name = name;
        this.email = email;
    }
    public MemberRequestDTO() {
    }
    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    
}
