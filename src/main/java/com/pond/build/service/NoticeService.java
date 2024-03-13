package com.pond.build.service;

import com.pond.build.model.ResponseResult;

import java.util.HashMap;

public interface NoticeService {
    ResponseResult getNoticeListByPage(Integer page, Integer pageSize, String searchText);

    ResponseResult allUserIdByNoticeId(String noticeId);

    ResponseResult createNotice(HashMap<String, Object> notices);

    ResponseResult deleteNoticeByIds(HashMap<String, Object> noticeIds);

    ResponseResult updateNoticeById(String noticeId, HashMap<String, Object> notice);

    ResponseResult updateAndPublishNotice(String noticeId, HashMap<String, Object> notice);

    ResponseResult createAndPublishNotice(HashMap<String, Object> notice);
}
