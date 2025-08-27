package com.skillnest.arka.domain.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class ShopingCart {
    Long id;
    // Otros campos simplificados para el ejemplo
}
