package com.dagawon.web.common.repo;

import com.dagawon.web.common.entity.TbMailLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TbMailLogRepository extends JpaRepository<TbMailLog, Long> {
}