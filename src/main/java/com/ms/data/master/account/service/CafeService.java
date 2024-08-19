package com.ms.data.master.account.service;

import com.ms.data.master.account.model.Cafe;
import com.ms.data.master.account.model.dto.account.CafeDTO;
import com.ms.data.master.account.respository.CafeRepository;
import com.ms.data.master.account.respository.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class CafeService {
    private final CafeRepository cafeRepository;
    private final EmployeeRepository employeeRepository;


    private CafeDTO toDTO(Cafe cafe) {
        CafeDTO cafeDTO = new CafeDTO();
        cafeDTO.setId(cafe.getId());
        cafeDTO.setName(cafe.getName());
        cafeDTO.setDescription(cafe.getDescription());
        cafeDTO.setLogo(cafe.getLogo());
        cafeDTO.setLocation(cafe.getLocation());

        // Fetch the count of employees associated with the cafe
        int employeeCount = employeeRepository.countByCafeId(cafe.getId());
        cafeDTO.setEmployees(employeeCount);

        return cafeDTO;
    }

    // Convert CafeDTO to Cafe entity
    private Cafe fromDTO(CafeDTO cafeDTO) {
        Cafe cafe = new Cafe();
        cafe.setId(cafeDTO.getId());
        cafe.setName(cafeDTO.getName());
        cafe.setDescription(cafeDTO.getDescription());
        cafe.setLogo(cafeDTO.getLogo());
        cafe.setLocation(cafeDTO.getLocation());
        return cafe;
    }

    // Get cafes by location, or all cafes if location is null
    public List<CafeDTO> getCafesByLocation(String location) {
        List<Cafe> cafes = location == null ?
                cafeRepository.findAll() :
                cafeRepository.findByLocation(location);

        return cafes.stream()
                .map(this::toDTO)
                .sorted(Comparator.comparingInt(CafeDTO::getEmployees).reversed())
                .collect(Collectors.toList());
    }

    // Create a new cafe
    public CafeDTO createCafe(CafeDTO cafeDTO) {
        Cafe cafe = fromDTO(cafeDTO);
        Cafe savedCafe = cafeRepository.save(cafe);
        return toDTO(savedCafe);
    }

    // Update an existing cafe
    public CafeDTO updateCafe(CafeDTO cafeDTO) {
        if (!cafeRepository.existsById(cafeDTO.getId())) {
            throw new EntityNotFoundException("Cafe not found with id: " + cafeDTO.getId());
        }
        Cafe cafe = fromDTO(cafeDTO);
        Cafe updatedCafe = cafeRepository.save(cafe);
        return toDTO(updatedCafe);
    }

    // Delete an existing cafe and all employees associated with it
    public void deleteCafe(Long id) {
        if (!cafeRepository.existsById(id)) {
            throw new EntityNotFoundException("Cafe not found with id: " + id);
        }
        // Delete all employees associated with this cafe
        employeeRepository.deleteAll(employeeRepository.findByCafeId(id));
        cafeRepository.deleteById(id);
    }

    public CafeDTO getCafeById(Long id) {
        Cafe cafe = cafeRepository. findById(id)
                .orElseThrow(() -> new NoSuchElementException("Cafe not found with id: " + id));
        return toDTO(cafe);
    }


}
