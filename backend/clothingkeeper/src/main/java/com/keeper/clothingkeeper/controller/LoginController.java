package com.keeper.clothingkeeper.controller;
import com.keeper.clothingkeeper.annotation.PassToken;
import net.sf.json.JSONObject;
import com.keeper.clothingkeeper.service.UserService;
import com.keeper.clothingkeeper.annotation.UserLoginToken;
import com.keeper.clothingkeeper.service.impl.TokenService;
import com.keeper.clothingkeeper.domain.User;
import com.keeper.clothingkeeper.myutils.TokenUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import com.keeper.clothingkeeper.myutils.MD5Util;



@RestController

public class LoginController {
    @Autowired
    private UserService userService;
    @Autowired
   private TokenService tokenService;
    // 登录
   @ApiOperation(value = "登陆", notes = "登陆")
    @RequestMapping(value = "/api/login" ,method = RequestMethod.GET)
    public Object login(User user, HttpServletResponse response) {
       JSONObject jsonObject=new JSONObject();
       Object obj = new  Object();
       User userForBase = new User();
       userForBase = null;

/*        userForBase.setUserid(userService.findUserById(user.getUserid()).getUserid());
        userForBase.setuName(userService.findUserById(user.getUserid()).getuName());
        userForBase.setuPassword(userService.findUserById(user.getUserid()).getuPassword());*/

        userForBase = userService.findByUsername(user.getuName());
       if(userForBase==null){
           obj = 404;
           return obj;
       }else {
           String md5 = MD5Util.getMD5(user.getuPassword());
           if (!userForBase.getuPassword().equals(md5)) {
               jsonObject.put("message", "登录失败,密码错误");
               //return jsonObject;
               obj = 400;
               return obj;
           }
           else {
               String token = tokenService.getToken(userForBase);
               jsonObject.put("token", token);
               Cookie cookie = new Cookie("token", token);
               cookie.setPath("/");
               response.addCookie(cookie);
               return jsonObject;
           }
       }

    }
    /***
     * 这个请求需要验证token才能访问
     */
    @UserLoginToken
    @ApiOperation(value = "获取信息", notes = "获取信息")
    @RequestMapping(value = "/getMessage" ,method = RequestMethod.GET)
    public String getMessage() {

        // 取出token中带的用户id 进行操作
        System.out.println(TokenUtil.getTokenUserId());

        return "您已通过验证";
    }

    }

