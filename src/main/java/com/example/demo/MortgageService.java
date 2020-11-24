package com.example.demo;

import org.springframework.stereotype.Service;

@Service
public class MortgageService {

    public MortgageService(RateService rateService) {
        this.rateService = rateService;
    }

    private final RateService rateService;

    public MortgageDto calculate(long cost, long downPayment, int years) {
        double interestRate = rateService.getInterestRate();
        long startAmount = cost - downPayment;
        long amount = startAmount;
        for (int i = years; i > 0; --i) {
            amount = (long) (amount * (1 + interestRate));
        }
        var mortgageDto = new MortgageDto();
        mortgageDto.setMonthlyCharge(amount / years / 12);
        mortgageDto.setOverpayment(amount - startAmount);
        return mortgageDto;
    }
}
