package com.card91.closedloopsystem.restcontroller;

import com.card91.closedloopsystem.entity.Request;
import com.card91.closedloopsystem.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/banks/")
public class BankController {
    private BankService bankService;

    @Autowired
    public BankController(BankService bankService) {
        this.bankService = bankService;
    }

    @PostMapping("{bankId}/cards")
    public String issueCard(
            @RequestBody Request request
            ) {

    }

}
