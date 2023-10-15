package com.vp.wxzx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.vp.wxzx.entity.ResultBody;
import com.vp.wxzx.mapper.DictionaryMapper;
import com.vp.wxzx.pojo.ao.DictionaryInfoAo;
import com.vp.wxzx.pojo.dto.DictionaryInfoDto;
import com.vp.wxzx.service.DictionaryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author qlh
 * @date 2023/10/15 14:08
 * @description
 */
@Service
public class DictionaryServiceImpl implements DictionaryService {
    @Autowired
    DictionaryMapper dictionaryMapper;

    /**
     * 查询所有单词
     * @return
     */
    @Override
    public ResultBody list() {
        List<DictionaryInfoDto> list = dictionaryMapper.getAllDictionary();
        return ResultBody.ok().data("rows", list.size()).data("words", list);
    }

    /**
     * 分页查询
     * @param pageNum
     * @param pageSize
     * @param dictionaryInfoAo 查询条件
     * @return
     */
    @Override
    public ResultBody listByPage(Integer pageNum, Integer pageSize, DictionaryInfoAo dictionaryInfoAo) {
        Page<DictionaryInfoDto> page = new Page<>(pageNum, pageSize);
        QueryWrapper<DictionaryInfoDto> wrapper = new QueryWrapper<>();

        // 模糊查询
        if(dictionaryInfoAo.getChinese() != null) wrapper.like("chinese", dictionaryInfoAo.getChinese());
        // 前缀查询
        else if(dictionaryInfoAo.getEnglish() != null) wrapper.likeRight("english", dictionaryInfoAo.getEnglish());
        // 词性判断
        else if(dictionaryInfoAo.getProperty() != null) wrapper.like("property", dictionaryInfoAo.getProperty());
        // 没有查询条件
        else wrapper = null;

        page = dictionaryMapper.selectPage(page, wrapper);
        long total = page.getTotal();
        List<DictionaryInfoDto> records = page.getRecords();
        return ResultBody.ok().data("total", total).data("words", records);
    }

    /**
     * 单个查询
     * @param id
     * @return
     */
    @Override
    public ResultBody getById(Integer id) {
        DictionaryInfoDto dictionaryInfoDto = dictionaryMapper.selectById(id);
        if(dictionaryInfoDto == null) return ResultBody.error().message("暂无此数据");
        return ResultBody.ok().data("word", dictionaryInfoDto);
    }

    /**
     * 修改单词
     * @param dictionaryInfoAo
     * @return
     */
    @Override
    public ResultBody update(DictionaryInfoAo dictionaryInfoAo) {
        DictionaryInfoDto dictionaryInfoDto = new DictionaryInfoDto();
        BeanUtils.copyProperties(dictionaryInfoAo, dictionaryInfoDto);
        try {
            dictionaryMapper.updateById(dictionaryInfoDto);
            return ResultBody.ok().message("更新成功");
        }catch (Exception e){
            return ResultBody.error().message("更新失败");
        }
    }

    /**
     * 删除单词
     * @param id
     * @return
     */
    @Override
    public ResultBody delete(Integer id) {
        dictionaryMapper.deleteById(id);
        return ResultBody.ok().message("删除成功");
    }

    /**
     * 插入单词
     * @param dictionaryInfoAo
     * @return
     */
    @Override
    public ResultBody insert(DictionaryInfoAo dictionaryInfoAo) {
        DictionaryInfoDto dictionaryInfoDto = new DictionaryInfoDto();
        BeanUtils.copyProperties(dictionaryInfoAo, dictionaryInfoDto);
        return dictionaryMapper.insert(dictionaryInfoDto) > 0 ? ResultBody.ok().message("插入成功") : ResultBody.ok().message("插入失败");

    }

    /**
     * 导入Excel数据到字典
     * @param file Excel文件
     * @return 返回导入结果的响应对象
     */
    @Override
    public ResultBody importData(MultipartFile file) {
        // TODO: 实现导入Excel数据到字典的逻辑
        return null;
    }
}
