/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package TrendingMethod;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TestTest {
    public static void main(String[] args) {
        try {
            // Đọc và phân tích tệp JSON thành một JSONArray
            JSONParser parser = new JSONParser();
            JSONArray jsonArray = (JSONArray) parser.parse(new FileReader("src/main/java/TrendingMethod/Contents.json"));

            // Tạo một mảng String để lưu trữ các giá trị từ JSON
            String[] strings = new String[jsonArray.size()];

            // Đổ dữ liệu từ mảng JSON vào mảng String
            for (int i = 0; i < jsonArray.size(); i++) {
                Object element = jsonArray.get(i);
                if (element instanceof String) {
                    strings[i] = (String) element;
                } else if (element instanceof JSONObject) {
                    // Xử lý trường hợp phần tử là một đối tượng JSON
                    // Ở đây bạn có thể thực hiện các hành động phù hợp, ví dụ: bỏ qua hoặc chuyển đổi thành chuỗi
                    strings[i] = element.toString(); // Ví dụ chuyển đổi thành chuỗi
                } else {
                    // Xử lý các trường hợp khác nếu cần thiết
                }
            }

            // Tìm từ xuất hiện nhiều nhất trong mảng các giá trị
            String mostCommonWord = findMostCommonWord(strings);
            System.out.println("Từ xuất hiện nhiều nhất là: " + mostCommonWord);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    public static String findMostCommonWord(String[] strings) {
        // Tạo một HashMap để lưu trữ số lần xuất hiện của mỗi từ
        HashMap<String, Integer> wordCount = new HashMap<>();

        // Duyệt qua mỗi xâu trong mảng
        for (String str : strings) {
            // Tách các từ từ xâu
            String[] words = str.split("\\s+");
            // Đếm số lần xuất hiện của mỗi từ và cập nhật vào HashMap
            for (String word : words) {
                wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
            }
        }

        // Tìm từ xuất hiện nhiều nhất trong HashMap
        String mostCommonWord = "";
        int maxCount = 0;
        for (Map.Entry<String, Integer> entry : wordCount.entrySet()) {
            if (entry.getValue() > maxCount) {
                mostCommonWord = entry.getKey();
                maxCount = entry.getValue();
            }
        }

        return mostCommonWord;
    }
}
