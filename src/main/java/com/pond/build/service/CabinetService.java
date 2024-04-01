package com.pond.build.service;

import com.pond.build.model.CabinetQuotation;
import com.pond.build.model.ResponseResult;

import java.util.HashMap;

public interface CabinetService {
    ResponseResult getAllQuotation(Integer page, Integer pageSize,String searchText);

    ResponseResult createQuotation(CabinetQuotation cabinetQuotation);

    ResponseResult detailDataByQuotationId(String quotationId);

    ResponseResult attachDataByQuotationId(String quotationId);

    ResponseResult updateQuotationById(String quotationId, CabinetQuotation cabinetQuotation);

    ResponseResult removeQuotationAttachs(HashMap<String, Object> map);

    ResponseResult deleteQuotationByIds(HashMap<String, Object> quotationIds);
}
