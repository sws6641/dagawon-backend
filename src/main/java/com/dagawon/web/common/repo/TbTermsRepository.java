package com.dagawon.web.common.repo;

import com.dagawon.web.common.entity.TbMemb;
import com.dagawon.web.common.entity.TbTerms;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TbTermsRepository extends JpaRepository<TbTerms, Long> {
    Optional<TbTerms> findByTermsId(Long termsId);

}