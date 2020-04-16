package com.commerceApp.commerceApp.security;

import com.commerceApp.commerceApp.Models.*;
import com.commerceApp.commerceApp.repositories.UserRepository;
import com.commerceApp.commerceApp.repositories.roleRepository;
import org.graalvm.compiler.core.common.type.ArithmeticOpTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements ApplicationRunner {
    @Autowired
    UserRepository userRepository;
    @Autowired
    roleRepository roleRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (userRepository.count() < 1) {
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            Role admin = new Role(1, "ROLE_ADMIN");
            Role seller = new Role(2, "ROLE_SELLER");
            Role customer = new Role(3, "ROLE_CUSTOMER");

            roleRepository.save(admin);
            roleRepository.save(customer);
            roleRepository.save(seller);


            Admin admin1 = new Admin("ameeshaswaroop@gmail.com", "Ameesha", "", "Swaroop");
            admin1.setPassword(passwordEncoder.encode("pass"));
            admin1.addRole(admin);
            admin1.addRole(seller);
            admin1.addAddress(new Address("541", "Allahabad", "U.P", "211002", "India", "home"));
            admin1.setActive(true);


            Customer customer1 = new Customer("amus14119@gmail.com", "customer", "", "customer", "8368495191");
            customer1.setPassword(passwordEncoder.encode("pass"));
            customer1.addAddress(new Address("39B", "Noida", "U.P", "201308", "India", "home"));
            customer1.addAddress(new Address("44", "Lucknow", "U.P", "778884", "india", "office"));
            customer1.setActive(true);

            Seller seller1 = new Seller("ameesha.swaroop@tothenew.com", "seller", "", "seller", "bh7ht754r5", "pewds", "9455689412");
            seller1.setPassword(passwordEncoder.encode("pass"));
            seller1.addAddress(new Address("62", "Noida", "U.P", "201302", "india", "home"));
            seller1.setActive(true);


            userRepository.save(admin1);
            userRepository.save(customer1);
            userRepository.save(seller1);

            System.out.println("Total users saved::" + userRepository.count());


        }

    }
}
