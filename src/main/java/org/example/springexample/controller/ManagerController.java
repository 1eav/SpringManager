package org.example.springexample.controller;

import lombok.RequiredArgsConstructor;
import org.example.springexample.entity.Manager;
import org.example.springexample.repository.ManagerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/managers")
@RequiredArgsConstructor
public class ManagerController {
    private final ManagerRepository managerRepository;
    @GetMapping
    public ResponseEntity<List<Manager>> getAllManagers() {
        List<Manager> managers = managerRepository.findAll();
        return new ResponseEntity<>(managers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Manager> getManagerById(@PathVariable Long id) {
        Manager manager = managerRepository.findById(id).orElse(null);
        if (manager != null) {
            return new ResponseEntity<>(manager, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(manager, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Manager> createManagers(@RequestBody Manager manager) {
        Manager createManager = managerRepository.save(manager);
        return new ResponseEntity<>(createManager, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Manager> updateManagers(@RequestBody Manager manager) {
        if (managerRepository.existsById(manager.getId())) {
            Manager updateManager = managerRepository.save(manager);
            return new ResponseEntity<>(updateManager, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(manager, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteManager(@PathVariable Long id) {
        if (managerRepository.existsById(id)) {
            managerRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}