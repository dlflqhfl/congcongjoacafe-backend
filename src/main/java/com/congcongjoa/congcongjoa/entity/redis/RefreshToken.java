package com.congcongjoa.congcongjoa.entity.redis;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Getter
@RedisHash(value = "refreshToken", timeToLive = 60 * 60 * 24 * 14)
@AllArgsConstructor
public class RefreshToken {

    @Id
    private String refreshToken;

    private String username;
}
