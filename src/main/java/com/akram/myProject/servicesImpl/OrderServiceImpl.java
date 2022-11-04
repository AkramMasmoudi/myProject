package com.akram.myProject.servicesImpl;

import com.akram.myProject.repositories.OrderLineRepository;
import com.akram.myProject.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderLineRepository orderLineRepository;
    @Override
    public Boolean articleHasOrderLines(Long articleId) {
        return orderLineRepository.countAllByOrderLineArticleId(articleId) > 0;
    }
}
