package com.pond.build.mapper;

import com.pond.build.model.Response.NoticeResponse;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface SseMapper {


    List<NoticeResponse> getAllNeedPendingNotice();

    List<NoticeResponse> getAllNeedPendingNoticeByUserId(String userId);
}
