package com.example.demo;

public class MortgageDto {

    private long monthlyCharge;
    private long overpayment;

    public long getMonthlyCharge() {
        return monthlyCharge;
    }

    public void setMonthlyCharge(long monthlyCharge) {
        this.monthlyCharge = monthlyCharge;
    }

    public long getOverpayment() {
        return overpayment;
    }

    public void setOverpayment(long overpayment) {
        this.overpayment = overpayment;
    }
}
