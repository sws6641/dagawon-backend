package com.dagawon.web.common.entity;

import com.dagawon.web.common.entity.base.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "TB_TERMS")
public class TbTerms extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TERMS_ID", nullable = false)
    private Long termsId;

    @Column(name = "TERMS_CODE", nullable = false, length = 50)
    private String termsCode;

    @Column(name = "VERSION", nullable = false, length = 10)
    private String version;

    @Column(name = "TITLE", nullable = false, length = 200)
    private String title;

    @Column(name = "CONTENT", nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(name = "IS_REQUIRED", nullable = false, length = 1)
    private String isRequired;

    @Column(name = "START_DT", nullable = false)
    private LocalDate startDt;

    @Column(name = "END_DT")
    private LocalDate endDt;

    @Column(name = "CREATED_DTM", nullable = false, updatable = false)
    private LocalDateTime createdDtm;

    @Column(name = "UPDATED_DTM")
    private LocalDateTime updatedDtm;

}