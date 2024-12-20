import java.util.List;
import java.util.stream.Collectors;

public class TransformProcessor {
    @DataProcessor
    public List<String> transformData(List<String> data) {
        List<String> transformedData = data.stream()
                                            .map(String::toUpperCase)
                                            .collect(Collectors.toList());
        System.out.println("Преобразованные данные: " + transformedData);
        return transformedData;
    }
}
