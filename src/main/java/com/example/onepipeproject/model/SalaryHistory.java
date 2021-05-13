package com.example.onepipeproject.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@AllArgsConstructor
@Data
@Entity
@Table(name = "salary_history")
public class SalaryHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private BigDecimal salary;

    public SalaryHistory(User user, BigDecimal salary) {
        this.user = user;
        this.salary = salary;
    }
}
