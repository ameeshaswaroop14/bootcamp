package com.commerceApp.commerceApp.bootloader;

import com.commerceApp.commerceApp.models.*;
import com.commerceApp.commerceApp.models.category.Category;
import com.commerceApp.commerceApp.models.category.CategoryMetadataField;
import com.commerceApp.commerceApp.models.category.CategoryMetadataFieldValues;
import com.commerceApp.commerceApp.models.category.CategoryMetadataFieldValuesId;
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
import java.util.*;


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
        }
        /*
        Category fashion = new Category("fashion");
        Category clothing = new Category("clothing");
        fashion.addSubCategory(clothing);
        Category men = new Category("men");
        Category women = new Category("women");
        clothing.addSubCategory(men);
        clothing.addSubCategory(women);

        categoryRepository.save(fashion);

        System.out.println("total categories saved - "+ categoryRepository.count());

         */


        ProductVariation mSize = new ProductVariation(5, 1500d);
        Map<String, Object> attributes1= new HashMap<>();
        attributes1.put("size", "M-Size");
        attributes1.put("gender", "female");
        mSize.setProductAttributes(attributes1);

        ProductVariation lSize = new ProductVariation(3, 1600d);
        Map<String, Object> attributes2= new HashMap<>();
        attributes2.put("size", "L-Size");
        attributes2.put("gender", "male");
        lSize.setProductAttributes(attributes2);
        Category category = new Category("Clothing");

        Set<Category> categories = new HashSet<>();

        Category category1 = new Category("Men");
        Category category2 = new Category("Women");
        //men
        Category category3 = new Category("Jeans");
        Category category4 = new Category("Shirts");
        Category category5 = new Category("Winter Wear");
        //women
        Category category6 = new Category("Western Wear");
        Category category7 = new Category("Ethnic Wear");
        Category category8 = new Category("Accessories");

        //add to set of categories
        categories.add(category1);
        categories.add(category2);
        categories.add(category3);
        categories.add(category4);
        categories.add(category5);
        categories.add(category6);
        categories.add(category7);
        categories.add(category8);

        category1.setParentCategory(category);
        category2.setParentCategory(category);

        category3.setParentCategory(category1);
        category4.setParentCategory(category1);
        category5.setParentCategory(category1);

        category6.setParentCategory(category2);
        category7.setParentCategory(category2);
        category8.setParentCategory(category2);

        category.setSubCategories(categories);
        category1.setSubCategories(categories);
        category2.setSubCategories(categories);

        Category category9 = new Category("Electronics");

        Set<Category> categories1 = new HashSet<>();

        Category category10 = new Category("Mobile");
        Category category11 = new Category("Laptop");
        //Mobile
        Category category12= new Category("SmartPhones");
        Category category13 = new Category("NormalPhones");
        //Laptop
        Category category15 = new Category("GamingLaptop");
        Category category16 = new Category("NormalLaptop");

        //add to set of categories
        categories1.add(category9);
        categories1.add(category10);
        categories1.add(category12);
        categories1.add(category13);
        categories1.add(category15);
        categories1.add(category16);
        categories1.add(category11);

        category10.setParentCategory(category9);
        category11.setParentCategory(category9);
        category12.setParentCategory(category10);
        category13.setParentCategory(category10);
        category15.setParentCategory(category11);
        category16.setParentCategory(category11);


        category9.setSubCategories(categories1);
        category10.setSubCategories(categories1);
        category11.setSubCategories(categories1);

        categoryRepository.save(category1);
        categoryRepository.save(category2);
        categoryRepository.save(category3);
        categoryRepository.save(category4);
        categoryRepository.save(category5);
        categoryRepository.save(category6);
        categoryRepository.save(category7);
        categoryRepository.save(category8);
        categoryRepository.save(category9);
        categoryRepository.save(category10);
        categoryRepository.save(category11);
        categoryRepository.save(category12);
        categoryRepository.save(category13);
        categoryRepository.save(category15);
        categoryRepository.save(category16);


        Product product1 = new Product("UCB T-Shirt", "comfortable", "UCB");
        product1.setCategory(category4);
        Product product2 = new Product("RedTape Jeans", "slim fit", "RedTape");
        product1.setCategory(category3);
        Product product3 = new Product("Nike shoes", "light weight", "Nike");
        product1.setCategory(category8);

        ///metadata

        CategoryMetadataField categoryMetadataField = new CategoryMetadataField();
        categoryMetadataField.setName("SIZE");
        categoryFieldRepository.save(categoryMetadataField);


        CategoryMetadataField categoryMetadataField1 = new CategoryMetadataField();
        categoryMetadataField1.setName("COLOR");
        categoryFieldRepository.save(categoryMetadataField1);

        CategoryMetadataFieldValuesId categoryMetadataFieldValuesId= new CategoryMetadataFieldValuesId();
        categoryMetadataFieldValuesId.setCategoryId(category4.getId());
        categoryMetadataFieldValuesId.setCategoryMetadataFieldId(categoryMetadataField.getId());

        CategoryMetadataFieldValuesId categoryMetadataFieldValuesId1= new CategoryMetadataFieldValuesId();
        categoryMetadataFieldValuesId1.setCategoryId(category4.getId());
        categoryMetadataFieldValuesId1.setCategoryMetadataFieldId(categoryMetadataField1.getId());


        CategoryMetadataFieldValues categoryMetadataFieldValues= new CategoryMetadataFieldValues();
        String sizeValues = "XS,S,M,L,XL,XXL";
        CategoryMetadataFieldValues fieldValues = new CategoryMetadataFieldValues(sizeValues);

        categoryMetadataFieldValues.setId(categoryMetadataFieldValuesId);
        categoryMetadataFieldValues.setCategoryMetadataField(categoryMetadataField);
        categoryMetadataFieldValues.setCategory(category4);

        CategoryMetadataFieldValues categoryMetadataFieldValues1= new CategoryMetadataFieldValues();
        String colorValues = "grey,Blue,Black";
        CategoryMetadataFieldValues fieldValues1 = new CategoryMetadataFieldValues(colorValues);

        categoryMetadataFieldValues1.setId(categoryMetadataFieldValuesId1);
        categoryMetadataFieldValues1.setCategoryMetadataField(categoryMetadataField1);
        categoryMetadataFieldValues1.setCategory(category4);


        Set<CategoryMetadataFieldValues> categoryMetadataFieldValues2 = new HashSet<>();
        categoryMetadataFieldValues2.add(categoryMetadataFieldValues);
        categoryMetadataFieldValues2.add(categoryMetadataFieldValues1);
        category4.setFieldValues(categoryMetadataFieldValues2);


        productRepository.save(product1);
            /*categoryMetadataFieldValuesRepository.save(categoryMetadataFieldValues);
            categoryMetadataFieldValuesRepository.save(categoryMetadataFieldValues1);
            */productRepository.save(product2);
        productRepository.save(product3);

        //product1.setCategory(men);
        product1.addVariation(mSize);
        product1.addVariation(lSize);

        //seller1.addProduct(product1);

        productRepository.save(product1);


    }

    }


/*

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



