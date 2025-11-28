package com.dagawon.web.common.repo;

import com.dagawon.web.common.entity.TbCommCode;
import com.dagawon.web.common.entity.TbCommCodeId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TbCommCodeRepository extends JpaRepository<TbCommCode, TbCommCodeId> {
}