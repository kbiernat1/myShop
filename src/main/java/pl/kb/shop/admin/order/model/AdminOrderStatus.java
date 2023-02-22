package pl.kb.shop.admin.order.model;

public enum AdminOrderStatus {
    NEW("nowe"),
    PAID("opłacone"),
    PROCESSING("przetwarzane"),
    WAITING_FOR_DELIVERY("czeka na dostawę"),
    COMPLETED("zrealizowane"),
    CANCELED("anulowane"),
    REFUND("zwrócone");

    private String value;

    AdminOrderStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
