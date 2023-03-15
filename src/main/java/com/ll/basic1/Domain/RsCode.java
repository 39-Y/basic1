package com.ll.basic1.Domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RsCode {
    private final String resultCode;
    private final String message;

}
