package com.dagawon.web.common.repo;

import com.dagawon.web.common.entity.TbMemb;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TbMembRepository extends JpaRepository<TbMemb, Long> {
}