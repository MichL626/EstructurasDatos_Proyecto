import java.util.*;

public class Grafo {

    private final Map<String, List<Arista>> listaAdyacencia;

    public Grafo() {
        listaAdyacencia = new HashMap<>();
    }

    public void agregarVertice(String nuevoVertice) {
        if (nuevoVertice == null || nuevoVertice.trim().isEmpty()) {
            return;
        }

        listaAdyacencia.putIfAbsent(nuevoVertice.trim(), new ArrayList<>());
    }

    public void agregarArista(String origen, String nuevoDestino, int pesoArista) {
        if (origen == null || nuevoDestino == null) return;
        if (origen.trim().isEmpty() || nuevoDestino.trim().isEmpty()) return;
        if (pesoArista <= 0) return;

        origen = origen.trim();
        nuevoDestino = nuevoDestino.trim();

        agregarVertice(origen);
        agregarVertice(nuevoDestino);

        listaAdyacencia.get(origen).add(new Arista(nuevoDestino, pesoArista));
        listaAdyacencia.get(nuevoDestino).add(new Arista(origen, pesoArista));
    }

    public boolean existeVertice(String nombreVertice) {
        if (nombreVertice == null) return false;
        return listaAdyacencia.containsKey(nombreVertice.trim());
    }

    public boolean estaDesconectado(String vertice) {
        if (!existeVertice(vertice)) return true;
        return listaAdyacencia.get(vertice.trim()).isEmpty();
    }

    public void mostrarGrafo() {
        if (listaAdyacencia.isEmpty()) {
            System.out.println("El grafo está vacío.");
            return;
        }

        System.out.println("=========== MAPA DE UBICACIONES ===========");
        for (Map.Entry<String, List<Arista>> entry : listaAdyacencia.entrySet()) {
            System.out.print(entry.getKey() + " -> ");
            for (Arista arista : entry.getValue()) {
                System.out.print("(" + arista.getDestino() + ", " + arista.getPeso() + ") ");
            }
            System.out.println();
        }
        System.out.println("===========================================");
    }

    public void algoritmoDijkstra(String inicio,
                                  Map<String, Integer> distancias,
                                  Map<String, String> predecesores) {

        PriorityQueue<Vertice> colaVertices =
                new PriorityQueue<>(Comparator.comparingInt(Vertice::getDistancia));

        for (String vertice : listaAdyacencia.keySet()) {
            distancias.put(vertice, Integer.MAX_VALUE);
            predecesores.put(vertice, null);
        }

        if (!listaAdyacencia.containsKey(inicio)) {
            return;
        }

        distancias.put(inicio, 0);
        colaVertices.add(new Vertice(inicio, 0));

        while (!colaVertices.isEmpty()) {
            Vertice v = colaVertices.poll();
            String verticeActual = v.getNombre();

            for (Arista arista : listaAdyacencia.get(verticeActual)) {
                String vecino = arista.getDestino();

                if (distancias.get(verticeActual) == Integer.MAX_VALUE) {
                    continue;
                }

                int nuevaDistancia = distancias.get(verticeActual) + arista.getPeso();

                if (nuevaDistancia < distancias.get(vecino)) {
                    distancias.put(vecino, nuevaDistancia);
                    predecesores.put(vecino, verticeActual);
                    colaVertices.add(new Vertice(vecino, nuevaDistancia));
                }
            }
        }
    }

    public List<String> reconstruirCamino(String inicio, String destino, Map<String, String> predecesores) {
        List<String> camino = new ArrayList<>();

        if (!listaAdyacencia.containsKey(inicio) || !listaAdyacencia.containsKey(destino)) {
            return camino;
        }

        for (String verticeActual = destino; verticeActual != null; verticeActual = predecesores.get(verticeActual)) {
            camino.add(verticeActual);
        }

        Collections.reverse(camino);

        if (!camino.isEmpty() && camino.get(0).equals(inicio)) {
            return camino;
        }

        return new ArrayList<>();
    }

    public int obtenerDistanciaMinima(String inicio, String destino) {
        Map<String, Integer> distancias = new HashMap<>();
        Map<String, String> predecesores = new HashMap<>();

        algoritmoDijkstra(inicio, distancias, predecesores);

        return distancias.getOrDefault(destino, Integer.MAX_VALUE);
    }
}