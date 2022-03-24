package com.example.xmlexercise.service.impl;

import com.example.xmlexercise.model.dto.seed.UserSeedDto;
import com.example.xmlexercise.model.dto.successfullySoldProducts.ProductSoldRootDto;
import com.example.xmlexercise.model.dto.successfullySoldProducts.ProductWithBuyerDto;
import com.example.xmlexercise.model.dto.successfullySoldProducts.UserInfoDto;
import com.example.xmlexercise.model.dto.usersAndProducts.UserCurrentDto;
import com.example.xmlexercise.model.dto.usersAndProducts.UserInfoRootDto;
import com.example.xmlexercise.model.entity.User;
import com.example.xmlexercise.repository.UserRepository;
import com.example.xmlexercise.service.UserService;
import com.example.xmlexercise.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public long getCount() {
        return userRepository.count();
    }

    @Override
    public void seedUsers(List<UserSeedDto> users) {
        users
                .stream()
                .filter(validationUtil::isValid)
                .map(userSeedDto -> modelMapper.map(userSeedDto, User.class))
                .forEach(userRepository::save);
    }

    @Override
    public User getRandomUser() {
        long randomId = ThreadLocalRandom.current()
                .nextLong(1, userRepository.count() + 1);


        return userRepository
                .findById(randomId)
                .orElse(null);
    }

    @Override
    public ProductSoldRootDto getUserInfo() {
        ProductSoldRootDto rootDto = new ProductSoldRootDto();
        rootDto.setUsers(userRepository
                .findUsersSuccessfullySoldProducts()
                .stream()
                .map(user -> {
                    UserInfoDto userInfoDto = modelMapper
                            .map(user, UserInfoDto.class);

                    List<ProductWithBuyerDto> productWithBuyerDtos = new ArrayList<>();
                    userInfoDto.getSoldProducts().forEach(product ->
                    {
                        if(product.getBuyerLastName() != null
                                && product.getBuyerFirstName() != null){
                            productWithBuyerDtos.add(product);
                        }
                    });
                    userInfoDto.setSoldProducts(productWithBuyerDtos);
                    if(productWithBuyerDtos.stream().count() > 0){
                        return userInfoDto;
                    }
                    return null;
                }).collect(Collectors.toList()));

        return rootDto;
    }

    @Override
    public UserInfoRootDto getProductUserInfo() {
        UserInfoRootDto rootDto = new UserInfoRootDto();
            rootDto.setUser(userRepository
                    .findUsersHasSoldProduct()
                    .stream()
                    .map(user -> {
                        UserCurrentDto userCurrentDto = modelMapper
                                .map(user, UserCurrentDto.class);
                        return userCurrentDto;
                    }).collect(Collectors.toList()));
        rootDto.setCount(rootDto.getUser().stream().count());
        rootDto.getUser().forEach(user -> {
            user.getSoldProducts().setCount(user.getSoldProducts().getProducts().stream().count());
        });
        return rootDto;
    }
}
