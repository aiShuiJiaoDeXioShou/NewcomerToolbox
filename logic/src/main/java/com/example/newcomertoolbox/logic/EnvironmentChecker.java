package com.example.newcomertoolbox.logic;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class EnvironmentChecker {
    public static String checkMySQL() {
        String version = getCommandOutput("mysql --version");
        if (!version.isEmpty()) {
            return "Installed. Version: " + version;
        } else {
            return "Not installed.";
        }
    }

    public static String checkJDK() {
        String version = System.getProperty("java.version");
        if (version != null) {
            return "Installed. Version: " + version;
        } else {
            return "Not installed.";
        }
    }

    public static String checkNodeJS() {
        String version = getCommandOutput("node --version");
        if (!version.isEmpty()) {
            return "Installed. Version: " + version;
        } else {
            return "Not installed.";
        }
    }

    private static String getCommandOutput(String command) {
        try {
            Process process = new ProcessBuilder("bash", "-c", command).start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            StringBuilder output = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                output.append(line);
            }
            int exitCode = process.waitFor();
            if (exitCode == 0) {
                return output.toString().trim();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
