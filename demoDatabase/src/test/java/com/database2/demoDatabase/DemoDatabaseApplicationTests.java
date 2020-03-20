package com.database2.demoDatabase;

import com.sun.xml.bind.v2.model.core.ID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class DemoDatabaseApplicationTests {
	@Autowired
	EmployeeRepository employeeRepository;
	@Test
	void contextLoads() {
	}
		@Test
	public void createUser() {
		Employee employee = new Employee();
		employee.setEmployeeid(1);
		employee.setFirstname("Chandler");
		employee.setLastname("Bing");
		employee.setAge(22);
		employee.setLocation("Yemen");
		employeeRepository.save(employee);

		Employee employee1 = new Employee();
		employee1.setEmployeeid(2);
		employee1.setFirstname("Monica");
		employee1.setLastname("Geller");
		employee1.setAge(22);
		employee1.setLocation("Greenwich Village");
		employeeRepository.save(employee1);

		Employee employee2 = new Employee();
		employee2.setEmployeeid(3);
		employee2.setFirstname("Rachel");
		employee2.setLastname("Green");
		employee2.setAge(22);
		employee2.setLocation("New York");
		employeeRepository.save(employee2);

		Employee employee3 = new Employee();
		employee3.setEmployeeid(4);
		employee3.setFirstname("Ross");
		employee3.setLastname("Geller");
		employee3.setAge(22);
		employee3.setLocation("Greenwich Village");
		employeeRepository.save(employee3);

		Employee employee4 = new Employee();
		employee4.setEmployeeid(5);
		employee4.setFirstname("Pheobe");
		employee4.setLastname("Buffay");
		employee4.setAge(22);
		employee4.setLocation("Authority Bus Terminal");
		employeeRepository.save(employee4);

		Employee employee5 = new Employee();
		employee5.setEmployeeid(6);
		employee5.setFirstname("Joey");
		employee5.setLastname("Tribbiani");
		employee5.setAge(22);
		employee5.setLocation("Queens");
		employeeRepository.save(employee5);

	}

		@Test
	public void update(){
	Employee employee=employeeRepository.findById(3).get();
	employee.setLocation("Paris");
	employeeRepository.save(employee);

	}
	@Test
	public void delete(){
		employeeRepository.deleteById(2);
	}
	@Test
	public void testRead(){
		Employee employee=employeeRepository.findById(3).get();
		Assertions.assertNotNull(employee);
		Assertions.assertEquals("Joey",employee.getFirstname());
	}

	@Test
	public void testCount(){
		System.out.println("/////////////////////////////////////////////////////////"+employeeRepository.count());

	}
	@Test
	public void sortAge(){
		Pageable pageable= PageRequest.of(0,2, Sort.Direction.DESC, "age");
		employeeRepository.findAll(pageable).forEach(p-> System.out.println(p.getFirstname()));
	}
	@Test
	public void testFindByName()
	{
		List<Employee> employee=employeeRepository.findByName("Chandler");
		employee.forEach(p-> System.out.println(p.getAge()));
	}
	@Test
	public void testFindNameLike()
	{
		List<Employee> employee=employeeRepository.findByNameLike("A%");
		employee.forEach(p-> System.out.println(p.getFirstname()));
	}

	@Test
	public void testFindAgeBetween()
	{
		List<Employee> employee=employeeRepository.findByAgeBetween(18,22);
		employee.forEach(p-> System.out.println(p.getFirstname()));
	}


}
