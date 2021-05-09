package com.example.onepipeproject.model;

import com.example.onepipeproject.model.audit.DateAudit;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "email"
        })
})
@Data
@ToString
@NoArgsConstructor
public class User extends DateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 255)
    private String firstName;

    @NotBlank
    @Size(max = 255)
    private String lastName;

    @NotBlank
    @Size(max = 11)
    private String phoneNo;

    @NaturalId
    @NotBlank
    @Size(max = 255)
    @Email
    private String email;

    @JsonIgnore
    private String password;




//    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
//    private List<Token> tokens;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();




    public User(@NotBlank @Size(max = 255) String firstName,
                @NotBlank @Size(max = 255) String lastName,
                @NotBlank @Size(max = 11) String phoneNo,
                @NotBlank @Size(max = 255)
                @Email String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNo = phoneNo;
        this.email = email;
        this.password = password;
    }
}
