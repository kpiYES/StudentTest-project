package com.app.dto;

import com.app.model.Role;

public class UserDTO {

    private Long id;
    private Role role;
    private String firstName;
    private String lastName;
    private String mail;

    public UserDTO(Long id, Role role, String firstName, String lastName, String mail) {
        this.id = id;
        this.role = role;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mail = mail;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMail() {
        return mail;
    }

    public Role getRole() {
        return role;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private Role role;
        private String firstName;
        private String lastName;
        private String mail;

        private Builder() {
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder role(Role role) {
            this.role = role;
            return this;
        }

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder mail(String mail) {
            this.mail = mail;
            return this;
        }

        public UserDTO build() {
            return new UserDTO(id, role, firstName, lastName, mail);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserDTO)) return false;

        UserDTO userDTO = (UserDTO) o;

        if (id != null ? !id.equals(userDTO.id) : userDTO.id != null) return false;
        if (role != null ? !role.equals(userDTO.role) : userDTO.role != null) return false;
        if (firstName != null ? !firstName.equals(userDTO.firstName) : userDTO.firstName != null) return false;
        if (lastName != null ? !lastName.equals(userDTO.lastName) : userDTO.lastName != null) return false;
        return mail != null ? mail.equals(userDTO.mail) : userDTO.mail == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (mail != null ? mail.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "fistName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", mail='" + mail + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
