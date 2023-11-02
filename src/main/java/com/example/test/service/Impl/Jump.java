package com.example.test.service.Impl;

import com.example.test.service.Play;
import org.springframework.stereotype.Service;

@Service("isMyNeedService")
public class Jump implements Play {

    @Override
    public void doing() {
        System.out.println("Jump doing...");
    }
}
