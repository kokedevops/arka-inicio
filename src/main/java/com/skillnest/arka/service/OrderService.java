package com.skillnest.arka.service;

import com.skillnest.arka.dto.OrderDTO;
import com.skillnest.arka.mapper.OrderDetailMapper;
import com.skillnest.arka.mapper.OrderMapper;
import com.skillnest.arka.model.*;
import com.skillnest.arka.model.enums.CartStatus;
import com.skillnest.arka.model.enums.OrderStatus;
import com.skillnest.arka.repository.OrderDetailRepository;
import com.skillnest.arka.repository.OrderRepository;
import com.skillnest.arka.repository.ShopingCartRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final PymentMethodService pymentMethodService;
    private final OrderMapper orderMapper;
    private final OrderDetailMapper orderDetailMapper;
    private  final ShopingCartRepository shopingCartRepository;
    private final ShimpmentService shimpmentService;

    @Transactional
    public OrderDTO createOrderFromCart(Long cartId, Long paymentMethodId, Long shippingAddressId) {
        // Obtener carrito con detalles
        ShopingCart cart = shopingCartRepository.findById(cartId)
                .orElseThrow(() -> new EntityNotFoundException("shoping cart not found"));

        // Validar carrito y estado
        if (cart.getShopingCartDetails().isEmpty()) {
            throw new IllegalStateException("El carrito está vacío");
        }

        // Crear orden y asignar usuario
        Order order = new Order();
        order.setUser(cart.getUser());
        order.setStatus(OrderStatus.PENDING);

        PymentMethod paymentMethod = pymentMethodService.getPymentmethodEntity(paymentMethodId);
        order.setPymentMethod(paymentMethod);

        Shipment shippingAddress = shimpmentService.getentityShipment(shippingAddressId);
        order.setShipment(shippingAddress);

        // Convertir detalles carrito a detalles orden
        Order finalOrder = order;
        List<OrderDetail> orderDetails = cart.getShopingCartDetails().stream()
                .map(cartDetail -> {
                    OrderDetail od = new OrderDetail();
                    od.setOrder(finalOrder);
                    od.setProduct(cartDetail.getProduct());
                    od.setQuantity(cartDetail.getQuantity());
                    od.setPriceUnit(cartDetail.getPriceUnit());
                    od.setTotalPrice(cartDetail.getTotalPrice());
                    return od;
                }).collect(Collectors.toList());
        order.setOrderDetails(orderDetails);
        order.setTotalAmount(orderDetails.getLast().getTotalPrice() + shippingAddress.getShippingCost());

        // Guardar orden y detalles
        order = orderRepository.save(order);

        // Actualizar estado carrito
        cart.setStatus(CartStatus.CLOSED);
        shopingCartRepository.save(cart);

        // Devolver DTO con datos de la orden creada
        return orderMapper.orderToDTO(order);
    }


}
