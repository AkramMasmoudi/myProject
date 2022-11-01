package com.akram.myProject.objects;

import com.akram.myProject.entities.Article;
import com.akram.myProject.entities.Coefficient;
import com.akram.myProject.entities.Quantities;
import com.akram.myProject.entities.Unit;
import lombok.*;

import javax.persistence.FetchType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static javax.persistence.FetchType.LAZY;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UnitVO implements Serializable {
    private Long unitId;
    private String shortName;
    private String name;
    private List<QuantitiesVO> lstQuantities;
    private List<CoefficientVO> lstCoefficients;

    public UnitVO(Unit unit, FetchType fetchType){
        this.unitId = unit.getUnitId();
        this.shortName = unit.getShortName();
        this.name = unit.getName();
        if(fetchType.equals(LAZY)){
            this.lstCoefficients = new ArrayList<>();
            this.lstQuantities = new ArrayList<>();
        }else {
            this.lstCoefficients = unit.getLstCoefficients().stream().map((Coefficient coefficient) -> {
                return new CoefficientVO(coefficient, LAZY);
            }).collect(Collectors.toList());
            this.lstQuantities = unit.getLstQuantities().stream().map((Quantities quantity) -> {
                return new QuantitiesVO(quantity, LAZY);
            }).collect(Collectors.toList());
        }
    }
}
