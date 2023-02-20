package com.example.managecity.service;

import com.example.managecity.dto.EmployeeDTO;
import com.example.managecity.dto.Response;
import com.example.managecity.entity.City;
import com.example.managecity.entity.District;
import com.example.managecity.entity.Employee;
import com.example.managecity.entity.Ward;
import com.example.managecity.repository.CityRepository;
import com.example.managecity.repository.DistrictRepository;
import com.example.managecity.repository.EmployeeRepository;
import com.example.managecity.repository.WardRepository;
import com.example.managecity.request.UpsertEmployeeRequest;
import com.example.managecity.validate.EmployeeValidate;
import com.example.managecity.validate.ResponseStatus;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImportExcelService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeValidate employeeValidate;
    private final CityRepository cityRepository;
    private final DistrictRepository districtRepository;
    private final WardRepository wardRepository;

    private String SHEET = "Employees";
    private String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

    public Response<Object> importExcel(MultipartFile file) {
        if (TYPE.equals(file.getContentType())) {
            try {
                Workbook workbook = new XSSFWorkbook(file.getInputStream());

                Sheet sheet = workbook.getSheet(SHEET);
                List<String> listStatus = new ArrayList<>();

                int rowIndex = 0;
                int columnIndex;
                int rowNum = sheet.getLastRowNum();
                while (rowIndex < rowNum) {
                    rowIndex++;
                    columnIndex = 0;
                    Row row = sheet.getRow(rowIndex);
                    UpsertEmployeeRequest request = new UpsertEmployeeRequest();

                    try {
                        request.setCode(row.getCell(columnIndex++).getStringCellValue());
                        request.setName(row.getCell(columnIndex++).getStringCellValue());
                        request.setEmail(row.getCell(columnIndex++).getStringCellValue());
                        request.setAge((int) row.getCell(columnIndex++).getNumericCellValue());
                        request.setPhone(row.getCell(columnIndex++).getStringCellValue());
                        request.setCityId((int) row.getCell(columnIndex++).getNumericCellValue());
                        request.setDistrictId((int) row.getCell(columnIndex++).getNumericCellValue());
                        request.setWardId((int) row.getCell(columnIndex++).getNumericCellValue());
                    } catch (NullPointerException | IllegalStateException | IllegalArgumentException e) {
                        listStatus.add("Row " + rowIndex + " 'false': (colum " + columnIndex + ")");
                        continue;
                    }

                    ResponseStatus status = employeeValidate.validate(request);

                    if (status != ResponseStatus.OK) {
                        listStatus.add("Row " + rowIndex + " 'false': (" + status.getMessage() + ")");
                        continue;
                    }

                    City city = cityRepository.getById(request.getCityId());
                    District district = districtRepository.getById(request.getDistrictId());
                    Ward ward = wardRepository.getById(request.getWardId());
                    Employee employee = Employee.builder()
                            .code(request.getCode())
                            .name(request.getName())
                            .email(request.getEmail())
                            .age(request.getAge())
                            .phone(request.getPhone())
                            .city(city)
                            .district(district)
                            .ward(ward)
                            .build();
                    employeeRepository.save(employee);
                    listStatus.add("Row " + rowIndex + " 'Success':");
                }
                return new Response<>(listStatus);
            } catch (IOException e) {
                return new Response<>(ResponseStatus.EXCEL_ERROR);
            }
        }
        return new Response<>(ResponseStatus.FILE_ERROR);
    }
}
