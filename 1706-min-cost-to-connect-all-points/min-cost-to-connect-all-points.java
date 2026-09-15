class Solution {
    public int minCostConnectPoints(int[][] points) {
        int n = points.length;
        boolean[] visited = new boolean[n];
        int[] minDist = new int[n];

        for (int i = 0; i < n; i++) {
            minDist[i] = Integer.MAX_VALUE;
        }

        minDist[0] = 0;
        int cost = 0;

        for (int count = 0; count < n; count++) {

            // Find unvisited point with minimum distance
            int u = -1;

            for (int i = 0; i < n; i++) {
                if (!visited[i] &&
                    (u == -1 || minDist[i] < minDist[u])) {
                    u = i;
                }
            }

            // Add it to MST
            visited[u] = true;
            cost += minDist[u];

            // Update distances
            for (int v = 0; v < n; v++) {
                if (!visited[v]) {
                    int distance =
                        Math.abs(points[u][0] - points[v][0]) +
                        Math.abs(points[u][1] - points[v][1]);

                    minDist[v] = Math.min(minDist[v], distance);
                }
            }
        }

        return cost;
    }
}