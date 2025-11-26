package com.dagawon.web.common.repo;

import com.dagawon.web.common.entity.TbMemb;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TbMembRepository extends JpaRepository<TbMemb, Long> {
    Optional<TbMemb> findByMembNo(Long membNo);

    Optional<TbMemb> findByMembEmail(String membId);

    Boolean existsByMembEmailAndBizNo_BizNo(String membId, Long bizNo);
}