package org.econtact.model.entity.account;

import org.econtact.model.entity.AbstractEntity;

import java.math.BigDecimal;

public class PersonEntity extends AbstractEntity<BigDecimal> {

    private BigDecimal id;

    private String firstName;

    private String lastName;

    @Override
    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
