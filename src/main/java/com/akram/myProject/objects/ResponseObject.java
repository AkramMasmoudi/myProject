package com.akram.myProject.objects;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ResponseObject<T> {
    private String errorMessage;
    private String infoMessage;
    private String warningMessage;
    private T singleData;
    private List<T> listData;
}
