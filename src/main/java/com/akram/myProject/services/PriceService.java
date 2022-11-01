package com.akram.myProject.services;

import com.akram.myProject.entities.Price;

import java.util.List;

public interface PriceService {
    public List<Price> saveAllPrices(List<Price> prices);
}
