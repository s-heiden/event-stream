package com.heiden.controller;


import com.heiden.domain.Customer;
import com.heiden.domain.CustomerBalance;
import com.heiden.service.TransactionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(
        value = "/transaction",
        description = "Gets a list of customers and their balance",
        produces = "application/json"
)
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @RequestMapping(
            path = "/transaction",
            method = RequestMethod.GET
    )
    @ApiOperation(
            value = "get transaction",
            response = Customer.class
    )
    public List<CustomerBalance> getLatest() {
        return transactionService.getCustomerBalances();
    }

}

