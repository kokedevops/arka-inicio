package com.skillnest.arka.mapper;

import com.skillnest.arka.dto.OrderDetailDTO;
import com.skillnest.arka.model.Order;
import com.skillnest.arka.model.OrderDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderDetailMapper {

    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "order.id", target = "orderId")
    OrderDetailDTO orderDetailToDTO(OrderDetail orderDetail);

    @Mapping(source = "productId", target = "product.id")
    @Mapping(source = "orderId", target = "order.id")
    OrderDetail orderDetailDTOToEntity(OrderDetailDTO orderDetailDTO);

    List<OrderDetailDTO> orderDetailsToDTOs(List<OrderDetail> orderDetails);

    void updateOrderDetailFromDTO(OrderDetailDTO dto, @MappingTarget OrderDetail entity);
}
