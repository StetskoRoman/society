package com.rv.society.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

@Entity
@Data
@Table(name = "usr")
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @NotBlank(message = "username can`t be empty")
    private String username;

    @NotNull
    @NotBlank(message = "password can`t be empty")
    private String password;

    @Transient
    @NotBlank(message = "password confirmation can`t be empty")
    private String password2;
    private boolean active;

    @Email(message = "email is`t correct")
    private String email;
    private String activationCode;

    //создать табличку user_role куда загрузить все роли и добавить колонку user_id
    //а вообще сделал бы репозиторий для ролей как нормальный чел в repos и не запарывался бы
//    @ManyToMany(cascade = CascadeType.ALL)
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    public boolean isAdmin() {
        return roles.contains(Role.ADMIN);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isActive();
    }
}
