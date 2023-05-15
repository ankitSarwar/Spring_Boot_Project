package com.geekster.ecommerce.controller;

import com.geekster.ecommerce.model.Address;
import com.geekster.ecommerce.model.Order;
import com.geekster.ecommerce.model.User;
import com.geekster.ecommerce.repository.IAddressRepo;
import com.geekster.ecommerce.service.AddressService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/address")

public class AddressController {

    @Autowired
    AddressService addressService;

    @GetMapping("/getById/{id}")
    Address getAddressById(@PathVariable Integer id)
    {
        return addressService.getAddressById(id);
    }


    @PostMapping("/add")
    ResponseEntity<String> addAddress(@RequestBody Address myAddress)
    {
        String response;
        HttpStatus status;
        try {
            addressService.save(myAddress);
            response = "Your Address of " + myAddress.getAddressName() + " was save...successfully!!!";
            status = HttpStatus.CREATED;
        }
        catch(Exception e)
        {
            response = "Details passed with your Address are invalid!!!!...Please check and reOrder...";
            status = HttpStatus.BAD_REQUEST;
            System.out.println(e);
        }
        return new ResponseEntity<String>(response,status);

    }


}
