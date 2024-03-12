package com.pond.build.service.impl;

import com.pond.build.service.NotificationService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.IOException;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

/**
 * 循环通知Service
 */
@Service
public class NotificationServiceImpl implements NotificationService {


    private Map<String, HttpServletResponse> clientMap = new HashMap<>();


    @Override
    public void addClient(String userId, HttpServletResponse client) {
        clientMap.put(userId, client);
    }


    public void notifyClient(String userId, String message) {
        HttpServletResponse client = clientMap.get(userId);
        if (client != null) {
            try {
                client.getWriter().println("data: " + message + "\n\n");
                client.getWriter().flush();
            } catch (IOException e) {
                // 客户端已关闭连接
                clientMap.remove(userId);
            }
        }
    }


    @Scheduled(fixedRate = 10 * 60 * 1000) // 每10分钟执行一次
    public void sendNotification() {
        // Todo 数据库数据
        String userId = "123"; // 假设要通知的用户 ID 是 123
        String message = LocalTime.now().toString();
        notifyClient(userId, message);
    }

}
