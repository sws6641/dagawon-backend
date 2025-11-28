package com.dagawon.web.common.entity;

import com.dagawon.web.common.entity.base.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "TB_COMM_CODE")
public class TbCommCode extends BaseTimeEntity {
    @EmbeddedId
    private TbCommCodeId id;

    @Size(max = 100)
    @NotNull
    @ColumnDefault("'코드명'")
    @Column(name = "CODE_NM", nullable = false, length = 100)
    private String codeNm;

    @Size(max = 100)
    @NotNull
    @ColumnDefault("'그룹명'")
    @Column(name = "GRP_NM", nullable = false, length = 100)
    private String grpNm;

    @Size(max = 500)
    @Column(name = "GRP_DESC", length = 500)
    private String grpDesc;

    @NotNull
    @ColumnDefault("999999")
    @Column(name = "NUM", nullable = false)
    private Integer num;

    @Size(max = 1000)
    @Column(name = "ETC_1", length = 1000)
    private String etc1;

    @Size(max = 1000)
    @Column(name = "ETC_2", length = 1000)
    private String etc2;

    @Size(max = 1000)
    @Column(name = "ETC_3", length = 1000)
    private String etc3;

    @Size(max = 1)
    @NotNull
    @ColumnDefault("'Y'")
    @Column(name = "USE_YN", nullable = false, length = 1)
    private String useYn;

}