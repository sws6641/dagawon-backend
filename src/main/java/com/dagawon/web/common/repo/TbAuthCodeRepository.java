package com.dagawon.web.common.repo;

import com.dagawon.web.common.entity.TbAuthCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TbAuthCodeRepository extends JpaRepository<TbAuthCode, Long> {
}