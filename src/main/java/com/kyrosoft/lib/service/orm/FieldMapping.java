package com.kyrosoft.lib.service.orm;

/**
 * Created by Administrator on 3/5/2016.
 */
public class FieldMapping {

    public String propertyName;

    public String tableFieldName;

    public DataType dataType;

    public Class<?> classType;

    public Table table;

    public FieldMapping(String propertyName,String fieldName,DataType dataType) {
        this.propertyName = propertyName;
        this.tableFieldName = fieldName;
        this.dataType = dataType;
    }

    public FieldMapping(String propertyName,String fieldName,Table table,DataType dataType,Class<?> classType) {
        this.propertyName = propertyName;
        this.tableFieldName = fieldName;
        this.table = table;
        this.dataType = dataType;
        this.classType = classType;
    }
}
