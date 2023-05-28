package ru.stomprf.discount51.domain;

public enum Sale {
    FIVE(5),
    SEVEN(7),
    TEN(10),
    FIFTEEN(15),
    TWENTY(20);

    private final int value;

    Sale(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
