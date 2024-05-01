/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package TrendingMethod;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.*;


/**
 *
 * @author Admin
 */
public class TestThuong {

    public static void main(String[] args) {
        // Đường dẫn đến tệp JSON
        String filePath = "src/main/java/TrendingMethod/Test.json";

        try {
            // Read the JSON file
            JSONParser parser = new JSONParser();
            JSONArray jsonArray = (JSONArray) parser.parse(new FileReader(filePath));

            // Count the occurrence of each hashtag
            Map<String, Integer> hashtagCounts = new HashMap<>();
            for (Object obj : jsonArray) {
                JSONObject jsonObject = (JSONObject) obj;
                JSONArray hashtags = (JSONArray) jsonObject.get("tags");

                for (Object hashtagObj : hashtags) {
                    String hashtag = (String) hashtagObj;
                    hashtagCounts.put(hashtag, hashtagCounts.getOrDefault(hashtag, 0) + 1);
                }
            }

            // Sort hashtags by frequency in descending order
            List<Map.Entry<String, Integer>> sortedHashtags = new ArrayList<>(hashtagCounts.entrySet());
            sortedHashtags.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

            // Print the result
            System.out.println("Hashtags in descending order of occurrence:");
            for (Map.Entry<String, Integer> entry : sortedHashtags) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            } }catch (Exception e) {
            e.printStackTrace();
        }
    }
}