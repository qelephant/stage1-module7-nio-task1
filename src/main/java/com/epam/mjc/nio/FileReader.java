package com.epam.mjc.nio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class FileReader {

    public Profile getDataFromFile(File file) throws IOException {
        String fileContent = readContentFromFile(file);
        Map<String, String> profileData = parseContent(fileContent);
        return createProfile(profileData);
    }

    private String readContentFromFile(File file) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        }
        return content.toString();
    }

    private Map<String, String> parseContent(String content) {
        Map<String, String> profileData = new HashMap<>();
        String[] lines = content.split("\n");
        for (String line : lines) {
            String[] keyValue = line.split(": ");
            if (keyValue.length == 2) {
                String key = keyValue[0].trim();
                String value = keyValue[1].trim();
                profileData.put(key, value);
            }
        }
        return profileData;
    }

    private Profile createProfile(Map<String, String> profileData) {
        String name = profileData.get("Name");
        Integer age = Integer.valueOf(profileData.get("Age"));
        String email = profileData.get("Email");
        Long phone = Long.valueOf(profileData.get("Phone"));
        return new Profile(name, age, email, phone);
    }
}
