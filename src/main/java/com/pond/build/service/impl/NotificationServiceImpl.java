package com.pond.build.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.pond.build.mapper.SseMapper;
import com.pond.build.model.Response.NoticeResponse;
import com.pond.build.service.NotificationService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.IOException;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 循环通知Service
 */
@Service
public class NotificationServiceImpl implements NotificationService {


    @Autowired
    private SseMapper sseMapper;

    private Map<String, HttpServletResponse> clientMap = new HashMap<>();


    @Override
    public void addClient(String userId, HttpServletResponse client) {
        clientMap.put(userId, client);
    }


    public void notifyClient(String userId, String noticeResponse) {
        HttpServletResponse client = clientMap.get(userId);
        if (client != null) {
            try {
                client.getWriter().println("data: " + noticeResponse + "\n\n" + "event: notification\\n\\n");
                client.getWriter().flush();
            } catch (IOException e) {
                // 客户端已关闭连接
                clientMap.remove(userId);
            }
        }
    }


//    @Scheduled(fixedRate = 1000) // 每10分钟执行一次
    public void sendNotification() {
        // Todo 数据库数据
        List<NoticeResponse> noticeResponses = sseMapper.getAllNeedPendingNotice();
        System.out.println("111111111111111111111111111111");
        for (NoticeResponse noticeResponse : noticeResponses) {
            notifyClient(noticeResponse.getUserId(), JSONObject.toJSONString(noticeResponse));
        }
    }

}
