package org.example.springexample.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.springexample.DTO.EmployeeDto;
import org.example.springexample.entity.Employee;
import org.example.springexample.entity.Manager;
import org.example.springexample.repository.EmployeeRepository;
import org.example.springexample.repository.ManagerRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmployeeCrudService implements CrudService<EmployeeDto> {
    private final EmployeeRepository employeeRepository1;
    private final ManagerRepository managerRepository2;

    @Override
    public Collection<EmployeeDto> getAll() {
        return employeeRepository1.findAll().stream().map(EmployeeCrudService::mapToDto).toList();
    }

    @Override
    public EmployeeDto getById(Long id) {
        Employee employee = employeeRepository1.findById(id).orElseThrow();
        return mapToDto(employee);
    }

    @Override
    public void create(EmployeeDto employeeDto) {
        Employee employee = mapToEntity(employeeDto);
        Long managerId = employeeDto.getManagerId();
        Manager manager = managerRepository2.findById(managerId).orElseThrow();
        employee.setManager(manager);
        employeeRepository1.save(employee);
    }

    @Override
    public void update(EmployeeDto employeeDto) {
        Employee employee = mapToEntity(employeeDto);
        Long managerId = employeeDto.getManagerId();
        Manager manager = managerRepository2.findById(managerId).orElseThrow();
        employee.setManager(manager);
        employeeRepository1.save(employee);
    }

    @Override
    public void delete(Long id) {
        employeeRepository1.deleteById(id);
    }

    public static EmployeeDto mapToDto(Employee employee) {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(employee.getId());
        employeeDto.setFirstname(employee.getFirstname());
        employeeDto.setLastname(employee.getLastname());
        employeeDto.setPosition(employee.getPosition());
        employeeDto.setDepartment(employee.getDepartment());
        employeeDto.setSalary(employee.getSalary());
        employeeDto.setManagerId(employee.getManager().getId());
        return employeeDto;
    }

    public static Employee mapToEntity(EmployeeDto employeeDto) {
        Employee employee = new Employee();
        employee.setId(employeeDto.getId());
        employee.setFirstname(employeeDto.getFirstname());
        employee.setLastname(employeeDto.getLastname());
        employee.setPosition(employeeDto.getPosition());
        employee.setDepartment(employeeDto.getDepartment());
        employee.setSalary(employeeDto.getSalary());
        return employee;
    }
}