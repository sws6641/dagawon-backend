package com.dagawon.web.common.repo;

import com.dagawon.web.common.entity.TbCompany;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TbCompanyRepository extends JpaRepository<TbCompany, Long> {
}