import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GraphColoringDSatur {
    private final Graph graph;
    private final String[] labels;
    private final int[] color;
    private final List<Integer> coloringOrder;
    private int totalColors;

    public GraphColoringDSatur(Graph graph, String[] labels) {
        this.graph = graph;
        this.labels = labels;
        this.color = new int[graph.V()];
        Arrays.fill(this.color, -1);
        this.coloringOrder = new ArrayList<>();
        this.totalColors = 0;
    }

    public void solve() {
        int start = chooseInitialVertex();
        color[start] = 0;
        totalColors = 1;
        coloringOrder.add(start);

        boolean[] colored = new boolean[graph.V()];
        colored[start] = true;
        int coloredCount = 1;

        while (coloredCount < graph.V()) {
            int next = chooseNextVertex(colored);
            int chosenColor = smallestAvailableColor(next);
            color[next] = chosenColor;
            colored[next] = true;
            coloredCount++;
            coloringOrder.add(next);

            if (chosenColor + 1 > totalColors) {
                totalColors = chosenColor + 1;
            }
        }
    }

    private int chooseInitialVertex() {
        int best = 0;
        for (int v = 1; v < graph.V(); v++) {
            if (graph.degree(v) > graph.degree(best)) {
                best = v;
            } else if (graph.degree(v) == graph.degree(best) && v < best) {
                best = v;
            }
        }
        return best;
    }

    private int chooseNextVertex(boolean[] colored) {
        int best = -1;
        int bestSaturation = -1;
        int bestDegree = -1;

        for (int v = 0; v < graph.V(); v++) {
            if (colored[v]) {
                continue;
            }

            int saturation = saturationDegree(v);
            int degree = graph.degree(v);

            if (saturation > bestSaturation) {
                best = v;
                bestSaturation = saturation;
                bestDegree = degree;
            } else if (saturation == bestSaturation) {
                if (degree > bestDegree) {
                    best = v;
                    bestDegree = degree;
                } else if (degree == bestDegree && v < best) {
                    best = v;
                }
            }
        }

        return best;
    }

    private int saturationDegree(int v) {
        Set<Integer> usedColors = new HashSet<>();
        for (int neighbor : graph.adj(v)) {
            if (color[neighbor] != -1) {
                usedColors.add(color[neighbor]);
            }
        }
        return usedColors.size();
    }

    private int smallestAvailableColor(int v) {
        boolean[] unavailable = new boolean[graph.V()];

        for (int neighbor : graph.adj(v)) {
            if (color[neighbor] != -1) {
                unavailable[color[neighbor]] = true;
            }
        }

        for (int c = 0; c < unavailable.length; c++) {
            if (!unavailable[c]) {
                return c;
            }
        }

        return unavailable.length;
    }

    public boolean isValidColoring() {
        for (int v = 0; v < graph.V(); v++) {
            if (color[v] == -1) {
                return false;
            }
            for (int w : graph.adj(v)) {
                if (v < w && color[v] == color[w]) {
                    return false;
                }
            }
        }
        return true;
    }

    public int[] getColors() {
        return color;
    }

    public List<Integer> getColoringOrder() {
        return coloringOrder;
    }

    public int getTotalColors() {
        return totalColors;
    }

    public void printColoringOrder() {
        System.out.println("Ordem de coloracao (DSatur):");
        for (int i = 0; i < coloringOrder.size(); i++) {
            int v = coloringOrder.get(i);
            System.out.printf("%2d) %s (%d)%n", i + 1, labels[v], v);
        }
    }

    public void printFinalColors() {
        System.out.println("Cor final de cada estado:");
        for (int v = 0; v < graph.V(); v++) {
            System.out.printf("%s (%2d) -> cor %d%n", labels[v], v, color[v] + 1);
        }
    }
}
