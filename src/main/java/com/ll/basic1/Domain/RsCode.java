package com.ll.basic1.Domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RsCode {
    private final String resultCode;
    private final String message;
    private final Object data;

    public static RsCode of(String resultCode, String message){
        return of(resultCode, message, null);
    }
    public static RsCode of(String resultCode, String message, Object data){
        return new RsCode(resultCode, message, data);
    }
    public boolean isSuccess(){
        return resultCode.contains("S-");
    }

}
