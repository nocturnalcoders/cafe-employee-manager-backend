package com.ms.data.master.account.respository;

import com.ms.data.master.account.model.Cafe;
import com.ms.data.master.account.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CafeRepository extends JpaRepository<Cafe, UUID> {

    Page<Cafe> findAll(Specification<Cafe> spec, Pageable pageable);
    List<Cafe> findByLocation(String location);
    Optional<Cafe> findByName(String name);


    boolean existsById(Long id);

    Optional<Cafe> findById(Long id);

    void deleteById(Long id);
}
