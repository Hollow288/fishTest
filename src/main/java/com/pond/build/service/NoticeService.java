package com.pond.build.service;

import com.pond.build.model.ResponseResult;

public interface NoticeService {
    ResponseResult getNoticeListByPage(Integer page, Integer pageSize, String searchText);

    ResponseResult allUserIdByNoticeId(String noticeId);
}
