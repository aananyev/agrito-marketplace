package com.itpearls.agritomarketplace.entity;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.DependsOnProperties;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@JmixEntity
@Table(name = "VARIETY", indexes = {
        @Index(name = "IDX_VARIETY_VARIETY_PRODUCT_TYPE", columnList = "VARIETY_PRODUCT_TYPE_ID")
})
@Entity
public class Variety {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @Column(name = "VARIETY_NAME", nullable = false, length = 128)
    @NotNull
    private String varietyName;

    @JoinColumn(name = "VARIETY_PRODUCT_TYPE_ID", nullable = false)
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private ProductType varietyProductType;

    @Column(name = "COMMENT_")
    @Lob
    private String comment;

    public ProductType getVarietyProductType() {
        return varietyProductType;
    }

    public void setVarietyProductType(ProductType varietyProductType) {
        this.varietyProductType = varietyProductType;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getVarietyName() {
        return varietyName;
    }

    public void setVarietyName(String varietyName) {
        this.varietyName = varietyName;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @InstanceName
    @DependsOnProperties({"varietyName"})
    public String getInstanceName() {
        return String.format("%s", varietyName);
    }
}