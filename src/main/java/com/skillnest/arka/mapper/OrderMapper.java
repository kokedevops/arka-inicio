package com.skillnest.arka.mapper;

import com.skillnest.arka.dto.OrderDTO;
import com.skillnest.arka.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "pymentMethod.id", target = "pymentMethodId")
    @Mapping(source = "shipment.id", target = "shipmentId")
    @Mapping(source = "status", target = "orderStatus")
    OrderDTO orderToDTO(Order order);

    @Mapping(source = "userId", target = "user.id")
    @Mapping(source = "pymentMethodId", target = "pymentMethod.id")
    @Mapping(source = "shipmentId", target = "shipment.id")
    @Mapping(source = "orderStatus", target = "status")
    Order orderDTOToEntity(OrderDTO dto);

    List<OrderDTO> ordersToOrderDTOs(List<Order> orders);

    void updateOrderFromDTO(OrderDTO dto, @MappingTarget Order entity);
}

