package com.geekster.ecommerce.controller;


import com.geekster.ecommerce.model.Address;
import com.geekster.ecommerce.model.Order;
import com.geekster.ecommerce.model.User;
import com.geekster.ecommerce.repository.IUserRepo;
import com.geekster.ecommerce.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLOutput;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;


    @PostMapping()
   public ResponseEntity<String> addUser(@RequestBody User myUser){

        String response;
        HttpStatus status;

        try{

            List<Address> Addresses = myUser.getUserAddresses();
            for (Address add : Addresses)
            {
                add.setUser(myUser);
            }
            userService.save(myUser);

            response="your user of "+myUser.getUserName()+" was placed successfully";
            status=HttpStatus.CREATED;

        }catch(Exception e){
            response="Details passed with your user are invalid!!!!";
            status=HttpStatus.BAD_REQUEST;

            System.out.println(e);
        }

        return new ResponseEntity<String>(response,status) ;
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id)
    {
        return userService.getUserById(id);
    }

}
