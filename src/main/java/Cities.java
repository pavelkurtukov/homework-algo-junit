import mygraph.MyGraph;

import java.util.ArrayList;
import java.util.List;

public class Cities {
    public static void main(String[] args) {
        // Кол-во городов
        int v = 6;
        // Массив названий городов
        String[] cities = new String[v];
        cities[0] = "Барнаул";
        cities[1] = "Новосибирск";
        cities[2] = "Томск";
        cities[3] = "Нью-Йорк";
        cities[4] = "Лос-Анджелес";
        cities[5] = "Сидней";
        // Граф со связями городов
        MyGraph cityGraph = new MyGraph(new boolean[v][v]);
        cityGraph.addEdge(0, 1);
        cityGraph.addEdge(1, 2);
        cityGraph.addEdge(3, 4);

        // Массив ответов
        int[] answer = calcPaths(cityGraph);

        System.out.println("Кол-во городов, до которых можно дохать из города:");
        for (int i = 0; i < cityGraph.size(); i++) {
            System.out.println(cities[i] + " -  " + answer[i]);
        }
    }

    // Поиск размера компонеты связности с вершиной vertex
    // vertex - текущая вершина, которую мы рассматриваем
    // mark - номер текущей компоненты связности
    // marks - массив, в котором мы для каждой вершины графа отмечаем номер компоненты связности
    public static int dfs(MyGraph graph, int vertex, int mark, int[] marks) {
        int size = 1;
        marks[vertex] = mark;
        for (int v : graph.adjacent(vertex)) {
            if (marks[v] == 0) size += dfs(graph, v, mark, marks);
        }
        return size;
    }

    // Определение кол-ва связей для каждого города
    public static int[] calcPaths(MyGraph graph) {
        // Массив, в котором мы для каждой вершины графа отмечаем номер компоненты связности
        int[] marks = new int[graph.size()];
        // Список компонент связности, содержащий их размеры
        List<Integer> markSizes = new ArrayList<>();
        // Перебираем вершины и считаем размеры компонент связности, в которые они входят
        for (int v = 0; v < graph.size(); v++) {
            // Если текущую вершину v мы ещё не рассматривали, считаем размер её компоненты связности
            if (marks[v] == 0) {
                int size = dfs(graph, v, markSizes.size() + 1, marks);
                markSizes.add(size - 1);
            }
        }
        // Заполняем массив кол-ва связей для каждого города
        int[] paths = new int[graph.size()];
        for (int i = 0; i < graph.size(); i++) {
            paths[i] = markSizes.get(marks[i] - 1);
        }
        return paths;
    }
}
