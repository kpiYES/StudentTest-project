package com.app.model;

import java.io.Serializable;

abstract class AbstractEntity implements Serializable {
    private Long id;

    public AbstractEntity(){}

    public AbstractEntity(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
