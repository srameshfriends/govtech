package com.govtech.project.h2;

import com.govtech.project.utils.OrmUtil;
import jakarta.persistence.Query;

import java.util.ArrayList;
import java.util.List;

public abstract class H2AbstractRepoImpl {
    @SuppressWarnings("unchecked")
    protected <T> T getSingleResult(Query query, Class<T> classType, String fields) {
        List<Object[]> resultList = query.getResultList();
        return resultList.isEmpty() ? null : OrmUtil.as(classType, fields, resultList.get(0));
    }

    @SuppressWarnings("unchecked")
    protected <T> List<T> getResultList(Query query, Class<T> classType, String fields) {
        List<Object[]> resultList = query.getResultList();
        List<T> typeList = new ArrayList<>();
        if(resultList.isEmpty()) {
            return typeList;
        }
        resultList.forEach(objects -> typeList.add(OrmUtil.as(classType, fields, objects)));
        return typeList;
    }
}
