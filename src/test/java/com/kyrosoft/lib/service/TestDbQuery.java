package com.kyrosoft.lib.service;

import com.kyrosoft.lib.service.orm.*;
import com.kyrosoft.lib.service.tables.QuizTables;
import com.kyrosoft.quizapp.entity.Quiz;
import com.kyrosoft.quizapp.entity.User;
import org.junit.Test;

import java.sql.*;
import java.util.List;

/**
 * Created by Administrator on 3/5/2016.
 */
public class TestDbQuery {

    private Orm orm = new Orm();

    public TestDbQuery() {
        Connection con = DBConnection.getConnection();
        orm.setCon(con);
        orm.setTables(new QuizTables());
    }

    @Test
    public void testDBQuery() throws SQLException {
        Connection con = DBConnection.getConnection();

        String selectTableSQL = "SELECT * from user";
        Statement statement = con.createStatement();
        ResultSet rs = statement.executeQuery(selectTableSQL);
        while (rs.next()) {
            String username = rs.getString("username");
            System.out.println(username);
        }
    }

    @Test
    public void testGetObject() throws SQLException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        Connection con = DBConnection.getConnection();

        QuizTables quizTables = new QuizTables();

        String selectTableSQL = quizTables.getSelectQuery(User.class);

        PreparedStatement statement = orm.getCon().prepareStatement(selectTableSQL);
        ResultSet rs = statement.executeQuery(selectTableSQL);
        while (rs.next()) {
            User user = orm.getObject(rs,User.class);
            System.out.println(user.getUsername());
        }
    }

    @Test
    public void testGetObject2() throws SQLException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        QuizTables quizTables = new QuizTables();

        String selectTableSQL = quizTables.getSelectQuery(Quiz.class);

        PreparedStatement statement = orm.getCon().prepareStatement(selectTableSQL);

        List<Quiz> quizes = orm.getQueryResult(statement,Quiz.class);
        int hammertime = -1;
    }

    @Test
    public void testGetObject3() throws SQLException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        QuizTables quizTables = new QuizTables();
        String quizTableName = quizTables.quizTable.getName();
        String selectTableSQL = quizTables.getSelectQuery(Quiz.class)+" WHERE "+quizTableName+".id=?";

        PreparedStatement statement = orm.getCon().prepareStatement(selectTableSQL);
        statement.setInt(1,6);

        List<Quiz> quizes = orm.getQueryResult(statement,Quiz.class);
        int hammertime = -1;
    }
}
