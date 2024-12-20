public class Main {
    public static void main(String[] args) {
        DataManager manager = new DataManager();
        
        manager.registerDataProcessor(new FilterProcessor());
        manager.registerDataProcessor(new TransformProcessor());
        
        manager.loadData("source.txt");
        manager.processData();
        manager.saveData("destination.txt");
    }
}
