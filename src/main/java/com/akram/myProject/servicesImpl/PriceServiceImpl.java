package com.akram.myProject.servicesImpl;

import com.akram.myProject.entities.Price;
import com.akram.myProject.repositories.PriceRepository;
import com.akram.myProject.services.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PriceServiceImpl implements PriceService {

    @Autowired
    private PriceRepository priceRepository;

    @Override
    public List<Price> saveAllPrices(List<Price> prices) {
        if(!prices.isEmpty())
            priceRepository.deleteAllByPriceArticleId(prices.get(0).getPriceArticleId().getArticleId());
        return priceRepository.saveAll(prices);
    }
}
