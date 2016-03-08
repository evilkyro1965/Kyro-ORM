package com.kyrosoft.lib.service;

import com.kyrosoft.lib.service.orm.*;
import com.kyrosoft.lib.service.tables.QuizTables;
import com.kyrosoft.quizapp.entity.Quiz;
import com.kyrosoft.quizapp.entity.User;
import org.junit.Test;
import static org.junit.Assert.*;

import java.sql.*;
import java.util.List;

/**
 * Orm Main Class
 *
 * @author : Fahrurrazi (evilkyro1965@yahoo.com)
 */
public class TestOrm {

    private Orm orm = new Orm();

    public TestOrm() {
        Connection con = DBConnection.getConnection();
        orm.setCon(con);
        orm.setTables(new QuizTables());
    }

    @Test
    public void testGetUser() throws SQLException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        Connection con = DBConnection.getConnection();

        QuizTables quizTables = new QuizTables();

        String selectTableSQL = quizTables.getSelectQuery(User.class);

        PreparedStatement statement = orm.getCon().prepareStatement(selectTableSQL);
        ResultSet rs = statement.executeQuery(selectTableSQL);
        rs.next();
        User user = orm.getObject(rs,User.class);
        assertEquals(1L,(long) user.getId());
        assertEquals("teacher",user.getUsername());

    }

    @Test
    public void testGetQuiz() throws SQLException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        QuizTables quizTables = new QuizTables();

        String selectTableSQL = quizTables.getSelectQuery(Quiz.class);
        PreparedStatement statement = orm.getCon().prepareStatement(selectTableSQL);

        List<Quiz> quizes = orm.getQueryResult(statement,Quiz.class);
        Quiz quiz = quizes.get(0);
        assertEquals(1L,(long) quiz.getId());
        assertEquals("Computer Basic 1",quiz.getName());
    }

    @Test
    public void testGetQuizWhere() throws SQLException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        QuizTables quizTables = new QuizTables();
        String quizTableName = quizTables.quizTable.getName();
        String selectTableSQL = quizTables.getSelectQuery(Quiz.class)+" WHERE "+quizTableName+".id=?";

        PreparedStatement statement = orm.getCon().prepareStatement(selectTableSQL);
        statement.setInt(1,1);

        List<Quiz> quizes = orm.getQueryResult(statement,Quiz.class);
        Quiz quiz = quizes.get(0);
        assertEquals(1L,(long) quiz.getId());
        assertEquals("Computer Basic 1",quiz.getName());
    }
}
