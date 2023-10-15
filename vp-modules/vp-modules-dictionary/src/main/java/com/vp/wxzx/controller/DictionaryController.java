package com.vp.wxzx.controller;

import com.vp.wxzx.entity.ResultBody;
import com.vp.wxzx.pojo.ao.DictionaryInfoAo;
import com.vp.wxzx.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author qlh
 * @date 2023/10/15 14:07
 * @description 词典功能
 */

@RestController
@RequestMapping("dictionary")
public class DictionaryController {
    @Autowired
    DictionaryService dictionaryService;

    /**
     * 查询所有单词
     * @return
     */
    @GetMapping("/list")
    public ResultBody list(){
        return dictionaryService.list();
    }

    /**
     * 查询所有单词（分页查询）
     * @param pageNum 当前页码
     * @param pageSize 每页显示的记录数
     * @return
     */
    @GetMapping("list/page")
    public ResultBody listByPage(@RequestParam(defaultValue = "1") Integer pageNum,
                           @RequestParam(defaultValue = "10") Integer pageSize,
                           @RequestBody DictionaryInfoAo dictionaryInfoAo) {
        return dictionaryService.listByPage(pageNum, pageSize, dictionaryInfoAo);
    }

    /**
     * 根据ID查询单词
     * @param id 单词ID
     * @return
     */
    @GetMapping
    public ResultBody getById(@RequestParam Integer id) {
        return dictionaryService.getById(id);
    }

    /**
     * 修改单词
     * @param dictionaryInfoAo 更新后的单词信息
     * @return
     */
    @PutMapping("/update")
    public ResultBody update(@RequestBody DictionaryInfoAo dictionaryInfoAo) {
        return dictionaryService.update(dictionaryInfoAo);
    }

    /**
     * 删除单词
     * @param id 单词ID
     * @return
     */
    @DeleteMapping("/delete")
    public ResultBody delete(@RequestParam Integer id) {
        return dictionaryService.delete(id);
    }

    /**
     * 添加单词
     * @param dictionaryInfoAo 新的单词信息
     * @return
     */
    @PostMapping("save")
    public ResultBody save(@RequestBody DictionaryInfoAo dictionaryInfoAo) {
        return dictionaryService.insert(dictionaryInfoAo);
    }

    /**
     * 导入Excel数据到字典
     * @param file Excel文件
     * @return 返回导入结果的响应对象
     */
    @PostMapping("file")
    public ResultBody importData(@RequestParam("file") MultipartFile file){
        return dictionaryService.importData(file);
    }
}
