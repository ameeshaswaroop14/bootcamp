package com.commerceApp.commerceApp.security;

import com.commerceApp.commerceApp.Models.*;
import com.commerceApp.commerceApp.repositories.UserRepository;
import com.commerceApp.commerceApp.repositories.roleRepository;
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
       if(userRepository.count()<1) {
           PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
           Role admin = new Role(1, "ROLE_ADMIN");
           Role seller = new Role(2, "ROLE_SELLER");
           Role customer = new Role(3, "ROLE_CUSTOMER");
           roleRepository.save(admin);
           roleRepository.save(seller);
           roleRepository.save(customer);
           Admin admin1 = new Admin("amus14119@gmail.com", "Amy", "", "swp");
           admin1.setPassword(passwordEncoder.encode("pass"));
           admin1.addRole(admin);
           admin1.addRole(seller);
           admin1.setActive(true);



           Customer customer1 = new Customer("ameeshaswaroop@gmail.com", "ameesha", "", "swaroop", "8368495191");
           customer1.setPassword(passwordEncoder.encode("pass"));


           Seller seller1 = new Seller("felixkjelberg@gmail.com", "Felix", "", "Kjelberg","bh7ht754r5", "pewds", "9455689412");
           seller1.setPassword(passwordEncoder.encode("pass"));

           userRepository.save(customer1);
           userRepository.save(seller1);
           userRepository.save(admin1);

           System.out.println("Total users saved::"+userRepository.count());





       }
    }
}
