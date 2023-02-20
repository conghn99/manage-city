package com.example.managecity.validate;

import com.example.managecity.dto.EmployeeDTO;
import com.example.managecity.entity.District;
import com.example.managecity.entity.Ward;
import com.example.managecity.repository.CityRepository;
import com.example.managecity.repository.DistrictRepository;
import com.example.managecity.repository.EmployeeRepository;
import com.example.managecity.repository.WardRepository;
import com.example.managecity.request.UpsertEmployeeRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@RequiredArgsConstructor
public class EmployeeValidate {
    private final EmployeeRepository employeeRepository;
    private final CityRepository cityRepository;
    private final DistrictRepository districtRepository;
    private final WardRepository wardRepository;

    public ResponseStatus validate(UpsertEmployeeRequest request){
        ResponseStatus status = validateCode(request.getCode());
        if (status!=ResponseStatus.OK){
            return status;
        }

        if (!StringUtils.hasText(request.getName())){
            return ResponseStatus.EMPLOYEE_NAME_IS_NULL;
        }

        status = validateEmail(request.getEmail());
        if (status!=ResponseStatus.OK){
            return status;
        }

        status = validatePhone(request.getPhone());
        if (status!=ResponseStatus.OK){
            return status;
        }

        status = validateAge(request.getAge());
        if (status!=ResponseStatus.OK){
            return status;
        }

        Integer cityId = request.getCityId();
        Integer districtId = request.getDistrictId();
        Integer wardId = request.getWardId();

        status = validateAddress(cityId, districtId, wardId);
        if (status!=ResponseStatus.OK){
            return status;
        }

        return ResponseStatus.OK;
    }

    public ResponseStatus validateCode(String code){
        if(!StringUtils.hasText(code)){
            return ResponseStatus.EMPLOYEE_CODE_IS_NULL;
        }
        Pattern pattern = Pattern.compile("^[0-9\\w-!@#$%^&*]{6,10}$");
        Matcher matcher = pattern.matcher(code);
        if (!matcher.find()) {
            return ResponseStatus.EMPLOYEE_CODE_WRONG_FORMAT;
        }
        if(employeeRepository.findByCode(code) != null) {
            return ResponseStatus.EMPLOYEE_CODE_ALREADY_EXIST;
        }

        return ResponseStatus.OK;
    }

    public ResponseStatus validateEmail(String email){
        if(!StringUtils.hasText(email)){
            return ResponseStatus.EMPLOYEE_EMAIL_IS_NULL;
        }
        Pattern pattern = Pattern.compile("^[a-zA-Z]+[a-zA-Z0-9]*(\\.[a-zA-Z0-9]+)*@{1}[a-zA-Z]+(\\.[a-zA-Z0-9]+)*(\\.[a-zA-Z]{2,})$");
        Matcher matcher = pattern.matcher(email);
        if(!matcher.find()){
            return ResponseStatus.EMAIL_WRONG_FORMAT;
        }
        return ResponseStatus.OK;
    }

    public ResponseStatus validatePhone(String phone){
        if(!StringUtils.hasText(phone)){
            return ResponseStatus.EMPLOYEE_PHONE_IS_NULL;
        }
        Pattern pattern = Pattern.compile("^[\\d]{11}$");
        Matcher matcher = pattern.matcher(phone);
        if(!matcher.find()){
            return ResponseStatus.PHONE_WRONG_FORMAT;
        }
        return ResponseStatus.OK;
    }
    public ResponseStatus validateAge(Integer age){
        if(age == null){
            return ResponseStatus.EMPLOYEE_AGE_IS_NULL;
        }
        if(age <= 0){
            return ResponseStatus.EMPLOYEE_AGE_WRONG_FORMAT;
        }
        return ResponseStatus.OK;
    }

    public ResponseStatus validateAddress(Integer cityId, Integer districtId, Integer wardId){
        if(cityId == null || districtId == null || wardId == null){
            return ResponseStatus.HAS_CITY_PROVINCE_WARD_ID;
        }

        if (cityRepository.getById(cityId) == null) {
            return ResponseStatus.CITY_NOT_EXIST;
        }

        District district = districtRepository.getById(districtId);
        if (!cityId.equals(district.getCity().getId())){
            return ResponseStatus.DISTRICT_NOT_EXIST;
        }
        Ward village = wardRepository.getById(wardId);
        if (!districtId.equals(village.getDistrict().getId())){
            return ResponseStatus.WARD_NOT_EXIST;
        }
        return ResponseStatus.OK;
    }
}
