package com.epam.mjc.nio;

import java.io.File;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class FileReader {
    public Profile getDataFromFile(File file) {
        try {
            String content = readFromFile(file);

            Map<String, String> profileData = parseProfileData(content);

            String name = profileData.get("Name");
            int age = Integer.parseInt(profileData.get("Age"));
            String email = profileData.get("Email");
            String phone = profileData.get("Phone");

            return new Profile(name, age, email, phone);
        } catch (IOException e) {
            e.printStackTrace();
            return null; // Handle error appropriately
        }
    }

    private String readFromFile(File file) throws IOException {
        StringBuilder content = new StringBuilder();
        BufferedReader reader = new BufferedReader(new FileReader(file));
        
        String line;
        while ((line = reader.readLine()) != null) {
            content.append(line).append("\n");
        }
        
        reader.close();
        return content.toString();
    }
}
