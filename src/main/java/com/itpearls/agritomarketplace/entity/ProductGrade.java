package com.itpearls.agritomarketplace.entity;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.DependsOnProperties;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@JmixEntity
@Table(name = "PRODUCT_GRADE", indexes = {
        @Index(name = "IDX_PRODUCT_GRADE_GRADE_GROUP", columnList = "GRADE_GROUP_ID")
})
@Entity
public class ProductGrade {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @Column(name = "GRADE_NAME", nullable = false, length = 64)
    @NotNull
    private String gradeName;

    @Column(name = "GRADE_COMMENT")
    @Lob
    private String gradeComment;

    @JoinColumn(name = "GRADE_GROUP_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private ProductGrade gradeGroup;

    public ProductGrade getGradeGroup() {
        return gradeGroup;
    }

    public void setGradeGroup(ProductGrade gradeGroup) {
        this.gradeGroup = gradeGroup;
    }

    public String getGradeComment() {
        return gradeComment;
    }

    public void setGradeComment(String gradeComment) {
        this.gradeComment = gradeComment;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @InstanceName
    @DependsOnProperties({"gradeName"})
    public String getInstanceName() {
        return String.format("%s", gradeName);
    }
}