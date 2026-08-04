class MedianFinder {

    private PriorityQueue<Integer> maxheap;
    private PriorityQueue<Integer> minheap;

    public MedianFinder() {
        maxheap = new PriorityQueue<>(Collections.reverseOrder());
        minheap = new PriorityQueue<>();
    }

    public void addNum(int num) {

        if (maxheap.isEmpty() || num <= maxheap.peek()) {
            maxheap.offer(num);
        } else {
            minheap.offer(num);
        }

        // Balance the heaps
        if (maxheap.size() > minheap.size() + 1) {
            minheap.offer(maxheap.poll());
        } else if (minheap.size() > maxheap.size()) {
            maxheap.offer(minheap.poll());
        }
    }

    public double findMedian() {

        if (maxheap.size() == minheap.size()) {
            return ((double) maxheap.peek() + minheap.peek()) / 2.0;
        }

        return maxheap.peek();
    }
}