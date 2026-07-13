package com.zomato.auth.entity;

import com.zomato.auth.enums.AccountStatus;
import com.zomato.auth.dto.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SuperBuilder
@Table(name ="user_data")
@SequenceGenerator(sequenceName = "user_seq",initialValue = 1, allocationSize = 10)
public class User extends BaseEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "user_seq")
    private Long id;

    private String firstName;
    private String lastName;
    private String emailId;
    private String phoneNumber;

    private String username;
    private String password;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Address> addresses;

    private Boolean isEmailVerified;
    private Boolean isPhoneVerified;

    private Timestamp lastLoggedInAt;

    private AccountStatus status;

    @ManyToOne
    private Role role;

    @Override
    public  Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.getName()));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }
}
