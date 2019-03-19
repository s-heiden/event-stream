package com.heiden.controller;


import com.heiden.domain.Customer;
import com.heiden.service.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(
        value = "/customer",
        description = "Most Recent Customer",
        produces = "application/json"
)
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @ApiOperation(value="get customer",response=Customer.class)
    @RequestMapping(path = "/customer", method = RequestMethod.GET)
    public Customer getLatest() {
        return customerService.getLatest();
    }

}

