package com.pond.build.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pond.build.enums.HttpStatusCode;
import com.pond.build.mapper.CabinetMapper;
import com.pond.build.model.*;
import com.pond.build.service.CabinetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class CabinetServiceImpl implements CabinetService {


    @Autowired
    private CabinetMapper cabinetMapper;

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
    public ResponseResult createQuotation(HashMap<String, Object> quotation) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        User userInfo = loginUser.getUser();


        CabinetQuotation cabinetQuotation = new CabinetQuotation();
        cabinetQuotation.setCustomerName(quotation.get("customerName").toString());
        cabinetQuotation.setTelephone(quotation.get("telephone").toString());
        cabinetQuotation.setAddress(quotation.get("address").toString());
        cabinetQuotation.setProductName(quotation.get("productName").toString());



        return null;
    }
}
