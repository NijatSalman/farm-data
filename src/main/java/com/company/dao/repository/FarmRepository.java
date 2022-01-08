package com.company.dao.repository;

import com.company.dao.entity.Farm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FarmRepository extends JpaRepository<Farm,Long> {

    @Query(nativeQuery =true,value = "SELECT * FROM farm WHERE MONTH(DATE_TIME) IN (:months)")
    Page<Farm> findAllByMonths(Pageable pageable, @Param("months") List<Integer> months);

}
