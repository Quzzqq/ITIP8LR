import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataManager {
    private final List<Object> processors = new ArrayList<>();
    private List<String> data;

    public void registerDataProcessor(Object processor) {
        processors.add(processor);
    }

    public void loadData(String source) {
        data = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(source))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Разбиваем строку на слова и добавляем их в список
                String[] words = line.split(" ");
                for (String word : words) {
                    data.add(word);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void processData() {
        for (Object processor : processors) {
            for (var method : processor.getClass().getDeclaredMethods()) {
                if (method.isAnnotationPresent(DataProcessor.class)) {
                    try {
                        data = (List<String>) method.invoke(processor, data);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void saveData(String destination) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(destination))) {
            String aggregatedData = String.join(" ", data);
            writer.write(aggregatedData);
            System.out.println("Данные сохранены в файл: " + destination);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
