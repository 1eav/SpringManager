package org.example.springexample.DTO;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployeeDto {
    Long id;
    String firstname;
    String lastname;
    String position;
    String department;
    BigDecimal salary;
    Long managerId;
}