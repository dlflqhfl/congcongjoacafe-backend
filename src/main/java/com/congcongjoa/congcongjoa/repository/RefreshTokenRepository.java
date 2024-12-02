package com.congcongjoa.congcongjoa.repository;

import com.congcongjoa.congcongjoa.entity.redis.RefreshToken;
import org.springframework.data.repository.CrudRepository;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String> {

}
