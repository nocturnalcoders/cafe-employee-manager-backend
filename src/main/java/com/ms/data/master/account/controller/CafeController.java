package com.ms.data.master.account.controller;

import com.ms.data.master.account.constant.PathConstant;
import com.ms.data.master.account.model.dto.account.CafeDTO;
import com.ms.data.master.account.service.CafeService;

import com.mysql.cj.log.Log;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(PathConstant.CAFE_CONTROLLER)
@RequiredArgsConstructor
@Slf4j
public class CafeController {
    private final CafeService cafeService;

    @Value("${common.pageable.size}")
    private Integer pageableSize;

    @Value("${common.pageable.page}")
    private Integer pageablePage;

    @Value("${common.sorting}")
    private String sortingPage;

    @GetMapping
    public List<CafeDTO> getCafes(@RequestParam(required = false) String location) {
        return cafeService.getCafesByLocation(location);
    }

    @PostMapping
    public CafeDTO createCafe(@RequestParam("name") String name,
                              @RequestParam("description") String description,
                              @RequestParam("location") String location,
                              @RequestParam(value = "logo", required = false) MultipartFile logoFile) {
        String logoUrl = null;
        if (logoFile != null) {
            // Upload the logo and get the URL
            logoUrl = uploadLogo(logoFile);
        }
        CafeDTO cafeDTO = new CafeDTO();
        cafeDTO.setName(name);
        cafeDTO.setDescription(description);
        cafeDTO.setLocation(location);
        cafeDTO.setLogo(logoUrl);
        return cafeService.createCafe(cafeDTO);
    }

    @PutMapping
    public CafeDTO updateCafe(@RequestParam("id") Long id,
                              @RequestParam("name") String name,
                              @RequestParam("description") String description,
                              @RequestParam("location") String location,
                              @RequestParam(value = "logo", required = false) MultipartFile logoFile) {
        String logoUrl = null;
        if (logoFile != null) {
            logoUrl = uploadLogo(logoFile);
        }

        CafeDTO existingCafeDTO = cafeService.getCafeById(id);

        existingCafeDTO.setName(name);
        existingCafeDTO.setDescription(description);
        existingCafeDTO.setLocation(location);
        if (logoUrl != null) {
            existingCafeDTO.setLogo(logoUrl);
        }
        return cafeService.updateCafe(existingCafeDTO);
    }


    @DeleteMapping("/{id}")
    public void deleteCafe(@PathVariable Long id) {
        cafeService.deleteCafe(id);
    }

    private String uploadLogo(MultipartFile file) {
        return new FileUploadController().uploadLogo(file);
    }
}
