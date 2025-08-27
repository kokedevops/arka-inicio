package com.skillnest.arka.dto;

import com.skillnest.arka.model.ShopingCartDetail;
import com.skillnest.arka.model.enums.CartStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopingCartDTO {

    private Long id;
    private CartStatus status;
    private Long userId;
    private List<ShopingCartDetailDTO> shopingCartDetails;
}
