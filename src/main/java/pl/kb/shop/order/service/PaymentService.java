package pl.kb.shop.order.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.kb.shop.order.model.Payment;
import pl.kb.shop.order.repository.PaymentRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public List<Payment> getPayments() {
        return paymentRepository.findAll();
    }
}
