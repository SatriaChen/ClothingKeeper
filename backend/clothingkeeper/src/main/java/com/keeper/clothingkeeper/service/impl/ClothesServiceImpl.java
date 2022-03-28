package com.keeper.clothingkeeper.service.impl;

import com.keeper.clothingkeeper.dao.ClothesMapper;
import com.keeper.clothingkeeper.domain.Clothes;
import com.keeper.clothingkeeper.domain.ClothesKey;
import com.keeper.clothingkeeper.dto.ClothDTO;
import com.keeper.clothingkeeper.dto.ClothForShowDTO;
import com.keeper.clothingkeeper.myutils.CommonUtil;
import com.keeper.clothingkeeper.service.ClothesService;
import com.keeper.clothingkeeper.domain.ClothesResponse;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import com.keeper.clothingkeeper.myutils.ObjectStorageUtil;
import com.keeper.clothingkeeper.myutils.connstant.CommonConst;
import com.keeper.clothingkeeper.myutils.connstant.ThirdConst;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;



@Service
public class ClothesServiceImpl implements ClothesService{
    @Autowired
    private ClothesMapper ClothesMapper;

    @Override
    public List<Clothes> selectClothesRecord(String userid){
        return ClothesMapper.selectClothesRecord(userid);
    }


    //从数据库里按季节获得该用户的衣服
    @Override
    public List<Clothes> getClothesBySeason(String season,String userid) {
        List<Clothes> userAllClothes = ClothesMapper.getAllClothes(userid);
        List<Clothes> clothesBySeason = new ArrayList<Clothes>();
        for (Clothes cloth : userAllClothes) {
            if (cloth.getcSeason().equals(season)) {

                clothesBySeason.add(cloth);
            }
        }
        return clothesBySeason;
    }

    //List<Clothes> userAllClothes = ClothesMapper.getAllClothes(TokenUtil.getTokenUserId());

    //从数据库里按衣服类型获得该用户的衣服
    @Override
    public List<Clothes> getClothesByType(String typeId) {
        List<Clothes> userAllClothes = ClothesMapper.getAllClothes("1");
        List<Clothes> clothesByType = new ArrayList<Clothes>();
        for (Clothes cloth : userAllClothes) {
            if (cloth.getcTypeid().equals(typeId)) {
                clothesByType.add(cloth);
            }
        }
        return clothesByType;

    }

    //将该用户的同季节的衣服按类型分类
    @Override
    public ClothesResponse getSameSeasonClothesByType(List<Clothes> SameSeasonClothes) {
        ClothesResponse clothesResponse = new ClothesResponse();
        List<Clothes> upper = new ArrayList<Clothes>();
        List<Clothes> bottom = new ArrayList<Clothes>();
        List<Clothes> suit = new ArrayList<Clothes>();
        List<Clothes> elseCloth = new ArrayList<Clothes>();
        for (Clothes cloth:SameSeasonClothes){
            if (cloth.getcTypeid().equals("上装")) {
                if(cloth.getcSeason().equals("0"))
                {
                    cloth.setcSeason("春秋");
                }else if (cloth.getcSeason().equals("1")){
                    cloth.setcSeason("夏季");
                }else if (cloth.getcSeason().equals("2")){
                    cloth.setcSeason("冬季");
                }

                if(cloth.getcStyle().equals("0"))
                {
                    cloth.setcStyle("正式");
                }else if(cloth.getcStyle().equals("1"))
                {
                    cloth.setcStyle("休闲");
                }else {
                    cloth.setcStyle("运动");
                }
                if(cloth.getcState().equals("0"))
                {
                    cloth.setcState("可穿");

                }else if (cloth.getcState().equals("1")){
                    cloth.setcState("待洗");
                }
                cloth.setcClothimg(ObjectStorageUtil.getObjectUrl(
                        ThirdConst.TencentCOS.CLOTH ,cloth.getcClothimg()));

                upper.add(cloth);
            }
            else if (cloth.getcTypeid().equals("下装")){
                if(cloth.getcSeason().equals("0"))
                {
                    cloth.setcSeason("春秋");
                }else if (cloth.getcSeason().equals("1")){
                    cloth.setcSeason("夏季");
                }else if (cloth.getcSeason().equals("2")){
                    cloth.setcSeason("冬季");
                }

                if(cloth.getcStyle().equals("0"))
                {
                    cloth.setcStyle("正式");
                }else if(cloth.getcStyle().equals("1"))
                {
                    cloth.setcStyle("休闲");
                }else {
                    cloth.setcStyle("运动");
                }
                if(cloth.getcState().equals("0"))
                {
                    cloth.setcState("可穿");

                }else if (cloth.getcState().equals("1")){
                    cloth.setcState("待洗");
                }

                cloth.setcClothimg(ObjectStorageUtil.getObjectUrl(
                        ThirdConst.TencentCOS.CLOTH ,cloth.getcClothimg()));
                bottom.add(cloth);
            }
            else if (cloth.getcTypeid().equals("套装")) {
                if(cloth.getcSeason().equals("0"))
                {
                    cloth.setcSeason("春秋");
                }else if (cloth.getcSeason().equals("1")){
                    cloth.setcSeason("夏季");
                }else if (cloth.getcSeason().equals("2")){
                    cloth.setcSeason("冬季");
                }

                if(cloth.getcStyle().equals("0"))
                {
                    cloth.setcStyle("正式");
                }else if(cloth.getcStyle().equals("1"))
                {
                    cloth.setcStyle("休闲");
                }else {
                    cloth.setcStyle("运动");
                }
                if(cloth.getcState().equals("0"))
                {
                    cloth.setcState("可穿");

                }else if (cloth.getcState().equals("1")){
                    cloth.setcState("待洗");
                }

                cloth.setcClothimg(ObjectStorageUtil.getObjectUrl(
                        ThirdConst.TencentCOS.CLOTH ,cloth.getcClothimg()));
                suit.add(cloth);
            }
            else if (cloth.getcTypeid().equals("其他")) {
                if(cloth.getcSeason().equals("0"))
                {
                    cloth.setcSeason("春秋");
                }else if (cloth.getcSeason().equals("1")){
                    cloth.setcSeason("夏季");
                }else if (cloth.getcSeason().equals("2")){
                    cloth.setcSeason("冬季");
                }

                if(cloth.getcStyle().equals("0"))
                {
                    cloth.setcStyle("正式");
                }else if(cloth.getcStyle().equals("1"))
                {
                    cloth.setcStyle("休闲");
                }else {
                    cloth.setcStyle("运动");
                }

                if(cloth.getcState().equals("0"))
                {
                    cloth.setcState("可穿");

                }else if (cloth.getcState().equals("1")){
                    cloth.setcState("待洗");
                }

                cloth.setcClothimg(ObjectStorageUtil.getObjectUrl(
                        ThirdConst.TencentCOS.CLOTH ,cloth.getcClothimg()));
                elseCloth.add(cloth);
            }
        }
        clothesResponse.setUpper(upper);
        clothesResponse.setBottom(bottom);
        clothesResponse.setSuit(suit);
        clothesResponse.setElseCloth(elseCloth);
        return clothesResponse;
    }

    @Override
    public String uploadFileGetObjId(MultipartFile multipartFile) {
        return ObjectStorageUtil.saveFile(multipartFile, ThirdConst.TencentCOS.CLOTH);
    }

    @Override
    public void addCloth(ClothDTO clothDTO,String userid) {

        int clothId = CommonUtil.rand();
        while (ClothesMapper.countId(clothId) != 0) {
            clothId = CommonUtil.rand();
        }
        Clothes clothEntity=new Clothes(
                clothId+"",
                userid,
                clothDTO.getcTypeid(),
                clothDTO.getcSeason(),
                clothDTO.getcStyle(),
                clothDTO.getcState(),
                clothDTO.getcFrequences(),
                clothDTO.getcTag(),
                clothDTO.getcRfid(),
                clothDTO.getcClothimg()
        );
        ClothesMapper.insert(clothEntity);

    }

    @Override
    public List<ClothForShowDTO> findClothByType(String userId, String clothType) {
        List<Clothes> rawResult =ClothesMapper.selectByUserIdAndType(userId, clothType);

        return this.changeRawList(rawResult);
    }

    @Override
    public List<ClothForShowDTO> findClothByRfid(String userId, String cRfid) {

        List<Clothes> rawResult =ClothesMapper.selectByUserIdAndRfid(userId, cRfid);
        for(Clothes clothes:rawResult){
            clothes.setcFrequences(clothes.getcFrequences()+1);
            ClothesMapper.updateByPrimaryKey(clothes);
        }

        return this.changeRawList(rawResult);
    }

    @Override
    public int countByrfid(String userId, String cRfid) {
        int Count=ClothesMapper.countByrfid(userId, cRfid);
        return Count;
    }


    @Override
    public void deleteCloth(String userId, String clothId) {
        ClothesKey clothesKey=new ClothesKey(clothId,userId);
        ClothesMapper.deleteByPrimaryKey(clothesKey);
    }

    @Override
    public void cleanCloth(String userId, String clothId) {
        ClothesKey clothesKey=new ClothesKey(clothId,userId);
        Clothes clothes=ClothesMapper.selectByPrimaryKey(clothesKey);
        if(clothes.getcState().equals("0")){
            clothes.setcState("1");
        }else {
            clothes.setcState("0");
        }
        ClothesMapper.updateByPrimaryKey(clothes);
    }
    /**
     * change raw list
     * @param rawResult entity  list
     * @return DTO list
     */
    private List<ClothForShowDTO> changeRawList(List<Clothes> rawResult) {
        // 换 obj_id -> url
        List<ClothForShowDTO> res = new ArrayList<>();
        for (Clothes clothes : rawResult) {
            ClothForShowDTO clothForShowDTO = new ClothForShowDTO("","","","","","",0,"","","");

            String clothId = clothes.getcClothesid();

            // 一般字段
            clothForShowDTO.setClothId(clothId);
            clothForShowDTO.setcSeason(clothes.getcSeason());
            clothForShowDTO.setcState(clothes.getcState());
            clothForShowDTO.setcFrequences(clothes.getcFrequences());
            clothForShowDTO.setUserId(clothes.getcUid());
            clothForShowDTO.setcClothImgUrl(
                    ObjectStorageUtil.getObjectUrl(
                            ThirdConst.TencentCOS.CLOTH ,clothes.getcClothimg()));
            clothForShowDTO.setcStyle(clothes.getcStyle());
            clothForShowDTO.setcTypeid(clothes.getcTypeid());
            clothForShowDTO.setcRfid(clothes.getcRfid());
            clothForShowDTO.setcTag(clothes.getcTag());
            // tag

            res.add(clothForShowDTO);
        }

        return res;
    }
}
