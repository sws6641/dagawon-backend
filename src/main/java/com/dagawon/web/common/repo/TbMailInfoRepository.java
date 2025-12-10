package com.dagawon.web.common.repo;

import com.dagawon.web.common.entity.TbMailInfo;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TbMailInfoRepository extends JpaRepository<TbMailInfo, Long> {
    Optional<TbMailInfo> findTop1ByAcntCdAndUseYnOrderByCrtDtmDesc(String acntCd, String useYn);
}