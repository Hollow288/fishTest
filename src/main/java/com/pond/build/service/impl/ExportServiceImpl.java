package com.pond.build.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.deepoove.poi.XWPFTemplate;

import com.deepoove.poi.config.Configure;
import com.deepoove.poi.data.Texts;
import com.deepoove.poi.plugin.table.LoopRowTableRenderPolicy;
import com.pond.build.mapper.CabinetMapper;
import com.pond.build.mapper.CabinetQuotationDetailMapper;
import com.pond.build.model.CabinetQuotation;
import com.pond.build.model.CabinetQuotationDetail;
import com.pond.build.service.ExportService;
import com.pond.build.utils.CommonUtil;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.*;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class ExportServiceImpl implements ExportService {

    @Autowired
    private CabinetMapper cabinetMapper;

    @Autowired
    private CabinetQuotationDetailMapper cabinetQuotationDetailMapper;
    @Override
    public ResponseEntity<byte[]> exportQuotation(String quotationId) {
        LambdaQueryWrapper<CabinetQuotation> cabinetQueryWrapper = new LambdaQueryWrapper<>();
        cabinetQueryWrapper.eq(CabinetQuotation::getQuotationId,quotationId);
        cabinetQueryWrapper.eq(CabinetQuotation::getDelFlag,"0");
        //主表信息
        CabinetQuotation cabinetQuotation = cabinetMapper.selectOne(cabinetQueryWrapper);

        LambdaQueryWrapper<CabinetQuotationDetail> detailQueryWrapper = new LambdaQueryWrapper<>();
        detailQueryWrapper.eq(CabinetQuotationDetail::getQuotationId,quotationId);
        detailQueryWrapper.eq(CabinetQuotationDetail::getDelFlag,"0");
        //子表信息
        List<CabinetQuotationDetail> cabinetQuotationDetails = cabinetQuotationDetailMapper.selectList(detailQueryWrapper);


        try {

            Map<String, Object> innerMap = new HashMap<>();             //inner对象
            List<CabinetQuotationDetail> cabinetsExcelList= new ArrayList<>();    //自己的动态表格数据-橱柜类
            List<CabinetQuotationDetail> kitchenAppliancesExcelList= new ArrayList<>();    //自己的动态表格数据-厨电五金类
            cabinetsExcelList = cabinetQuotationDetails.stream()
                    .filter(n->n.getDetailType().equals("0"))
                    .map(detail -> {
                // 对于 BigDecimal 类型的属性，保留两位小数
                detail.setPricingQuantity(detail.getPricingQuantity().setScale(2, BigDecimal.ROUND_DOWN));
                detail.setUnitPrice(detail.getUnitPrice().setScale(2, BigDecimal.ROUND_DOWN));
                detail.setPricingCoefficient(detail.getPricingCoefficient().setScale(2, BigDecimal.ROUND_DOWN));
                detail.setPriceAmount(detail.getPriceAmount().setScale(2, BigDecimal.ROUND_DOWN));
                // 如果还有其他属性需要处理，继续添加类似的代码
                return detail;
            }).toList();
            kitchenAppliancesExcelList = cabinetQuotationDetails.stream()
                    .filter(n->n.getDetailType().equals("1"))
                    .map(detail -> {
                        // 对于 BigDecimal 类型的属性，保留两位小数
                        detail.setPricingQuantity(detail.getPricingQuantity().setScale(0, BigDecimal.ROUND_DOWN));
                        detail.setUnitPrice(detail.getUnitPrice().setScale(2, BigDecimal.ROUND_DOWN));
                        detail.setPricingCoefficient(detail.getPricingCoefficient().setScale(2, BigDecimal.ROUND_DOWN));
                        detail.setPriceAmount(detail.getPriceAmount().setScale(2, BigDecimal.ROUND_DOWN));
                        // 如果还有其他属性需要处理，继续添加类似的代码
                        return detail;
                    }).toList();


            List<CabinetQuotationDetail> resultCabinetsExcelList= new ArrayList<>();    //自己的动态表格数据-橱柜类
            List<CabinetQuotationDetail> resultKitchenAppliancesExcelList= new ArrayList<>();    //自己的动态表格数据-厨电五金类

            // 补齐空白行 如果长度合理 那么总共26行
            if(cabinetQuotationDetails.size()<26){

                //如果总共的数量小于28行
                if(cabinetsExcelList.size()<13){
                    resultCabinetsExcelList.addAll(cabinetsExcelList);
                    for (int i = 0; i < 13-cabinetsExcelList.size(); i++) {
                        resultCabinetsExcelList.add(new CabinetQuotationDetail());
                    }
                }
                if(kitchenAppliancesExcelList.size()<13){
                    resultKitchenAppliancesExcelList.addAll(kitchenAppliancesExcelList);
                    for (int i = 0; i < 13-kitchenAppliancesExcelList.size(); i++) {
                        resultKitchenAppliancesExcelList.add(new CabinetQuotationDetail());
                    }
                }
            }else{
                resultCabinetsExcelList.addAll(cabinetsExcelList);
                resultKitchenAppliancesExcelList.addAll(kitchenAppliancesExcelList);
            }

            innerMap.put("customerName", Texts.of(cabinetQuotation.getCustomerName()).create());
            innerMap.put("address", Texts.of(cabinetQuotation.getAddress()).create());
            innerMap.put("telephone", Texts.of(cabinetQuotation.getTelephone()).create());
            innerMap.put("productName", Texts.of(cabinetQuotation.getProductName()).create());
            innerMap.put("color", Texts.of(cabinetQuotation.getColor()).create());
            innerMap.put("cabinetBody", Texts.of(cabinetQuotation.getCabinetBody()).create());
            LocalDateTime localDateTime = cabinetQuotation.getQuotationDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            // 格式化为 "yyyy-MM-dd" 格式
            String formattedDate = localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            innerMap.put("quotationDate", Texts.of(formattedDate).create());
            innerMap.put("cabinetTotalPrice", Texts.of(cabinetQuotation.getCabinetTotalPrice().setScale(2, BigDecimal.ROUND_DOWN).toString()).create());
            innerMap.put("electricalTotalPrice", Texts.of(cabinetQuotation.getElectricalTotalPrice().setScale(2, BigDecimal.ROUND_DOWN).toString()).create());
            innerMap.put("allTotalPriceWords", Texts.of(CommonUtil.toChineseAmount(cabinetQuotation.getAllTotalPrice().setScale(2, BigDecimal.ROUND_DOWN))).create());
            innerMap.put("allTotalPrice", Texts.of(cabinetQuotation.getAllTotalPrice().setScale(2, BigDecimal.ROUND_DOWN).toString()).create());
            innerMap.put("cabinetsExcelList", resultCabinetsExcelList);
            innerMap.put("kitchenAppliancesExcelList", resultKitchenAppliancesExcelList);
//            innerMapList.add(innerMap);

            // 使用 ByteArrayOutputStream
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

            LoopRowTableRenderPolicy policy = new LoopRowTableRenderPolicy();
            Configure config = Configure.builder()
                    .bind("cabinetsExcelList", policy).bind("kitchenAppliancesExcelList", policy).build();

            // 渲染模板并将结果写入 ByteArrayOutputStream
            XWPFTemplate.compile("src/main/resources/WordExcelTemplate/报价单模板.docx",config).render(innerMap).write(byteArrayOutputStream);
            // 将 ByteArrayOutputStream 中的数据转换为 byte[]
            byte[] data = byteArrayOutputStream.toByteArray();

            // 设置响应头，指定文件名和文件类型
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", URLEncoder.encode(cabinetQuotation.getAddress()+"报价单.docx", "UTF-8"));

            // 返回 ResponseEntity 对象，设置状态码为 200 OK
            return ResponseEntity.ok()
                    .headers(headers)
                    .body(data);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    // Todo 导出PDF 字体问题
    @Override
    public ResponseEntity<byte[]> exportQuotationPdf(String quotationId) {
        LambdaQueryWrapper<CabinetQuotation> cabinetQueryWrapper = new LambdaQueryWrapper<>();
        cabinetQueryWrapper.eq(CabinetQuotation::getQuotationId,quotationId);
        cabinetQueryWrapper.eq(CabinetQuotation::getDelFlag,"0");
        //主表信息
        CabinetQuotation cabinetQuotation = cabinetMapper.selectOne(cabinetQueryWrapper);

        LambdaQueryWrapper<CabinetQuotationDetail> detailQueryWrapper = new LambdaQueryWrapper<>();
        detailQueryWrapper.eq(CabinetQuotationDetail::getQuotationId,quotationId);
        detailQueryWrapper.eq(CabinetQuotationDetail::getDelFlag,"0");
        //子表信息
        List<CabinetQuotationDetail> cabinetQuotationDetails = cabinetQuotationDetailMapper.selectList(detailQueryWrapper);


        try {

            Map<String, Object> innerMap = new HashMap<>();             //inner对象
            List<CabinetQuotationDetail> cabinetsExcelList= new ArrayList<>();    //自己的动态表格数据-橱柜类
            List<CabinetQuotationDetail> kitchenAppliancesExcelList= new ArrayList<>();    //自己的动态表格数据-厨电五金类
            cabinetsExcelList = cabinetQuotationDetails.stream()
                    .filter(n->n.getDetailType().equals("0"))
                    .map(detail -> {
                        // 对于 BigDecimal 类型的属性，保留两位小数
                        detail.setPricingQuantity(detail.getPricingQuantity().setScale(2, BigDecimal.ROUND_DOWN));
                        detail.setUnitPrice(detail.getUnitPrice().setScale(2, BigDecimal.ROUND_DOWN));
                        detail.setPricingCoefficient(detail.getPricingCoefficient().setScale(2, BigDecimal.ROUND_DOWN));
                        detail.setPriceAmount(detail.getPriceAmount().setScale(2, BigDecimal.ROUND_DOWN));
                        // 如果还有其他属性需要处理，继续添加类似的代码
                        return detail;
                    }).toList();
            kitchenAppliancesExcelList = cabinetQuotationDetails.stream()
                    .filter(n->n.getDetailType().equals("1"))
                    .map(detail -> {
                        // 对于 BigDecimal 类型的属性，保留两位小数
                        detail.setPricingQuantity(detail.getPricingQuantity().setScale(0, BigDecimal.ROUND_DOWN));
                        detail.setUnitPrice(detail.getUnitPrice().setScale(2, BigDecimal.ROUND_DOWN));
                        detail.setPricingCoefficient(detail.getPricingCoefficient().setScale(2, BigDecimal.ROUND_DOWN));
                        detail.setPriceAmount(detail.getPriceAmount().setScale(2, BigDecimal.ROUND_DOWN));
                        // 如果还有其他属性需要处理，继续添加类似的代码
                        return detail;
                    }).toList();


            List<CabinetQuotationDetail> resultCabinetsExcelList= new ArrayList<>();    //自己的动态表格数据-橱柜类
            List<CabinetQuotationDetail> resultKitchenAppliancesExcelList= new ArrayList<>();    //自己的动态表格数据-厨电五金类

            // 补齐空白行 如果长度合理 那么总共26行
            if(cabinetQuotationDetails.size()<26){

                //如果总共的数量小于28行
                if(cabinetsExcelList.size()<13){
                    resultCabinetsExcelList.addAll(cabinetsExcelList);
                    for (int i = 0; i < 13-cabinetsExcelList.size(); i++) {
                        resultCabinetsExcelList.add(new CabinetQuotationDetail());
                    }
                }
                if(kitchenAppliancesExcelList.size()<13){
                    resultKitchenAppliancesExcelList.addAll(kitchenAppliancesExcelList);
                    for (int i = 0; i < 13-kitchenAppliancesExcelList.size(); i++) {
                        resultKitchenAppliancesExcelList.add(new CabinetQuotationDetail());
                    }
                }
            }else{
                resultCabinetsExcelList.addAll(cabinetsExcelList);
                resultKitchenAppliancesExcelList.addAll(kitchenAppliancesExcelList);
            }

            innerMap.put("customerName", Texts.of(cabinetQuotation.getCustomerName()).create());
            innerMap.put("address", Texts.of(cabinetQuotation.getAddress()).create());
            innerMap.put("telephone", Texts.of(cabinetQuotation.getTelephone()).create());
            innerMap.put("productName", Texts.of(cabinetQuotation.getProductName()).create());
            innerMap.put("color", Texts.of(cabinetQuotation.getColor()).create());
            innerMap.put("cabinetBody", Texts.of(cabinetQuotation.getCabinetBody()).create());
            LocalDateTime localDateTime = cabinetQuotation.getQuotationDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            // 格式化为 "yyyy-MM-dd" 格式
            String formattedDate = localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            innerMap.put("quotationDate", Texts.of(formattedDate).create());
            innerMap.put("cabinetTotalPrice", Texts.of(cabinetQuotation.getCabinetTotalPrice().setScale(2, BigDecimal.ROUND_DOWN).toString()).create());
            innerMap.put("electricalTotalPrice", Texts.of(cabinetQuotation.getElectricalTotalPrice().setScale(2, BigDecimal.ROUND_DOWN).toString()).create());
            innerMap.put("allTotalPriceWords", Texts.of(CommonUtil.toChineseAmount(cabinetQuotation.getAllTotalPrice().setScale(2, BigDecimal.ROUND_DOWN))).create());
            innerMap.put("allTotalPrice", Texts.of(cabinetQuotation.getAllTotalPrice().setScale(2, BigDecimal.ROUND_DOWN).toString()).create());
            innerMap.put("cabinetsExcelList", resultCabinetsExcelList);
            innerMap.put("kitchenAppliancesExcelList", resultKitchenAppliancesExcelList);
//            innerMapList.add(innerMap);

            // 使用 ByteArrayOutputStream
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

            LoopRowTableRenderPolicy policy = new LoopRowTableRenderPolicy();
            Configure config = Configure.builder()
                    .bind("cabinetsExcelList", policy).bind("kitchenAppliancesExcelList", policy).build();

            // 渲染模板并将结果写入 ByteArrayOutputStream
            XWPFTemplate.compile("src/main/resources/WordExcelTemplate/报价单模板.docx",config).render(innerMap).write(byteArrayOutputStream);


            // 将 ByteArrayOutputStream 中的数据转换为 byte[]
            byte[] data = byteArrayOutputStream.toByteArray();

            // 在这里转成PDF
            PDDocument pdfDocument = new PDDocument();
            PDPage page = new PDPage();
            pdfDocument.addPage(page);

            PDPageContentStream contentStream = new PDPageContentStream(pdfDocument, page);

            // 将从Word模板中读取的内容写入PDF
            ByteArrayInputStream inputStream = new ByteArrayInputStream(data);
            int c;
            while ((c = inputStream.read()) != -1) {
                byteArrayOutputStream.write(c);
            }
            byteArrayOutputStream.flush();
            byteArrayOutputStream.close();
            inputStream.close();

            contentStream.beginText();
            contentStream.setFont(new PDType0Font(PDType0Font.load(pdfDocument, getClass().getClassLoader().getResourceAsStream("Arial.ttf")).getCOSObject()), 12);
//            contentStream.setFont( new PDType1Font(Standard14Fonts.FontName.SYMBOL), 12);
//            contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 12);
            contentStream.newLineAtOffset(100, 700);
//            contentStream.showText(new String(byteArrayOutputStream.toByteArray(), StandardCharsets.UTF_8));
            contentStream.showText("lll");
            contentStream.endText();
            contentStream.close();
            pdfDocument.save(byteArrayOutputStream);
            byte[] pdfData = byteArrayOutputStream.toByteArray();

            // 设置响应头，指定文件名和文件类型
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", URLEncoder.encode(cabinetQuotation.getAddress()+"报价单.pdf", "UTF-8"));

            // 返回 ResponseEntity 对象，设置状态码为 200 OK
            return ResponseEntity.ok()
                    .headers(headers)
                    .body(pdfData);

    } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
