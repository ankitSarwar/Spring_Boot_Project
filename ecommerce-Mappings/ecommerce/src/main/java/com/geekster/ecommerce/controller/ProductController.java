package com.geekster.ecommerce.controller;

import com.geekster.ecommerce.model.Product;
import com.geekster.ecommerce.model.User;
import com.geekster.ecommerce.repository.IProductRepo;
import com.geekster.ecommerce.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping()
    ResponseEntity<String> addProduct(@RequestBody Product myProduct){

        String response;
        HttpStatus status;

        try{
            productService.save(myProduct);
            response="Your Product of"+myProduct.getProductName()+"was add successfully";
            status=HttpStatus.CREATED;
        }catch(Exception e){
            response="Details of your Product is Invalid!!!!";
            status=HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<String>(response,status);
    }



    @GetMapping("/category/{category}")
    List<Product> getProductsByCategoryName(@PathVariable String category)
    {
        return productService.findByProductCategory(category);
    }

    @DeleteMapping("/{productId}") // first delete address then delete user
    ResponseEntity<Void> removeProductById(@PathVariable int productId)
    {  // delete from address where address_id in (2,3);
       // delete from User where user_id=2;

        HttpStatus status;
        try {
            productService.removeProductById(productId);
            status = HttpStatus.OK;
        }
        catch(Exception e)
        {
            System.out.println(e);
            status = HttpStatus.NOT_ACCEPTABLE;
        }

        return new ResponseEntity<Void>(status);
    }
}
