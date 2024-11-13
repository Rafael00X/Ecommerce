package com.ecommerce.inventory.services;

import com.ecommerce.inventory.repositories.UnitOfMeasureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UnitOfMeasureService {
    private final UnitOfMeasureRepository unitOfMeasureRepository;
}
