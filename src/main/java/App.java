import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App {
    public static void main(String[] args) {
        int numVert = 5;

        Map<Integer, List<Integer>> neighborLists = Map.of(
                0, Stream.of(1, 2, 3).collect(Collectors.toList()),
                1, Stream.of(0, 3, 4).collect(Collectors.toList()),
                2, Stream.of(0, 3).collect(Collectors.toList()),
                3, Stream.of(0, 1, 2).collect(Collectors.toList()),
                4, Stream.of(1).collect(Collectors.toList())
        );

        int iteration = 1000000;

        Random random = new Random();

        double[][] result = Simulator.simPassageTime(
                numVert, neighborLists, iteration, random);

        Simulator.printResult(result);
    }
}
