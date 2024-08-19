//package com.ms.data.master.account.model.mapper;
//
//import com.ms.data.master.account.model.Cafe;
//import com.ms.data.master.account.model.Employee;
//import com.ms.data.master.account.model.dto.account.EmployeeDTO;
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//import org.mapstruct.MappingTarget;
//import org.mapstruct.Named;
//import org.mapstruct.factory.Mappers;
//
//import java.util.UUID;
//
//@Mapper(componentModel = "spring")
//public interface EmployeeMapper {
//    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);
//
//    @Mapping(target = "cafeId", source = "cafe.id")
//    @Mapping(target = "cafeName", source = "cafe.name")
//    @Mapping(target = "daysWorked", expression = "java(java.time.temporal.ChronoUnit.DAYS.between(employee.getStartDate(), java.time.LocalDateTime.now()))")
//    EmployeeDTO toDTO(Employee employee);
//
//    @Mapping(target = "cafe", source = "cafeId", qualifiedByName = "uuidToCafe")
//    Employee toEntity(EmployeeDTO employeeDTO);
//
//    void updateFromDTOToEntity(EmployeeDTO employeeDTO, @MappingTarget Employee employee);
//
//    @Named("uuidToCafe")
//    default Cafe uuidToCafe(UUID cafeId) {
//        if (cafeId == null) {
//            return null;
//        }
//        Cafe cafe = new Cafe();
//        cafe.setId(cafeId);
//        return cafe;
//    }
//}