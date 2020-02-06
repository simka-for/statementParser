import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;


public class Main {

    public static void main(String[] args) {

        List<Movement> movementsList = loadMovementList();

        System.out.println("\nОбщий приход денежных средств в период <<01.03.17 - 31.05.17>> : " + movementsList.stream()
                .mapToDouble(Movement::getComing).sum() + " RUB");

        System.out.println("Общий расход денежных средств в период <<01.03.17 - 31.05.17>> : " + movementsList.stream()
                .mapToDouble(Movement::getConsumption).sum() + " RUB");

        ArrayList<Movement> coming = new ArrayList<>();
        for (Movement com : movementsList){
            if (com.getComing() != 0){
                coming.add(com);
            }
        }

        ArrayList<Movement> consumption = new ArrayList<>();
        for (Movement con : movementsList){
            if (con.getConsumption() != 0){
                consumption.add(con);
            }
        }

        System.out.println("\nОтчет о приходе: \n" +
                "==================================");
        for (String key :  getPayment(coming).keySet()) {
            System.out.println(key + " : " +  getPayment(coming).get(key) + " RUB");
        }

        System.out.println("\nОтчет о расходе: \n" +
                "==================================");
        for (String key :  getPayment(consumption).keySet()) {
            System.out.println(key + " : " +  getPayment(consumption).get(key) + " RUB");
        }


    }

    private static ArrayList<Movement> loadMovementList() {
        ArrayList<Movement> list = new ArrayList<>();

        try {
            String movementList = "data/movementList.csv";
            List<String> lines = Files.readAllLines(Paths.get(movementList));
            for (int i = 1; i < lines.size(); i++) {
                String[] fragments = lines.get(i).
                        replaceAll("\"([^\"]*),([^\"]*)\"", "$1.$2").
                        split(",", 8);
                String operation = clearOperation(fragments[5]).replaceAll("\\s+", "/");
                list.add(new Movement(operation,
                        Double.parseDouble(fragments[6]),
                        Double.parseDouble(fragments[7])));
            }
            list.remove(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    private static String clearOperation(String operation) {
        if (!"Описание операции".equals(operation)) {
            int last = operation.indexOf("       ");
            return operation.substring(17, last).trim();
        }
        return operation;
    }

    private static TreeMap<String, Double> getPayment(List<Movement> operations) {
        TreeMap<String, Double> report = new TreeMap<>();
        for (Movement operation : operations) {
            String operate = operation.getOperation();
            double amount = 0.0;
            for (Movement o : operations) {
                if (operate.equals(o.getOperation())) {
                    amount += o.getConsumption();
                    amount += o.getComing();
                }
                report.put(operate, amount);
            }
        }
        return report;
    }

}
