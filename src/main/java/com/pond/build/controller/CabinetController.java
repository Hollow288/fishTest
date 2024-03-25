package com.pond.build.controller;


import com.pond.build.model.ResponseResult;
import com.pond.build.service.CabinetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

}
