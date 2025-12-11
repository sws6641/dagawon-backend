package com.dagawon.web.common.repo;

import com.dagawon.web.common.entity.TbTerms;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TbTermsRepository extends JpaRepository<TbTerms, Long> {
    List<TbTerms> findByUsagePlace(String usagePlace);

}