class Solution { 
    public boolean validPath(int n, int[][] edges, int source, int destination) { 
        ArrayList<Integer>[] graph = new ArrayList[n]; 
        for(int i = 0; i < n; i++){ 
            graph[i] = new ArrayList<>(); 
        } 
        
        for(int[] edge : edges){ 
            int v1 = edge[0]; 
            int v2 = edge[1]; 
            graph[v1].add(v2); 
            graph[v2].add(v1); 
        } 
        
        boolean[] visited = new boolean[n]; 
        return isPath(graph, source, destination, visited); 
    } 
    
    public boolean isPath(ArrayList<Integer>[] graph, int src, int dest, boolean[] visited){ 
        if(src == dest) return true; 
        visited[src] = true; 
        
        for(int nbr : graph[src]){ 
            if(visited[nbr] == false){ 
                if(isPath(graph, nbr, dest, visited)) return true; 
            } 
        } 
        return false; 
    } 
}
