package com.pond.build.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pond.build.enums.HttpStatusCode;
import com.pond.build.mapper.NoticeMapper;
import com.pond.build.model.NoticeManagement;
import com.pond.build.model.ResponseResult;
import com.pond.build.model.Role;
import com.pond.build.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeMapper noticeMapper;

    @Override
    public ResponseResult getNoticeListByPage(Integer page, Integer pageSize, String searchText) {

        int offset = (page - 1) * pageSize;
        int limit = pageSize;

        List<Map<String, Object>> noticeByPage = noticeMapper.getNoticeByPage(offset, limit, searchText);
        Integer noticeCountByPage = noticeMapper.getNoticeCountByPage(searchText);

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("data",noticeByPage);
        resultMap.put("total",noticeCountByPage);
        resultMap.put("page",page);
        resultMap.put("pageSize",pageSize);
        return new ResponseResult(HttpStatusCode.OK.getCode(),"获取成功",resultMap);
    }

    @Override
    public ResponseResult allUserIdByNoticeId(String noticeId) {
        List<String> result = noticeMapper.allUserIdByNoticeId(noticeId);
        return new ResponseResult(HttpStatusCode.OK.getCode(),"获取成功",result);
    }
}
