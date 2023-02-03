package pl.kb.shop.common.email;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class EmailClientService {

    private final Map<String, EmailSender> senderMap;
    @Value("${app.email.sender}")
    private String isFakeProperty;

    public EmailSender getInstance() {
        if(isFakeProperty.equals("fakeEmailService")) {
            return senderMap.get("fakeEmailService");
        }
        return senderMap.get("emailService");
    }

    public EmailSender getInstance(String beanName) {
        if(isFakeProperty.equals("fakeEmailService")) {
            return senderMap.get("fakeEmailService");
        }
        return senderMap.get(beanName);
    }
}
