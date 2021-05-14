package com.example.onepipeproject.model;

import com.example.onepipeproject.model.audit.DateAudit;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
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

    @NaturalId(mutable = true)
    @NotBlank
    @Size(max = 255)
    @Email
    private String email;

    @JsonIgnore
    private String password;


    private BigDecimal salary;

    private BigDecimal vacationBalance;

    private BigDecimal annualBonus;


    @JsonManagedReference
    @ManyToOne(cascade={CascadeType.ALL})
    @JoinColumn(name="manager_id")
    private User manager;

//    @JsonIgnore
    @JsonBackReference
    @OneToMany(mappedBy="manager")
    private Set<User> employees = new HashSet<User>();



//    @OneToMany(mappedBy = "batch", fetch = FetchType.LAZY)
//    private List<User> employees;
//

//    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
//    private List<Token> tokens;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @JsonIgnore
//    @JsonManagedReference
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY,
                cascade = CascadeType.ALL)
    private Set<SalaryHistory> salaryHistories;


    public User(@NotBlank @Size(max = 255) String firstName,
                @NotBlank @Size(max = 255) String lastName,
                @NotBlank @Size(max = 255)
                @Email String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public User(String firstName,
                String lastName,
                String email,
                String password,
                BigDecimal salary,
                BigDecimal vacationBalance,
                BigDecimal annualBonus) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.salary = salary;
        this.vacationBalance = vacationBalance;
        this.annualBonus = annualBonus;
    }


}
