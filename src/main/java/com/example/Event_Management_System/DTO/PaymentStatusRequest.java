package com.example.Event_Management_System.DTO;

import com.example.Event_Management_System.Modal.Payment;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentStatusRequest {
    private Payment.PaymentStatus status;
    private Payment.PaymentMethod method;
    private String referenceNote;

    public Payment.PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(Payment.PaymentStatus status) {
        this.status = status;
    }

    public Payment.PaymentMethod getMethod() {
        return method;
    }

    public void setMethod(Payment.PaymentMethod method) {
        this.method = method;
    }

    public String getReferenceNote() {
        return referenceNote;
    }

    public void setReferenceNote(String referenceNote) {
        this.referenceNote = referenceNote;
    }
}

