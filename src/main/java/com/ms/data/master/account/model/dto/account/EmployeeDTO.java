package com.ms.data.master.account.model.dto.account;

import com.ms.data.master.account.constant.Gender;
import com.ms.data.master.account.model.Cafe;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {

    private Long id;
    private String employeeId;
    private String name;
    private String emailAddress;
    private String phoneNumber;
    private Gender gender;
    private LocalDate startDate;
    private String cafeName;
    private int daysWorked;
}
