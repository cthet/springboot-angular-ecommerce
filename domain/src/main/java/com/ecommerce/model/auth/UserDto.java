package com.ecommerce.model.auth;


import java.util.Objects;

public class UserDto {

    private Long id;

    private String role;

    public UserDto(Long id, String role) {
        this.id = id;
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto userDto = (UserDto) o;
        return Objects.equals(id, userDto.id) && Objects.equals(role, userDto.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, role);
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", role='" + role + '\'' +
                '}';
    }
}
