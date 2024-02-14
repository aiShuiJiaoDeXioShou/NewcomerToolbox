package com.example.newcomertoolbox.logic;

import cn.hutool.db.ds.DSFactory;
import cn.hutool.setting.Setting;

import javax.sql.DataSource;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.xwpf.usermodel.*;

public class SqlToWord {
    private DataSource ds;

    public SqlToWord(String url, String username, String password) {
        Setting setting = new Setting();
        setting.put("url", url);
        setting.put("username", username);
        setting.put("password", password);
        this.ds = DSFactory.create(setting).getDataSource();
    }

    // 获取该数据库所有的表
    public List<String> getTableNames() {
        // 获取数据库连接
        try (Connection conn = ds.getConnection()) {
            // 使用conn进行数据库操作, 获取该数据库所有表的姓名
            List<String> tables = new ArrayList<>();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SHOW TABLES");
            while (rs.next()) {
                tables.add(rs.getString(1));
            }
            return tables;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // 获取该链接所有的数据库名称
    public List<String> getDatabaseNames() {
        // 获取数据库连接
        try (Connection conn = ds.getConnection()) {
            // 使用conn进行数据库操作, 获取该数据库所有表的姓名
            List<String> databases = new ArrayList<>();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SHOW DATABASES");
            while (rs.next()) {
                databases.add(rs.getString(1));
            }
            return databases;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public record SqlTableRow(String name, String type, boolean isNull, String primaryKeyOrForeignKey,
                              String description) {
    }

    public record SqlTable(String name,List<SqlTableRow> tableRows) {}

    // 根据getTableNames, 获取一张表的详细信息
    public List<SqlTable> getTables(String databaseName) {
        List<SqlTable> tables = new ArrayList<>();
        // 打开databaseName数据库, 获取指定数据库的所有表
        try (Connection conn = ds.getConnection()) {
            Statement stmt = conn.createStatement();
            stmt.execute("USE " + databaseName);
            ResultSet rs = stmt.executeQuery("SHOW TABLES");
            while (rs.next()) {
                String tableName = rs.getString(1);
                // 创建一个新的Statement对象用于第二个查询
                try (Statement stmt2 = conn.createStatement()) {
                    // 获取该表的所有字段
                    ResultSet rs2 = stmt2.executeQuery("SHOW FULL COLUMNS FROM " + tableName);
                    List<SqlTableRow> tableRows = new ArrayList<>();
                    while (rs2.next()) {
                        String name = rs2.getString("Field");
                        String type = rs2.getString("Type");
                        boolean isNull = rs2.getString("Null").equals("YES");
                        String key = rs2.getString("Key");
                        String description = rs2.getString("Comment");
                        tableRows.add(new SqlTableRow(name, type, isNull, key, description));
                    }
                    tables.add(new SqlTable(tableName, tableRows));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tables;
    }

    public void sqlToWord(String outputFilePath, String databaseName) {
        List<SqlTable> tables = getTables(databaseName);

        try (XWPFDocument doc = new XWPFDocument();
             FileOutputStream out = new FileOutputStream(outputFilePath)) {

            for (SqlTable sqlTable : tables) {
                // Create a new table for each SqlTable object
                XWPFTable table = doc.createTable(1, 5);

                // Add table header
                XWPFTableRow headerRow = table.getRow(0);
                headerRow.getCell(0).setText("字段名称");
                headerRow.getCell(1).setText("字段属性");
                headerRow.getCell(2).setText("是否为空");
                headerRow.getCell(3).setText("主键/外键");
                headerRow.getCell(4).setText("说明");


                // Add rows to the table
                for (SqlTableRow sqlTableRow : sqlTable.tableRows()) {
                    XWPFTableRow row = table.createRow();
                    row.getCell(0).setText(sqlTableRow.name());
                    row.getCell(1).setText(sqlTableRow.type());
                    row.getCell(2).setText(sqlTableRow.isNull() ? "是" : "否");
                    row.getCell(3).setText(sqlTableRow.primaryKeyOrForeignKey());
                    row.getCell(4).setText(sqlTableRow.description());
                }
                doc.createParagraph();
            }

            // Save the Word document
            doc.write(out);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
