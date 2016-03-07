package com.kyrosoft.lib.service.orm;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 3/5/2016.
 */
public class Table {

    private String id;

    private String name;

    private List<FieldMapping> mapping;

    public Table() {}

    public Table(String name,String id,List<FieldMapping> mapping) {
        this.id = id;
        this.name = name;
        this.mapping = mapping;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<FieldMapping> getMapping() {
        return mapping;
    }

    public void setMapping(List<FieldMapping> mapping) {
        this.mapping = mapping;
    }



}
