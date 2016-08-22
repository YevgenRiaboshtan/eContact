package org.econtact.model.entity;

import java.io.Serializable;

public interface AbstractView<PK extends Serializable> extends Serializable {

    PK getId();
}
