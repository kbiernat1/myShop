package pl.kb.shop.admin.order.service;

import pl.kb.shop.admin.order.model.AdminOrderStatus;

public class AdminOrderEmailMessage {
    public static String createProcessingEmailMessage(Long id, AdminOrderStatus newStatus) {
        return "Twoje zamówienie: " + id + " jest w trakcie przetwarzania." +
                "\nJego status został zmieniony na: " + newStatus.getValue() +
                "\nPo skompletowaniu zamówienia przekażemy je do wysyłki" +
                "\n Kamilla With Magic";
    }

    public static String createCompletedEmailMessage(Long id, AdminOrderStatus newStatus) {
        return "Twoje zamówienie: " + id + " zostało zrealizowane." +
                "\nStatus twojego zamówienia został zmieniony na: " + newStatus.getValue() +
                "\n\n Dziekujemy za zakupy i zapraszamy ponownie!" +
                "\n Kamilla With Magic";
    }

    public static String createRefundEmailMessage(Long id, AdminOrderStatus newStatus) {
        return "Twoje zamówienie: " + id + " zostało zwrócone." +
                "\nStatus twojego zamówienia został zmieniony na: " + newStatus.getValue() +
                "\n Kamilla With Magic";
    }
}
