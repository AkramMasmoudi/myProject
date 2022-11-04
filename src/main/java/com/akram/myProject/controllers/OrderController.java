package com.akram.myProject.controllers;


import com.akram.myProject.services.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
@CrossOrigin("*")
@Slf4j
public class OrderController {
    private final OrderService orderService;
    @GetMapping("/orderLine/has/{articleId}")
    public ResponseEntity<Boolean> articleHasOrderLines(@PathVariable Long articleId) {
        try {
            return new ResponseEntity<>(orderService.articleHasOrderLines(articleId),OK);
        }catch (Exception e){
            return new ResponseEntity<>(null,INTERNAL_SERVER_ERROR);
        }
    }

}
