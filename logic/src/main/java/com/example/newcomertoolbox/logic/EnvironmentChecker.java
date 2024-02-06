package com.example.newcomertoolbox.logic;

import java.io.File;

public class EnvironmentChecker {

    public static void main(String[] args) {
        checkMySQL();
        checkJDK();
        checkNodeJS();
    }

    private static void checkMySQL() {
        if (isCommandAvailable("mysql")) {
            System.out.println("MySQL is installed.");
        } else {
            System.out.println("MySQL is not installed.");
        }
    }

    private static void checkJDK() {
        if (System.getProperty("java.home").toLowerCase().contains("jdk")) {
            System.out.println("JDK is installed.");
        } else {
            System.out.println("JDK is not installed.");
        }
    }

    private static void checkNodeJS() {
        if (isCommandAvailable("node")) {
            System.out.println("Node.js is installed.");
        } else {
            System.out.println("Node.js is not installed.");
        }
    }

    private static boolean isCommandAvailable(String command) {
        try {
            Process process = new ProcessBuilder(command, "--version").start();
            int exitCode = process.waitFor();
            return exitCode == 0;
        } catch (Exception e) {
            return false;
        }
    }
}

