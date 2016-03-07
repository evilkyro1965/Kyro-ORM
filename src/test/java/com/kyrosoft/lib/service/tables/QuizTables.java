package com.kyrosoft.lib.service.tables;

import com.kyrosoft.lib.service.orm.DataType;
import com.kyrosoft.lib.service.orm.FieldMapping;
import com.kyrosoft.lib.service.orm.Table;
import com.kyrosoft.lib.service.orm.Tables;
import com.kyrosoft.quizapp.entity.Quiz;
import com.kyrosoft.quizapp.entity.QuizCategory;
import com.kyrosoft.quizapp.entity.User;
import com.kyrosoft.quizapp.entity.UserType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

/**
 * Created by Administrator on 3/5/2016.
 */
public class QuizTables extends Tables {

    private static final String userTableName = "user";
    private static final String quizTableName = "quiz";
    private static final String quizCategoryTableName = "quiz_category";

    public Table userTable;
    public Table quizCategoryTable;
    public Table quizTable;

    {
        userTable = new Table(
                userTableName,
                "id",
                new ArrayList<FieldMapping>(Arrays.asList(
                        new FieldMapping("id", "id", DataType.INTEGER),
                        new FieldMapping("email", "email", DataType.STRING),
                        new FieldMapping("firstName", "firstName", DataType.STRING),
                        new FieldMapping("isActive", "isActive", DataType.BOOLEAN),
                        new FieldMapping("lastName", "lastName", DataType.STRING),
                        new FieldMapping("username", "username", DataType.STRING),
                        new FieldMapping("userType", "userType", DataType.STRING_ENUM)
                ))
        );

        quizCategoryTable = new Table(
                quizCategoryTableName,
                "id",
                new ArrayList<FieldMapping>(Arrays.asList(
                        new FieldMapping("id", "id", DataType.INTEGER),
                        new FieldMapping("name", "name", DataType.STRING)
                ))
        );

        quizTable = new Table(
                quizTableName,
                "id",
                new ArrayList<FieldMapping>(Arrays.asList(
                        new FieldMapping("id", "id", DataType.INTEGER),
                        new FieldMapping("name", "name", DataType.STRING),
                        new FieldMapping("category", "categoryId", quizCategoryTable, DataType.OBJECT, QuizCategory.class)
                ))
        );

        mappingMap.put(User.class,userTable);
        mappingMap.put(QuizCategory.class,quizCategoryTable);
        mappingMap.put(Quiz.class,quizTable);
    }


    public QuizTables() {
    }

}
