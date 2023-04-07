package com.itpearls.agritomarketplace.entity;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.DependsOnProperties;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@JmixEntity
@Table(name = "PRODUCT", indexes = {
        @Index(name = "IDX_PRODUCT_PRODUCT_VARIETY", columnList = "PRODUCT_VARIETY_ID")
})
@Entity
public class Product {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @Column(name = "PRODUCT_NAME", nullable = false, length = 128)
    @NotNull
    private String productName;

    @JoinColumn(name = "PRODUCT_VARIETY_ID", nullable = false)
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Variety productVariety;

    @Column(name = "COMMENT_")
    @Lob
    private String comment;

    public Variety getProductVariety() {
        return productVariety;
    }

    public void setProductVariety(Variety productVariety) {
        this.productVariety = productVariety;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @InstanceName
    @DependsOnProperties({"productName", "productVariety"})
    public String getInstanceName() {
        return String.format("%s %s", productName, productVariety);
    }
}