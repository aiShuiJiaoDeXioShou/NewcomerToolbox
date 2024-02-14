package com.example.newcomertoolbox.logic;

import org.junit.jupiter.api.Test;

import java.util.List;


public class SqlToWordTest {
    @Test
    public void testCreateWordChart() throws Exception {
        SqlToWord sqlToWord = new SqlToWord("jdbc:mysql://localhost:3306/information_community?useSSL=false&serverTimezone=UTC","root","root");
        List<String> tableNames = sqlToWord.getTableNames();
        System.out.println(tableNames);
        List<SqlToWord.SqlTable> tables = sqlToWord.getTables("information_community");
        System.out.println(tables);
    }

    @Test
    public void testSqlToWord() {
        SqlToWord sqlToWord = new SqlToWord("jdbc:mysql://localhost:3306/information_community?useSSL=false&serverTimezone=UTC","root","root");
        sqlToWord.sqlToWord("test1.docx", "information_community");
    }
}
