package com.springRestful2.SpringRestful2.DaoService;

import com.springRestful2.SpringRestful2.ModelClasses.UsersModel;
import io.swagger.annotations.ApiModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@ApiModel(description="User service and dao class.")
@Component
public class UsersDao {
    private List<UsersModel> usersModelList=new ArrayList<>();
    int count=usersModelList.size();
    UsersDao(){
        usersModelList.add(new UsersModel(001, "ameesha", 23, "ttn"));
        usersModelList.add(new UsersModel(002, "Monica", 22, "ttn"));
        usersModelList.add(new UsersModel(003, "Joey", 21,"ttn"));
        usersModelList.add(new UsersModel(004, "Pheobe", 20,"ttn"));
        usersModelList.add(new UsersModel(005, "Ross", 25,"ttn"));
    }

    public List<UsersModel> getAll(){
        return usersModelList;
    }


    public UsersModel getOneUser(int id){
        Iterator<UsersModel> iterator=usersModelList.iterator();
        while (iterator.hasNext()){
            UsersModel usersModel= iterator.next();
            if(usersModel.getId()==id){
                return usersModel;
            }
        }
        return null;
    }


    public UsersModel addUser(UsersModel usersModel){
        if((Integer)usersModel.getId()==null)
            usersModel.setId(count++);
        else{
            usersModelList.add(usersModel);
        }
        return usersModel;
    }
    public UsersModel deleteUser( int id){
        Iterator<UsersModel> iterator=usersModelList.iterator();
        while (iterator.hasNext()){
            UsersModel usersModel= iterator.next();
            if(usersModel.getId()==id){
                    iterator.remove();
                    return usersModel;
                }
        }
            return null;
    }
}
