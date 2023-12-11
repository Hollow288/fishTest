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


    //INSERT INTO QU_System_file (row_guid,BOE_TYPE_CODE, BOE_TYPE_NAME, OPERATION_TYPE_CODE, OPERATION_TYPE_NAME, FLOW_STATUS, DEPT_ID, DEPT_NAME, ENABLED_FLAG, File_code, File_name, File_version, Main_dept_id, Main_dept_name, modify_type_code, modify_type_name, Release_date, File_state, Implement_date) VALUES (N'01005817-5cc3-4719-afc3-d389f7cd1472', N'QU_System_file', N'体系文件管理', N'QU_System_file', N'体系文件管理', N'正常完成', 4, N'市场部', N'Y', 5, N'文件编号', N'文件名称文件名称2', N'文件版次2', 6, N'技术部', N'02', N'修订', N'2023-12-04', N'正常运行', N'2023-12-04');
    @Test
    public Map<String, String> deptNameExcelToSql(){
        try {
            Map<String, String> deptMap = new HashMap<>();
            FileInputStream fileInputStream = new FileInputStream("C:\\Users\\11\\Desktop\\部门.xlsx");
//            sheets对象
            XSSFWorkbook sheets = new XSSFWorkbook(fileInputStream);
            //总共的工作表数量
//            int numberOfSheets = sheets.getNumberOfSheets();
            //第一个sheet
            XSSFSheet sheet = sheets.getSheetAt(0);
            //总共的行数
            int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();
            for (int i = 0; i < physicalNumberOfRows; i++) {
                XSSFRow row = sheet.getRow(i);
                //编码
                String deptName = String.valueOf(row.getCell(0));
                String deptId = String.valueOf(row.getCell(1));
                deptMap.put(deptName,deptId);
            }
//            System.out.println(deptMap);
            return deptMap;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Test
    public void fileMangerExcelToSql(){
        try {
            FileInputStream fileInputStream = new FileInputStream("C:\\Users\\11\\Desktop\\体系文件管理导入模板.xlsx");
//            sheets对象
            XSSFWorkbook sheets = new XSSFWorkbook(fileInputStream);
            //总共的工作表数量
//            int numberOfSheets = sheets.getNumberOfSheets();
            //第一个sheet
            XSSFSheet sheet = sheets.getSheetAt(0);
            //总共的行数
            int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();
            for (int i = 2; i < physicalNumberOfRows; i++) {
                XSSFRow row = sheet.getRow(i);
                //编码
                String fileCode = String.valueOf(row.getCell(0));
                String fileName = String.valueOf(row.getCell(1));
                String fileVersion = String.valueOf(row.getCell(2));
                String mainDeptName = String.valueOf(row.getCell(3));
                String releaseDate = String.valueOf(row.getCell(4));
                String implementDate = String.valueOf(row.getCell(5));
//                System.out.println("11");
                this.pjFileManger(fileCode,fileName,fileVersion,mainDeptName,releaseDate,implementDate);
//                deptMap.put(deptName,deptId);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //INSERT INTO QU_System_file (row_guid,BOE_TYPE_CODE, BOE_TYPE_NAME, OPERATION_TYPE_CODE, OPERATION_TYPE_NAME, FLOW_STATUS, DEPT_ID, DEPT_NAME, ENABLED_FLAG, File_code, File_name, File_version, Main_dept_id, Main_dept_name, modify_type_code, modify_type_name, Release_date, File_state, Implement_date) VALUES (N'01005817-5cc3-4719-afc3-d389f7cd1472', N'QU_System_file', N'体系文件管理', N'QU_System_file', N'体系文件管理', N'正常完成', 4, N'市场部', N'Y', N'文件编号', N'文件名称文件名称2', N'文件版次2', 6, N'技术部', N'02', N'修订', N'2023-12-04', N'正常运行', N'2023-12-04');

    public void pjFileManger(String fileCode,String fileName,String fileVersion,String mainDeptName,String releaseDate,String implementDate ){

        //部门列表->map
        Map<String, String> stringStringMap = this.deptNameExcelToSql();

        //检查是否有该部门
        boolean isHaving = stringStringMap.containsKey(mainDeptName);


        StringBuffer temSb = new StringBuffer();
        //insert
        temSb.append("INSERT INTO QU_System_file (row_guid,BOE_TYPE_CODE, BOE_TYPE_NAME, OPERATION_TYPE_CODE, OPERATION_TYPE_NAME, FLOW_STATUS, DEPT_ID, DEPT_NAME, ENABLED_FLAG, File_code, File_name, File_version, Main_dept_id, Main_dept_name, modify_type_code, modify_type_name, Release_date, File_state, Implement_date) VALUES (N'");
        //uuid
        temSb.append(UUID.randomUUID().toString());
        //拼接
        temSb.append("', N'QU_System_file', N'体系文件管理', N'QU_System_file', N'体系文件管理', N'正常完成', ");
        //部门id
        if(isHaving){
            temSb.append(stringStringMap.get(mainDeptName));
        }else{
            temSb.append("null");
        }
        //拼接
        temSb.append(", N'");
        //部门名称
        temSb.append(mainDeptName);
        //是否启用
        temSb.append("', N'Y', N'");
        //', N' 文件code
        temSb.append(fileCode);
        //拼接
        temSb.append("', N'");
        //文件名称  文件名称文件名称2', N'文件版次2', 6, N'技术部', N'01', N'新增', N'2023-12-04', N'正常运行', N'2023-12-04');
//        String fileName,String fileVersion,String mainDeptName,String releaseDate,String implementDate
        temSb.append(fileName);
        //拼接
        temSb.append("', N'");
        //文件版次
        temSb.append(fileVersion);
        //拼接
        temSb.append("',");
        //主板部门id
        if(isHaving){
            temSb.append(stringStringMap.get(mainDeptName));
        }else{
            temSb.append("null");
        }
        //拼接
        temSb.append(", N'");
        //部门名称
        temSb.append(mainDeptName);
        //拼接 01 -新增
        temSb.append("', N'01', N'新增', N'");
        //发布日期
        temSb.append(releaseDate);
        //拼接
        temSb.append("', N'正常运行', N'");
        //实施日期
        temSb.append(implementDate);
        //拼接
        temSb.append("');");
        System.out.println(temSb);
        System.out.println("go");

    }
}
