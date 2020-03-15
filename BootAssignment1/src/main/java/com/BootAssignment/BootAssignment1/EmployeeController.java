package com.BootAssignment.BootAssignment1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class EmployeeController {
    @Autowired
    EmployeeDaoService employeeDaoService;
    @GetMapping("listofall")
    public List<Employee> getAll(){
        return employeeDaoService.findAll();
    }
    @GetMapping("/getone/{id}")
    public Employee getById(@PathVariable int id){
        return employeeDaoService.findSingle(id);
    }
    @PostMapping("/create")
    public void createEmployee(@RequestBody Employee emp){
        Employee employee=employeeDaoService.addEmployee(emp);

    }
    @GetMapping("/valid/{id}")
    public Employee getEmployeeByValidId( @PathVariable int id){
        Employee emp = employeeDaoService.findSingle(id);
        if(emp == null){
            throw new EmployeeNotFoundException("Id:"+id);
        }
        return emp;
    }
    @PutMapping("/Ques8/{id}")
    public void updateEmployee(@PathVariable int id, @RequestBody Employee emp){
        employeeDaoService.updateEmployee(id,emp);
    }

    @PostMapping("/Ques5/{id}")
    public ResponseEntity<Object> createEmployeeApplyValidation(@Valid @RequestBody Employee emp){
        Employee savedEmployee = employeeDaoService.addEmployee(emp);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("{id}")
                .buildAndExpand(savedEmployee.getId())
                .toUri();

        return   ResponseEntity.created(location).build();
    }
}



