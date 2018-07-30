package model;

import org.springframework.data.annotation.Id;

public class Expence {

    @Id
    private String id;

    private final String amount;

    public Expence(String amount) {
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public String getAmount() {
        return amount;
    }
}
