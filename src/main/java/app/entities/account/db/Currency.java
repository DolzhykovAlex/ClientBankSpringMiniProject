package app.entities.account.db;

import lombok.Getter;


@Getter
public enum Currency {
    USD("USD"),
    EUR("EUR"),
    UAN("UAN"),
    CHF("CHF"),
    GBP("GBP");

    private final String enumValueToString;

    Currency(String value) {
        this.enumValueToString = value;
    }


}
