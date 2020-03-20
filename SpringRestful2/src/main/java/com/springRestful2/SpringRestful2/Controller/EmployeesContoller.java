package com.springRestful2.SpringRestful2.Controller;


import com.springRestful2.SpringRestful2.ModelClasses.EmployeesV1;
import com.springRestful2.SpringRestful2.ModelClasses.EmployeesV2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeesContoller {
   /* @Autowired
    EmployeesV1 employeesV1;
    @Autowired
    EmployeesV2 employeesV2;*/

    //using URI versioning
    @GetMapping("/Employees/v1")
    public EmployeesV1 getEmployeesV1(){
        return new EmployeesV1(001, "ameesha", "ttn");
    }

    //using URI versioning
    @GetMapping("/Employees/v2")
    public EmployeesV2 getEmployeesV2(){
        return new EmployeesV2(001, "ameesha","ameesha.swaroop@tothenew.com",  "ttn");
    }

    //using Request versioning
    @GetMapping(value = "/Employees/param", params="version=1")
    public EmployeesV1 paramV1(){
        return new EmployeesV1(001, "ameesha", "ttn");
    }

    //using Request versioning
    @GetMapping(value = "/Employees/param", params = "version=2")
    public EmployeesV2 paramV2(){
        return new EmployeesV2(001, "ameesha","ameesha.swaroop@tothenew.com",  "ttn");
    }

    //using Header versioning
    @GetMapping(value = "/Employees/header", headers="hver=1")
    public EmployeesV1 headerV1(){
        return new EmployeesV1(001, "ameesha", "ttn");
    }

    //using Header versioning
    @GetMapping(value = "/Employees/header", headers = "hver=2")
    public EmployeesV2 headerV2(){
        return new EmployeesV2(001, "ameesha","ameesha.swaroop@tothenew.com",  "ttn");
    }

    //using Producer versioning
    @GetMapping(value = "/Employees/param", produces="application/v1+json")
    public EmployeesV1 mimeV1(){
        return new EmployeesV1(001, "ameesha", "ttn");
    }

    //using Header versioning
    @GetMapping(value = "/Employees/param", produces = "application/v2+json")
    public EmployeesV2 mimeV2(){
        return new EmployeesV2(001, "ameesha","ameesha.swaroop@tothenew.com",  "ttn");
    }
}
