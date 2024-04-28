package com.pond.build.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pond.build.enums.HttpStatusCode;
import com.pond.build.mapper.*;
import com.pond.build.model.*;
import com.pond.build.service.CabinetService;
import com.pond.build.utils.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
public class CabinetServiceImpl implements CabinetService {

    @Autowired
    private CabinetMapper cabinetMapper;

    @Autowired
    private CabinetQuotationDetailMapper cabinetQuotationDetailMapper;

    @Autowired
    private AttachmentInformationMapper attachmentInformationMapper;

    @Autowired
    private SelectTypeMapper selectTypeMapper;

    @Autowired
    private PortFolioMapper portFolioMapper;

    @Autowired
    private PortFolioTypeMapper portFolioTypeMapper;

    @Autowired
    private NewsInformationMapper newsInformationMapper;

    @Autowired
    private MessageBoardMapper messageBoardMapper;

    @Autowired
    private WorkArrangementMapper workArrangementMapper;

    @Autowired
    private OrderStatusMapper orderStatusMapper;

    @Override
    public ResponseResult getAllQuotation(Integer page, Integer pageSize, String searchText) {

        Page<CabinetQuotation> pages = new Page<>(page, pageSize);

        LambdaQueryWrapper<CabinetQuotation> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(CabinetQuotation::getDelFlag, "0");

        if(!searchText.isBlank()){
            lambdaQueryWrapper.and(wrapper -> wrapper.like(CabinetQuotation::getCustomerName, searchText)
                    .or().like(CabinetQuotation::getAddress, searchText));
        }

        IPage<CabinetQuotation> cabinetQuotationPage = cabinetMapper.selectPage(pages, lambdaQueryWrapper);
        List<CabinetQuotation> cabinetQuotationPages = cabinetQuotationPage.getRecords();
        long total = cabinetQuotationPage.getTotal();

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("data",cabinetQuotationPages);
        resultMap.put("total",total);

        return new ResponseResult(HttpStatusCode.OK.getCode(),"获取成功",resultMap);
    }

    @Override
    public ResponseResult createQuotation(CabinetQuotation cabinetQuotation) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        User userInfo = loginUser.getUser();

        cabinetQuotation.setCreateBy(userInfo.getUserId().toString());
        cabinetQuotation.setCreateTime(new Date());

        List<CabinetQuotationDetail> cabinetQuotationDetails = cabinetQuotation.getCabinetQuotationDetails();

        cabinetMapper.insert(cabinetQuotation);

        for (CabinetQuotationDetail cabinetQuotationDetail : cabinetQuotationDetails) {
            cabinetQuotationDetail.setQuotationId(Integer.valueOf(cabinetQuotation.getQuotationId()));
            cabinetQuotationDetail.setCreateBy(userInfo.getUserId().toString());
            cabinetQuotationDetail.setCreateTime(new Date());
            cabinetQuotationDetailMapper.insert(cabinetQuotationDetail);
        }

        return new ResponseResult(HttpStatusCode.OK.getCode(),"操作成功",cabinetQuotation.getQuotationId());
    }

    @Override
    public ResponseResult detailDataByQuotationId(String quotationId) {

        LambdaQueryWrapper<CabinetQuotationDetail> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CabinetQuotationDetail::getQuotationId,quotationId);
        queryWrapper.eq(CabinetQuotationDetail::getDelFlag,"0");
        List<CabinetQuotationDetail> cabinetQuotationDetails = cabinetQuotationDetailMapper.selectList(queryWrapper);

        return new ResponseResult(HttpStatusCode.OK.getCode(),"操作成功",cabinetQuotationDetails);
    }

    @Override
    public ResponseResult attachDataByQuotationId(String quotationId) {
        List<Map<String, Object>> list = attachmentInformationMapper.attachDataByQuotationId(quotationId);

        String[] suffixes = {"pdf", "gif", "txt", "png", "jpg", ".bmp"};
        list.stream().forEach(n -> {
            try {
                if(n.get("url")!= null && !n.get("url").toString().isBlank()){
                    String[] split = n.get("url").toString().split("\\.");
                    String type = split[split.length - 1];

                    URL url = new URL(n.get("url").toString());
                    String filename = Paths.get(url.getPath()).getFileName().toString();
                    n.put("name", filename);
                    n.put("status", "finished");

//                    if(endsWithAny(type, suffixes)){
//                        n.put("url", CommonUtil.fileUrlEncoderChance(n.get("url").toString()));
//                    }else{
//                        n.put("url",null);
//                    }
                    n.put("url",null);
                }
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        });

        // 假设这里需要返回处理后的列表
        return new ResponseResult(HttpStatusCode.OK.getCode(),"操作成功",list);
    }

    @Override
    public ResponseResult updateQuotationById(String quotationId, CabinetQuotation cabinetQuotation) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        User userInfo = loginUser.getUser();

        cabinetQuotation.setUpdateBy(userInfo.getUserId().toString());
        cabinetQuotation.setUpdateTime(new Date());

        cabinetMapper.updateById(cabinetQuotation);

        //删除之前的子表
        UpdateWrapper<CabinetQuotationDetail> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("quotation_id",quotationId);
        updateWrapper.set("del_flag","1");
        cabinetQuotationDetailMapper.update(null,updateWrapper);

        //创建新的子表
        List<CabinetQuotationDetail> cabinetQuotationDetails = cabinetQuotation.getCabinetQuotationDetails();
        for (CabinetQuotationDetail cabinetQuotationDetail : cabinetQuotationDetails) {
            cabinetQuotationDetail.setDetailId(null);
            cabinetQuotationDetail.setQuotationId(Integer.valueOf(cabinetQuotation.getQuotationId()));
            cabinetQuotationDetail.setCreateBy(userInfo.getUserId().toString());
            cabinetQuotationDetail.setCreateTime(new Date());
            cabinetQuotationDetailMapper.insert(cabinetQuotationDetail);
        }

        return new ResponseResult(HttpStatusCode.OK.getCode(),"操作成功");
    }

    @Override
    public ResponseResult removeQuotationAttachs(HashMap<String, Object> map) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        User userInfo = loginUser.getUser();

        List<String> ids = (List<String>) map.get("ids");
        if(!CollectionUtils.isEmpty(ids)){
            Integer effectNum = attachmentInformationMapper.deleteAttachByIds(ids, userInfo.getUserId(), new Date());
        }
        return new ResponseResult(HttpStatusCode.OK.getCode(),"操作成功");
    }

    @Override
    public ResponseResult deleteQuotationByIds(HashMap<String, Object> quotationIds) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        User userInfo = loginUser.getUser();

        List<String> ids = (List<String>)quotationIds.get("ids");
        if(!CollectionUtils.isEmpty(ids)){
            cabinetMapper.deleteQuotationById(ids,userInfo.getUserId().toString(),new Date());
        }
        return new ResponseResult(HttpStatusCode.OK.getCode(),"操作成功");

    }

    @Override
    public ResponseResult getPortFolioType() {
        List<Map<String, Object>> portFolioType = selectTypeMapper.getPortFolioType();
        return new ResponseResult(HttpStatusCode.OK.getCode(),"操作成功",portFolioType);
    }

    @Override
    public ResponseResult editPortFolioType(Map<String, Object> typeMap) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        User userInfo = loginUser.getUser();

        // 获取之前的
        QueryWrapper<SelectType> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("select_code","PortFolioType");
        queryWrapper.eq("del_flag","0");
        List<SelectType> selectTypes = selectTypeMapper.selectList(queryWrapper);
        List<String> beforeTypeIds = selectTypes.stream().map(SelectType::getTypeId).toList();
        // 返回的
        List<Map<String,String>> typeList = (List<Map<String, String>>) typeMap.get("formData");
        // 需要删除的Ids
        List<String> willDeleteTypeIds = beforeTypeIds.stream()
                .filter(typeId -> typeList.stream().noneMatch(map -> map.containsKey("typeId") && String.valueOf(map.get("typeId")).equals(typeId)))
                .collect(Collectors.toList());
        if(!CollectionUtils.isEmpty(willDeleteTypeIds)){
            selectTypeMapper.deleteByTypeIds(willDeleteTypeIds,userInfo.getUserId().toString(),new Date());
        }
        // 需要添加的
        List<Map<String, String>> willAddTypeIds = typeList.stream()
                .filter(map -> !map.containsKey("typeId"))
                .collect(Collectors.toList());

        if(!CollectionUtils.isEmpty(willAddTypeIds)){
            selectTypeMapper.addType(willAddTypeIds,userInfo.getUserId().toString(),new Date(),"PortFolioType");
        }

        return new ResponseResult(HttpStatusCode.OK.getCode(),"操作成功");
    }

    @Override
    public ResponseResult addPortfolio(Map<String, Object> typeMap) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        User userInfo = loginUser.getUser();

        PortFolio portFolio = new PortFolio();
        portFolio.setCreateBy(userInfo.getUserId().toString());
        portFolio.setCreateTime(new Date());

        portFolioMapper.insert(portFolio);

        if(typeMap.containsKey("typeIds")){
            List<String> typeList = (List<String>) typeMap.get("typeIds");
            if(!CollectionUtils.isEmpty(typeList)){
                for (String typeId : typeList) {
                    PortFolioType portFolioType = new PortFolioType();
                    portFolioType.setFolioId(portFolio.getFolioId());
                    portFolioType.setTypeId(typeId);
                    portFolioTypeMapper.insert(portFolioType);
                }
            }
        }
        return new ResponseResult(HttpStatusCode.OK.getCode(),"操作成功",portFolio.getFolioId());
    }

    @Override
    public ResponseResult listPortfolioWeb(Map<String, Object> queryParams) {
        int page = (int) queryParams.get("page");
        int pageSize = (int) queryParams.get("pageSize");
        List<String> searchList = (List<String>) queryParams.get("searchText");

        int offset = (page - 1) * pageSize;
        int limit = pageSize;

        List<PortFolio> portFolioList = portFolioMapper.getPortFolioByPage(offset,limit,searchList);

        Integer portFolioByPageCount = portFolioMapper.getPortFolioByPageCount(searchList);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("data",portFolioList);
        resultMap.put("page",page);
        resultMap.put("pageSize",pageSize);
        resultMap.put("total",portFolioByPageCount);

        return new ResponseResult(HttpStatusCode.OK.getCode(),"操作成功",resultMap);
    }

    @Override
    public ResponseResult deletePortfolioWebByIds(HashMap<String, Object> folioIds) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        User userInfo = loginUser.getUser();

        List<String> ids = (List<String>)folioIds.get("ids");
        if(!CollectionUtils.isEmpty(ids)){
            cabinetMapper.deletePortfolioWebById(ids,userInfo.getUserId().toString(),new Date());
        }

        return new ResponseResult(HttpStatusCode.OK.getCode(),"操作成功");
    }

    @Override
    public ResponseResult addNewsInformation(NewsInformation newsInformation) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        User userInfo = loginUser.getUser();
        newsInformation.setCreateBy(userInfo.getUserId().toString());
        newsInformation.setCreateTime(new Date());
        newsInformationMapper.insert(newsInformation);

        return new ResponseResult(HttpStatusCode.OK.getCode(),"操作成功",newsInformation.getNewsId());
    }

    @Override
    public ResponseResult listNewsInformation(Integer page, Integer pageSize, String searchText) {

        Page<NewsInformation> pages = new Page<>(page, pageSize);

        LambdaQueryWrapper<NewsInformation> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(NewsInformation::getDelFlag, "0");

        if(!searchText.isBlank()){
            lambdaQueryWrapper.and(wrapper -> wrapper.like(NewsInformation::getNewsTitle, searchText));
        }

        IPage<NewsInformation> newsInformationPage = newsInformationMapper.selectPage(pages, lambdaQueryWrapper);
        List<NewsInformation> newsInformationPages = newsInformationPage.getRecords();
        long total = newsInformationPage.getTotal();

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("data",newsInformationPages);
        resultMap.put("total",total);

        return new ResponseResult(HttpStatusCode.OK.getCode(),"获取成功",resultMap);
    }

    @Override
    public ResponseResult editNewsInformation(NewsInformation newsInformation) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        User userInfo = loginUser.getUser();
        newsInformation.setUpdateBy(userInfo.getUserId().toString());
        newsInformation.setUpdateTime(new Date());
        newsInformationMapper.updateById(newsInformation);
        return new ResponseResult(HttpStatusCode.OK.getCode(),"获取成功");
    }

    @Override
    public ResponseResult deleteNewsInformation(HashMap<String, Object> newsIds) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        User userInfo = loginUser.getUser();

        List<String> ids = (List<String>)newsIds.get("ids");
        if(!CollectionUtils.isEmpty(ids)){
            cabinetMapper.deleteNewsInformationById(ids,userInfo.getUserId().toString(),new Date());
        }

        return new ResponseResult(HttpStatusCode.OK.getCode(),"操作成功");
    }

    @Override
    public ResponseResult messageBoardList(Integer page, Integer pageSize) {

        Page<MessageBoard> pages = new Page<>(page, pageSize);

        LambdaQueryWrapper<MessageBoard> lambdaQueryWrapper = new LambdaQueryWrapper<>();


        IPage<MessageBoard> messageBoardPage = messageBoardMapper.selectPage(pages, lambdaQueryWrapper);
        List<MessageBoard> messageBoardPages = messageBoardPage.getRecords();
        long total = messageBoardPage.getTotal();

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("data",messageBoardPages);
        resultMap.put("total",total);

        return new ResponseResult(HttpStatusCode.OK.getCode(),"获取成功",resultMap);
    }

    @Override
    public ResponseResult deleteMessageBoard(HashMap<String, Object> messageIds) {
        List<String> ids = (List<String>)messageIds.get("ids");
        if(!CollectionUtils.isEmpty(ids)){
            messageBoardMapper.deleteMessageBoardById(ids);
        }
        return new ResponseResult(HttpStatusCode.OK.getCode(),"操作成功");
    }

    @Override
    public ResponseResult editOrganizationWork(Map<String, Object> args) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        User userInfo = loginUser.getUser();

        Map<String,Object> dateMap = (Map<String, Object>) args.get("date");
        Integer year = (Integer) dateMap.get("year");
        Integer month = (Integer) dateMap.get("month");
        Integer date = (Integer) dateMap.get("date");
        QueryWrapper<WorkArrangement> workArrangementQueryWrapper = new QueryWrapper<>();
        workArrangementQueryWrapper.eq("year",year);
        workArrangementQueryWrapper.eq("month",month);
        workArrangementQueryWrapper.eq("date",date);
        workArrangementMapper.delete(workArrangementQueryWrapper);

        List<Map<String,Object>> dataMap =  (List<Map<String,Object>>)args.get("data");
        for (Map<String, Object> data : dataMap) {
            WorkArrangement workArrangement = new WorkArrangement();
            workArrangement.setYear(year);
            workArrangement.setMonth(month);
            workArrangement.setDate(date);
            workArrangement.setCreateBy(userInfo.getUserId().toString());
            workArrangement.setCreateTime(new Date());
            workArrangement.setAgencyMatters(data.get("agencyMatters").toString());
            workArrangement.setArrangePeople(data.get("userIds").toString());
            workArrangementMapper.insert(workArrangement);
        }

        return new ResponseResult(HttpStatusCode.OK.getCode(),"操作成功");
    }

    @Override
    public ResponseResult getInfoByYearMonthDate(Integer year, Integer month, Integer date) {
        QueryWrapper<WorkArrangement> workArrangementQueryWrapper = new QueryWrapper<>();
        workArrangementQueryWrapper.eq("del_flag","0");
        workArrangementQueryWrapper.eq("year",year);
        workArrangementQueryWrapper.eq("month",month);
        workArrangementQueryWrapper.eq("date",date);
        List<WorkArrangement> workArrangements = workArrangementMapper.selectList(workArrangementQueryWrapper);
        return new ResponseResult(HttpStatusCode.OK.getCode(),"操作成功",workArrangements);
    }

    @Override
    public ResponseResult listTodos(Integer year, Integer month) {
        QueryWrapper<WorkArrangement> workArrangementQueryWrapper = new QueryWrapper<>();

        workArrangementQueryWrapper.and(wrapper -> {
            wrapper.eq("year", year).eq("month", month).or();
            if (month == 1) {
                // 处理当前月份为1月的情况，需要查询上一年的12月份
                wrapper.eq("year", year - 1).eq("month", 12).or();
            } else {
                // 查询当前年份的前一个月份和当前月份
                wrapper.eq("year", year).eq("month", month - 1).or();
            }
            // 查询当前年份的当前月份和下一个月份
            wrapper.eq("year", year).eq("month", month).or().eq("year", year).eq("month", month + 1);
        });
        List<WorkArrangement> workArrangements = workArrangementMapper.selectList(workArrangementQueryWrapper);
        return new ResponseResult(HttpStatusCode.OK.getCode(),"操作成功",workArrangements);
    }

    @Override
    public ResponseResult autoOrderStatus(Map<String, Object> quotationId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        User userInfo = loginUser.getUser();

        String id = (String) quotationId.get("id");
        QueryWrapper<OrderStatus> orderStatusQueryWrapper = new QueryWrapper<>();
        orderStatusQueryWrapper.eq("quotation_id",id);
        orderStatusQueryWrapper.eq("del_flag","0");
        List<OrderStatus> orderStatuses = orderStatusMapper.selectList(orderStatusQueryWrapper);
        if (CollectionUtils.isEmpty(orderStatuses)){

            QueryWrapper<CabinetQuotation> cabinetQuotationQueryWrapper = new QueryWrapper<>();
            cabinetQuotationQueryWrapper.eq("quotation_id",id);
            cabinetQuotationQueryWrapper.eq("del_flag","0");
            CabinetQuotation cabinetQuotation = cabinetMapper.selectOne(cabinetQuotationQueryWrapper);

            OrderStatus orderStatus = new OrderStatus();
            orderStatus.setCreateBy(userInfo.getUserId().toString());
            orderStatus.setCreateTime(new Date());
            orderStatus.setQuotationId(Integer.valueOf(cabinetQuotation.getQuotationId()));
            orderStatus.setCustomerName(cabinetQuotation.getCustomerName());
            orderStatus.setAddress(cabinetQuotation.getAddress());
            orderStatus.setTelephone(cabinetQuotation.getTelephone());
            orderStatus.setProductName(cabinetQuotation.getProductName());
            orderStatus.setAllTotalPrice(cabinetQuotation.getAllTotalPrice());
            orderStatus.setPaidPrice(new BigDecimal(0));
            orderStatus.setUnPaidPrice(cabinetQuotation.getAllTotalPrice());
            orderStatusMapper.insert(orderStatus);
            return new ResponseResult(HttpStatusCode.OK.getCode(),"生成成功");
        }else{
            return new ResponseResult(HttpStatusCode.OK.getCode(),"该报价单已经生成订单状态");
        }


    }

    @Override
    public ResponseResult listOrderStatus(Integer page, Integer pageSize, String searchText) {
        Page<OrderStatus> pages = new Page<>(page, pageSize);

        LambdaQueryWrapper<OrderStatus> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(OrderStatus::getDelFlag, "0");

        if(!searchText.isBlank()){
            lambdaQueryWrapper.and(wrapper -> wrapper.like(OrderStatus::getCustomerName, searchText)
                    .or().like(OrderStatus::getAddress, searchText));
        }
        lambdaQueryWrapper.orderByDesc(OrderStatus::getUnPaidPrice);
        IPage<OrderStatus> orderStatusPage = orderStatusMapper.selectPage(pages, lambdaQueryWrapper);
        List<OrderStatus> orderStatusPages = orderStatusPage.getRecords();
        long total = orderStatusPage.getTotal();

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("data",orderStatusPages);
        resultMap.put("total",total);

        return new ResponseResult(HttpStatusCode.OK.getCode(),"获取成功",resultMap);
    }

    @Override
    public ResponseResult createOrderStatus(OrderStatus orderStatus) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        User userInfo = loginUser.getUser();

        orderStatus.setCreateBy(userInfo.getUserId().toString());
        orderStatus.setCreateTime(new Date());

        orderStatusMapper.insert(orderStatus);

        return new ResponseResult(HttpStatusCode.OK.getCode(),"获取成功",orderStatus.getOrderId());
    }

    @Override
    public ResponseResult attachDataByOrderId(String orderId) {
        List<Map<String, Object>> list = attachmentInformationMapper.attachDataByOrderId(orderId);

        list.stream().forEach(n -> {
            try {
                if(n.get("url")!= null && !n.get("url").toString().isBlank()){

                    URL url = new URL(n.get("url").toString());
                    String filename = Paths.get(url.getPath()).getFileName().toString();
                    n.put("name", filename);
                    n.put("status", "finished");

                    n.put("url",null);
                }
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        });


        return new ResponseResult(HttpStatusCode.OK.getCode(),"操作成功",list);
    }

    @Override
    public ResponseResult updateOrderStatusById(String orderId, OrderStatus orderStatus) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        User userInfo = loginUser.getUser();

        orderStatus.setUpdateBy(userInfo.getUserId().toString());
        orderStatus.setUpdateTime(new Date());

        orderStatusMapper.updateById(orderStatus);
        return new ResponseResult(HttpStatusCode.OK.getCode(),"操作成功");
    }

    @Override
    public ResponseResult removeOrderStatusAttachs(HashMap<String, Object> map) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        User userInfo = loginUser.getUser();

        List<String> ids = (List<String>) map.get("ids");
        if(!CollectionUtils.isEmpty(ids)){
            Integer effectNum = attachmentInformationMapper.deleteAttachByIds(ids, userInfo.getUserId(), new Date());
        }
        return new ResponseResult(HttpStatusCode.OK.getCode(),"操作成功");
    }
}
