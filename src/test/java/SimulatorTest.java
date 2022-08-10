import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class SimulatorTest {
    static Stream<Arguments> input() {
        Map<Integer, List<Integer>> neighborLists = Map.of(
                0, Stream.of(1, 2).collect(Collectors.toList()),
                1, Stream.of(0, 2).collect(Collectors.toList()),
                2, Stream.of(0, 1, 3).collect(Collectors.toList()),
                3, Stream.of(2).collect(Collectors.toList())
        );

        return Stream.of(
                Arguments.arguments(neighborLists)
        );
    }

    @ParameterizedTest
    @MethodSource("input")
    void simPassageTimeIter_shouldReturnApproxCorrectValue(
            Map<Integer, List<Integer>> neighborLists) {
        Random random = new Random();

        double result01 = Simulator.simPassageTimeIter(
                0, 1, neighborLists, 1000000, random);

        assertEquals(8.0/3.0, result01, 0.005);

        double result32 = Simulator.simPassageTimeIter(
                3, 2, neighborLists, 1000000, random);

        assertEquals(1.0, result32, 0.005);

        double result21 = Simulator.simPassageTimeIter(
                2, 1, neighborLists, 1000000, random);

        assertEquals(10.0/3.0, result21, 0.005);

        double result31 = Simulator.simPassageTimeIter(
                3, 1, neighborLists, 1000000, random);

        assertEquals(13.0/3.0, result31, 0.005);
    }

    @ParameterizedTest
    @MethodSource("input")
    void simPassageTime_testRun(
            Map<Integer, List<Integer>> neighborLists) {
        Random random = new Random();
        int numVert = 4;
        int iteration = 1000000;

        double[][] result = Simulator.simPassageTime(
                numVert, neighborLists, iteration, random);

        Simulator.printResult(result);
    }
}