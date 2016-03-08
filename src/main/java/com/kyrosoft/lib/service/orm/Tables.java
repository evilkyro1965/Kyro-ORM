package com.kyrosoft.lib.service.orm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Orm Main Class
 *
 * @author : Fahrurrazi (evilkyro1965@yahoo.com)
 */
public abstract class Tables {

    public Map<Class<?>,Table> mappingMap = new HashMap<Class<?>, Table>();

    public String getFieldsOfEntity(Class<?> classType) {
        List<Table> tables = new ArrayList<Table>();
        Table table1 = mappingMap.get(classType);
        tables.add(table1);

        if(table1.getMapping()!=null) {
            for(FieldMapping fieldMapping : table1.getMapping()) {
                if(fieldMapping.dataType==DataType.OBJECT) {
                    Table table = mappingMap.get(fieldMapping.classType);
                    tables.add(table);
                }
            }
        }

        StringBuilder str = new StringBuilder("");
        String ret = "";

        if(tables!=null) {
            for(Table table:tables) {
                String tableName = table.getName();
                List<FieldMapping> mapping = table.getMapping();
                if (mapping != null) {
                    for (FieldMapping fieldMapping : mapping) {
                        if(fieldMapping.dataType!=DataType.OBJECT) {
                            str.append(tableName + "." + fieldMapping.tableFieldName + " AS " + tableName + "_" + fieldMapping.tableFieldName + ",");
                        }
                    }
                }
            }
            ret = str.toString();
            ret = ret.substring(0, ret.length() - 1);
        }

        return ret;
    }

    public String getSelectQuery(Class<?> classType) {
        List<Table> tables = new ArrayList<Table>();
        Table table1 = mappingMap.get(classType);
        String tableName = table1.getName();

        if(table1.getMapping()!=null) {
            for(FieldMapping fieldMapping : table1.getMapping()) {
                if(fieldMapping.dataType==DataType.OBJECT) {
                    Table table = mappingMap.get(fieldMapping.classType);
                    tables.add(table);
                }
            }
        }

        StringBuilder sql = new StringBuilder("SELECT ");
        sql.append(getFieldsOfEntity(classType));
        sql.append(" FROM ");
        sql.append(tableName);

        if(table1.getMapping()!=null) {
            for(FieldMapping fieldMapping : table1.getMapping()) {
                if(fieldMapping.dataType==DataType.OBJECT) {
                    Table joinTable = mappingMap.get(fieldMapping.classType);
                    String joinTableId = joinTable.getId();
                    String joinTableName = joinTable.getName();
                    String sourceField = fieldMapping.tableFieldName;
                    sql.append(" LEFT JOIN "+joinTableName+" ON "+
                            tableName+"."+sourceField+" = "+joinTableName+"."+joinTableId);
                }
            }
        }

        return sql.toString();
    }

}
