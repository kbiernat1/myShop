package pl.kb.shop.order.service.mapper;

import pl.kb.shop.order.model.Order;

import java.time.format.DateTimeFormatter;

public class OrderEmailMsgMapper {

    public static String createEmailMsg(Order order) {
        return "Dziękujemy za zakupy w naszym serwisie.\n" +
                "Twoje zamówienie zostało przekazane do realizacji. Informujemy, że przygotowanie i wysyłka " +
                "towarów trwa około 3 - 4 dni roboczych (z wyłączeniem weekendów i świąt).\n\n" +
                "DANE ZAMÓWIENIA:\n" +
                "Numer zamówienia: " + order.getId() + "\n" +
                "Data zamówienia: " + order.getPlaceDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")) + "\n" +
                "Kwota łączna: " + order.getGrossValue() + " PLN\n" +
                "Sposób płatności: " + order.getPayment().getName() +
                (order.getPayment().getNote() != null ? "\n" + order.getPayment().getNote() : "") +
                "\n\nADRES DOSTAWY:\n" +
                order.getFirst_name() + " " + order.getLast_name() + "\n" +
                order.getStreet() + "\n" +
                order.getZipcode() + " " + order.getCity();
    }
}
