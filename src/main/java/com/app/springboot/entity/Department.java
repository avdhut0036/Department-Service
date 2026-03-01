package com.app.springboot.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long departmentId;

    @NotBlank(message = "Add a valid DepartmentName")
    /*@Length(max = 10, min = 1)
    @Size(max = 5, min = 2)
    @Positive
    @PositiveOrZero
    @Negative
    @NegativeOrZero
    @Future
    @FutureOrPresent
    @Past
    @PastOrPresent
    @Email*/
    private String departmentName;

    private String departmentAddress;

    private String departmentCode;
}
