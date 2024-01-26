package org.example.springexample.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.springexample.DTO.ManagerDto;
import org.example.springexample.entity.Manager;
import org.example.springexample.repository.ManagerRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@Slf4j
@RequiredArgsConstructor
public class ManagerCrudService implements CrudService<ManagerDto> {
    private final ManagerRepository managerRepository1;

    @Override
    public Collection<ManagerDto> getAll() {
        return managerRepository1.findAll().stream().map(ManagerCrudService::mapToDto).toList();
    }

    @Override
    public ManagerDto getById(Long id) {
        return mapToDto((managerRepository1.findById(id).orElseThrow()));
    }

    @Override
    public void create(ManagerDto item) {
        managerRepository1.save(mapToEntity(item));
    }

    @Override
    public void update(ManagerDto item) {
        managerRepository1.save(mapToEntity(item));
    }

    @Override
    public void delete(Long id) {
        managerRepository1.deleteById(id);
    }

    public static ManagerDto mapToDto(Manager manager) {
        ManagerDto managerDto = new ManagerDto();
        managerDto.setId(manager.getId());
        managerDto.setFirstname(manager.getFirstname());
        managerDto.setLastname(manager.getLastname());
        managerDto.setAge(manager.getAge());
        managerDto.setEmployees(manager.getEmployees().stream().map(EmployeeCrudService::mapToDto).toList());
        return managerDto;
    }

    public static Manager mapToEntity(ManagerDto managerDto) {
        Manager manager = new Manager();
        manager.setId(managerDto.getId());
        manager.setFirstname(managerDto.getFirstname());
        manager.setLastname(managerDto.getLastname());
        manager.setAge(managerDto.getAge());
        manager.setEmployees(managerDto.getEmployees().stream().map(EmployeeCrudService::mapToEntity).toList());
        return manager;
    }
}