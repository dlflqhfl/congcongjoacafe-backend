package com.congcongjoa.congcongjoa.enums;

import com.congcongjoa.congcongjoa.RsData.RsData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import lombok.AccessLevel;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum ResponseCode {

    // 200 OK
    OK(HttpStatus.OK, true, "요청이 성공적으로 처리되었습니다."),
    USER_READ_SUCCESS(HttpStatus.OK, true, "사용자 정보 조회 성공"),
    USER_UPDATE_SUCCESS(HttpStatus.OK, true, "사용자 정보 수정 성공"),
    USER_LOGIN_SUCCESS(HttpStatus.OK, true, "사용자 로그인 성공"),

    // 201 Created
    USER_CREATE_SUCCESS(HttpStatus.CREATED, true, "사용자 생성 성공"),
    RESOURCE_CREATED(HttpStatus.CREATED, true, "리소스가 성공적으로 생성되었습니다."),

    // 204 No Content
    NO_CONTENT(HttpStatus.NO_CONTENT, true, "요청이 성공적으로 처리되었으며 응답할 콘텐츠가 없습니다."),

    // 400 Bad Request
    BAD_REQUEST(HttpStatus.BAD_REQUEST, false, "잘못된 요청입니다."),
    INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, false, "유효하지 않은 입력값입니다."),

    // 401 Unauthorized
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, false, "인증되지 않은 사용자입니다."),

    // 403 Forbidden
    FORBIDDEN(HttpStatus.FORBIDDEN, false, "권한이 없습니다."),

    // 404 Not Found
    NOT_FOUND(HttpStatus.NOT_FOUND, false, "리소스를 찾을 수 없습니다."),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, false, "사용자를 찾을 수 없습니다."),

    // 405 Method Not Allowed
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, false, "허용되지 않은 메소드입니다."),

    // 409 Conflict
    CONFLICT(HttpStatus.CONFLICT, false, "충돌이 발생했습니다."),
    USER_ALREADY_EXIST(HttpStatus.CONFLICT, false, "이미 가입된 사용자입니다."),
    USER_NAME_ALREADY_EXIST(HttpStatus.CONFLICT, false, "이미 존재하는 닉네임입니다."),

    // 500 Internal Server Error
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, false, "서버에 오류가 발생했습니다."),

    // 503 Service Unavailable
    SERVICE_UNAVAILABLE(HttpStatus.SERVICE_UNAVAILABLE, false, "서비스를 사용할 수 없습니다.")
    ;

    private final HttpStatus httpStatus;
    private final Boolean success;
    private final String message;

    public <T> RsData<T> toRsData(T data) {
        return RsData.of(this, data);
    }
}