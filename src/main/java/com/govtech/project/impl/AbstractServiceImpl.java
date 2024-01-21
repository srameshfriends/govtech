package com.govtech.project.impl;

import com.govtech.project.entity.BaseEntity;

import java.time.LocalDateTime;

public abstract class AbstractServiceImpl {
    protected void setBaseFields(BaseEntity old, BaseEntity target) {
        BaseEntity session = getSession();
        if(old != null) {
            target.setId(old.getId());
            target.setRev(old.getRev() + 1);
        } else {
            target.setRev(1);
            target.setCreatedBy(session.getId());
            target.setCreatedOn(session.getUpdatedOn());
        }
        target.setUpdatedBy(session.getId());
        target.setUpdatedOn(session.getUpdatedOn());
    }

    private BaseEntity getSession() {
        BaseEntity entity = new BaseEntity();
        entity.setId(1000L);
        entity.setUpdatedOn(LocalDateTime.now());
        return entity;
    }
}
