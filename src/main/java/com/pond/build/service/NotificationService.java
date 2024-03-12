package com.pond.build.service;

import jakarta.servlet.http.HttpServletResponse;

//循环通知Service
public interface NotificationService {
    void addClient(String userId, HttpServletResponse client);

}
