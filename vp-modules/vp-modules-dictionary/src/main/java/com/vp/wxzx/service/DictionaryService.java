package com.vp.wxzx.service;

import com.vp.wxzx.entity.ResultBody;
import com.vp.wxzx.pojo.ao.DictionaryInfoAo;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author qlh
 * @date 2023/10/15 14:08
 * @description
 */
public interface DictionaryService {
    /**
     * 查询所有单词
     * @return
     */
    ResultBody list();

    /**
     * 分页查询
     * @param pageNum
     * @param pageSize
     * @param dictionaryInfoAo
     * @return
     */
    ResultBody listByPage(Integer pageNum, Integer pageSize, DictionaryInfoAo dictionaryInfoAo);

    /**
     * 单个查询
     * @param id
     * @return
     */
    ResultBody getById(Integer id);

    /**
     * 修改单词
     * @param dictionaryInfoAo
     * @return
     */
    ResultBody update(DictionaryInfoAo dictionaryInfoAo);

    /**
     * 删除单词
     * @param id
     * @return
     */
    ResultBody delete(Integer id);

    /**
     * 插入单词
     * @param dictionaryInfoAo
     * @return
     */
    ResultBody insert(DictionaryInfoAo dictionaryInfoAo);

    /**
     * 导入Excel数据到字典
     * @param file Excel文件
     * @return 返回导入结果的响应对象
     */
    ResultBody importData(MultipartFile file);
}
