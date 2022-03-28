package com.keeper.clothingkeeper.controller;
import com.auth0.jwt.JWT;
import com.keeper.clothingkeeper.annotation.PassToken;
import com.keeper.clothingkeeper.annotation.UserLoginToken;
import com.keeper.clothingkeeper.domain.User;

import com.keeper.clothingkeeper.myutils.MD5Util;
import com.keeper.clothingkeeper.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;


@RestController
@CrossOrigin(origins = "*",maxAge = 3600)
public class UserController {
    @Autowired
    private UserService userService;
    @UserLoginToken
    @RequestMapping(value = "/api/user/{id}",method = RequestMethod.GET)
    public User findOneCity(@PathVariable("id") String id){
        return userService.findUserById(id);
    }

    @PassToken
    @ApiOperation(value = "注册", notes = "注册")
    @RequestMapping(value = "/api/register" ,method = RequestMethod.GET)
    public Object register(User user, HttpServletResponse response){
        Object obj = new  Object();
        User userForBase = new User();
        userForBase = null;
        userForBase = userService.findByUsername(user.getuName());
        if(userForBase!=null){
            obj = 404;
            return obj; //用户名已存在
        }
        else {
            String md5 = MD5Util.getMD5(user.getuPassword());
            user.setuPassword(md5);
            if(userService.count()==0){
                user.setUserid("1");
                obj = userService.insertSelective(user);
            }else {
                int id = userService.idMax()+1;
                String s="";
                s = id+"";
                user.setUserid(s);
            }
            obj = userService.insertSelective(user);
            return obj;//注册成功
        }
    }
    @UserLoginToken
    @ApiOperation(value = "注销", notes = "注销")
    @RequestMapping(value = "/api/deleteID",method = RequestMethod.GET)
    public Object deleteID(HttpServletRequest httpServletRequest){
        //获取用户ID
        String token = httpServletRequest.getHeader("Authorization");
        String userId;
        userId = JWT.decode(token).getAudience().get(0);
        Object obj = new  Object();
        obj = userService.deleteByPrimaryKey(userId);
        return obj;
    }

}
