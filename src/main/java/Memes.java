import mygraph.MyOrgraph;

import java.util.LinkedList;
import java.util.Queue;

public class Memes {
    public static void main(String[] args) {
        // Массив пользователей
        final int v = 7;
        String[] users = new String[v];
        for (int i = 0; i < v; i++) {
            users[i] = "Пользователь " + i;
        }

        // Ориентированный граф со связями пользователей
        MyOrgraph graph = new MyOrgraph(new boolean[v][v]);
        graph.addEdge(0, 1);
        graph.addEdge(0, 3);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);

        // Массив отметок о получении i-м пользователем мема
        boolean[] visited = new boolean[v];

        // Очередь пользователей, получивших рассылку
        Queue<Integer> sent = new LinkedList<>();
        sent.offer(0);
        visited[0] = true;

        // Обход графа в ширину с помощью очереди
        int lastViewer = sent.peek();
        while (!sent.isEmpty()) {
            lastViewer = sent.poll();
            for (int receiver : graph.nexts(lastViewer)) {
                if (!visited[receiver]) {
                    visited[receiver] = true;
                    sent.offer(receiver);
                }
            }
        }

        // Ответ. Последний пользователь будет последним в очереди
        System.out.println(users[lastViewer] + " позже всех впервые посмотрит мем.");
    }
}
