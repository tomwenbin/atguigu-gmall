package com.atguigu.gmall.product.service.impl;

import com.atguigu.gmall.model.product.BaseAttrInfo;
import com.atguigu.gmall.model.product.BaseAttrValue;
import com.atguigu.gmall.product.mapper.BaseAttrValueMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.atguigu.gmall.product.service.BaseAttrInfoService;
import com.atguigu.gmall.product.mapper.BaseAttrInfoMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
* @author TomDog
* @description 针对表【base_attr_info(属性表)】的数据库操作Service实现
* @createDate 2022-08-24 16:13:28
*/
@Service
public class BaseAttrInfoServiceImpl extends ServiceImpl<BaseAttrInfoMapper, BaseAttrInfo>
    implements BaseAttrInfoService{

    @Resource
     BaseAttrInfoMapper baseAttrInfoMapper;
    @Resource
    BaseAttrValueMapper baseAttrValueMapper;

    @Override
    public List<BaseAttrInfo> getAttrInfoAndValueByCategoryId( long category1Id,
                                                              long category2Id,
                                                              long category3Id) {
        List<BaseAttrInfo> infos = baseAttrInfoMapper.getAttrInfoAndValueByCategoryId(category1Id,category2Id,category3Id);
        return infos;
    }

    // 新增修改二合一
    @Override
    public void saveAttrInfo(BaseAttrInfo baseAttrInfo) {
       //判断bai 表中的id 是否为空
        if (baseAttrInfo.getId() != null){
         //  删除修改
            updateBaseAttrInfo(baseAttrInfo);
        }else {
            //为空进行新增操作
            addBaseAttrInfo(baseAttrInfo);
        }
    }

    private void updateBaseAttrInfo(BaseAttrInfo baseAttrInfo) {
        baseAttrInfoMapper.updateById(baseAttrInfo);
        List<BaseAttrValue> attrValueList = baseAttrInfo.getAttrValueList();
        // 先删除操作  思路:删除的话  之前的数据集的id不存在于新修改的增的id 集中
        List<Long> vids = new ArrayList<>();
        for (BaseAttrValue attrValueId : attrValueList) {
            Long ids = attrValueId.getId();
            vids.add(ids);   //得到新修改数据集中id集合
        }
        if (vids.size() > 0){
            //部分删除
            QueryWrapper<BaseAttrValue> deleteWrapper = new QueryWrapper<>();
            deleteWrapper.eq("attr_id", baseAttrInfo.getId());
            deleteWrapper.notIn("id", vids);
            baseAttrValueMapper.delete(deleteWrapper);
        }else {
            //全部删除
            QueryWrapper<BaseAttrValue> deleteWrapper = new QueryWrapper<>();
            deleteWrapper.eq("attr_id", baseAttrInfo.getId());
            baseAttrValueMapper.delete(deleteWrapper);
        }
        //数据的修改或者增加
        for (BaseAttrValue attrV : attrValueList) {
            if ( attrV.getId() == null){
                //数据回增  直接增加
                attrV.setAttrId(baseAttrInfo.getId());
                baseAttrValueMapper.insert(attrV);
            }else {
                // 对value的数据进行修改
                baseAttrValueMapper.updateById(attrV);
            }
        }
    }

    private void addBaseAttrInfo(BaseAttrInfo baseAttrInfo) {
        //增加属性名
        baseAttrInfoMapper.insert(baseAttrInfo);
        Long infoId = baseAttrInfo.getId();
        //增加属性值
        List<BaseAttrValue> attrValueList = baseAttrInfo.getAttrValueList();
        for (BaseAttrValue attrValue : attrValueList) {
            attrValue.setAttrId(infoId);
            baseAttrValueMapper.insert(attrValue);
        }
    }
}




