package com.keeper.clothingkeeper.controller;

import com.auth0.jwt.JWT;
import com.keeper.clothingkeeper.dto.ClothDTO;
import com.keeper.clothingkeeper.dto.ClothForShowDTO;
import com.keeper.clothingkeeper.myutils.ResponseUtil;
import com.keeper.clothingkeeper.service.ClothesService;
import java.util.List;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: clothingkeeper
 * @description: MyClothController 我的衣柜controller
 * @author: Vaskka
 * @create: 2020/3/7 5:40 PM
 **/

@RestController
public class MyClothController {

    @Resource
    private ClothesService clothesService;
//
//    @GetMapping(value = "/test")
//    @ApiOperation(value = "测试", notes = "sss")
//    public Map<String, Object> test() {
//
//        Map<String, Object> res = new HashMap<>();
//
//        res.put("res", 0);
//
//        return res;
//
//    }
//
    @RequestMapping(value = "cloth/new",method = RequestMethod.GET)
    @ApiOperation(value = "增加一件衣服")
    public Map<String, Object> addCloth(String cClothimg,String cTypeid,String cSeason, String cStyle,String cTag,String cRfid,HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("Authorization");
        String userId;
        userId = JWT.decode(token).getAudience().get(0);
        ClothDTO clothDTO=new ClothDTO(
                cTypeid,
                cSeason,
                cStyle,
               "0",
                0,
                cTag,
               cRfid,
                cClothimg
        );
        clothesService.addCloth(clothDTO,userId);
        return ResponseUtil.success();
    }
//
@RequestMapping(value = "cloth/clothdelete",method = RequestMethod.GET)
    @ApiOperation(value = "永远删除一个衣服")
    public Map<String, Object> deleteCloth(String clothId,HttpServletRequest httpServletRequest) {
    String token = httpServletRequest.getHeader("Authorization");
    String userId;
    userId = JWT.decode(token).getAudience().get(0);
    clothesService.deleteCloth(userId,clothId);
    return ResponseUtil.success();
    }
//
@RequestMapping(value = "cloth/clothType",method = RequestMethod.GET)

    @ApiOperation(value = "根据衣服类型（上装 下装 套装 其他）查找衣服")
    public Map<String, Object> findClothByType(String clothType,HttpServletRequest httpServletRequest) {
    String token = httpServletRequest.getHeader("Authorization");
    String userId;
    userId = JWT.decode(token).getAudience().get(0);
    List<ClothForShowDTO> res = clothesService.findClothByType(userId, clothType);

        return ResponseUtil.success(res);
    }

    @RequestMapping(value = "cloth/rfid",method = RequestMethod.GET)

    @ApiOperation(value = "根据衣服类型（上装 下装 套装 其他）查找衣服")
    public Map<String, Object> findClothByRfid(String cRfid,HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("Authorization");
        String userId;
        userId = JWT.decode(token).getAudience().get(0);
        List<ClothForShowDTO> res = clothesService.findClothByRfid(userId, cRfid);

        return ResponseUtil.success(res);
    }

    @RequestMapping(value = "cloth/countByrfid",method = RequestMethod.GET)

    @ApiOperation(value = "根据衣服类型（上装 下装 套装 其他）查找衣服")
    public int countByrfid(String cRfid,HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("Authorization");
        String userId;
        userId = JWT.decode(token).getAudience().get(0);
        int res = clothesService.countByrfid(userId, cRfid);

        return res;
    }

//
//    @GetMapping(value = "/cloth/search/{userId}/{keyWord}")
//    @ApiOperation(value = "搜索用户的衣服")
//    public Map<String, Object> searchCloth(@PathVariable(value = "userId") String userId,
//                                               @PathVariable(value = "keyWord") String keyWord) {
//
//        List<ClothForShowDTO> res = clothesService.searchCloth(userId, keyWord);
//
//        return ResponseUtil.success(res);
//    }
//
//    @GetMapping(value = "/cloth/move/out/{userId}/{clothId}")
//    @ApiOperation(value = "将一个衣服移出衣柜 频率加一")
//    public Map<String, Object> moveOutCloth(@PathVariable(value = "userId") String userId,
//                                            @PathVariable(value = "clothId") int clothId) {
//
//        clothesService.moveOutCloth(userId, clothId);
//
//        return ResponseUtil.success();
//    }
//
//    @GetMapping(value = "/cloth/move/in/{userId}/{clothId}")
//    @ApiOperation(value = "将一个衣服移进衣柜")
//    public Map<String, Object> moveInCloth(@PathVariable(value = "userId") String userId,
//                                            @PathVariable(value = "clothId") int clothId) {
//
//        clothesService.moveInCloth(userId, clothId);
//
//        return ResponseUtil.success();
//    }
//

    @RequestMapping(value = "cloth/changeState",method = RequestMethod.GET)
    @ApiOperation(value = "更改衣物状态")
    public Map<String, Object> cleanCloth(String clothId,HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("Authorization");
        String userId;
        userId = JWT.decode(token).getAudience().get(0);
        clothesService.cleanCloth(userId, clothId);

        return ResponseUtil.success();
    }
//
//    @GetMapping(value = "/cloth/unclean/{userId}/{clothId}")
//    @ApiOperation(value = "标记一件衣服是不干净的")
//    public Map<String, Object> uncleanCloth(@PathVariable(value = "userId") String userId,
//                                          @PathVariable(value = "clothId") int clothId) {
//
//        clothesService.uncleanCloth(userId, clothId);
//
//        return ResponseUtil.success();
//    }
//
    @PostMapping(value = "/cloth/picture")
    @ApiOperation(value = "上传图片文件获得相应图片id")
    public Map<String, Object> uploadFile(@RequestBody MultipartFile file) {

        String objId = clothesService.uploadFileGetObjId(file);

        return ResponseUtil.success(objId);
    }


}
