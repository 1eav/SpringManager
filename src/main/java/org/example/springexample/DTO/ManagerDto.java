package org.example.springexample.DTO;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ManagerDto {
    Long id;
    String firstname;
    String lastname;
    Integer age;
    List<EmployeeDto> employees;
}