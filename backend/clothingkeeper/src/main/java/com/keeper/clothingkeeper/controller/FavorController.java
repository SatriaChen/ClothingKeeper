package com.keeper.clothingkeeper.controller;

import com.auth0.jwt.JWT;
import com.keeper.clothingkeeper.annotation.UserLoginToken;
import com.keeper.clothingkeeper.domain.Clothes;
import com.keeper.clothingkeeper.myutils.ObjectStorageUtil;
import com.keeper.clothingkeeper.myutils.connstant.ThirdConst;
import com.keeper.clothingkeeper.service.ClothesService;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
public class FavorController {
    @Autowired
    private ClothesService clothesService;

    @UserLoginToken
    @ApiOperation(value = "榜单", notes = "榜单")
    @RequestMapping(value = "/api/favor",method = RequestMethod.GET)
    public String favor(HttpServletRequest httpServletRequest) {
        //获取用户ID
        String token = httpServletRequest.getHeader("Authorization");
        String userId;
        userId = JWT.decode(token).getAudience().get(0);
        List<Clothes> clothesList= clothesService.selectClothesRecord(userId);
        //System.out.println(clothesService.selectClothesRecord("1"));
        JSONArray inJson = new JSONArray();
        for(Clothes clo : clothesList){

            JSONObject jo = new JSONObject();
            jo.put("clothesID",clo.getcClothesid());
            jo.put("typeID",clo.getcTypeid());//有问题 故未完成
            if(clo.getcSeason().equals("0")){
                jo.put("season","春秋");
            }else if(clo.getcSeason().equals("1")){
                jo.put("season","夏季");
            }else if(clo.getcSeason().equals("2")){
                jo.put("season","冬季");
            }
            if(clo.getcStyle().equals("0")){
                jo.put("style","正式");
            }else if(clo.getcStyle().equals("1")){
                jo.put("style","休闲");
            }else{
                jo.put("style","运动");
            }

            jo.put("tag",clo.getcTag());
            jo.put("frequence",clo.getcFrequences());
            jo.put("ImageUrl", ObjectStorageUtil.getObjectUrl(
                    ThirdConst.TencentCOS.CLOTH ,clo.getcClothimg()));

            inJson.add(jo);
        }
        System.out.println(inJson);
        return inJson.toString();
    }

}
