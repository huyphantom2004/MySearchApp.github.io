package TrendingMethod;

import java.util.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.IOException;

public class Trending {
    public static void main(String[] args) {
        // Đường dẫn đến file JSON
        String filePath = "src/main/java/TrendingMethod/Test.json";

        // Key đã cho trước
        String key = "tags";
        List<String> values = new ArrayList<>();
        List<String> trendObjects = new ArrayList<>(); // Danh sách các đối tượng JSON tương ứng với trend
        String trend = null;

        try (FileReader reader = new FileReader(filePath)) {
            // Parse file JSON
            JSONParser jsonParser = new JSONParser();
            JSONArray jsonArray = (JSONArray) jsonParser.parse(reader);

            for (Object obj : jsonArray) {
                JSONObject jsonObject = (JSONObject) obj;
                JSONArray tags = (JSONArray) jsonObject.get(key);
                for (Object tag : tags) {
                    values.add(tag.toString());
                }
            }

            trend = findMostFrequentString(values);
            System.out.println("Most frequent tags: " + trend);
        } catch (IOException e) {
            System.err.println("Không thể đọc file: " + e.getMessage());
        } catch (ParseException e) {
            System.err.println("Lỗi khi parse file JSON: " + e.getMessage());
        }

        try (FileReader reader = new FileReader(filePath)) {
            // Parse file JSON
            JSONParser jsonParser = new JSONParser();
            JSONArray jsonArray = (JSONArray) jsonParser.parse(reader);

            // Duyệt qua các đối tượng trong mảng JSON
            for (Object obj : jsonArray) {
                JSONObject jsonObject = (JSONObject) obj;
                JSONArray tags = (JSONArray) jsonObject.get(key);

                // Kiểm tra xem giá trị mong muốn có trong mảng tags không
                if (tags.contains(trend)) {
                    trendObjects.add(jsonObject.toJSONString()); // Thêm object JSON vào danh sách
                }
            }

            // In ra các object JSON tương ứng với giá trị mong muốn của khóa
            for (String json : trendObjects) {
                System.out.println(json);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // In ra trend sau khi đã xử lý values
        System.out.println("Trending :" + trend);
    }

    public static String findMostFrequentString(List<String> array) {
        Map<String, Integer> frequencyMap = new HashMap<>();

        // Đếm số lần xuất hiện của mỗi chuỗi trong mảng
        for (String str : array) {
            frequencyMap.put(str, frequencyMap.getOrDefault(str, 0) + 1);
        }

        // Tìm chuỗi có tần suất xuất hiện cao nhất
        int maxFrequency = 0;
        String mostFrequentString = null;
        for (Map.Entry<String, Integer> entry : frequencyMap.entrySet()) {
            if (entry.getValue() > maxFrequency) {
                maxFrequency = entry.getValue();
                mostFrequentString = entry.getKey();
            }
        }

        return mostFrequentString;
    }
}
