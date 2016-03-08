package com.kyrosoft.lib.service.orm;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.*;
import java.util.Date;

/**
 * Orm Main Class
 *
 * @author : Fahrurrazi (evilkyro1965@yahoo.com)
 */
public class Orm {

    private Connection con;

    private Tables tables;

    public <T> List<T>getQueryResult(PreparedStatement statement,Class<T> classType)
            throws IllegalAccessException, InstantiationException, SQLException, NoSuchFieldException {
        List<T> ret = new ArrayList<T>();

        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            T row = getObject(rs,classType);
            ret.add(row);
        }
        return ret;
    }

    public <T> T getObject(ResultSet resultSet, Class<T> classType)
            throws IllegalAccessException, InstantiationException, SQLException, NoSuchFieldException {
        T ret = classType.newInstance();

        Table table = tables.mappingMap.get(classType);
        String tableName = table.getName();
        List<FieldMapping> mapping = table.getMapping();
        Iterator it = mapping.iterator();
        while (it.hasNext()) {
            FieldMapping fieldMapping = (FieldMapping) it.next();

            String propName = fieldMapping.propertyName;
            DataType dataType = fieldMapping.dataType;
            String fieldName = getFieldName(tableName,fieldMapping.tableFieldName);
            if(dataType!=DataType.OBJECT && dataType!=DataType.STRING_ENUM && dataType!=DataType.ORDINAL_ENUM) {
                Class<?> propClassType = getClassType(dataType);

                Object val = getValueOf(propClassType, fieldName, resultSet);
                Field field = classType.getDeclaredField(propName);
                field.setAccessible(true);
                field.set(ret, val);
            }
            else if(dataType==DataType.STRING_ENUM) {
                String strVal = resultSet.getString(fieldName);

                Field field = classType.getDeclaredField(propName);
                field.setAccessible(true);
                field.set(ret, Enum.valueOf((Class<Enum>) field.getType(), strVal));
            }
            else if(dataType==DataType.ORDINAL_ENUM) {
                Integer intVal = resultSet.getInt(fieldName);

                Field field = classType.getDeclaredField(propName);
                field.setAccessible(true);
                field.set(ret, ((Class<Enum>) field.getType()).getEnumConstants()[intVal]);
            }
            else if(dataType==DataType.OBJECT) {
                Object val = getObject(resultSet,fieldMapping.classType);
                Field field = classType.getDeclaredField(propName);
                field.setAccessible(true);
                field.set(ret, val);
            }
        }

        return ret;
    }

    private <T> T getValueOf(Class<T> classType,String fieldName,ResultSet resultSet)
            throws SQLException, IllegalAccessException, InstantiationException
    {
        T ret = null;
        if(classType==String.class) {
            ret = (T) new String(resultSet.getString(fieldName));
        }
        else if(classType==Integer.class) {
            ret = (T) new Integer(resultSet.getInt(fieldName));
        }
        else if(classType==Float.class) {
            ret = (T) new Float(resultSet.getFloat(fieldName));
        }
        else if(classType==Double.class) {
            ret = (T) new Double(resultSet.getDouble(fieldName));
        }
        else if(classType==Boolean.class) {
            ret = (T) new Boolean(resultSet.getBoolean(fieldName));
        }
        else if(classType==Date.class) {
            ret = (T) new Date(resultSet.getDate(fieldName).getTime());
        }
        else if(classType==Date.class) {
            ret = (T) new Date(resultSet.getDate(fieldName).getTime());
        }

        return ret;
    }

    private Class<?> getClassType(DataType dataType) {

        switch(dataType) {
            case STRING : return String.class;
            case INTEGER : return Integer.class;
            case FLOAT : return Float.class;
            case DOUBLE : return Double.class;
            case BOOLEAN : return Boolean.class;
            case DATE : return Date.class;
            default : return String.class;
        }
    }

    private String getFieldName(String tableName,String fieldName) {
        String ret = "";
        ret += tableName+"_"+fieldName;
        return ret;
    }

    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }

    public Tables getTables() {
        return tables;
    }

    public void setTables(Tables tables) {
        this.tables = tables;
    }


}
