package com.dagawon.web.common.commSvc;

import com.dagawon.web.common.dto.TbMailLogDto;
import com.dagawon.web.common.entity.TbMailLog;
import com.dagawon.web.common.mapper.TbMailLogMapper;
import com.dagawon.web.common.repo.TbMailLogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

/**
 *  트랜잭션 처리를 독립적으로 담당 하는 클래스
 *
 * @author rojoon
 */


@Slf4j
@Component
@Validated
@RequiredArgsConstructor
public class TransactionHandler {

    private final TbMailLogRepository tbMailLogRepository;
    private final TbMailLogMapper tbMailLogMapper;


    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public TbMailLogDto savePreMailLog(TbMailLogDto dto) {
        TbMailLog entity = tbMailLogMapper.toEntity(dto);
        TbMailLog saved = tbMailLogRepository.save(entity);
        tbMailLogRepository.flush(); // 즉시 반영
        return tbMailLogMapper.toDto(saved);
    }



}
