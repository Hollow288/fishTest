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

    @Autowired
    private PeopleServiceImpl peopleService;


    //Java 8 中的 java.time 包是一个全新的日期和时间 API，被设计来纠正之前 Date 和 Calendar API 中存在的多个问题
    @Test
    void testDate() {
        LocalDate date = LocalDate.now(); // 获取今天的日期
        System.out.println(date);//2023-10-12

        LocalTime time = LocalTime.now(); // 获取今天的时间
        System.out.println(time);//15:38:02.758

        LocalDateTime dateTime = LocalDateTime.now(); // 获取今天的日期时间
        System.out.println(dateTime);//2023-10-12T15:39:04.109

        LocalDate specificDate = LocalDate.of(2023, Month.JANUARY, 1); // 创建一个特定的日期
        System.out.println(specificDate);//2023-01-01

        LocalDateTime specificDateTime = LocalDateTime.of(2023, Month.JANUARY, 1,16,30,45); // 创建一个特定的日期
        System.out.println(specificDateTime);//2023-01-01T16:30:45

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formatted = specificDateTime.format(formatter);
        System.out.println(formatted); // 输出: 2023-01-01 16:30

        //SimpleDateFormat 属于 java.text 包，并且主要与 java.util.Date 类进行交互
        // 线程不安全，多线程使用单个实例会出现问题
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dateNew = new Date();
        String simpleDate = simpleDateFormat.format(dateNew);
        System.out.println(simpleDate);//2023-10-12 15:31:19


        //操作

//        加/减天数:
        LocalDate tomorrow = date.plusDays(1);
        LocalDate yesterday = date.minusDays(1);

//        加/减周数:
        LocalDate nextWeek = date.plusWeeks(1);
        LocalDate lastWeek = date.minusWeeks(1);

//        加/减月数:
        LocalDate nextMonth = date.plusMonths(1);
        LocalDate lastMonth = date.minusMonths(1);

//        加/减年数:
        LocalDate nextYear = date.plusYears(1);
        LocalDate lastYear = date.minusYears(1);
    }


    @Test
    void contextLoads() {

        outTest("row_guid\n" +
                "CREATION_DATE\n" +
                "CREATED_BY\n" +
                "CREATED_BY_NAME\n" +
                "CREATED_BY_NUMBER\n" +
                "LAST_UPDATE_DATE\n" +
                "LAST_UPDATED_BY\n" +
                "LAST_UPDATED_BY_NAME\n" +
                "LAST_UPDATED_BY_NUMBER\n" +
                "BOE_TYPE_CODE\n" +
                "BOE_TYPE_NAME\n" +
                "OPERATION_TYPE_CODE\n" +
                "OPERATION_TYPE_NAME\n" +
                "FLOW_STATUS\n" +
                "DEPT_ID\n" +
                "DEPT_CODE\n" +
                "DEPT_NAME\n" +
                "ENABLED_FLAG\n" +
                "ATTRIBUTE1\n" +
                "ATTRIBUTE2\n" +
                "ATTRIBUTE3\n" +
                "ATTRIBUTE4\n" +
                "ATTRIBUTE5\n" +
                "Boe_num\n" +
                "CREATED_year\n" +
                "employee_id\n" +
                "employee_name\n" +
                "employee_DEPT_ID\n" +
                "employee_DEPT_CODE\n" +
                "employee_DEPT_NAME\n" +
                "Professional_post\n" +
                "Profession_code\n" +
                "Profession_name\n" +
                "Main_technical_work\n" +
                "Training_situation\n" +
                "Publication_status\n" +
                "Technical_director_id\n" +
                "Technical_director_name\n" +
                "Professional_knowledge_code\n" +
                "Professional_knowledge_name\n" +
                "Professional_skill_code\n" +
                "Professional_skill_name\n" +
                "Working_ability_code\n" +
                "Working_ability_name\n" +
                "Work_performance_code\n" +
                "Work_performance_name\n" +
                "Employee_assessment_code\n" +
                "Employee_assessment_name\n" +
                "design_obey_task_code\n" +
                "design_obey_task_name\n" +
                "design_follow_law_code\n" +
                "design_follow_law_name\n" +
                "design_rating_code\n" +
                "design_rating_name\n" +
                "project_obey_task_code\n" +
                "project_obey_task_name\n" +
                "project_difficulty_level_code\n" +
                "project_difficulty_level_name\n" +
                "annual_drawing_quantity\n" +
                "verification_quantity\n" +
                "review_quantity\n" +
                "Project_overall_workload_code\n" +
                "Project_overall_workload_name\n" +
                "Project_workload_code\n" +
                "Project_workload_name\n" +
                "schedule_and_quality_code\n" +
                "schedule_and_quality_name\n" +
                "org_coord_ability_code\n" +
                "org_coord_ability_name\n" +
                "project_rating_code\n" +
                "project_rating_name\n" +
                "quality_complaints_code\n" +
                "quality_complaints_name\n" +
                "rectification_status_code\n" +
                "rectification_status_name\n" +
                "process_control_level_code\n" +
                "process_control_level_name\n" +
                "quality_awareness_code\n" +
                "quality_awareness_name\n" +
                "Quality_project_rating_code\n" +
                "Quality_project_rating_name\n" +
                "technical_training_code\n" +
                "technical_training_name\n" +
                "technology_promotion_code\n" +
                "technology_promotion_name\n" +
                "technical_rating_code\n" +
                "technical_rating_name\n" +
                "violation_attendance_code\n" +
                "violation_attendance_name\n", "uid\n" +
                "创建时间\n" +
                "创建人id\n" +
                "创建人name\n" +
                "创建人code\n" +
                "最近更新时间\n" +
                "最近更新人id\n" +
                "最近更新人\n" +
                "最近更新人code\n" +
                "单据类型-code\n" +
                "单据类型-name\n" +
                "业务类型-code\n" +
                "业务类型-name\n" +
                "流程状态\n" +
                "登记部门id\n" +
                "登记部门code\n" +
                "登记部门name\n" +
                "是否逻辑删除\n" +
                "备用字段1\n" +
                "备用字段2\n" +
                "备用字段3\n" +
                "备用字段4\n" +
                "备用字段5\n" +
                "单据编号\n" +
                "年度\n" +
                "人员id\n" +
                "人员姓名\n" +
                "部门id\n" +
                "部门code\n" +
                "部门name\n" +
                "职称\n" +
                "专业code\n" +
                "专业name\n" +
                "本年度承担的主要技术工作\n" +
                "培训情况\n" +
                "论文发表情况\n" +
                "总工程师（技术负责人）\n" +
                "总工程师（技术负责人）\n" +
                "专业知识评审结果code\n" +
                "专业知识评审结果\n" +
                "专业技能评审结果code\n" +
                "专业技能评审结果\n" +
                "工作能力评审结果code\n" +
                "工作能力评审结果\n" +
                "工作业绩评审结果code\n" +
                "工作业绩评审结果\n" +
                "员工考核登记code\n" +
                "员工考核登记\n" +
                "1、服从任务安排\n" +
                "1、服从任务安排\n" +
                "2、遵守劳动纪律\n" +
                "2、遵守劳动纪律\n" +
                "考核等级\n" +
                "考核等级\n" +
                "1、服从任务安排\n" +
                "1、服从任务安排\n" +
                "2、在手及完成项目难易程度\n" +
                "2、在手及完成项目难易程度\n" +
                "年出图量（张）\n" +
                "校核量（张）\n" +
                "审核量（张）\n" +
                "3、总体工作负荷(EPC及监理人员请填4)\n" +
                "3、总体工作负荷(EPC及监理人员请填4)\n" +
                "4、工作负荷（EPC及监理人员，公司全年共246工作日）\n" +
                "4、工作负荷（EPC及监理人员，公司全年共246工作日）\n" +
                "5、工期及质量\n" +
                "5、工期及质量\n" +
                "6、组织协调能力\n" +
                "6、组织协调能力\n" +
                "考核等级\n" +
                "考核等级\n" +
                "1、质量投诉\n" +
                "1、质量投诉\n" +
                "2、配合质量整改情况（若无质量问题则不填）\n" +
                "2、配合质量整改情况（若无质量问题则不填）\n" +
                "3、过程控制水平（日常工作中对管理体系文件的理解和执行情况）\n" +
                "3、过程控制水平（日常工作中对管理体系文件的理解和执行情况）\n" +
                "4、质量意识（质量投诉、质量检查等反馈的岗位职责履行情况）\n" +
                "4、质量意识（质量投诉、质量检查等反馈的岗位职责履行情况）\n" +
                "考核等级\n" +
                "考核等级\n" +
                "1、技术培训\n" +
                "1、技术培训\n" +
                "2、是否积极配合软件应用等技术推广（研发）\n" +
                "2、是否积极配合软件应用等技术推广（研发）\n" +
                "考核等级\n" +
                "考核等级\n" +
                "是否违反公司考勤等规章制度\n" +
                "是否违反公司考勤等规章制度\n", "hr_Technical_post_assessment");
    }

    public static void outTest(String zd, String bz, String bm) {
        String[] zdList = zd.split("\n");
        String[] bzList = bz.split("\n");
        StringBuffer resultSb = new StringBuffer();
        StringBuffer temSb = new StringBuffer();
        for (int i = 0; i < zdList.length; i++) {
            temSb.append("exec sp_addextendedproperty 'MS_Description', N'");
            temSb.append(bzList[i]);
            temSb.append("', 'SCHEMA', 'dbo', 'TABLE', '");
            temSb.append(bm);
            temSb.append("', 'COLUMN','");
            temSb.append(zdList[i]);
            temSb.append("'");
            resultSb.append(temSb + "\n" + "go" + "\n\n");
            temSb.setLength(0);
        }
        System.out.println(resultSb);
    }


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
//    @Test
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
            FileInputStream fileInputStream = new FileInputStream("C:\\Users\\11\\Desktop\\体系文件管理导入模板（已解密）.xlsx");
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


    @Test
    public void checkRepeatToList(){
        String needCheck = "improveId\n" +
                "fileCode\n" +
                "fileName\n" +
                "fileVersion\n" +
                "mainDeptId\n" +
                "mainDeptName\n" +
                "existProblem\n" +
                "updateContext\n" +
                "fileI\n" +
                "improveId\n" +
                "fileCode\n" +
                "fileName\n" +
                "fileVersion\n" +
                "mainDeptId\n" +
                "mainDeptName\n" +
                "fileId";

        String[] split = needCheck.split("\n");
        List<String> collect = Arrays.stream(split).distinct().sorted().collect(Collectors.toList());
        for (String s : collect) {
            System.out.println(s);
        }
//        System.out.println(collect);
    }


    @Test
    public void employeesAccountExcelToSql(){
        try {

            DataFormatter dataFormatter = new DataFormatter();

            FileWriter file = new FileWriter("C:\\Users\\11\\Desktop\\employeesAccountExcelToSql.sql");

            FileInputStream fileInputStream = new FileInputStream("C:\\Users\\11\\Desktop\\安评个人账户初始化.xlsx");
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
                //银行账户
                String bankAccount = dataFormatter.formatCellValue(row.getCell(0));
                //银行名称
                String bankName = String.valueOf(row.getCell(1));
                //开户行
                String bankAccountName = String.valueOf(row.getCell(2));
                //手机号
                String createdByNumber = dataFormatter.formatCellValue(row.getCell(4));
                //所在公司
                String bearUnitName = dataFormatter.formatCellValue(row.getCell(5));
                //转
//                if(bearUnitName.equals("合肥") || bearUnitName.equals("安庆")){
//                    bearUnitName = "总部" + bearUnitName;
//                }else{
//                    bearUnitName = bearUnitName + "分公司";
//                }

                StringBuffer stringBuffer = this.pjEmployeesAccount(bankAccount, bankName, bankAccountName, createdByNumber, bearUnitName);

                file.write(stringBuffer.toString());
                file.write("\r\n");
                file.write("\r\n");
                file.write("go");
                file.write("\r\n");
                file.write("\r\n");

            }
            file.write("update fv\r\n" +
                    "set fv.CREATED_BY = fe.EMPLOYEE_ID,\r\n" +
                    "    fv.CREATED_BY_NAME = fe.EMPLOYEE_NAME,\r\n" +
                    "    fv.LAST_UPDATE_DATE = fv.CREATION_DATE,\r\n" +
                    "    fv.LAST_UPDATED_BY = fe.EMPLOYEE_ID,\r\n" +
                    "    fv.LAST_UPDATED_BY_NUMBER = fv.CREATED_BY_NUMBER,\r\n" +
                    "    fv.LAST_UPDATED_BY_NAME = fe.EMPLOYEE_NAME,\r\n" +
                    "    fv.VENDOR_NAME = fe.EMPLOYEE_NAME,\r\n" +
                    "    fv.DEPT_ID = fe.DEPT_ID,\r\n" +
                    "    fv.DEPT_NAME = fd.DEPT_NAME,\r\n" +
                    "    fv.EMPLOYEE_ID = fe.EMPLOYEE_ID,\r\n" +
                    "    fv.EMPLOYEE_NUMBER = fv.CREATED_BY_NUMBER,\r\n" +
                    "    fv.EMPLOYEE_NAME = fe.EMPLOYEE_NAME,\r\n" +
                    "    fv.vendor_emp_Id = fe.EMPLOYEE_ID\r\n" +
                    "from FM_VENDOR fv\r\n" +
                    "            inner join FBP_EMPLOYEES fe on fv.CREATED_BY_NUMBER = fe.EMPLOYEE_NUMBER\r\n" +
                    "              inner join FBP_DEPT fd on fe.DEPT_ID = fd.DEPT_ID;\r\n");
            file.write("\r\n");
            file.write("go");
            file.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * update fv
     * set fv.CREATED_BY = fe.EMPLOYEE_ID,
     *     fv.CREATED_BY_NAME = fe.EMPLOYEE_NAME,
     *     fv.LAST_UPDATE_DATE = fv.CREATION_DATE,
     *     fv.LAST_UPDATED_BY = fe.EMPLOYEE_ID,
     *     fv.LAST_UPDATED_BY_NUMBER = fv.CREATED_BY_NUMBER,
     *     fv.LAST_UPDATED_BY_NAME = fe.EMPLOYEE_NAME,
     *     fv.VENDOR_NAME = fe.EMPLOYEE_NAME,
     *     fv.DEPT_ID = fe.DEPT_ID,
     *     fv.DEPT_NAME = fd.DEPT_NAME,
     *     fv.EMPLOYEE_ID = fe.EMPLOYEE_ID,
     *     fv.EMPLOYEE_NUMBER = fv.CREATED_BY_NUMBER,
     *     fv.EMPLOYEE_NAME = fe.EMPLOYEE_NAME,
     *     fv.vendor_emp_Id = fe.EMPLOYEE_ID
     * from FM_VENDOR fv
     *             inner join FBP_EMPLOYEES fe on fv.CREATED_BY_NUMBER = fe.EMPLOYEE_NUMBER
     *               inner join FBP_DEPT fd on fe.DEPT_ID = fd.DEPT_ID
     *
     */


    public StringBuffer pjEmployeesAccount(String bankAccount, String bankName, String bankAccountName, String createdByNumber, String bearUnitName){

        Map<String, String> bearUnitMap = new HashMap<>();
        bearUnitMap.put("总部合肥","HF");
        bearUnitMap.put("总部安庆","AQ");
        bearUnitMap.put("上海分公司","SH");
        bearUnitMap.put("宁波分公司","NB");
        bearUnitMap.put("天津分公司","TJ");
        bearUnitMap.put("海南分公司","HN");

        String formatted = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

//        INSERT INTO asec_deve.dbo.FM_VENDOR (CREATION_DATE, CREATED_BY_NUMBER, ENABLED_FLAG, BANK_NAME, BANK_ACCOUNT_NAME, BANK_ACCOUNT, ROW_GUID, Bear_Unit_code, Bear_Unit_name, BOE_TYPE_CODE) VALUES (N'2023-12-20 16:13:17.833', N'admin', N'Y', N'XX银行', N'XX银行XX支行', N'9558804301011XXXXXX', N'9c0be6e1-c893-4e56-b253-fb99a1364517', N'APNB', N'安评宁波分公司', N'fm_vendor_GR_AN');
        StringBuffer temSb = new StringBuffer();
        //insert
        temSb.append("INSERT INTO FM_VENDOR (CREATION_DATE, CREATED_BY_NUMBER, ENABLED_FLAG, BANK_NAME, BANK_ACCOUNT_NAME, BANK_ACCOUNT, ROW_GUID, Bear_Unit_code, Bear_Unit_name, BOE_TYPE_CODE) VALUES (N'");
        //CREATION_DATE
        temSb.append(formatted);
        //拼接
        temSb.append("', N'");
        //CREATED_BY_NUMBER
        temSb.append(createdByNumber);
        //拼接
        temSb.append("', N'Y', N'");
        //BANK_NAME
        temSb.append(bankName);
        //拼接
        temSb.append("', N'");
        //BANK_ACCOUNT_NAME
        temSb.append(bankAccountName);
        //拼接
        temSb.append("', N'");
        //BANK_ACCOUNT
        temSb.append(bankAccount);
        //拼接
        temSb.append("', N'");
        //uuid
        temSb.append(UUID.randomUUID().toString());
        //拼接
        temSb.append("', N'");
        //Bear_Unit_code
//        temSb.append(bearUnitMap.get(bearUnitName));
        temSb.append("AP");
        //拼接
        temSb.append("', N'");
        //Bear_Unit_name
//        temSb.append(bearUnitName);
        temSb.append("安评公司");
        //拼接
        temSb.append("', N'fm_vendor_GR_AN');");
//        System.out.println(temSb);
//        System.out.println();
//        System.out.println("go");
//        System.out.println();
        return temSb;

    }


    @Test
    public void testStringCols(){
        String[] stringColByClass = getStringColByClass(User.class);
    }

    public static String[] getStringColByClass(Class clazz,String...noNeedCols){

        String[] NoNeedBasicCols = {
                "enabledFlag","rowGuid","toRowGuid",
                "boeTypeCode","boeTypeName","operationTypeCode",
                "operationTypeName","flowStatus","boeNum"
        };
        // 实体类的所有属性
        List<String> allColList = new ArrayList<>();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            allColList.add(field.getName());
        }
        //过滤基础字段和需要额外过滤的字段
        List<String> resultCols = allColList.stream()
                .filter(n -> !Arrays.asList(NoNeedBasicCols).contains(n))
                .filter(n -> !Arrays.asList(noNeedCols).contains(n)).collect(Collectors.toList());

        return resultCols.toArray(new String[]{});
    }


    @Test
    public void testUrl(){
//        String urlString = "http://127.0.0.1:9000/fishtest-cabinet-quotation/2024-03-28/新建%20文本文档_1711618517026.txt";
//        URL urlObj;
        try {
            String url = "http://127.0.0.1:9000/fishtest-cabinet-quotation/2024-03-28/新建%20文本文档_1711618517026.txt";

            String[] split = url.split("/");
            String fileName = "/"+split[split.length-2]+"/"+split[split.length-1];
            System.out.println(fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
