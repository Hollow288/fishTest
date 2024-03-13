package com.pond.build.controller;

import com.pond.build.service.NotificationService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SseController {

    @Autowired
    private NotificationService notificationService;


    @GetMapping("/sse/notification")
    public void handleSSE(@RequestParam("userId")String userId, HttpServletResponse response) {
        response.setContentType(MediaType.TEXT_EVENT_STREAM_VALUE);
        response.setCharacterEncoding("UTF-8");
        notificationService.addClient(userId, response);
    }


}
