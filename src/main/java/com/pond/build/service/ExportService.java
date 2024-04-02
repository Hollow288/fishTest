package com.pond.build.service;

import org.springframework.http.ResponseEntity;

public interface ExportService {
    ResponseEntity<byte[]> exportQuotation(String quotationId);

    ResponseEntity<byte[]> exportQuotationPdf(String quotationId);
}
