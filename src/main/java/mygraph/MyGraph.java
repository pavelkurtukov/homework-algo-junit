package mygraph;

import java.util.ArrayList;
import java.util.List;

public class MyGraph {
    // Массив списков связей вершин (i - вершины, j - список связей i-ой вершины)
    boolean[][] verticesInfo;

    public MyGraph(boolean[][] verticesInfo) {
        this.verticesInfo = verticesInfo;
    }

    // Добавление направленной связи между двумя вершинами
    public void addEdge(int vertex1, int vertex2) {
        verticesInfo[vertex1][vertex2] = true;
        verticesInfo[vertex2][vertex1] = true;
    }

    // Кол-во вершин графа
    public int size() {
        return verticesInfo.length;
    }

    // Список номеров связанных вершин для указанной вершины
    public Integer[] vertices() {
        List<Integer> vertices = new ArrayList<>();
        for (int i = 0; i < verticesInfo.length; i++) {
            vertices.add(i);
        };
        return vertices.toArray(new Integer[0]);
    }

    // Список номеров связанных вершин для указанной вершины
    public Integer[] adjacent(int v) {
        List<Integer> adjacents = new ArrayList<>();
        for (int i = 0; i < verticesInfo[v].length; i++) {
            if (verticesInfo[v][i]) adjacents.add(i);
        };
        return adjacents.toArray(new Integer[0]);
    }
}
