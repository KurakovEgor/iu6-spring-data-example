package com.example.demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class MortgageServiceTest {

	private RateService rateService;

	private MortgageService mortgageService;

	@BeforeEach
	void init() {
		rateService = Mockito.mock(RateService.class);
		mortgageService = new MortgageService(rateService);
	}

	@Test
	void test() {
		Mockito.when(rateService.getInterestRate()).thenReturn(0.01);
		var mortageDto = mortgageService.calculate(100000000, 10000000, 1);
		Assertions.assertEquals(7575000, mortageDto.getMonthlyCharge());
		Assertions.assertEquals(900000, mortageDto.getOverpayment());
	}

}
