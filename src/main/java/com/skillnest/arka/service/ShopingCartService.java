package com.skillnest.arka.service;

import com.skillnest.arka.dto.ShopingCartDTO;
import com.skillnest.arka.dto.ShopingCartDetailDTO;
import com.skillnest.arka.mapper.ShopingCartDetailMapper;
import com.skillnest.arka.mapper.ShopingCartMapper;
import com.skillnest.arka.model.ShopingCart;
import com.skillnest.arka.model.ShopingCartDetail;
import com.skillnest.arka.model.User;
import com.skillnest.arka.repository.ShopingCartDetailRepository;
import com.skillnest.arka.repository.ShopingCartRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShopingCartService {
    private final ShopingCartRepository shopingCartRepository;
    private final ShopingCartDetailRepository shopingCartDetailRepository;
    private  final ShopingCartMapper shopingCartMapper;
    private final ShopingCartDetailMapper shopingCartDetailMapper;
    private final UserService userService;

    @Transactional
    public ShopingCartDTO createShopingCart(ShopingCartDTO shopingCartDTO) {
        ShopingCart newShopingCart = shopingCartMapper.shopingCartDTOToEntity(shopingCartDTO);

        User user = userService.getEntityUser(shopingCartDTO.getUserId());
        newShopingCart.setUser(user);

        if (shopingCartDTO.getShopingCartDetails() != null) {
            ShopingCart finalNewShopingCart = newShopingCart;
            List<ShopingCartDetail> details = shopingCartDTO.getShopingCartDetails().stream()
                    .map(shopingCartDetailMapper::shopingCartDetailDTOToEntity)
                    .peek(detail -> detail.setShopingCart(finalNewShopingCart))
                    .collect(Collectors.toList());

            newShopingCart.setShopingCartDetails(details);

            newShopingCart = shopingCartRepository.save(newShopingCart);
        } else {
            newShopingCart = shopingCartRepository.save(newShopingCart);
        }

        // Convertir entidad guardada a DTO y devolver
        return shopingCartMapper.ShopingCartToDTO(newShopingCart);
    }

}
