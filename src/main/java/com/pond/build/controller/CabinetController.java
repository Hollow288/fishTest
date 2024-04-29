package com.pond.build.controller;


import com.pond.build.model.CabinetQuotation;
import com.pond.build.model.NewsInformation;
import com.pond.build.model.OrderStatus;
import com.pond.build.model.ResponseResult;
import com.pond.build.service.CabinetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CabinetController {

    @Autowired
    private CabinetService cabinetService;

    @GetMapping("/cabinet/quotation")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseResult getAllQuotation(@RequestParam(value = "page") Integer page,
                                          @RequestParam(value = "pageSize") Integer pageSize,
                                          @RequestParam(value = "searchText", defaultValue = "") String searchText){
        return cabinetService.getAllQuotation(page, pageSize,searchText);
    }


    @PostMapping("/cabinet/quotation")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseResult createQuotation(@RequestBody CabinetQuotation cabinetQuotation){
        return cabinetService.createQuotation(cabinetQuotation);
    }


    @GetMapping("/cabinet/{quotationId}/all-detail-by-quotationId")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseResult detailDataByQuotationId(@PathVariable("quotationId") String quotationId){
        return cabinetService.detailDataByQuotationId(quotationId);
    }


    @GetMapping("/cabinet/{quotationId}/all-attach-by-quotationId")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseResult attachDataByQuotationId(@PathVariable("quotationId") String quotationId){
        return cabinetService.attachDataByQuotationId(quotationId);
    }


    @PutMapping("cabinet/remove-quotation-attach")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseResult removeQuotationAttachs(@RequestBody HashMap<String,Object> map){
        return cabinetService.removeQuotationAttachs(map);
    }


    @PutMapping("cabinet/{quotationId}/update-quotation")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseResult updateQuotationById(@PathVariable("quotationId") String quotationId, @RequestBody CabinetQuotation cabinetQuotation){
        return cabinetService.updateQuotationById(quotationId,cabinetQuotation);
    }


    @PatchMapping("/cabinet/delete-quotations")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseResult deleteQuotationByIds(@RequestBody HashMap<String,Object> quotationIds){
        return cabinetService.deleteQuotationByIds(quotationIds);
    }

    @GetMapping("/cabinet/port-folio-type")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseResult getPortFolioType(){
        return cabinetService.getPortFolioType();
    }


    @PutMapping("cabinet/port-folio-type")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseResult editPortFolioType(@RequestBody Map<String,Object> typeMap){
        return cabinetService.editPortFolioType(typeMap);
    }


    @PostMapping("/cabinet/port-folio")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseResult addPortfolio(@RequestBody Map<String,Object> typeMap){
        return cabinetService.addPortfolio(typeMap);
    }


    @PostMapping("/cabinet/portfolio-web")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseResult listPortfolioWeb(@RequestBody Map<String,Object> queryParams){
        return cabinetService.listPortfolioWeb(queryParams);
    }


    @PatchMapping("/cabinet/delete-portfolio-web")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseResult deletePortfolioWebByIds(@RequestBody HashMap<String,Object> folioIds){
        return cabinetService.deletePortfolioWebByIds(folioIds);
    }


    @PostMapping("/cabinet/news-information")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseResult addNewsInformation(@RequestBody NewsInformation newsInformation){
        return cabinetService.addNewsInformation(newsInformation);
    }


    @GetMapping("/cabinet/news-information-list")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseResult listNewsInformation(@RequestParam(value = "page") Integer page,
                                           @RequestParam(value = "pageSize") Integer pageSize,
                                           @RequestParam(value = "searchText", defaultValue = "") String searchText){
        return cabinetService.listNewsInformation(page,pageSize,searchText);
    }



    @PutMapping("/cabinet/news-information")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseResult editNewsInformation(@RequestBody NewsInformation newsInformation){
        return cabinetService.editNewsInformation(newsInformation);
    }

    @PatchMapping("/cabinet/delete-news-information")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseResult deleteNewsInformation(@RequestBody HashMap<String,Object> newsIds){
        return cabinetService.deleteNewsInformation(newsIds);
    }


    @GetMapping("/cabinet/message-board-list")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseResult messageBoardList(@RequestParam(value = "page") Integer page,
                                              @RequestParam(value = "pageSize") Integer pageSize){
        return cabinetService.messageBoardList(page,pageSize);
    }


    @PatchMapping("/cabinet/delete-message-board")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseResult deleteMessageBoard(@RequestBody HashMap<String,Object> messageIds){
        return cabinetService.deleteMessageBoard(messageIds);
    }

    @PostMapping("/cabinet/edit-organization-work")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseResult editOrganizationWork(@RequestBody Map<String,Object> args){
        return cabinetService.editOrganizationWork(args);
    }

    @GetMapping("/cabinet/info-schedule")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseResult getInfoByYearMonthDate(@RequestParam(value = "year") Integer year,
                                              @RequestParam(value = "month") Integer month,
                                              @RequestParam(value = "date") Integer date){
        return cabinetService.getInfoByYearMonthDate(year,month,date);
    }


    @GetMapping("/cabinet/list-todos")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseResult listTodos(@RequestParam(value = "year") Integer year,
                                                 @RequestParam(value = "month") Integer month){
        return cabinetService.listTodos(year,month);
    }


    @PostMapping("/cabinet/auto-order-status")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseResult autoOrderStatus(@RequestBody Map<String,Object> quotationId){
        return cabinetService.autoOrderStatus(quotationId);
    }


    @GetMapping("/cabinet/order-status")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseResult listOrderStatus(@RequestParam(value = "page") Integer page,
                                          @RequestParam(value = "pageSize") Integer pageSize,
                                          @RequestParam(value = "searchText", defaultValue = "") String searchText){
        return cabinetService.listOrderStatus(page, pageSize,searchText);
    }


    @PostMapping("/cabinet/create-order-status")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseResult createOrderStatus(@RequestBody OrderStatus orderStatus){
        return cabinetService.createOrderStatus(orderStatus);
    }


    @GetMapping("/cabinet/{orderId}/all-attach-by-orderId")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseResult attachDataByOrderId(@PathVariable("orderId") String orderId){
        return cabinetService.attachDataByOrderId(orderId);
    }

    @PutMapping("cabinet/{orderId}/update-order-status")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseResult updateOrderStatusById(@PathVariable("orderId") String orderId, @RequestBody OrderStatus orderStatus){
        return cabinetService.updateOrderStatusById(orderId,orderStatus);
    }


    @PutMapping("cabinet/remove-order-status-attach")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseResult removeOrderStatusAttachs(@RequestBody HashMap<String,Object> map){
        return cabinetService.removeOrderStatusAttachs(map);
    }


    @PatchMapping("/cabinet/delete-order-status")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseResult deleteOrderStatusByIds(@RequestBody HashMap<String,Object> orderIds){
        return cabinetService.deleteOrderStatusByIds(orderIds);
    }


    @PutMapping("/cabinet/{orderId}/add-arrived-price")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseResult addArrivedPrice(@RequestBody HashMap<String,Object> map,@PathVariable("orderId") String orderId){
        return cabinetService.addArrivedPrice(map,orderId);
    }

}
