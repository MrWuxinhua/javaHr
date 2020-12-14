package com.wuxinhua.service.utils;

import com.wuxinhua.model.*;
import org.apache.poi.hpsf.DocumentSummaryInformation;
import org.apache.poi.hpsf.SummaryInformation;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/**
 * excel导入导出
 */

public class POIUtil {


    public static ResponseEntity<byte[]> exportExcel(List<Employee> list) {


        //创建一个excel 文档
        HSSFWorkbook workbook = new HSSFWorkbook();
        //创建文档摘要
        workbook.createInformationProperties();
        //获取并配置文档摘要信息
        DocumentSummaryInformation document = workbook.getDocumentSummaryInformation();
        //文档类别
        document.setCategory("");
        //管理员
        document.setManager("");
        //公司
        document.setCompany("");
        //获取文档摘要信息
        SummaryInformation information = workbook.getSummaryInformation();
        information.setTitle("员工信息表");
        information.setAuthor("伍新华");
        information.setComments("文档由伍新华 提交");

        //创建标题行样式
        HSSFCellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.YELLOW.index);
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        HSSFCellStyle dateStyle = workbook.createCellStyle();
        dateStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy"));

        //创建表单
        HSSFSheet sheet = workbook.createSheet("员工信息表");
        //设置每一咧的宽度
        //创建标题行
        HSSFRow rowTitle = sheet.createRow(0);
        HSSFCell c0 = rowTitle.createCell(0);
        c0.setCellValue("编号");
        c0.setCellStyle(headerStyle);
        sheet.setColumnWidth(0, 5 * 256);
        HSSFCell c1 = rowTitle.createCell(1);
        c1.setCellStyle(headerStyle);
        c1.setCellValue("姓名");
        sheet.setColumnWidth(1, 10 * 256);
        HSSFCell c2 = rowTitle.createCell(2);
        c2.setCellStyle(headerStyle);
        c2.setCellValue("工号");
        sheet.setColumnWidth(2, 25 * 256);
        HSSFCell c3 = rowTitle.createCell(3);
        c3.setCellStyle(headerStyle);
        c3.setCellValue("出生日期");
        sheet.setColumnWidth(3, 30 * 256);
        HSSFCell c4 = rowTitle.createCell(4);
        c4.setCellStyle(headerStyle);
        c4.setCellValue("身份证号码");
        sheet.setColumnWidth(4, 40 * 256);
        HSSFCell c5 = rowTitle.createCell(5);
        c5.setCellStyle(headerStyle);
        c5.setCellValue("婚姻状况");
        sheet.setColumnWidth(5, 10 * 256);
        HSSFCell c6 = rowTitle.createCell(6);
        c6.setCellStyle(headerStyle);
        c6.setCellValue("民族");
        sheet.setColumnWidth(6, 10 * 256);
        HSSFCell c7 = rowTitle.createCell(7);
        c7.setCellStyle(headerStyle);
        c7.setCellValue("籍贯");
        sheet.setColumnWidth(7, 15 * 256);
        HSSFCell c8 = rowTitle.createCell(8);
        c8.setCellStyle(headerStyle);
        c8.setCellValue("政治面貌");
        sheet.setColumnWidth(8, 10 * 256);
        HSSFCell c9 = rowTitle.createCell(9);
        c9.setCellStyle(headerStyle);
        c9.setCellValue("电子邮件");
        sheet.setColumnWidth(9, 23 * 256);
        HSSFCell c10 = rowTitle.createCell(10);
        c10.setCellStyle(headerStyle);
        c10.setCellValue("电话号码");
        sheet.setColumnWidth(10, 35 * 256);
        HSSFCell c11 = rowTitle.createCell(11);
        c11.setCellStyle(headerStyle);
        c11.setCellValue("联系地址");
        sheet.setColumnWidth(11, 40 * 256);
        HSSFCell c12 = rowTitle.createCell(12);
        c12.setCellStyle(headerStyle);
        c12.setCellValue("所属部门");
        sheet.setColumnWidth(12, 15 * 256);
        HSSFCell c13 = rowTitle.createCell(13);
        c13.setCellStyle(headerStyle);
        c13.setCellValue("职位");
        sheet.setColumnWidth(13, 10 * 256);
        HSSFCell c14 = rowTitle.createCell(14);
        c14.setCellStyle(headerStyle);
        c14.setCellValue("职称");
        sheet.setColumnWidth(14, 10 * 256);
        HSSFCell c15 = rowTitle.createCell(15);
        c15.setCellStyle(headerStyle);
        c15.setCellValue("聘用形式");
        sheet.setColumnWidth(15, 15 * 256);
        HSSFCell c16 = rowTitle.createCell(16);
        c16.setCellStyle(headerStyle);
        c16.setCellValue("入职日期");
        sheet.setColumnWidth(16, 25 * 256);
        HSSFCell c17 = rowTitle.createCell(17);
        c17.setCellStyle(headerStyle);
        c17.setCellValue("转正日期");
        sheet.setColumnWidth(17, 25 * 256);
        HSSFCell c18 = rowTitle.createCell(18);
        c18.setCellStyle(headerStyle);
        c18.setCellValue("同起始日期");
        sheet.setColumnWidth(18, 25 * 256);
        HSSFCell c19 = rowTitle.createCell(19);
        c19.setCellStyle(headerStyle);
        c19.setCellValue("合同截止日期");
        sheet.setColumnWidth(19, 25 * 256);
        HSSFCell c20 = rowTitle.createCell(20);
        c20.setCellStyle(headerStyle);
        c20.setCellValue("合同期限");
        sheet.setColumnWidth(20, 10 * 256);
        HSSFCell c21 = rowTitle.createCell(21);
        c21.setCellStyle(headerStyle);
        c21.setCellValue("最高学历");
        sheet.setColumnWidth(21, 10 * 256);

        for (int i = 0; i < list.size(); i++) {
            Employee employee = list.get(0);
            HSSFRow row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(employee.getId());
            row.createCell(1).setCellValue(employee.getName());
            row.createCell(2).setCellValue(employee.getWorkID());
            HSSFCell cell3 = row.createCell(3);
            cell3.setCellValue(employee.getBirthday());
            cell3.setCellStyle(dateStyle);
            row.createCell(4).setCellValue(employee.getIdCard());
            row.createCell(5).setCellValue(employee.getWedlock());
            row.createCell(6).setCellValue(employee.getNation().getName());
            row.createCell(7).setCellValue(employee.getNativePlace());
            row.createCell(8).setCellValue(employee.getPoliticsstatus().getName());
            row.createCell(9).setCellValue(employee.getEmail());
            row.createCell(10).setCellValue(employee.getPhone());
            row.createCell(11).setCellValue(employee.getAddress());
            row.createCell(12).setCellValue(employee.getDepartment().getName());
            row.createCell(13).setCellValue(employee.getPosition().getName());
            row.createCell(14).setCellValue(employee.getJobLevel().getName());
            row.createCell(15).setCellValue(employee.getEngageForm());
            HSSFCell cell16 = row.createCell(16);
            cell16.setCellValue(employee.getBeginDate());
            cell16.setCellStyle(dateStyle);
            HSSFCell cell17 = row.createCell(17);
            cell17.setCellValue(employee.getConversionTime());
            cell17.setCellStyle(dateStyle);
            HSSFCell cell18 = row.createCell(18);
            cell18.setCellValue(employee.getBeginContract());
            cell18.setCellStyle(dateStyle);
            HSSFCell cell19 = row.createCell(19);
            cell19.setCellValue(employee.getEndContract());
            cell19.setCellStyle(dateStyle);
            row.createCell(20).setCellValue(employee.getContractTerm());
            row.createCell(21).setCellValue(employee.getTiptopDegree());
        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        HttpHeaders headers = new HttpHeaders();
        try {
            headers.setContentDispositionFormData("attachment", new String("员工表.xls".getBytes("utf-8"), "ISO-8859-1"));
            workbook.write(baos);
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<byte[]>(baos.toByteArray(), headers, HttpStatus.CREATED);
    }

    public static List<Employee> importExcel(MultipartFile file, List<Nation> allNation, List<Politicsstatus> allPoliticsstatus, List<Department> allDepartment, List<Position> allPosition, List<JobLevel> jobLevels) throws IOException {


        List<Employee> employees = new ArrayList<>();

        Employee employee = null;

        HSSFWorkbook workbook = new HSSFWorkbook(file.getInputStream());
        int number = workbook.getNumberOfSheets();
        for (int i = 0; i < number; i++) {

            //获取表单
            HSSFSheet sheetAt = workbook.getSheetAt(i);
            //获取表单中有多少行
            int rowNumber = sheetAt.getPhysicalNumberOfRows();
            for (int j = 0; j < rowNumber; j++) {
                if (j == 0) {
                    //跳出第一行标题行
                    continue;
                }
                HSSFRow row = sheetAt.getRow(j);
                if (row == null) {
                    //跳过空行
                    continue;
                }
                int cellNumber = row.getPhysicalNumberOfCells();
                employee = new Employee();
                for (int k = 0; k < cellNumber; k++) {
                    //获取列
                    HSSFCell cell = row.getCell(k);
                    //通过switch 判断cell 的格式等
                    switch (cell.getCellType()) {

                        case STRING:
                            String cellValue = cell.getStringCellValue();
                            switch (k) {
                                case 1:
                                    employee.setName(cellValue);
                                    break;
                                case 2:
                                    employee.setWorkID(cellValue);
                                    break;
                                case 4:
                                    employee.setIdCard(cellValue);
                                    break;
                                case 5:
                                    employee.setWedlock(cellValue);
                                    break;
                                case 6:  //民族
                                    Optional<Nation> nationFirst = allNation.stream().filter(nation -> (cellValue).equals(nation.getName())).findFirst();
                                    employee.setNationId(nationFirst.get() == null ? null : nationFirst.get().getId());
                                    break;
                                case 7: //籍贯
                                    employee.setNativePlace(cellValue);
                                    break;
                                case 8: //政治面貌
                                    Optional<Politicsstatus> politicsstatusFirst = allPoliticsstatus.stream().filter(politicsstatus -> (cellValue).equals(politicsstatus.getName())).findFirst();
                                    employee.setPoliticId(politicsstatusFirst.get() == null ? null : politicsstatusFirst.get().getId());
                                    break;
                                case 9: //电子邮件
                                    employee.setEmail(cellValue);
                                    break;
                                case 10: //电话号码
                                    employee.setPhone(cellValue);
                                    break;
                                case 11: //联系地址
                                    employee.setAddress(cellValue);
                                    break;
                                case 12: //所属部门
                                    Optional<Department> departmentFirst = allDepartment.stream().filter(department -> (cellValue).equals(department.getName())).findFirst();
                                    employee.setDepartmentId(departmentFirst.get() == null ? null :departmentFirst.get().getId());
                                    break;
                                case 13: //职位
                                    Optional<Position> positionFirst = allPosition.stream().filter(position -> (cellValue).equals(position.getName())).findFirst();
                                    employee.setPosId(  positionFirst.get()==null ? null : positionFirst.get().getId());
                                    break;
                                case 14: //职称
                                    Optional<JobLevel> jobLevelFirst = jobLevels.stream().filter(jobLevel -> (cellValue).equals(jobLevel.getName())).findFirst();
                                    employee.setJobLevelId(jobLevelFirst.get() == null ? null : jobLevelFirst.get().getId());
                                    break;
                                case 15: //聘用形式
                                    employee.setEngageForm(cellValue);
                                    break;
                                case 20: //合同期限
                                    employee.setContractTerm(Double.valueOf(cellValue));
                                    break;
                                case 21:
                                    employee.setTiptopDegree(cellValue);
                                    break;
                            }
                            break;
                        default: {

                            switch (k) {

                                case 3: //生日
                                    employee.setBirthday(cell.getDateCellValue());
                                    break;
                                case 16: //入职日期
                                    employee.setBeginDate(cell.getDateCellValue());
                                    break;
                                case 17: //转正日期
                                    employee.setConversionTime(cell.getDateCellValue());
                                    break;
                                case 18: //合同开始日期
                                    employee.setBeginContract(cell.getDateCellValue());
                                    break;
                                case 19: //合同截止日期
                                    employee.setEndContract(cell.getDateCellValue());
                                    break;
                            }
                        }
                        break;
                    }
                }
                employees.add(employee);
            }
        }
        return employees;
    }
}
