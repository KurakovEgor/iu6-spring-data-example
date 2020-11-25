package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MortgageController {

    private final MortgageService mortgageService;

    public MortgageController(MortgageService mortgageService) {
        this.mortgageService = mortgageService;
    }

    @GetMapping("/mortgage")
    public MortgageDto getMortgage(@RequestParam long cost, @RequestParam long downPayment, @RequestParam int years) {
        return mortgageService.calculate(cost, downPayment, years);
    }
}
