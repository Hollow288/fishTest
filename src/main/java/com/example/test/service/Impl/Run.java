package com.example.test.service.Impl;

import com.example.test.service.Play;
import org.springframework.stereotype.Service;

@Service
public class Run implements Play {
    @Override
    public void doing() {
        System.out.println("Run doing...");
    }
}
