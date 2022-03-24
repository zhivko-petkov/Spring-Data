package com.example.xmlexercise.service;

import com.example.xmlexercise.model.dto.seed.UserSeedDto;
import com.example.xmlexercise.model.dto.successfullySoldProducts.ProductSoldRootDto;
import com.example.xmlexercise.model.dto.usersAndProducts.UserInfoRootDto;
import com.example.xmlexercise.model.entity.User;

import java.util.List;

public interface UserService {
    long getCount();
    void seedUsers(List<UserSeedDto> users);

    User getRandomUser();

    ProductSoldRootDto getUserInfo();

    UserInfoRootDto getProductUserInfo();
}
