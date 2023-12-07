package com.pond.build;

import com.pond.build.mapper.UserMapper;
import com.pond.build.service.impl.PeopleServiceImpl;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


import com.pond.build.model.User;

@SpringBootTest
class TestApplicationTests {

    @Autowired
    private PeopleServiceImpl peopleService;


    @Test
    public void tTest(){
        System.out.println("".isBlank());
        System.out.println("".isEmpty());
    }

    @Test
    public void tt(){
        peopleService.deleteStudentById(3);
    }

    public static void PJ(String subjectCode,String subjectName,String fullSubjectName,String balanceDirection,String subjectCategoryName){
        StringBuffer temSb = new StringBuffer();
        //insert
        temSb.append("INSERT INTO FM_Account_Maintenance (ROW_GUID, CREATION_DATE,ENABLED_FLAG, subject_code, subject_name, full_subject_name, balance_direction_code,balance_direction, subject_category_code, subject_category_name) VALUES (N'");
        //uuid
        temSb.append(UUID.randomUUID().toString());
        //拼接
        temSb.append("', N'");
        //日期
        temSb.append(LocalDateTime.now());
        //拼接 + Y
        temSb.append("', N'Y', N'");
        //编码
        temSb.append(subjectCode);
        //拼接
        temSb.append("', N'");
        //科目名称(简)
        temSb.append(subjectName);
        //拼接
        temSb.append("', N'");
        //科目全称
        temSb.append(fullSubjectName);
        //拼接
        temSb.append("', N'");
        //余额方向code

        switch (balanceDirection){
            case "借方":
                temSb.append("01");
                break;
        }
        //拼接
        temSb.append("', N'");
        //余额方向
        temSb.append(balanceDirection);
        //拼接
        temSb.append("', N'");

        //科目code
        switch (subjectCategoryName) {
            case "期间费用":
                temSb.append("01");
                break;
            case "流动资产":
                temSb.append("02");
                break;
            case "流动负债":
                temSb.append("03");
                break;
        }
        //拼接
        temSb.append("', N'");
        //科目name
        temSb.append(subjectCategoryName);
        //拼接
        temSb.append("');");
        System.out.println(temSb);
    }

    public static void testXxxm(String filePath){

        try {
            FileInputStream fileInputStream = new FileInputStream(filePath);
//            sheets对象
            XSSFWorkbook sheets = new XSSFWorkbook(fileInputStream);
            //总共的工作表数量
//            int numberOfSheets = sheets.getNumberOfSheets();
            //第一个sheet
            XSSFSheet sheet = sheets.getSheetAt(0);
            //总共的行数
            int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();
            for (int i = 1; i < physicalNumberOfRows; i++) {
                XSSFRow row = sheet.getRow(i);
                //编码
                String subjectCode = String.valueOf(row.getCell(0));
                String subjectName = String.valueOf(row.getCell(1));
                String fullSubjectName = String.valueOf(row.getCell(2));
                String balanceDirection = String.valueOf(row.getCell(3));
                String subjectCategoryName = String.valueOf(row.getCell(4));
                PJ(subjectCode,subjectName,fullSubjectName,balanceDirection,subjectCategoryName);
            }


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    @Test
    void outCXKMXLXS(){
        testXxxm("C:\\Students\\11\\Desktop\\589f8653e9302ad7fca10ca8cdff5e28.xlsx");
    }


    @Test
    void mapToMap(){
        Map<String, Object> map = new HashMap<>();
        map.put("flag", "F");
        map.put("rtn", map);
        System.out.println(map);
    }


    @Autowired
    private UserMapper userMapper;

    @Test
    public void testUserMapper(){
        List<User> userList = userMapper.selectList(null);
        for (User user : userList) {
            System.out.println(user);
        }
    }




}
