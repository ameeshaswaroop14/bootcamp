package com.commerceApp.commerceApp.controllers;

import com.commerceApp.commerceApp.models.AuditRevisionEntity;
import com.commerceApp.commerceApp.models.Customer;
import com.commerceApp.commerceApp.models.MongoInfo;
import com.commerceApp.commerceApp.models.User;
import com.commerceApp.commerceApp.models.product.Product;
import com.commerceApp.commerceApp.repositories.AuditRevisionEntityRepo;
import com.commerceApp.commerceApp.repositories.productRepos.ProductRepository;
import com.commerceApp.commerceApp.repositories.userRepos.CustomerRepository;
import com.commerceApp.commerceApp.repositories.userRepos.UserRepository;
import com.commerceApp.commerceApp.services.ProductService;
import com.commerceApp.commerceApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@Controller
public class ThymeleafController {
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
    @Autowired
    UserRepository userRepository;


    @RequestMapping(value = "/product/all", method = RequestMethod.GET)
    public String getAllProducts(Model model) {
        List<Product> products = productRepository.findAll();
        model.addAttribute("product", products);
        model.addAttribute("byName", Comparator.comparing(Product::getName));
        return "product";
    }

    @RequestMapping(value = "/log/all", method = RequestMethod.GET)
    public String getAllLogs(Model model) {
        List<MongoInfo> mongoInfos = mongoRepository.findAll();
        model.addAttribute("log", mongoInfos);
        model.addAttribute("byDate", Comparator.comparing(MongoInfo::getDate));
        return "log";
    }

    @RequestMapping(path = {"/user", "/user/{offset}/{size}/{sortByField}/{order}", "/user/{offset}/{size}/{sortByField}/{order}/{searchType}/{search}"}, method = RequestMethod.GET)
    public String customersPage(@PathVariable("offset") Optional<String> offset,
                                @PathVariable("size") Optional<String> size,
                                @PathVariable("sortByField") Optional<String> sortByField, @PathVariable("order") Optional<String> order,
                                @PathVariable("searchType") Optional<String> searchType,
                                @PathVariable("search") Optional<String> search,


                                Model model) {
        if (offset.isPresent() && size.isPresent() && sortByField.isPresent() && order.isPresent())

            model.addAttribute("user", userService.getAllUsers(offset, size, sortByField, order));
        if (search.isPresent() && searchType.isPresent())

            model.addAttribute("user", userService.getAllUsers(searchType, search));
        model.addAttribute("user",userService.getAllUser());
        return "user";
    }


}
