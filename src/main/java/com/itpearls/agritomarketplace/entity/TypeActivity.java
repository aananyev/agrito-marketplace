package com.itpearls.agritomarketplace.entity;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.DependsOnProperties;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@JmixEntity
@Table(name = "TYPE_ACTIVITY", indexes = {
        @Index(name = "IDX_TYPE_ACTIVITY_UNQ", columnList = "TYPE_ACTIVITY", unique = true)
})
@Entity
public class TypeActivity {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @Column(name = "TYPE_ACTIVITY", nullable = false, length = 128)
    @NotNull
    private String typeActivity;

    @Column(name = "COMMENT_")
    @Lob
    private String comment;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getTypeActivity() {
        return typeActivity;
    }

    public void setTypeActivity(String typeActivity) {
        this.typeActivity = typeActivity;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @InstanceName
    @DependsOnProperties({"typeActivity"})
    public String getInstanceName() {
        return String.format("%s", typeActivity);
    }
}