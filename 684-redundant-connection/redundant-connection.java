import java.util.*;

class Solution {
    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        ArrayList<Integer>[] graph = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            if (hasPath(graph, u, v, n)) {
                return edge;
            }

            graph[u].add(v);
            graph[v].add(u);
        }

        return new int[0];
    }

    private boolean hasPath(ArrayList<Integer>[] graph, int src, int dest, int n) {
        boolean[] visited = new boolean[n + 1];
        Queue<Integer> queue = new LinkedList<>();

        queue.add(src);
        visited[src] = true;

        while (!queue.isEmpty()) {
            int curr = queue.poll();

            if (curr == dest) {
                return true;
            }

            for (int nbr : graph[curr]) {
                if (!visited[nbr]) {
                    visited[nbr] = true;
                    queue.add(nbr);
                }
            }
        }

        return false;
    }
}