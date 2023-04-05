package pl.kb.shop.common.model;

public enum OrderStatus {
    NEW("nowe"),
    PAID("opłacone"),
    PROCESSING("przetwarzane"),
    WAITING_FOR_DELIVERY("czeka na dostawę"),
    COMPLETED("zrealizowane"),
    CANCELED("anulowane"),
    REFUND("zwrócone");

    private String value;

    OrderStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
