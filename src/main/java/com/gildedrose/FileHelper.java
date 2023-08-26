package com.gildedrose;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FileHelper {

    public static List<String> readItemsFromResource(String fileName) {
        List<String> itemList = new ArrayList<>();
        try (InputStream inputStream = FileHelper.class.getClassLoader().getResourceAsStream(fileName);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                itemList.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("File with name " + fileName + "cannot be read", e );
        }
        return itemList;
    }
}
