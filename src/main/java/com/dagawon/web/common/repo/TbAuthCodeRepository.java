package com.dagawon.web.common.repo;

import com.dagawon.web.common.entity.TbAuthCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TbAuthCodeRepository extends JpaRepository<TbAuthCode, Long> {

    Optional<TbAuthCode> findTop1ByMembEmailOrderByExpireDtmDesc(String membEmail);

}