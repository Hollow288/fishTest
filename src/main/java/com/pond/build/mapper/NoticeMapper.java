package com.pond.build.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pond.build.model.NoticeManagement;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface NoticeMapper extends BaseMapper<NoticeManagement> {
    List<Map<String,Object>> getNoticeByPage(@Param("offset") Integer offset,@Param("limit") Integer limit,@Param("searchText") String searchText);

    Integer getNoticeCountByPage(@Param("searchText") String searchText);

    List<String> allUserIdByNoticeId(@Param("noticeId")String noticeId);

    void insertPendingNotification(@Param("noticeId") String noticeId,@Param("userIds") List<String> userIds);

    void deleteNoticeById(@Param("ids") List<String> ids,@Param("userId") String userId,@Param("date") Date date);

    void deletePendingNotificationByNoticeIds(@Param("ids") List<String> ids);

    void processedNoticeById(@Param("pendingId") String pendingId);
}
