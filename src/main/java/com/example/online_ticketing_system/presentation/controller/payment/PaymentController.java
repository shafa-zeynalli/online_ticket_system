package com.example.online_ticketing_system.presentation.controller.payment;

import com.example.online_ticketing_system.application.dto.payment.PaymentCreateDTO;
import com.example.online_ticketing_system.application.dto.payment.PaymentResponseDTO;
import com.example.online_ticketing_system.application.dto.payment.PaymentUpdateDTO;
import com.example.online_ticketing_system.domain.model.User;
import com.example.online_ticketing_system.domain.service.PaymentService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/payment")
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping
    public List<PaymentResponseDTO> getPayments() {
       return paymentService.getAllByPayments();
    }

    @GetMapping("/{id}")
    public PaymentResponseDTO getPaymentById(@PathVariable("id") Long id) {
        return paymentService.getById(id);
    }

//    @PutMapping("/{id}")
//    public PaymentResponseDTO updatePaymentById(@PathVariable("id") Long id, @RequestBody PaymentUpdateDTO paymentDto) {
//        return paymentService.update(id, paymentDto);
//    }

    @PostMapping
    public PaymentResponseDTO createPayment(@RequestBody PaymentCreateDTO paymentDto) {
        return paymentService.create(paymentDto);
    }

    @DeleteMapping("/{id}")
    public void deletePaymentById(@PathVariable("id") Long id) {
         paymentService.delete(id);
    }
}
