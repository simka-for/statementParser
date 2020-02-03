import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;



public class Main {

    private static String movementList = "data/movementList.csv";

    public static void main(String[] args) {

        ArrayList<Movement> list = loadMovementList();
        list.forEach(System.out::println);


    }
    private static ArrayList<Movement> loadMovementList(){
        ArrayList <Movement> list = new ArrayList<>();

        try {
            List<String> lines = Files.readAllLines(Paths.get(movementList));
            for(String line : lines){
                String[] fragments = line.split(",", 8);
                list.add(new Movement(fragments[1],fragments[3],fragments[4],fragments[5],fragments[6],fragments[7]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  list;
    }
}
