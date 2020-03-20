package com.springRestful2.SpringRestful2.Controller;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.springRestful2.SpringRestful2.DaoService.UsersDao;
import com.springRestful2.SpringRestful2.ModelClasses.UsersModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@ApiModel(description="Controller class.")
@RestController
public class MyController {
    @Autowired
    MessageSource messageSource;
    @Autowired
    UsersDao usersDao;

    @ApiOperation(value = "To print a welcome message.")
    @GetMapping("/hello")
    public String print() {
        return "Hey you";
    }


    @ApiOperation(value = "to get all users.")
    @GetMapping("/alluser")
    public List<UsersModel> getAllUser(){
        return usersDao.getAll();
    }

    @ApiOperation(value = "To filter all values except id and name.")
    @GetMapping("/filtering")
    public MappingJacksonValue dFilter() {
        List<UsersModel> list = usersDao.getAll();
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id", "name");
        FilterProvider filters = new SimpleFilterProvider().addFilter("ignoring", filter);
        MappingJacksonValue mapping = new MappingJacksonValue(list);
        mapping.setFilters(filters);
        return mapping;
    }

    @ApiOperation(value = "To add a user.")
    @PostMapping("/users")
    public void addUser(@RequestBody UsersModel usersModel) {
        UsersModel usersModel1 = usersDao.addUser(usersModel);
    }

    @ApiOperation(value = "To delete a user, and also link to all employees")
    @GetMapping("/users/delete/{id}")
    public EntityModel<UsersModel> deleteOne(@PathVariable int id) {
        UsersModel usersModel = usersDao.deleteUser(id);
        EntityModel<UsersModel> entityModel=new EntityModel<>(usersModel);
        WebMvcLinkBuilder link=WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllUser());
        entityModel.add(link.withRel("All-Employees"));
        return entityModel;
    }

    @ApiOperation(value = "Internationalize with a morning message.")
    @GetMapping("/i18nDemos/{id}")
    public String shows(@PathVariable int id, @RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        UsersModel usersModel = usersDao.getOneUser(id);
        String param[] = {usersModel.getName()};
        return messageSource.getMessage("mrng.message", param, locale);
    }

    @ApiOperation(value = "Internationalize to a particular user.")
    @GetMapping("/i18nDemo/{id}")
    public String show(@PathVariable int id, @RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        UsersModel usersModel = usersDao.getOneUser(id);
        String param[] = {usersModel.getName()};
        return messageSource.getMessage("user.name", param, locale);
    }
}