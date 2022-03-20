package sk.upjs.sk.springsecuritydemo;

import java.math.BigDecimal;

public class MonetaryAmount {
    private BigDecimal amount;

    public MonetaryAmount() {

    }

    public MonetaryAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return amount.toString();
    }
}
