package com.dagawon.web.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class TbSequenceId implements Serializable {
    private static final long serialVersionUID = -1877395918915440594L;
    @NotNull
    @Column(name = "seq_date", nullable = false)
    private LocalDate seqDate;

    @Size(max = 100)
    @NotNull
    @Column(name = "seq_name", nullable = false, length = 100)
    private String seqName;

    @NotNull
    @Column(name = "seq_number", nullable = false)
    private Integer seqNumber;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        TbSequenceId entity = (TbSequenceId) o;
        return Objects.equals(this.seqDate, entity.seqDate) &&
                Objects.equals(this.seqName, entity.seqName) &&
                Objects.equals(this.seqNumber, entity.seqNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(seqDate, seqName, seqNumber);
    }

}