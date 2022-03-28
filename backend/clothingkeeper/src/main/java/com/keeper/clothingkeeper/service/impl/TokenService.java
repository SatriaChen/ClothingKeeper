package com.keeper.clothingkeeper.service.impl;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Service;

import com.keeper.clothingkeeper.domain.User;

import java.util.Date;

@Service
public class TokenService {
    public String getToken(User user){
        Date start = new Date();
        long currentTime = System.currentTimeMillis() + 60* 60 * 1000;//一小时有效时间
        Date end = new Date(currentTime);
        String token = "";
        token = JWT.create().withAudience(user.getUserid()).withIssuedAt(start).withExpiresAt(end)
                .sign(Algorithm.HMAC256(user.getuPassword()));
        return token;
    }
}
