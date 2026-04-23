public class Main {
    private static final String[] SIGLAS = {
        "AC", "AL", "AM", "AP", "BA", "CE", "DF", "ES", "GO", "MA",
        "MG", "MS", "MT", "PA", "PB", "PE", "PI", "PR", "RJ", "RN",
        "RO", "RR", "RS", "SC", "SE", "SP", "TO"
    };

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Uso: java Main <caminho-do-arquivo>");
            return;
        }

        try {
            Graph graph = Graph.fromFile(args[0]);

            System.out.println("=== GRAFO DO BRASIL ===");
            System.out.println("Vertices: " + graph.V());
            System.out.println("Arestas : " + graph.E());
            System.out.println();

            printAdjacencyList(graph);

            GraphColoringDSatur dsatur = new GraphColoringDSatur(graph, SIGLAS);
            dsatur.solve();

            System.out.println();
            System.out.println("=== RESULTADO DO DSATUR ===");
            dsatur.printColoringOrder();
            System.out.println();
            dsatur.printFinalColors();
            System.out.println();
            System.out.println("Total de cores utilizadas: " + dsatur.getTotalColors());
            System.out.println("Coloracao valida? " + (dsatur.isValidColoring() ? "SIM" : "NAO"));

        } catch (Exception e) {
            System.out.println("Erro ao executar o programa: " + e.getMessage());
        }
    }

    private static void printAdjacencyList(Graph graph) {
        System.out.println("Lista de adjacencia:");
        for (int v = 0; v < graph.V(); v++) {
            System.out.printf("%s (%2d): ", SIGLAS[v], v);
            boolean first = true;
            for (int w : graph.adj(v)) {
                if (!first) {
                    System.out.print(", ");
                }
                System.out.print(SIGLAS[w] + "(" + w + ")");
                first = false;
            }
            System.out.println();
        }
    }
}
