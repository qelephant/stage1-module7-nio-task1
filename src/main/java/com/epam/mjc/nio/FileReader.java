package com.epam.mjc.io;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileReader {

    public Profile getDataFromFile(Path filePath) {
        String name = "";
        int age = 0;
        String email = "";
        long phone = 0L;

        try {

            String fileContent = Files.readString(filePath, StandardCharsets.UTF_8);
            String[] lines = fileContent.split("\\n");
            for (String line : lines) {
                if (line.startsWith("Name:")) {
                    name = line.substring("Name:".length()).trim();
                } else if (line.startsWith("Age:")) {
                    age = Integer.parseInt(line.substring("Age:".length()).trim());
                } else if (line.startsWith("Email:")) {
                    email = line.substring("Email:".length()).trim();
                } else if (line.startsWith("Phone:")) {
                    phone = Long.parseLong(line.substring("Phone:".length()).trim());
                }
            }
        } catch (IOException e) {
            System.err.println("An error occurred while reading the file: " + e.getMessage());
        }
        return new Profile(name, age, email, phone);
    }
}
