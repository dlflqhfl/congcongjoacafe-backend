package com.congcongjoa.congcongjoa.RsData;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;

import com.congcongjoa.congcongjoa.enums.ResponseCode;
import lombok.Data;

@Data
@AllArgsConstructor
public class RsData<T> {
    private String resultCode;
    private String msg;
    private T data;

    public static <T> RsData<T> of(String resultCode, String msg, T data) {
        return new RsData<T>(resultCode, msg, data);
    }

    public static <T> RsData<T> of(String resultCode, String msg) {
        return new RsData<T>(resultCode, msg, null);
    }

    public static <T> RsData<T> of(ResponseCode responseCode, T data) {
        return new RsData<T>(responseCode.name(), responseCode.getMessage(), data);
    }

    public static <T> RsData<T> of(ResponseCode responseCode) {
        return new RsData<T>(responseCode.name(), responseCode.getMessage(), null);
    }

    @JsonIgnore
    public boolean isSuccess() {
        return "success".equals(resultCode);
    }
}