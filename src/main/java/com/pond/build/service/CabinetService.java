package com.pond.build.service;

import com.pond.build.model.CabinetQuotation;
import com.pond.build.model.NewsInformation;
import com.pond.build.model.ResponseResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface CabinetService {
    ResponseResult getAllQuotation(Integer page, Integer pageSize,String searchText);

    ResponseResult createQuotation(CabinetQuotation cabinetQuotation);

    ResponseResult detailDataByQuotationId(String quotationId);

    ResponseResult attachDataByQuotationId(String quotationId);

    ResponseResult updateQuotationById(String quotationId, CabinetQuotation cabinetQuotation);

    ResponseResult removeQuotationAttachs(HashMap<String, Object> map);

    ResponseResult deleteQuotationByIds(HashMap<String, Object> quotationIds);

    ResponseResult getPortFolioType();

    ResponseResult editPortFolioType(Map<String, Object> typeMap);

    ResponseResult addPortfolio(Map<String, Object> typeMap);

    ResponseResult listPortfolioWeb(Map<String, Object> queryParams);

    ResponseResult deletePortfolioWebByIds(HashMap<String, Object> folioIds);

    ResponseResult addNewsInformation(NewsInformation newsInformation);

    ResponseResult listNewsInformation(Integer page, Integer pageSize, String searchText);

    ResponseResult editNewsInformation(NewsInformation newsInformation);

    ResponseResult deleteNewsInformation(HashMap<String, Object> newsIds);

    ResponseResult messageBoardList(Integer page, Integer pageSize);

    ResponseResult deleteMessageBoard(HashMap<String, Object> messageIds);

    ResponseResult editOrganizationWork(Map<String, Object> args);

    ResponseResult getInfoByYearMonthDate(Integer year, Integer month, Integer date);

    ResponseResult listTodos(Integer year, Integer month);
}
