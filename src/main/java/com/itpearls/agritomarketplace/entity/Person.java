package com.itpearls.agritomarketplace.entity;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.DependsOnProperties;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;

@JmixEntity
@Table(name = "PERSON", indexes = {
        @Index(name = "IDX_PERSON_PERSON_POSITION", columnList = "PERSON_POSITION_ID")
})
@Entity
public class Person {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @Column(name = "SURNAME", nullable = false, length = 64)
    @NotNull
    private String personSurname;

    @Column(name = "FIRST_NAME", nullable = false, length = 64)
    @NotNull
    private String personName;

    @Column(name = "MIDDLE_NAME", length = 64)
    private String personMiddleName;

    @JoinColumn(name = "PERSON_POSITION_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Position personPosition;

    @Column(name = "PERSON_PHONE", length = 16)
    private String personPhone;

    @Column(name = "PERSON_EMAIL", length = 64)
    private String personEmail;

    @Column(name = "PERSON_BIRTH_DAY")
    @Temporal(TemporalType.DATE)
    private Date personBirthDay;

    public Position getPersonPosition() {
        return personPosition;
    }

    public void setPersonPosition(Position personPosition) {
        this.personPosition = personPosition;
    }

    public Date getPersonBirthDay() {
        return personBirthDay;
    }

    public void setPersonBirthDay(Date personBirthDay) {
        this.personBirthDay = personBirthDay;
    }

    public String getPersonEmail() {
        return personEmail;
    }

    public void setPersonEmail(String personEmail) {
        this.personEmail = personEmail;
    }

    public String getPersonPhone() {
        return personPhone;
    }

    public void setPersonPhone(String personPhone) {
        this.personPhone = personPhone;
    }

    public String getPersonMiddleName() {
        return personMiddleName;
    }

    public void setPersonMiddleName(String personMiddleName) {
        this.personMiddleName = personMiddleName;
    }

    public String getPersonSurname() {
        return personSurname;
    }

    public void setPersonSurname(String personSurname) {
        this.personSurname = personSurname;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @InstanceName
    @DependsOnProperties({"personSurname", "personName"})
    public String getInstanceName() {
        return String.format("%s %s", personSurname, personName);
    }
}