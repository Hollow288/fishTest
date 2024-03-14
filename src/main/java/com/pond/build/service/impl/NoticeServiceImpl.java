package com.pond.build.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;

import com.pond.build.enums.HttpStatusCode;
import com.pond.build.mapper.NoticeMapper;
import com.pond.build.mapper.SseMapper;
import com.pond.build.model.*;
import com.pond.build.model.Response.NoticeResponse;
import com.pond.build.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeMapper noticeMapper;

    @Autowired
    private SseMapper sseMapper;

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

    @Override
    public ResponseResult createNotice(HashMap<String, Object> notices) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        User userInfo = loginUser.getUser();

        NoticeManagement noticeManagement = new NoticeManagement();
        noticeManagement.setCreateBy(String.valueOf(userInfo.getUserId()));
        noticeManagement.setCreateTime(new Date());
        noticeManagement.setMessage((String) notices.get("message"));
        int insert = noticeMapper.insert(noticeManagement);

        String noticeId = noticeManagement.getNoticeId();

        List<String> userIds = (List<String>) notices.get("userIds");

        noticeMapper.insertPendingNotification(noticeId,userIds);

        return new ResponseResult(HttpStatusCode.OK.getCode(),"操作成功");

    }

    @Override
    public ResponseResult deleteNoticeByIds(HashMap<String, Object> noticeIds) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        User userInfo = loginUser.getUser();

        List<String> ids = (List<String>)noticeIds.get("ids");

        noticeMapper.deleteNoticeById(ids,userInfo.getUserId().toString(),new Date());


        noticeMapper.deletePendingNotificationByNoticeIds(ids);


        return new ResponseResult(HttpStatusCode.OK.getCode(),"操作成功");
    }

    @Override
    public ResponseResult updateNoticeById(String noticeId, HashMap<String, Object> notice) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        User userInfo = loginUser.getUser();

        String message = (String) notice.get("message");
        List<String> userIds = (List<String>) notice.get("userIds");

        //先改notice_management
        UpdateWrapper<NoticeManagement> noticeManagementUpdateWrapper = new UpdateWrapper<>();
        noticeManagementUpdateWrapper.eq("notice_id",noticeId);
        noticeManagementUpdateWrapper.set("message",message);
        noticeManagementUpdateWrapper.set("update_by",userInfo.getUserId().toString());
        noticeManagementUpdateWrapper.set("update_time",new Date());

        noticeMapper.update(null, noticeManagementUpdateWrapper);

        List<String> noticeIds = new ArrayList<>();
        noticeIds.add(noticeId);

        //删掉之前的PendingNotification
        noticeMapper.deletePendingNotificationByNoticeIds(noticeIds);

        //新增新的PendingNotification
        noticeMapper.insertPendingNotification(noticeId,userIds);

        return new ResponseResult(HttpStatusCode.OK.getCode(),"操作成功");
    }

    @Override
    public ResponseResult updateAndPublishNotice(String noticeId, HashMap<String, Object> notice) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        User userInfo = loginUser.getUser();

        String message = (String) notice.get("message");
        List<String> userIds = (List<String>) notice.get("userIds");

        //先改notice_management
        UpdateWrapper<NoticeManagement> noticeManagementUpdateWrapper = new UpdateWrapper<>();
        noticeManagementUpdateWrapper.eq("notice_id",noticeId);
        noticeManagementUpdateWrapper.set("message",message);
        noticeManagementUpdateWrapper.set("update_by",userInfo.getUserId().toString());
        noticeManagementUpdateWrapper.set("update_time",new Date());
        noticeManagementUpdateWrapper.set("release_date",new Date());
        noticeManagementUpdateWrapper.set("release_by",userInfo.getUserId().toString());

        noticeMapper.update(null, noticeManagementUpdateWrapper);

        List<String> noticeIds = new ArrayList<>();
        noticeIds.add(noticeId);

        //删掉之前的PendingNotification
        noticeMapper.deletePendingNotificationByNoticeIds(noticeIds);

        //新增新的PendingNotification
        noticeMapper.insertPendingNotification(noticeId,userIds);

        return new ResponseResult(HttpStatusCode.OK.getCode(),"操作成功");
    }

    @Override
    public ResponseResult createAndPublishNotice(HashMap<String, Object> notice) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        User userInfo = loginUser.getUser();

        NoticeManagement noticeManagement = new NoticeManagement();
        noticeManagement.setCreateBy(String.valueOf(userInfo.getUserId()));
        noticeManagement.setCreateTime(new Date());
        noticeManagement.setReleaseDate(new Date());
        noticeManagement.setMessage((String) notice.get("message"));
        noticeManagement.setReleaseBy(String.valueOf(userInfo.getUserId()));
        int insert = noticeMapper.insert(noticeManagement);

        String noticeId = noticeManagement.getNoticeId();

        List<String> userIds = (List<String>) notice.get("userIds");

        noticeMapper.insertPendingNotification(noticeId,userIds);

        return new ResponseResult(HttpStatusCode.OK.getCode(),"操作成功");

    }

    @Override
    public ResponseResult noticesByUserId(String userId) {

        List<NoticeResponse> noticeResponses = sseMapper.getAllNeedPendingNoticeByUserId(userId);

        return new ResponseResult(HttpStatusCode.OK.getCode(),"操作成功",noticeResponses);
    }

    @Override
    public ResponseResult processedNoticeById(String pendingId, String userId) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        User userInfo = loginUser.getUser();
        if(!Objects.equals(userInfo.getUserId().toString(),userId)){
            return new ResponseResult(HttpStatusCode.REQUEST_SERVER_ERROR.getCode(),"不能已阅读别人的通知");
        }

        noticeMapper.processedNoticeById(pendingId);

        return new ResponseResult(HttpStatusCode.OK.getCode(),"操作成功");
    }
}
