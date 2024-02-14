package com.example.newcomertoolbox.logic;

import org.junit.jupiter.api.Test;

import static com.example.newcomertoolbox.logic.EnvironmentChecker.*;
import static org.junit.jupiter.api.Assertions.*;

class EnvironmentCheckerTest {

    @Test
    public void checker() {
        String mysqlResult = checkMySQL();
        String jdkResult = checkJDK();
        String nodeJSResult = checkNodeJS();

        System.out.println("MySQL: " + mysqlResult);
        System.out.println("JDK: " + jdkResult);
        System.out.println("Node.js: " + nodeJSResult);
    }

}
