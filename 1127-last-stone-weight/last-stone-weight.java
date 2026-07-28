class Solution {
    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> maxheap = new PriorityQueue<>(Collections.reverseOrder());
        for(int stone : stones){
            maxheap.offer(stone);
        }
        while (maxheap.size() > 1){
            int first = maxheap.poll();
            int second = maxheap.poll();

            if(first != second){
                maxheap.offer(first-second);
            }
        }
        return maxheap.isEmpty() ? 0 : maxheap.poll();
    }
}