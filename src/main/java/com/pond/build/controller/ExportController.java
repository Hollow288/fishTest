package com.pond.build.controller;

import com.pond.build.service.ExportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExportController {


    @Autowired
    private ExportService exportService;

    @GetMapping("export/{id}/quotation")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<byte[]> exportQuotation(@PathVariable("id") String quotationId){

        return  exportService.exportQuotation(quotationId);
    }


}
