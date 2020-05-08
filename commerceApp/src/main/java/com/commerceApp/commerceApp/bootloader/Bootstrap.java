package com.commerceApp.commerceApp.bootloader;

import com.commerceApp.commerceApp.models.*;
import com.commerceApp.commerceApp.models.category.Category;
import com.commerceApp.commerceApp.models.category.CategoryMetadataField;
import com.commerceApp.commerceApp.models.category.CategoryMetadataFieldValues;
import com.commerceApp.commerceApp.models.order.OrderProduct;
import com.commerceApp.commerceApp.models.product.Product;
import com.commerceApp.commerceApp.models.product.ProductVariation;
import com.commerceApp.commerceApp.repositories.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/*
@Component
public class Bootstrap implements ApplicationRunner {
    @Autowired
    UserRepository userRepository;
    @Autowired
    roleRepository roleRepository;
   @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryMetadataFieldValueRepo categoryMetadataFieldValueRepo;
    @Autowired
    CategoryFieldRepository categoryFieldRepository;

    private static final Logger logger = LoggerFactory.getLogger(Bootstrap.class);


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
            admin1.setCreatedBy("AmeeshaSwaroop");
           // admin1.setCreationTime(new ZonedDateTime());
            //admin1.setModifiedDate(ZonedDateTime);
            admin1.setModifiedBy("mees");



            Customer customer1 = new Customer("amus14119@gmail.com", "customer", "", "customer", "8368495191");
            customer1.setPassword(passwordEncoder.encode("pass"));
            customer1.addAddress(new Address("39B", "Noida", "U.P", "201308", "India", "home"));
            customer1.addAddress(new Address("44", "Lucknow", "U.P", "778884", "india", "office"));
            customer1.setActive(true);

            Seller seller1 = new Seller("ameesha.swaroop@tothenew.com", "seller", "", "seller", "18AABCT3518Q1ZV", "pewds", "9455689412");
            seller1.setPassword(passwordEncoder.encode("pass"));
            seller1.addAddress(new Address("62", "Noida", "U.P", "201302", "india", "home"));
            seller1.setActive(true);


            userRepository.save(admin1);
            userRepository.save(customer1);
            userRepository.save(seller1);


            Category fashion = new Category("fashion");
            Category clothing = new Category("clothing");
            fashion.addSubCategory(clothing);
            Category men = new Category("Men");


            Category women = new Category("Women");
            clothing.addSubCategory(men);
            clothing.addSubCategory(women);
            categoryRepository.save(fashion);
            Product shirt = new Product("Shirt", "Check based design", "Levi's");


            Product jeans = new Product("Jeans", "slim fit", "Crimson club");

            shirt.setId(100L);
            jeans.setId(101L);
            productRepository.save(shirt);
            productRepository.save(jeans);
          //  System.out.println("total categories saved - " + categoryRepository.count());
            logger.info(String.valueOf(categoryRepository.count()));


            ProductVariation mSize = new ProductVariation(5, 1500d);
            Map<String, String> attributes1 = new HashMap<>();
            attributes1.put("size", "M");
            attributes1.put("section", "female");
            mSize.setProductAttributes(attributes1);

            ProductVariation lSize = new ProductVariation(3, 1600d);
            Map<String, String> attributes2 = new HashMap<>();
            attributes2.put("size", "L");
            attributes2.put("section", "male");
            lSize.setProductAttributes(attributes2);


            shirt.setCategory(men);
            shirt.addVariation(mSize);
            shirt.addVariation(lSize);
            seller1.addProduct(shirt);
            productRepository.save(shirt);

            jeans.setCategory(women);
            jeans.addVariation(mSize);
            jeans.addVariation(lSize);
            seller1.addProduct(jeans);
            productRepository.save(jeans);


            String sizeValues = "XS,S,M,L,XL,XXL";
            CategoryMetadataFieldValues fieldValues = new CategoryMetadataFieldValues(sizeValues);
            String colorValues = "red, green, yellow";
            CategoryMetadataFieldValues fieldValues1 = new CategoryMetadataFieldValues(colorValues);

            CategoryMetadataField sizeField = new CategoryMetadataField("size");
            CategoryMetadataField colorField = new CategoryMetadataField("color");


            Category kids = new Category("kids");

            Category clothing1 = categoryRepository.findByName("clothing");
            clothing1.addSubCategory(kids);
            categoryRepository.save(clothing1);

            kids = categoryRepository.findByName("kids");
            kids.addFieldValues(fieldValues);
            kids.addFieldValues(fieldValues1);

            sizeField.addFieldValues(fieldValues);
            categoryFieldRepository.save(sizeField);
            colorField.addFieldValues(fieldValues1);


            fieldValues.setCategoryMetadataField(sizeField);
            fieldValues.setCategory(kids);
            categoryMetadataFieldValueRepo.save(fieldValues);
            categoryMetadataFieldValueRepo.save(fieldValues1);


        }
    }
}

 */

