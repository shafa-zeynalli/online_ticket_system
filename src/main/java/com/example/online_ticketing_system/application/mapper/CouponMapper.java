package com.example.online_ticketing_system.application.mapper;


import com.example.online_ticketing_system.application.dto.CouponDTO;
import com.example.online_ticketing_system.domain.model.Coupon;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CouponMapper {

    CouponDTO toDto(Coupon entity);
    Coupon toEntity(CouponDTO dto);
}
