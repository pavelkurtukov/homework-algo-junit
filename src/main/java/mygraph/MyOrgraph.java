package mygraph;

import java.util.ArrayList;
import java.util.List;

public class MyOrgraph {
    // Массив списков связей вершин (i - вершины, j - список связей i-ой вершины)
    boolean[][] verticesInfo;

    public MyOrgraph(boolean[][] verticesInfo) {
        this.verticesInfo = verticesInfo;
    }

    // Добавление направленной связи между двумя вершинами
    public void addEdge(int vertex1, int vertex2) {
        verticesInfo[vertex1][vertex2] = true;
    }

    // Список номеров связанных вершин для указанной вершины
    public Integer[] nexts(int v) {
        List<Integer> nexts = new ArrayList<>();
        for (int i = 0; i < verticesInfo[v].length; i++) {
            if (verticesInfo[v][i]) nexts.add(i);
        };
        return nexts.toArray(new Integer[0]);
    }
}
