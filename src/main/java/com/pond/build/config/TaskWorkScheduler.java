package com.pond.build.config;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.pond.build.mapper.NoticeMapper;
import com.pond.build.mapper.WorkArrangementMapper;
import com.pond.build.model.NoticeManagement;
import com.pond.build.model.WorkArrangement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Component
public class TaskWorkScheduler {

    @Autowired
    private WorkArrangementMapper workArrangementMapper;

    @Autowired
    private NoticeMapper noticeMapper;

    @Scheduled(cron = "0 0 1 * * ?")
//    @Scheduled(fixedDelay = 600000)
    public void WorkArrangementTask() {

        //明天
        LocalDateTime tomorrow = LocalDateTime.now().plusDays(1);

        int year = tomorrow.getYear();
        int month = tomorrow.getMonthValue();
        int date = tomorrow.getDayOfMonth();

        QueryWrapper<WorkArrangement> workArrangementQueryWrapper = new QueryWrapper<>();
        workArrangementQueryWrapper.eq("year",year);
        workArrangementQueryWrapper.eq("month",month);
        workArrangementQueryWrapper.eq("date",date);
        workArrangementQueryWrapper.eq("del_flag","0");
        //明天的代办
        List<WorkArrangement> workArrangements = workArrangementMapper.selectList(workArrangementQueryWrapper);
        if(!CollectionUtils.isEmpty(workArrangements)){
            for (WorkArrangement workArrangement : workArrangements) {
                String arrangePeople = workArrangement.getArrangePeople();
                String[] split = arrangePeople.split(",");
                List<String> userList = Arrays.asList(split);
                if(!CollectionUtils.isEmpty(userList)){
                    NoticeManagement noticeManagement = new NoticeManagement();
                    noticeManagement.setMessage("待办通知：" + workArrangement.getAgencyMatters());
                    noticeManagement.setDelFlag("0");
                    noticeManagement.setReleaseDate(new Date());
                    noticeMapper.insert(noticeManagement);
                    noticeMapper.insertPendingNotification(noticeManagement.getNoticeId(),userList);
                }
            }
        }

    }
}
