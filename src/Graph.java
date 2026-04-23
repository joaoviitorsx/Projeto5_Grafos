import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Graph {
    private final int V;
    private int E;
    private final List<Integer>[] adj;

    @SuppressWarnings("unchecked")
    public Graph(int V) {
        if (V < 0) {
            throw new IllegalArgumentException("O numero de vertices nao pode ser negativo.");
        }
        this.V = V;
        this.E = 0;
        this.adj = (List<Integer>[]) new ArrayList[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new ArrayList<>();
        }
    }

    public static Graph fromFile(String filePath) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(filePath));

        int V = sc.nextInt();
        Graph g = new Graph(V);

        int expectedEdges = sc.nextInt();
        for (int i = 0; i < expectedEdges; i++) {
            int v = sc.nextInt();
            int w = sc.nextInt();
            g.addEdge(v, w);
        }

        sc.close();
        g.sortAdjacencyLists();
        return g;
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public void addEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);

        if (v == w) {
            throw new IllegalArgumentException("Lacos nao sao permitidos neste trabalho: " + v + "-" + w);
        }

        if (!adj[v].contains(w)) {
            adj[v].add(w);
            adj[w].add(v);
            E++;
        }
    }

    public Iterable<Integer> adj(int v) {
        validateVertex(v);
        return adj[v];
    }

    public int degree(int v) {
        validateVertex(v);
        return adj[v].size();
    }

    public boolean hasEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        return adj[v].contains(w);
    }

    private void sortAdjacencyLists() {
        for (int v = 0; v < V; v++) {
            Collections.sort(adj[v]);
        }
    }

    private void validateVertex(int v) {
        if (v < 0 || v >= V) {
            throw new IllegalArgumentException("Vertice fora do intervalo valido: " + v);
        }
    }
}
