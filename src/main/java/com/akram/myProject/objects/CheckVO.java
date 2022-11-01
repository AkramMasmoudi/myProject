package com.akram.myProject.objects;

import com.akram.myProject.entities.Check;
import lombok.*;

import javax.persistence.FetchType;
import java.io.Serializable;
import java.time.LocalDate;

import static javax.persistence.FetchType.LAZY;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CheckVO implements Serializable {
    private Long checkId;
    private String checkNumber;
    private String bank;
    private String bankBranch;
    private LocalDate checkDate;
    private OrderVO checkOrder;

    public CheckVO(Check check,FetchType fetchType){
        this.checkId = check.getCheckId();
        this.checkNumber = check.getCheckNumber();
        this.bank = check.getBank();
        this.bankBranch = check.getBankBranch();
        this.checkDate = check.getCheckDate();
        this.checkOrder = new OrderVO(check.getCheckOrderId(),LAZY);
    }
}
