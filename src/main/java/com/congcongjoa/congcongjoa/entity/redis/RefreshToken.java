package com.congcongjoa.congcongjoa.entity.redis;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Getter
@RedisHash(value = "refreshAccessToken", timeToLive = 60)
@AllArgsConstructor
public class RefreshToken {

    @Id
    private String refreshToken;

    private String username;
}
