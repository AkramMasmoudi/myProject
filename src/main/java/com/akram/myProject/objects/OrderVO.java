package com.akram.myProject.objects;

import com.akram.myProject.entities.Check;
import com.akram.myProject.entities.Coefficient;
import com.akram.myProject.entities.Order;
import com.akram.myProject.entities.OrderLine;
import lombok.*;

import javax.persistence.FetchType;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static javax.persistence.FetchType.LAZY;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class OrderVO implements Serializable {
    private Long orderId;
    private LocalDate orderDate;
    private PersonVO orderPerson;
    private double orderCash;
    private List<CheckVO> lstOrderChecks;
    private List<OrderLineVO> lstOrderLines;

    public OrderVO(Order order, FetchType fetchType) {
        this.orderId = order.getOrderId();
        this.orderDate = order.getOrderDate();
        this.orderCash = order.getOrderCash();
        this.orderPerson = new PersonVO(order.getOrderPersonId(),LAZY);
        if(fetchType.equals(LAZY)) {
            this.lstOrderChecks = new ArrayList<>();
            this.lstOrderLines = new ArrayList<>();
        }else {
            this.lstOrderChecks = order.getLstOrderChecks().stream().map((Check check) -> {
                return new CheckVO(check, LAZY);
            }).collect(Collectors.toList());
            this.lstOrderLines = order.getLstOrderLines().stream().map((OrderLine orderLine) -> {
                return new OrderLineVO(orderLine,LAZY);
            }).collect(Collectors.toList());
        }
    }

}
