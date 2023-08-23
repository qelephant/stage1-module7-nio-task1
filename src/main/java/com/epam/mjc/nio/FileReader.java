package com.epam.mjc.nio;

import java.io.File;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class FileReader {
    public static void main(String[] args) {
        File file = new File("resources/Profile.txt");
        Profile profile = FileReader.getDataFromFile(file);

        if (profile != null) {
            System.out.println("Name: " + profile.getName());
            System.out.println("Age: " + profile.getAge());
            System.out.println("Email: " + profile.getEmail());
            System.out.println("Phone: " + profile.getPhone());
        }
    }
    
    public static Profile getDataFromFile(File file) {
        try {
            String content = new String(Files.readAllBytes(Paths.get(file.toURI())));

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

    private static Map<String, String> parseProfileData(String content) {
        Map<String, String> profileData = new HashMap<>();
        String[] lines = content.split("\\r?\\n");

        for (String line : lines) {
            String[] parts = line.split(": ");
            if (parts.length == 2) {
                profileData.put(parts[0], parts[1]);
            }
        }

        return profileData;
    }
}
