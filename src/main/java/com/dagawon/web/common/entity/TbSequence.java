package com.dagawon.web.common.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "TB_SEQUENCE")
public class TbSequence {
    @EmbeddedId
    private TbSequenceId id;
}