package com.pond.build.controller;


import com.pond.build.model.CabinetQuotation;
import com.pond.build.model.ResponseResult;
import com.pond.build.service.CabinetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

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
    @PreAuthorize("hasRole('CIVILIAN')")
    public ResponseResult detailDataByQuotationId(@PathVariable String quotationId){
        return cabinetService.detailDataByQuotationId(quotationId);
    }

}
