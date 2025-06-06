package com.pond.build;

import com.pond.build.mapper.UserMapper;
import com.pond.build.service.impl.PeopleServiceImpl;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


import com.pond.build.model.User;
import org.springframework.util.CollectionUtils;

@SpringBootTest
class TestApplicationTests {

//    @Autowired
//    private PeopleServiceImpl peopleService;
//
//
//    //Java 8 中的 java.time 包是一个全新的日期和时间 API，被设计来纠正之前 Date 和 Calendar API 中存在的多个问题
//    @Test
//    void testDate() {
//        LocalDate date = LocalDate.now(); // 获取今天的日期
//        System.out.println(date);//2023-10-12
//
//        LocalTime time = LocalTime.now(); // 获取今天的时间
//        System.out.println(time);//15:38:02.758
//
//        LocalDateTime dateTime = LocalDateTime.now(); // 获取今天的日期时间
//        System.out.println(dateTime);//2023-10-12T15:39:04.109
//
//        LocalDate specificDate = LocalDate.of(2023, Month.JANUARY, 1); // 创建一个特定的日期
//        System.out.println(specificDate);//2023-01-01
//
//        LocalDateTime specificDateTime = LocalDateTime.of(2023, Month.JANUARY, 1,16,30,45); // 创建一个特定的日期
//        System.out.println(specificDateTime);//2023-01-01T16:30:45
//
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        String formatted = specificDateTime.format(formatter);
//        System.out.println(formatted); // 输出: 2023-01-01 16:30
//
//        //SimpleDateFormat 属于 java.text 包，并且主要与 java.util.Date 类进行交互
//        // 线程不安全，多线程使用单个实例会出现问题
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date dateNew = new Date();
//        String simpleDate = simpleDateFormat.format(dateNew);
//        System.out.println(simpleDate);//2023-10-12 15:31:19
//
//
//        //操作
//
////        加/减天数:
//        LocalDate tomorrow = date.plusDays(1);
//        LocalDate yesterday = date.minusDays(1);
//
////        加/减周数:
//        LocalDate nextWeek = date.plusWeeks(1);
//        LocalDate lastWeek = date.minusWeeks(1);
//
////        加/减月数:
//        LocalDate nextMonth = date.plusMonths(1);
//        LocalDate lastMonth = date.minusMonths(1);
//
////        加/减年数:
//        LocalDate nextYear = date.plusYears(1);
//        LocalDate lastYear = date.minusYears(1);
//    }
//
//
//    @Test
//    void contextLoads() {
//
//        outTest("row_guid\n" +
//                "CREATION_DATE\n" +
//                "CREATED_BY\n" +
//                "CREATED_BY_NAME\n" +
//                "CREATED_BY_NUMBER\n" +
//                "LAST_UPDATE_DATE\n" +
//                "LAST_UPDATED_BY\n" +
//                "LAST_UPDATED_BY_NAME\n" +
//                "LAST_UPDATED_BY_NUMBER\n" +
//                "BOE_TYPE_CODE\n" +
//                "BOE_TYPE_NAME\n" +
//                "OPERATION_TYPE_CODE\n" +
//                "OPERATION_TYPE_NAME\n" +
//                "FLOW_STATUS\n" +
//                "DEPT_ID\n" +
//                "DEPT_CODE\n" +
//                "DEPT_NAME\n" +
//                "ENABLED_FLAG\n" +
//                "ATTRIBUTE1\n" +
//                "ATTRIBUTE2\n" +
//                "ATTRIBUTE3\n" +
//                "ATTRIBUTE4\n" +
//                "ATTRIBUTE5\n" +
//                "Boe_num\n" +
//                "CREATED_year\n" +
//                "employee_id\n" +
//                "employee_name\n" +
//                "employee_DEPT_ID\n" +
//                "employee_DEPT_CODE\n" +
//                "employee_DEPT_NAME\n" +
//                "Professional_post\n" +
//                "Profession_code\n" +
//                "Profession_name\n" +
//                "Main_technical_work\n" +
//                "Training_situation\n" +
//                "Publication_status\n" +
//                "Technical_director_id\n" +
//                "Technical_director_name\n" +
//                "Professional_knowledge_code\n" +
//                "Professional_knowledge_name\n" +
//                "Professional_skill_code\n" +
//                "Professional_skill_name\n" +
//                "Working_ability_code\n" +
//                "Working_ability_name\n" +
//                "Work_performance_code\n" +
//                "Work_performance_name\n" +
//                "Employee_assessment_code\n" +
//                "Employee_assessment_name\n" +
//                "design_obey_task_code\n" +
//                "design_obey_task_name\n" +
//                "design_follow_law_code\n" +
//                "design_follow_law_name\n" +
//                "design_rating_code\n" +
//                "design_rating_name\n" +
//                "project_obey_task_code\n" +
//                "project_obey_task_name\n" +
//                "project_difficulty_level_code\n" +
//                "project_difficulty_level_name\n" +
//                "annual_drawing_quantity\n" +
//                "verification_quantity\n" +
//                "review_quantity\n" +
//                "Project_overall_workload_code\n" +
//                "Project_overall_workload_name\n" +
//                "Project_workload_code\n" +
//                "Project_workload_name\n" +
//                "schedule_and_quality_code\n" +
//                "schedule_and_quality_name\n" +
//                "org_coord_ability_code\n" +
//                "org_coord_ability_name\n" +
//                "project_rating_code\n" +
//                "project_rating_name\n" +
//                "quality_complaints_code\n" +
//                "quality_complaints_name\n" +
//                "rectification_status_code\n" +
//                "rectification_status_name\n" +
//                "process_control_level_code\n" +
//                "process_control_level_name\n" +
//                "quality_awareness_code\n" +
//                "quality_awareness_name\n" +
//                "Quality_project_rating_code\n" +
//                "Quality_project_rating_name\n" +
//                "technical_training_code\n" +
//                "technical_training_name\n" +
//                "technology_promotion_code\n" +
//                "technology_promotion_name\n" +
//                "technical_rating_code\n" +
//                "technical_rating_name\n" +
//                "violation_attendance_code\n" +
//                "violation_attendance_name\n", "uid\n" +
//                "创建时间\n" +
//                "创建人id\n" +
//                "创建人name\n" +
//                "创建人code\n" +
//                "最近更新时间\n" +
//                "最近更新人id\n" +
//                "最近更新人\n" +
//                "最近更新人code\n" +
//                "单据类型-code\n" +
//                "单据类型-name\n" +
//                "业务类型-code\n" +
//                "业务类型-name\n" +
//                "流程状态\n" +
//                "登记部门id\n" +
//                "登记部门code\n" +
//                "登记部门name\n" +
//                "是否逻辑删除\n" +
//                "备用字段1\n" +
//                "备用字段2\n" +
//                "备用字段3\n" +
//                "备用字段4\n" +
//                "备用字段5\n" +
//                "单据编号\n" +
//                "年度\n" +
//                "人员id\n" +
//                "人员姓名\n" +
//                "部门id\n" +
//                "部门code\n" +
//                "部门name\n" +
//                "职称\n" +
//                "专业code\n" +
//                "专业name\n" +
//                "本年度承担的主要技术工作\n" +
//                "培训情况\n" +
//                "论文发表情况\n" +
//                "总工程师（技术负责人）\n" +
//                "总工程师（技术负责人）\n" +
//                "专业知识评审结果code\n" +
//                "专业知识评审结果\n" +
//                "专业技能评审结果code\n" +
//                "专业技能评审结果\n" +
//                "工作能力评审结果code\n" +
//                "工作能力评审结果\n" +
//                "工作业绩评审结果code\n" +
//                "工作业绩评审结果\n" +
//                "员工考核登记code\n" +
//                "员工考核登记\n" +
//                "1、服从任务安排\n" +
//                "1、服从任务安排\n" +
//                "2、遵守劳动纪律\n" +
//                "2、遵守劳动纪律\n" +
//                "考核等级\n" +
//                "考核等级\n" +
//                "1、服从任务安排\n" +
//                "1、服从任务安排\n" +
//                "2、在手及完成项目难易程度\n" +
//                "2、在手及完成项目难易程度\n" +
//                "年出图量（张）\n" +
//                "校核量（张）\n" +
//                "审核量（张）\n" +
//                "3、总体工作负荷(EPC及监理人员请填4)\n" +
//                "3、总体工作负荷(EPC及监理人员请填4)\n" +
//                "4、工作负荷（EPC及监理人员，公司全年共246工作日）\n" +
//                "4、工作负荷（EPC及监理人员，公司全年共246工作日）\n" +
//                "5、工期及质量\n" +
//                "5、工期及质量\n" +
//                "6、组织协调能力\n" +
//                "6、组织协调能力\n" +
//                "考核等级\n" +
//                "考核等级\n" +
//                "1、质量投诉\n" +
//                "1、质量投诉\n" +
//                "2、配合质量整改情况（若无质量问题则不填）\n" +
//                "2、配合质量整改情况（若无质量问题则不填）\n" +
//                "3、过程控制水平（日常工作中对管理体系文件的理解和执行情况）\n" +
//                "3、过程控制水平（日常工作中对管理体系文件的理解和执行情况）\n" +
//                "4、质量意识（质量投诉、质量检查等反馈的岗位职责履行情况）\n" +
//                "4、质量意识（质量投诉、质量检查等反馈的岗位职责履行情况）\n" +
//                "考核等级\n" +
//                "考核等级\n" +
//                "1、技术培训\n" +
//                "1、技术培训\n" +
//                "2、是否积极配合软件应用等技术推广（研发）\n" +
//                "2、是否积极配合软件应用等技术推广（研发）\n" +
//                "考核等级\n" +
//                "考核等级\n" +
//                "是否违反公司考勤等规章制度\n" +
//                "是否违反公司考勤等规章制度\n", "hr_Technical_post_assessment");
//    }
//
//    public static void outTest(String zd, String bz, String bm) {
//        String[] zdList = zd.split("\n");
//        String[] bzList = bz.split("\n");
//        StringBuffer resultSb = new StringBuffer();
//        StringBuffer temSb = new StringBuffer();
//        for (int i = 0; i < zdList.length; i++) {
//            temSb.append("exec sp_addextendedproperty 'MS_Description', N'");
//            temSb.append(bzList[i]);
//            temSb.append("', 'SCHEMA', 'dbo', 'TABLE', '");
//            temSb.append(bm);
//            temSb.append("', 'COLUMN','");
//            temSb.append(zdList[i]);
//            temSb.append("'");
//            resultSb.append(temSb + "\n" + "go" + "\n\n");
//            temSb.setLength(0);
//        }
//        System.out.println(resultSb);
//    }
//
//
//    @Test
//    public void tTest(){
//        System.out.println("".isBlank());
//        System.out.println("".isEmpty());
//    }
//
//    @Test
//    public void tt(){
//        peopleService.deleteStudentById(3);
//    }
//
//    public static void PJ(String subjectCode,String subjectName,String fullSubjectName,String balanceDirection,String subjectCategoryName){
//        StringBuffer temSb = new StringBuffer();
//        //insert
//        temSb.append("INSERT INTO FM_Account_Maintenance (ROW_GUID, CREATION_DATE,ENABLED_FLAG, subject_code, subject_name, full_subject_name, balance_direction_code,balance_direction, subject_category_code, subject_category_name) VALUES (N'");
//        //uuid
//        temSb.append(UUID.randomUUID().toString());
//        //拼接
//        temSb.append("', N'");
//        //日期
//        temSb.append(LocalDateTime.now());
//        //拼接 + Y
//        temSb.append("', N'Y', N'");
//        //编码
//        temSb.append(subjectCode);
//        //拼接
//        temSb.append("', N'");
//        //科目名称(简)
//        temSb.append(subjectName);
//        //拼接
//        temSb.append("', N'");
//        //科目全称
//        temSb.append(fullSubjectName);
//        //拼接
//        temSb.append("', N'");
//        //余额方向code
//
//        switch (balanceDirection){
//            case "借方":
//                temSb.append("01");
//                break;
//        }
//        //拼接
//        temSb.append("', N'");
//        //余额方向
//        temSb.append(balanceDirection);
//        //拼接
//        temSb.append("', N'");
//
//        //科目code
//        switch (subjectCategoryName) {
//            case "期间费用":
//                temSb.append("01");
//                break;
//            case "流动资产":
//                temSb.append("02");
//                break;
//            case "流动负债":
//                temSb.append("03");
//                break;
//        }
//        //拼接
//        temSb.append("', N'");
//        //科目name
//        temSb.append(subjectCategoryName);
//        //拼接
//        temSb.append("');");
//        System.out.println(temSb);
//    }
//
//    public static void testXxxm(String filePath){
//
//        try {
//            FileInputStream fileInputStream = new FileInputStream(filePath);
////            sheets对象
//            XSSFWorkbook sheets = new XSSFWorkbook(fileInputStream);
//            //总共的工作表数量
////            int numberOfSheets = sheets.getNumberOfSheets();
//            //第一个sheet
//            XSSFSheet sheet = sheets.getSheetAt(0);
//            //总共的行数
//            int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();
//            for (int i = 1; i < physicalNumberOfRows; i++) {
//                XSSFRow row = sheet.getRow(i);
//                //编码
//                String subjectCode = String.valueOf(row.getCell(0)==null?"":row.getCell(0));
//                String subjectName = String.valueOf(row.getCell(1)==null?"":row.getCell(1));
//                String fullSubjectName = String.valueOf(row.getCell(2)==null?"":row.getCell(2));
//                String balanceDirection = String.valueOf(row.getCell(3)==null?"":row.getCell(3));
//                String subjectCategoryName = String.valueOf(row.getCell(4)==null?"":row.getCell(4));
//                PJ(subjectCode,subjectName,fullSubjectName,balanceDirection,subjectCategoryName);
//            }
//
//
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//    }
//
//
//    @Test
//    void outCXKMXLXS(){
//        testXxxm("C:\\Students\\11\\Desktop\\589f8653e9302ad7fca10ca8cdff5e28.xlsx");
//    }
//
//
//    @Test
//    void mapToMap(){
//        Map<String, Object> map = new HashMap<>();
//        map.put("flag", "F");
//        map.put("rtn", map);
//        System.out.println(map);
//    }
//
//
//    @Autowired
//    private UserMapper userMapper;
//
//    @Test
//    public void testUserMapper(){
//        List<User> userList = userMapper.selectList(null);
//        for (User user : userList) {
//            System.out.println(user);
//        }
//    }
//
//
//    //INSERT INTO QU_System_file (row_guid,BOE_TYPE_CODE, BOE_TYPE_NAME, OPERATION_TYPE_CODE, OPERATION_TYPE_NAME, FLOW_STATUS, DEPT_ID, DEPT_NAME, ENABLED_FLAG, File_code, File_name, File_version, Main_dept_id, Main_dept_name, modify_type_code, modify_type_name, Release_date, File_state, Implement_date) VALUES (N'01005817-5cc3-4719-afc3-d389f7cd1472', N'QU_System_file', N'体系文件管理', N'QU_System_file', N'体系文件管理', N'正常完成', 4, N'市场部', N'Y', 5, N'文件编号', N'文件名称文件名称2', N'文件版次2', 6, N'技术部', N'02', N'修订', N'2023-12-04', N'正常运行', N'2023-12-04');
////    @Test
//    public Map<String, String> deptNameExcelToSql(){
//        try {
//            Map<String, String> deptMap = new HashMap<>();
//            FileInputStream fileInputStream = new FileInputStream("C:\\Users\\11\\Desktop\\部门.xlsx");
////            sheets对象
//            XSSFWorkbook sheets = new XSSFWorkbook(fileInputStream);
//            //总共的工作表数量
////            int numberOfSheets = sheets.getNumberOfSheets();
//            //第一个sheet
//            XSSFSheet sheet = sheets.getSheetAt(0);
//            //总共的行数
//            int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();
//            for (int i = 0; i < physicalNumberOfRows; i++) {
//                XSSFRow row = sheet.getRow(i);
//                //编码
//                String deptName = String.valueOf(row.getCell(0));
//                String deptId = String.valueOf(row.getCell(1));
//                deptMap.put(deptName,deptId);
//            }
////            System.out.println(deptMap);
//            return deptMap;
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//
//    @Test
//    public void fileMangerExcelToSql(){
//        try {
//            FileInputStream fileInputStream = new FileInputStream("C:\\Users\\11\\Desktop\\指标1.xlsx");
////            sheets对象
//            XSSFWorkbook sheets = new XSSFWorkbook(fileInputStream);
//            //总共的工作表数量
////            int numberOfSheets = sheets.getNumberOfSheets();
//            //第一个sheet
//            XSSFSheet sheet = sheets.getSheetAt(0);
//            //总共的行数
//            int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();
//            for (int i = 1; i < physicalNumberOfRows; i++) {
//                XSSFRow row = sheet.getRow(i);
//                //编码
//                String assessmentDimensionsCode = String.valueOf(row.getCell(0)==null?"":row.getCell(0));
//                String indexType = String.valueOf(row.getCell(1)==null?"":row.getCell(1));
//                String indexName = String.valueOf(row.getCell(2)==null?"":row.getCell(2));
//                String targetValueContent = String.valueOf(row.getCell(3)==null?"":row.getCell(3));
//                String ratingDeptName = String.valueOf(row.getCell(4)==null?"":row.getCell(4));
//                String indicatorSubcategoryName = String.valueOf(row.getCell(5)==null?"":row.getCell(5));
////                System.out.println("11");
//                this.pjFileManger(assessmentDimensionsCode,indexType,indexName,targetValueContent,ratingDeptName,indicatorSubcategoryName);
////                deptMap.put(deptName,deptId);
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    //INSERT INTO QU_System_file (row_guid,BOE_TYPE_CODE, BOE_TYPE_NAME, OPERATION_TYPE_CODE, OPERATION_TYPE_NAME, FLOW_STATUS, DEPT_ID, DEPT_NAME, ENABLED_FLAG, File_code, File_name, File_version, Main_dept_id, Main_dept_name, modify_type_code, modify_type_name, Release_date, File_state, Implement_date) VALUES (N'01005817-5cc3-4719-afc3-d389f7cd1472', N'QU_System_file', N'体系文件管理', N'QU_System_file', N'体系文件管理', N'正常完成', 4, N'市场部', N'Y', N'文件编号', N'文件名称文件名称2', N'文件版次2', 6, N'技术部', N'02', N'修订', N'2023-12-04', N'正常运行', N'2023-12-04');
//
//    public void pjFileManger(String assessmentDimensionsCode,String indexType,String indexName,String targetValueContent,String ratingDeptName,String indicatorSubcategoryName ){
//
//        //部门列表->map
//        Map<String, String> stringStringMap = this.deptNameExcelToSql();
//
//
//        StringBuffer temSb = new StringBuffer();
//        //insert
//        temSb.append("INSERT INTO CM_ASSESSMENT_INDEX (ROW_GUID,ENABLED_FLAG, ASSESSMENT_DIMENSIONS_CODE, ASSESSMENT_DIMENSIONS_NAME, INDEX_TYPE, INDEX_NAME, TARGET_VALUE_CONTENT, RATING_DEPT_ID, RATING_DEPT_NAME, INDICATOR_SUBCATEGORY_CODE, INDICATOR_SUBCATEGORY_NAME, IS_ENABLED_CODE, IS_ENABLED_NAME,IS_NEED_CALCULATION_CODE, IS_NEED_CALCULATION_NAME) VALUES (N'");
//        //uuid
//        temSb.append(UUID.randomUUID().toString());
//        //是否启用
//        temSb.append("', N'Y', N'");
//        //', N' 文件code
//        temSb.append(assessmentDimensionsCode);
//        //拼接
//        temSb.append("', N'");
//        temSb.append(assessmentDimensionsCode);
//        //拼接
//        temSb.append("', N'");
//        //文件版次
//        temSb.append(indexType);
//        //拼接
//        temSb.append("',N'");
//
//        temSb.append(indexName);
//        //拼接
//        temSb.append("', N'");
//        //部门名称
//        temSb.append(targetValueContent);
//        temSb.append("', N'");
//
//        List<String> deptNames = Arrays.asList(ratingDeptName.split(","));
//        String resultDeptIds = deptNames.stream()
//                .map(stringStringMap::get)
//                .filter(Objects::nonNull)
//                .collect(Collectors.joining(","));
//
//        String resultDeptNames = deptNames.stream()
//                .filter(stringStringMap::containsKey)
//                .collect(Collectors.joining(","));
//
//        temSb.append(resultDeptIds);
//        temSb.append("', N'");
//        temSb.append(resultDeptNames);
//        temSb.append("', N'");
//        temSb.append(indicatorSubcategoryName);
//        temSb.append("', N'");
//        temSb.append(indicatorSubcategoryName);
//        temSb.append("', N'");
//        temSb.append("是");
//        temSb.append("', N'");
//        temSb.append("是");
//        temSb.append("', N'");
//        temSb.append("否");
//        temSb.append("', N'");
//        temSb.append("否");
//
//        //拼接
//        temSb.append("');");
//        System.out.println(temSb);
//        System.out.println("go");
//
//    }
//
//
//    @Test
//    public void checkRepeatToList(){
//        String needCheck = "improveId\n" +
//                "fileCode\n" +
//                "fileName\n" +
//                "fileVersion\n" +
//                "mainDeptId\n" +
//                "mainDeptName\n" +
//                "existProblem\n" +
//                "updateContext\n" +
//                "fileI\n" +
//                "improveId\n" +
//                "fileCode\n" +
//                "fileName\n" +
//                "fileVersion\n" +
//                "mainDeptId\n" +
//                "mainDeptName\n" +
//                "fileId";
//
//        String[] split = needCheck.split("\n");
//        List<String> collect = Arrays.stream(split).distinct().sorted().collect(Collectors.toList());
//        for (String s : collect) {
//            System.out.println(s);
//        }
////        System.out.println(collect);
//    }
//
//
//    @Test
//    public void employeesAccountExcelToSql(){
//        try {
//
//            DataFormatter dataFormatter = new DataFormatter();
//
//            FileWriter file = new FileWriter("C:\\Users\\11\\Desktop\\employeesAccountExcelToSql.sql");
//
//            FileInputStream fileInputStream = new FileInputStream("C:\\Users\\11\\Desktop\\安评个人账户初始化.xlsx");
////            sheets对象
//            XSSFWorkbook sheets = new XSSFWorkbook(fileInputStream);
//            //总共的工作表数量
////            int numberOfSheets = sheets.getNumberOfSheets();
//            //第一个sheet
//            XSSFSheet sheet = sheets.getSheetAt(0);
//            //总共的行数
//            int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();
//            for (int i = 2; i < physicalNumberOfRows; i++) {
//                XSSFRow row = sheet.getRow(i);
//                //银行账户
//                String bankAccount = dataFormatter.formatCellValue(row.getCell(0));
//                //银行名称
//                String bankName = String.valueOf(row.getCell(1));
//                //开户行
//                String bankAccountName = String.valueOf(row.getCell(2));
//                //手机号
//                String createdByNumber = dataFormatter.formatCellValue(row.getCell(4));
//                //所在公司
//                String bearUnitName = dataFormatter.formatCellValue(row.getCell(5));
//                //转
////                if(bearUnitName.equals("合肥") || bearUnitName.equals("安庆")){
////                    bearUnitName = "总部" + bearUnitName;
////                }else{
////                    bearUnitName = bearUnitName + "分公司";
////                }
//
//                StringBuffer stringBuffer = this.pjEmployeesAccount(bankAccount, bankName, bankAccountName, createdByNumber, bearUnitName);
//
//                file.write(stringBuffer.toString());
//                file.write("\r\n");
//                file.write("\r\n");
//                file.write("go");
//                file.write("\r\n");
//                file.write("\r\n");
//
//            }
//            file.write("update fv\r\n" +
//                    "set fv.CREATED_BY = fe.EMPLOYEE_ID,\r\n" +
//                    "    fv.CREATED_BY_NAME = fe.EMPLOYEE_NAME,\r\n" +
//                    "    fv.LAST_UPDATE_DATE = fv.CREATION_DATE,\r\n" +
//                    "    fv.LAST_UPDATED_BY = fe.EMPLOYEE_ID,\r\n" +
//                    "    fv.LAST_UPDATED_BY_NUMBER = fv.CREATED_BY_NUMBER,\r\n" +
//                    "    fv.LAST_UPDATED_BY_NAME = fe.EMPLOYEE_NAME,\r\n" +
//                    "    fv.VENDOR_NAME = fe.EMPLOYEE_NAME,\r\n" +
//                    "    fv.DEPT_ID = fe.DEPT_ID,\r\n" +
//                    "    fv.DEPT_NAME = fd.DEPT_NAME,\r\n" +
//                    "    fv.EMPLOYEE_ID = fe.EMPLOYEE_ID,\r\n" +
//                    "    fv.EMPLOYEE_NUMBER = fv.CREATED_BY_NUMBER,\r\n" +
//                    "    fv.EMPLOYEE_NAME = fe.EMPLOYEE_NAME,\r\n" +
//                    "    fv.vendor_emp_Id = fe.EMPLOYEE_ID\r\n" +
//                    "from FM_VENDOR fv\r\n" +
//                    "            inner join FBP_EMPLOYEES fe on fv.CREATED_BY_NUMBER = fe.EMPLOYEE_NUMBER\r\n" +
//                    "              inner join FBP_DEPT fd on fe.DEPT_ID = fd.DEPT_ID;\r\n");
//            file.write("\r\n");
//            file.write("go");
//            file.close();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    /**
//     * update fv
//     * set fv.CREATED_BY = fe.EMPLOYEE_ID,
//     *     fv.CREATED_BY_NAME = fe.EMPLOYEE_NAME,
//     *     fv.LAST_UPDATE_DATE = fv.CREATION_DATE,
//     *     fv.LAST_UPDATED_BY = fe.EMPLOYEE_ID,
//     *     fv.LAST_UPDATED_BY_NUMBER = fv.CREATED_BY_NUMBER,
//     *     fv.LAST_UPDATED_BY_NAME = fe.EMPLOYEE_NAME,
//     *     fv.VENDOR_NAME = fe.EMPLOYEE_NAME,
//     *     fv.DEPT_ID = fe.DEPT_ID,
//     *     fv.DEPT_NAME = fd.DEPT_NAME,
//     *     fv.EMPLOYEE_ID = fe.EMPLOYEE_ID,
//     *     fv.EMPLOYEE_NUMBER = fv.CREATED_BY_NUMBER,
//     *     fv.EMPLOYEE_NAME = fe.EMPLOYEE_NAME,
//     *     fv.vendor_emp_Id = fe.EMPLOYEE_ID
//     * from FM_VENDOR fv
//     *             inner join FBP_EMPLOYEES fe on fv.CREATED_BY_NUMBER = fe.EMPLOYEE_NUMBER
//     *               inner join FBP_DEPT fd on fe.DEPT_ID = fd.DEPT_ID
//     *
//     */
//
//
//    public StringBuffer pjEmployeesAccount(String bankAccount, String bankName, String bankAccountName, String createdByNumber, String bearUnitName){
//
//        Map<String, String> bearUnitMap = new HashMap<>();
//        bearUnitMap.put("总部合肥","HF");
//        bearUnitMap.put("总部安庆","AQ");
//        bearUnitMap.put("上海分公司","SH");
//        bearUnitMap.put("宁波分公司","NB");
//        bearUnitMap.put("天津分公司","TJ");
//        bearUnitMap.put("海南分公司","HN");
//
//        String formatted = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//
////        INSERT INTO asec_deve.dbo.FM_VENDOR (CREATION_DATE, CREATED_BY_NUMBER, ENABLED_FLAG, BANK_NAME, BANK_ACCOUNT_NAME, BANK_ACCOUNT, ROW_GUID, Bear_Unit_code, Bear_Unit_name, BOE_TYPE_CODE) VALUES (N'2023-12-20 16:13:17.833', N'admin', N'Y', N'XX银行', N'XX银行XX支行', N'9558804301011XXXXXX', N'9c0be6e1-c893-4e56-b253-fb99a1364517', N'APNB', N'安评宁波分公司', N'fm_vendor_GR_AN');
//        StringBuffer temSb = new StringBuffer();
//        //insert
//        temSb.append("INSERT INTO FM_VENDOR (CREATION_DATE, CREATED_BY_NUMBER, ENABLED_FLAG, BANK_NAME, BANK_ACCOUNT_NAME, BANK_ACCOUNT, ROW_GUID, Bear_Unit_code, Bear_Unit_name, BOE_TYPE_CODE) VALUES (N'");
//        //CREATION_DATE
//        temSb.append(formatted);
//        //拼接
//        temSb.append("', N'");
//        //CREATED_BY_NUMBER
//        temSb.append(createdByNumber);
//        //拼接
//        temSb.append("', N'Y', N'");
//        //BANK_NAME
//        temSb.append(bankName);
//        //拼接
//        temSb.append("', N'");
//        //BANK_ACCOUNT_NAME
//        temSb.append(bankAccountName);
//        //拼接
//        temSb.append("', N'");
//        //BANK_ACCOUNT
//        temSb.append(bankAccount);
//        //拼接
//        temSb.append("', N'");
//        //uuid
//        temSb.append(UUID.randomUUID().toString());
//        //拼接
//        temSb.append("', N'");
//        //Bear_Unit_code
////        temSb.append(bearUnitMap.get(bearUnitName));
//        temSb.append("AP");
//        //拼接
//        temSb.append("', N'");
//        //Bear_Unit_name
////        temSb.append(bearUnitName);
//        temSb.append("安评公司");
//        //拼接
//        temSb.append("', N'fm_vendor_GR_AN');");
////        System.out.println(temSb);
////        System.out.println();
////        System.out.println("go");
////        System.out.println();
//        return temSb;
//
//    }
//
//
//    @Test
//    public void testStringCols(){
//        String[] stringColByClass = getStringColByClass(User.class);
//    }
//
//    public static String[] getStringColByClass(Class clazz,String...noNeedCols){
//
//        String[] NoNeedBasicCols = {
//                "enabledFlag","rowGuid","toRowGuid",
//                "boeTypeCode","boeTypeName","operationTypeCode",
//                "operationTypeName","flowStatus","boeNum"
//        };
//        // 实体类的所有属性
//        List<String> allColList = new ArrayList<>();
//        Field[] fields = clazz.getDeclaredFields();
//        for (Field field : fields) {
//            allColList.add(field.getName());
//        }
//        //过滤基础字段和需要额外过滤的字段
//        List<String> resultCols = allColList.stream()
//                .filter(n -> !Arrays.asList(NoNeedBasicCols).contains(n))
//                .filter(n -> !Arrays.asList(noNeedCols).contains(n)).collect(Collectors.toList());
//
//        return resultCols.toArray(new String[]{});
//    }
//
//
//    @Test
//    public void testUrl(){
////        String urlString = "http://127.0.0.1:9000/fishtest-cabinet-quotation/2024-03-28/新建%20文本文档_1711618517026.txt";
////        URL urlObj;
//        try {
//            String url = "http://127.0.0.1:9000/fishtest-cabinet-quotation/2024-03-28/新建%20文本文档_1711618517026.txt";
//
//            String[] split = url.split("/");
//            String fileName = "/"+split[split.length-2]+"/"+split[split.length-1];
//            System.out.println(fileName);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//
//
//    @Test
//    public void importZJZ(){
//        String basicSql = "INSERT INTO dbo.hr_emp_cert (ENABLED_FLAG, cert_no, cert_type_code, cert_type_name, ROW_GUID, ATTRIBUTE4, employee_name, status_code, status_name, use_status_code, use_status_name, position, sign_date, vali_date) VALUES (N'Y', N'12', N'2', N'3', N'4', N'6', N'', N'', N'10', N'11', N'12', N'13', N'2024-04-16 10:13:51.000', N'2024-04-11 10:13:56.000')";
//
//        try {
//            DataFormatter dataFormatter = new DataFormatter();
//
//            FileWriter file = new FileWriter("C:\\Users\\11\\Desktop\\ZJZ.sql");
//
//            FileInputStream fileInputStream = new FileInputStream("C:\\Users\\11\\Desktop\\副本c7d4375a463ecadec47af8d63e3960be_1_8.xlsx");
////            sheets对象
//            XSSFWorkbook sheets = new XSSFWorkbook(fileInputStream);
//            //总共的工作表数量
////            int numberOfSheets = sheets.getNumberOfSheets();
//            //第一个sheet
//            XSSFSheet sheet = sheets.getSheetAt(0);
//            //总共的行数
//            int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();
//
//            for (int i = 1; i < physicalNumberOfRows; i++) {
//                XSSFRow row = sheet.getRow(i);
//
//                //证照姓名
//                String employeeName = String.valueOf(row.getCell(0));
//                //证件号码
//                String certNo = String.valueOf(row.getCell(1));
//                //证件类型name  cert_type_name
//                String certTypeName = String.valueOf(row.getCell(2));
//                //证件类型name  cert_type_code
//                String certTypeCode = "";
//                if(certTypeName.equals("因公通行证")){
//                    certTypeCode = "YinGongTongXingZheng";
//                }else{
//                    certTypeCode = "GongWuCommonPermit";
//                }
//                //签发日期sign_date
//                String signDate = String.valueOf(row.getCell(4));
//                //有效日期vali_date
//                String valiDate = String.valueOf(row.getCell(5));
//                //库存状态name status_name
//                String statusName = String.valueOf(row.getCell(6));
//                //库存状态code status_code
//                String statusCode = "";
//                if(statusName.equals("借出")){
//                    statusCode = "Lend";
//                }else{
//                    statusCode = "TheLibrary";
//                }
//                //使用状态name  use_status_name
//                String useStatusName = String.valueOf(row.getCell(7));
//                //使用状态code  use_status_code
//                String useStatusCode = "";
//                if(useStatusName.equals("无效")){
//                    useStatusCode = "NoAcail";
//                }else{
//                    useStatusCode = "Effective";
//                }
//                //存储位置 position
//                String position = String.valueOf(row.getCell(8));
//
//                StringBuffer stringBuffer = this.pxZjz(employeeName,certNo,certTypeCode,certTypeName,signDate,valiDate,statusCode,statusName,useStatusCode,useStatusName,position);
//
//                file.write(stringBuffer.toString());
//                file.write("\r\n");
//                file.write("\r\n");
//                file.write("go");
//                file.write("\r\n");
//                file.write("\r\n");
//
//            }
//            file.write("update hec\n" +
//                    "set\n" +
//                    "    hec.DEPT_ID = fe.DEPT_ID,\n" +
//                    "    hec.DEPT_CODE = fd.DEPT_CODE,\n" +
//                    "    hec.DEPT_NAME = fd.DEPT_NAME,\n" +
//                    "    hec.EMPLOYEE_ID = fe.EMPLOYEE_ID,\n" +
//                    "    hec.EMPLOYEE_NUMBER = fe.EMPLOYEE_NUMBER\n" +
//                    "from hr_emp_cert hec\n" +
//                    "            inner join FBP_EMPLOYEES fe on hec.EMPLOYEE_NAME = fe.EMPLOYEE_NAME\n" +
//                    "              inner join FBP_DEPT fd on fe.DEPT_ID = fd.DEPT_ID\n" +
//                    "where fe.ENABLED_FLAG = 'Y' and hec.ATTRIBUTE4 = '2024-04-11导入'");
//
//            file.write("\r\n");
//            file.write("\r\n");
//            file.write("go");
//            file.write("\r\n");
//            file.write("\r\n");
//            file.write("INSERT INTO dbo.hr_emp_cert (ENABLED_FLAG, cert_no, cert_type_code, cert_type_name, ROW_GUID, ATTRIBUTE4, employee_name, status_code, status_name, use_status_code, use_status_name, position, sign_date, vali_date, employee_id, employee_number, dept_id, dept_code, dept_name) VALUES (N'Y', N'', N'GongWuCommonPermit', N'公务普通护照', N'7c6496ad-1982-4236-a7ac-21a91ffdaffd', N'2024-04-11导入', N'李毅', N'Lend', N'借出', N'Effective', N'有效', N'A254', N'2023-02-28', N'2028-02-28',N'101',N'LIYI0417',N'174',N'200186',N'部门领导')\n" +
//                    "\n" +
//                    "go\n" +
//                    "\n" +
//                    "INSERT INTO dbo.hr_emp_cert (ENABLED_FLAG, cert_no, cert_type_code, cert_type_name, ROW_GUID, ATTRIBUTE4, employee_name, status_code, status_name, use_status_code, use_status_name, position, sign_date, vali_date, employee_id, employee_number, dept_id, dept_code, dept_name) VALUES (N'Y', N'', N'GongWuCommonPermit', N'公务普通护照', N'47ed4311-a5a2-45d5-81ac-39e2d560364c', N'2024-04-11导入', N'唐松涛', N'TheLibrary', N'在库', N'Effective', N'有效', N'A263', N'2021-05-21', N'2026-05-21',N'2084',N'TANGSONGTAO0515',N'30',N'200127',N'规划与投资咨询部')\n" +
//                    "\n" +
//                    "go\n" +
//                    "\n" +
//                    "INSERT INTO dbo.hr_emp_cert (ENABLED_FLAG, cert_no, cert_type_code, cert_type_name, ROW_GUID, ATTRIBUTE4, employee_name, status_code, status_name, use_status_code, use_status_name, position, sign_date, vali_date, employee_id, employee_number, dept_id, dept_code, dept_name) VALUES (N'Y', N'', N'YinGongTongXingZheng', N'因公通行证', N'5b97e7ed-272c-4e23-8343-ccdee4cea2cc', N'2024-04-11导入', N'李娜', N'Lend', N'借出', N'Effective', N'有效', N'B33', N'2022-09-16', N'2027-09-16',N'2395',N'LINA0814',N'168',N'200106',N'基础设施运营维护事业部')\n" +
//                    "\n" +
//                    "go\n" +
//                    "\n" +
//                    "INSERT INTO dbo.hr_emp_cert (ENABLED_FLAG, cert_no, cert_type_code, cert_type_name, ROW_GUID, ATTRIBUTE4, employee_name, status_code, status_name, use_status_code, use_status_name, position, sign_date, vali_date, employee_id, employee_number, dept_id, dept_code, dept_name) VALUES (N'Y', N'', N'YinGongTongXingZheng', N'因公通行证', N'eec80e2f-8feb-4a78-853b-d9596eadac1a', N'2024-04-11导入', N'刘高', N'TheLibrary', N'在库', N'Effective', N'有效', N'B121', N'2023-06-28', N'2028-06-28',N'3030',N'liugao0819',N'311',N'400063',N'长大桥研究中心')\n" +
//                    "\n" +
//                    "go");
//            file.write("\r\n");
//
//            file.close();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//    }
//
//    private StringBuffer pxZjz(String employeeName, String certNo, String certTypeCode, String certTypeName, String signDate, String valiDate, String statusCode, String statusName, String useStatusCode, String useStatusName, String position) {
//        StringBuffer temSb = new StringBuffer();
//        //insert
//        temSb.append("INSERT INTO dbo.hr_emp_cert (ENABLED_FLAG, cert_no, cert_type_code, cert_type_name, ROW_GUID, ATTRIBUTE4, employee_name, status_code, status_name, use_status_code, use_status_name, position, sign_date, vali_date) VALUES (N'Y', N'");
//        //cert_no
//        temSb.append(certNo);
//        //拼接
//        temSb.append("', N'");
//        //cert_type_code
//        temSb.append(certTypeCode);
//        //拼接
//        temSb.append("', N'");
//        //cert_type_name
//        temSb.append(certTypeName);
//        //拼接
//        temSb.append("', N'");
//        //ROW_GUID
//        temSb.append(UUID.randomUUID().toString());
//        //拼接
//        temSb.append("', N'");
//        //ATTRIBUTE4
//        temSb.append("2024-04-11导入");
//        //拼接
//        temSb.append("', N'");
//        //employee_name
//        temSb.append(employeeName);
//        //拼接
//        temSb.append("', N'");
//        //status_code
//        temSb.append(statusCode);
//        //拼接
//        temSb.append("', N'");
//        //status_name
//        temSb.append(statusName);
//        //拼接
//        temSb.append("', N'");
//        //use_status_code
//        temSb.append(useStatusCode);
//        //拼接
//        temSb.append("', N'");
//        //use_status_name
//        temSb.append(useStatusName);
//        //拼接
//        temSb.append("', N'");
//        //position
//        temSb.append(position);
//        //拼接
//        temSb.append("', N'");
//        //sign_date
//        temSb.append(signDate);
//        //拼接
//        temSb.append("', N'");
//        //vali_date
//        temSb.append(valiDate);
//        //拼接
//        temSb.append("')");
//
//        return temSb;
//
//    }
//
//    private StringBuffer splitLongString(String dictionaryName, String longString){
//        StringBuffer resultString = new StringBuffer();
//        String[] stringList = longString.split("\n");
//        resultString.append(dictionaryName + "字典项：");
//        for (int i = 0; i <stringList.length; i++) {
//            resultString.append( i+1 + "." +  stringList[i] + " ");
//        }
//        return resultString;
//    }
//
//    @Test
//    public void test6(){
//        System.out.println(this.splitLongString("租赁类型字典","其他\n" +
//                "光大银行\n" +
//                "交通银行\n" +
//                "中国建设银行\n" +
//                "中国银行"));
//    }
//
//
//
//    public List<String> findNotInList(String orgString, String needFindString){
//        List<String> orgList = Arrays.asList(orgString.split("\n"));
//        List<String> needFindList = Arrays.asList(needFindString.split("\n"));
//
//        List<String> notInOrgList = needFindList.stream()
//                .filter(element -> !orgList.contains(element))
//                .toList();
//        return notInOrgList;
//    }
//
//
//    @Test
//    public void test7(){
//        List<String> notInList = this.findNotInList("常志军\n" +
//                "刘峰\n" +
//                "伏首圣\n" +
//                "程德林\n" +
//                "曲春升\n" +
//                "门永斌\n" +
//                "陈占力\n" +
//                "张伟\n" +
//                "李国文\n" +
//                "王新磊\n" +
//                "廉福绵\n" +
//                "张恺\n" +
//                "任海涛\n" +
//                "刘刚\n" +
//                "白浩\n" +
//                "徐学明\n" +
//                "林巍\n" +
//                "阙云龙\n" +
//                "胡亮\n" +
//                "朱晓艳\n" +
//                "赵海涛\n" +
//                "陈军锋\n" +
//                "钟文香\n" +
//                "齐宏学\n" +
//                "王振海\n" +
//                "冯卓德\n" +
//                "丛强滋\n" +
//                "王晓东\n" +
//                "罗超\n" +
//                "陈玉彬\n" +
//                "候阳\n" +
//                "栗晗\n" +
//                "刘清\n" +
//                "朱元勋", "郑春\n" +
//                "王维昭\n" +
//                "柯玮\n" +
//                "刘清\n" +
//                "李会驰\n" +
//                "常志军\n" +
//                "林巍\n" +
//                "王炜\n" +
//                "苏江瑜\n" +
//                "罗超\n" +
//                "程德林\n" +
//                "魏乐永\n" +
//                "王志诚\n" +
//                "陈虎成\n" +
//                "陈占力\n" +
//                "张伟\n" +
//                "张文峰\n" +
//                "文锋\n" +
//                "赵磊\n" +
//                "齐宏学\n" +
//                "丛强滋\n" +
//                "赵国虎\n" +
//                "杨学良\n" +
//                "苏波\n" +
//                "任海涛\n" +
//                "钟文香\n" +
//                "童海涛\n" +
//                "公维强\n" +
//                "冯建明\n" +
//                "白浩\n" +
//                "刘刚\n" +
//                "黄建\n" +
//                "李宝坤\n" +
//                "王晓冬\n" +
//                "秦建军\n" +
//                "彭程\n" +
//                "曲春升\n" +
//                "王新磊\n" +
//                "门永斌\n" +
//                "裴铭海\n" +
//                "王艳萍\n" +
//                "王真\n" +
//                "王振海\n" +
//                "陶诗君\n" +
//                "周岑\n" +
//                "张志刚\n" +
//                "王勇\n" +
//                "季大雪\n" +
//                "黄清飞\n" +
//                "陈历新\n" +
//                "陈军锋\n" +
//                "张秋信\n" +
//                "胡亮\n" +
//                "朱晓艳\n" +
//                "曹佳\n" +
//                "栗晗\n" +
//                "张月瑶\n" +
//                "郑小军\n" +
//                "邵楠\n" +
//                "阙云龙\n" +
//                "王君\n" +
//                "赵颖\n" +
//                "赵海涛\n" +
//                "崔瑾\n" +
//                "刘国强\n" +
//                "施同平\n" +
//                "晋存田\n" +
//                "马健\n" +
//                "张帆\n" +
//                "杨柳");
//        System.out.println(notInList);
//    }
}







