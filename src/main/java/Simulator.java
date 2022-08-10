import java.util.*;

public class Simulator {
    public static double[][] simPassageTime(
            int numVert, Map<Integer, List<Integer>> neighborLists, int iteration, Random random) {
        boolean[][] reachable = calcReachable(numVert, neighborLists);
        double[][] passageTime = new double[numVert][numVert];

        for (int src = 0; src < numVert; src++) {
            for (int dest = 0; dest < numVert; dest++) {
                if (!reachable[src][dest]) {
                    passageTime[src][dest] = Double.POSITIVE_INFINITY;
                } else {
                    passageTime[src][dest] = simPassageTimeIter(src, dest, neighborLists, iteration, random);
                }
            }
        }

        return passageTime;
    }

    public static boolean[][] calcReachable(int numVert, Map<Integer, List<Integer>> neighborLists) {
        boolean[][] reachable = new boolean[numVert][numVert];

        for (int i = 0; i < numVert; i++) {
            Queue<Integer> bfsQueue = new LinkedList<>();
            bfsQueue.add(i);

            Set<Integer> visited = new HashSet<>();

            while (!bfsQueue.isEmpty()) {
                int node = bfsQueue.poll();
                visited.add(node);
                reachable[i][node] = true;

                for (int neighbor : neighborLists.get(node)) {
                    if (!visited.contains(neighbor)) {
                        bfsQueue.add(neighbor);
                    }
                }
            }
        }

        return reachable;
    }

    public static double simPassageTimeIter(
            int src, int dest, Map<Integer, List<Integer>> neighborLists, int iteration, Random random) {
        double sum = 0.0;

        for (int i = 0; i < iteration; i++) {
            sum += simRun(src, dest, neighborLists, random);
        }

        return sum / iteration;
    }

    private static int simRun(int src, int dest, Map<Integer, List<Integer>> neighborLists, Random random) {
        int currNode = src;
        int count = 0;

        do {
            List<Integer> myNeighbors = neighborLists.get(currNode);
            int index = (int)(random.nextDouble() * myNeighbors.size());
            currNode = myNeighbors.get(index);
            count++;
        } while(currNode != dest);

        return count;
    }

    public static void printResult(double[][] passageTime) {
        System.out.println("Passage time result:");
        System.out.print("Label\t");

        for (int i = 0; i < passageTime.length; i++) {
            String msg = String.format("%d     \t", i);
            System.out.print(msg);
        }

        System.out.println();

        for (int i = 0; i < passageTime.length; i++) {
            System.out.print(i + "\t");

            for (int j = 0; j < passageTime.length; j++) {
                String msg = String.format("%.3f\t", passageTime[i][j]);
                System.out.print(msg);
            }

            System.out.println();
        }
    }
}
