package com.backend.model;

import com.backend.model.enums.Currency;
import com.backend.model.enums.PaymentMethod;
import com.backend.model.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {

    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    

    private String orderId;

    @Column(unique = true, nullable = false)
    private String transactionId;
    
    @Column(nullable = false)
    private double amount;

    @CreationTimestamp
    private Date paymentDate;

    @OneToOne
    @JoinColumn(nullable = false)
    private Booking booking;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentStatus paymentStatus;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentMethod paymentMethod;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Currency currency;

    @PrePersist
    protected void onCreate(){
        this.transactionId = UUID.randomUUID().toString();
    }


    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", transactionId=" + transactionId +
                ", paymentDate=" + paymentDate +
                ", booking=" + booking +
                // ", totalPrice=" + totalPrice +
                ", paymentStatus=" + paymentStatus +
                ", paymentMethod=" + paymentMethod +
                ", currency=" + currency +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return Objects.equals(id, payment.id) && Objects.equals(transactionId, payment.transactionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, transactionId);
    }

}
