import java.util.List;
import java.util.stream.Collectors;

public class FilterProcessor {
    @DataProcessor
    public List<String> filterData(List<String> data) {
        List<String> filteredData = data.stream()
                   .filter(d -> d.contains("s"))
                   .collect(Collectors.toList());
        System.out.println("Отфильтрованные данные: " + filteredData);
        return filteredData;
    }
}
