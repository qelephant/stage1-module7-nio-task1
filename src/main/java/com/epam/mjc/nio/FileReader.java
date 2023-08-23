package com.epam.mjc.nio;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class FileReader {
    public Profile getDataFromFile(File file) {
        String data = readDataFromFile(file);
        Map<String, String> keyValuePairs = parseData(data);
        return createProfile(keyValuePairs);
    }

    private String readDataFromFile(File file) {
        try {
            return Files.readString(file.toPath(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Map<String, String> parseData(String data) {
        Map<String, String> keyValuePairs = new HashMap<>();
        String[] lines = data.split("\\r?\\n");
        for (String line : lines) {
            String[] parts = line.split(": ");
            if (parts.length == 2) {
                keyValuePairs.put(parts[0], parts[1]);
            }
        }
        return keyValuePairs;
    }

    private Profile createProfile(Map<String, String> keyValuePairs) {
        String name = keyValuePairs.get("Name");
        int age = Integer.parseInt(keyValuePairs.get("Age"));
        String email = keyValuePairs.get("Email");
        String phone = keyValuePairs.get("Phone");
        return new Profile(name, age, email, phone);
    }
}
