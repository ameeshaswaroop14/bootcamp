package com.BootAssignment.BootAssignment1;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
@Component
public class EmployeeDaoService {
    private static int idCounter = 5;
    private static List<Employee> emp = new ArrayList<>();

    static {
        emp.add(new Employee(1, "Chandler"));
        emp.add(new Employee(2, "Monica"));
        emp.add(new Employee(3, "Joey"));
        emp.add(new Employee(4, "Rachel"));
        emp.add(new Employee(5, "Ross"));
        emp.add(new Employee(6, "Pheobe"));
    }

    public List<Employee> findAll() {
        return emp;
    }

    public Employee addEmployee(Employee employee) {
        if (employee.getId() == null) {
            employee.setId(++idCounter);
        }
        emp.add(employee);
        return employee;
    }


    public Employee findSingle(int id) {
        for (Employee employee : emp) {
            if (employee.getId() == id) {
                return employee;
            }
        }
        return null;
    }


    public Employee deleteEmployeeByID(int id) {
        Iterator<Employee> employeeIterator = emp.iterator();
        while (employeeIterator.hasNext()) {
            Employee emp = employeeIterator.next();
            if (emp.getId() == id) {
                employeeIterator.remove();
            }
        }
        return null;
    }

    public void updateEmployee(int id, Employee employee) {
        Iterator<Employee> employeeIterator = emp.iterator();
        while (employeeIterator.hasNext()) {
            Employee emp = employeeIterator.next();
            if (emp.getId() == id) {
                emp.setId(employee.getId());
                emp.setName(employee.getName());
            }
        }


    }
}
