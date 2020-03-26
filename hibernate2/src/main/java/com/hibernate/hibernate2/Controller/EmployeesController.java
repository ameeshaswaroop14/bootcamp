package com.hibernate.hibernate2.Controller;

import com.hibernate.hibernate2.Model.EmployeesModel;
import com.hibernate.hibernate2.Repository.EmployeesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeesController {
    @Autowired
    EmployeesRepository employeesRepository;
    List<Object[]> list;
    @GetMapping("/hello")
    public String show(){
        return "hello";
    }
    @GetMapping("/create")
    public void create(){
        EmployeesModel employeesModel=new EmployeesModel();
        employeesModel.setId(001);
        employeesModel.setFirstName("Ankit");
        employeesModel.setLastName("Sagar");
        employeesModel.setAge(23);
        employeesModel.setSalary(15100);
        employeesRepository.save(employeesModel);
        EmployeesModel employeesModel1=new EmployeesModel();
        employeesModel1.setId(002);
        employeesModel1.setFirstName("Neha");
        employeesModel1.setLastName("Gupta");
        employeesModel1.setAge(22);
        employeesModel1.setSalary(12200);
        employeesRepository.save(employeesModel1);
        EmployeesModel employeesModel2=new EmployeesModel();
        employeesModel2.setId(003);
        employeesModel2.setFirstName("Ishika");
        employeesModel2.setLastName("Thakur");
        employeesModel2.setAge(21);
        employeesModel2.setSalary(16000);
        employeesRepository.save(employeesModel2);
        EmployeesModel employeesModel3=new EmployeesModel();
        employeesModel3.setId(004);
        employeesModel3.setFirstName("Ameesha");
        employeesModel3.setLastName("Swaroop");
        employeesModel3.setAge(23);
        employeesModel3.setSalary(14900);
        employeesRepository.save(employeesModel3);
        EmployeesModel employeesModel4=new EmployeesModel();
        employeesModel4.setId(005);
        employeesModel4.setFirstName("Himanshu");
        employeesModel4.setLastName("Bhansali");
        employeesModel4.setAge(25);
        employeesModel4.setSalary(11800);
        employeesRepository.save(employeesModel4);
    }

    @GetMapping("/all")
    public Iterable<EmployeesModel> showed(){
        return employeesRepository.findAll(/*new Sort.Order(Sort.Direction.DESC, "firstName"), new Sort.Order(Sort.Direction.ASC, "salary")*/);
    }

    @GetMapping("/jpql1")
    public List<Object[]> showDetails() {
        Sort sort = Sort.by(Sort.Order.asc("age"), Sort.Order.desc("salary"));
        List<Object[]> objects = employeesRepository.partialSelect(PageRequest.of(0, 3, sort));
        for (Object[] objects1 : objects) {
            System.out.print(objects1[0] + " ");
            System.out.print(objects1[1] + " ");
            System.out.println();
        }
        return objects;
    }

    @GetMapping("/jpql2")
    public void updateSal(){
        employeesRepository.updateSalary(19999, employeesRepository.avgsal());
    }

    @GetMapping("/jpql3")
    public void deleteSal(){
        employeesRepository.deleteSalary(employeesRepository.minsal());
    }

    @GetMapping("/native1")
    public void findSingh(){
        List<Object[]> objects=employeesRepository.findSingh();
        for (Object[] objects1 : objects) {
            System.out.print("id: "+objects1[0] + " name: "+objects1[1] + " age: "+objects1[2]);
            System.out.println();
        }
    }

    @GetMapping("/native2")
    public void deleteOld(){
        employeesRepository.deleteOld(45);
    }


}