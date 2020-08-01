package com.my.steinfield.Steinfield.models.dto;

import com.my.steinfield.Steinfield.models.User;
import com.my.steinfield.Steinfield.models.enuns.Profiles;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class UserDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String name;
    private String email;
    private Set<Profiles> profile = new HashSet<>();

    public UserDTO() {}
    public UserDTO(User currentUser) {
        this.id = currentUser.getId();
        this.email = currentUser.getEmail();
        this.name = currentUser.getName();
        this.profile = currentUser.getProfile();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Profiles> getProfile() {
        return this.profile.stream().map( x -> x).collect(Collectors.toSet());
    }

    public void setProfile(Profiles profiles) {
        this.profile.add(profiles);
    }
}