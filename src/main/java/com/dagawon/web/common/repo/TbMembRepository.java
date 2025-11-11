package com.dagawon.web.common.repo;

import com.dagawon.web.common.entity.TbMemb;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TbMembRepository extends JpaRepository<TbMemb, Long> {
    Optional<TbMemb> findByMembId(String membId);
}