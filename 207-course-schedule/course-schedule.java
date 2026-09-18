class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        
    ArrayList<Integer>[] graph = new ArrayList[numCourses];

    for (int i = 0; i < numCourses;i++){
        graph[i] = new ArrayList<>();
    }

    int[] indegree = new int[numCourses];

    for (int[] edge: prerequisites){
        int course = edge[0];
        int prerequisite = edge[1];

        graph[prerequisite].add(course);
        indegree[course]++;
    }

    Queue<Integer> queue = new LinkedList<>();

    for (int i = 0; i < numCourses; i++){
        if (indegree[i] == 0){
            queue.add(i);
        }
    }

    int count = 0;

    while (!queue.isEmpty()) {
        int current = queue.remove();
        count++;

        for ( int neighbor : graph[current]){
            indegree[neighbor]--;

            if (indegree[neighbor] == 0){
                queue.add(neighbor);
            }
        }
    }

        return count == numCourses;
    }
}