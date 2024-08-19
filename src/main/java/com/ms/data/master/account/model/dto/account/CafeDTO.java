package com.ms.data.master.account.model.dto.account;

import com.ms.data.master.account.model.Employee;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CafeDTO {

    private Long id;
    private String name;
    private String description;
    private String logo;
    private String location;
    private int employees;

}
