package pl.kb.shop.common.email;

public interface EmailSender {
    void send(String to, String subject, String msg);
}
