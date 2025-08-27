package com.skillnest.arka.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.skillnest.arka.model.enums.ShipmentStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aspectj.weaver.ast.Or;

import java.time.LocalDateTime;

@Entity
@Table(name = "shipments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Shipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @NotNull(message = "amount cannot be null")
    @Column(nullable = false)
    @Min(value = 0, message = "current amount cannot be negative")
    private Double shippingCost;

    @NotNull(message = "carrier user cannot be null")
    @NotBlank(message = "the carrier of shipment is mandatory")
    @Column(nullable = false)
    private String carrier;

    @Column( unique = true, nullable = false)
    @NotNull(message = "trackingNumber user cannot be null")
    @NotBlank(message = "the trackingNumber of shipment is mandatory")
    private String trackingNumber;

    @Enumerated(EnumType.STRING)
    private ShipmentStatus status;

    private LocalDateTime shippingDate;

    private LocalDateTime estimatedDeliveryDate;

    @JsonBackReference(value = "order_shipment")
    @OneToOne(mappedBy = "shipment", fetch = FetchType.LAZY)
    private Order order;

    @JsonBackReference(value = "shipment_direction")
    @ManyToOne
    @JoinColumn(name = "direction_id")
    private Direction direction;
}
