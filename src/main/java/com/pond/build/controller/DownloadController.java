package com.pond.build.controller;

import com.pond.build.model.ResponseResult;
import com.pond.build.service.DownloadService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DownloadController {

    @Autowired
    private DownloadService downloadService;

    @GetMapping("download/{id}/quotation")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<byte[]> downloadQuotation(@PathVariable("id") String attachId){

      return   downloadService.downloadQuotation(attachId);
    }


    @GetMapping("download/{id}/order-status")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<byte[]> getDownloadOrderStatus(@PathVariable("id") String attachId){
        return downloadService.downloadOrderStatus(attachId);
    }



}
