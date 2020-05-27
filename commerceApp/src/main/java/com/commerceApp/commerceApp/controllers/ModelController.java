package com.commerceApp.commerceApp.controllers;

import com.commerceApp.commerceApp.models.AuditRevisionEntity;
import com.commerceApp.commerceApp.models.Customer;
import com.commerceApp.commerceApp.models.MongoInfo;
import com.commerceApp.commerceApp.models.User;
import com.commerceApp.commerceApp.models.product.Product;
import com.commerceApp.commerceApp.repositories.AuditRevisionEntityRepo;
import com.commerceApp.commerceApp.repositories.productRepos.ProductRepository;
import com.commerceApp.commerceApp.repositories.userRepos.CustomerRepository;
import com.commerceApp.commerceApp.services.ProductService;
import com.commerceApp.commerceApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Comparator;
import java.util.List;


@Controller
public class ModelController {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    UserService userService;
    @Autowired
    ProductService productService;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    AuditRevisionEntityRepo auditRevisionEntityRepo;
    @Autowired
    MongoRepository mongoRepository;
    @RequestMapping(value = "/customer/all", method = RequestMethod.GET)
    public String getAllCustomers(Model model){
      List<Customer> customers=customerRepository.findAll();
      model.addAttribute("customer",customers);
      return "customer";
    }
    @RequestMapping(value = "/user/all", method = RequestMethod.GET)
    public String getAllUsers(Model model,@RequestParam(defaultValue = "0")String offset,@RequestParam(defaultValue = "3") String size,
                              @RequestParam(defaultValue = "id") String sortByField,
                              @RequestParam(defaultValue = "descending") String order){
        List<User>users=userService.getAllUsers(offset, size, sortByField, order);
        model.addAttribute("byEmail", Comparator.comparing(User::getEmail));
        model.addAttribute("user",users);

        return "user";
    }
    @RequestMapping(value = "/product/all", method = RequestMethod.GET)
    public String getAllProducts(Model model){
        List<Product>products=productRepository.findAll();
        model.addAttribute("product",products);
        model.addAttribute("byName",Comparator.comparing(Product::getName));
        return "product";
    }
    @RequestMapping(value = "/log/all", method = RequestMethod.GET)
    public String getAllLogs(Model model){
       List<MongoInfo>mongoInfos=mongoRepository.findAll();
       model.addAttribute("log",mongoInfos);
       model.addAttribute("byDate",Comparator.comparing(MongoInfo::getDate));
       return "log";
    }



}
