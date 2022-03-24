package com.example.cardealer.util;

import org.springframework.stereotype.Component;


public interface ValidationUtil {

    <E> boolean isValid(E entity);
}
