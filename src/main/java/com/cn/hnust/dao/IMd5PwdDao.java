package com.cn.hnust.dao;

import org.springframework.stereotype.Repository;

import com.cn.hnust.entity.md5Pwd;

@Repository
public interface IMd5PwdDao {
    int deleteByPrimaryKey(Integer id);

    int insert(md5Pwd record);

    int insertSelective(md5Pwd record);

    md5Pwd selectByPrimaryKey(Integer id);
    
    md5Pwd selectByContent(String content);

    int updateByPrimaryKeySelective(md5Pwd record);

    int updateByPrimaryKey(md5Pwd record);
}