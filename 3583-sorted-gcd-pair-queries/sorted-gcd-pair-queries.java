import java.util.*;

class Solution {
    public int[] gcdValues(int[] nums, long[] queries) {
        int maxVal = 0;
        for (int v : nums) maxVal = Math.max(maxVal, v);

        // frequency of each value
        int[] freq = new int[maxVal + 1];
        for (int v : nums) freq[v]++;

        // multCount[d] = how many numbers are divisible by d
        long[] multCount = new long[maxVal + 1];
        for (int d = 1; d <= maxVal; d++) {
            long s = 0;
            for (int m = d; m <= maxVal; m += d) {
                s += freq[m];
            }
            multCount[d] = s;
        }

        // pairCount[d] = number of pairs whose GCD is a multiple of d
        long[] pairCount = new long[maxVal + 1];
        for (int d = 1; d <= maxVal; d++) {
            long c = multCount[d];
            pairCount[d] = c * (c - 1) / 2;
        }

        // exact[d] = number of pairs whose GCD is exactly d
        long[] exact = new long[maxVal + 1];
        for (int d = maxVal; d >= 1; d--) {
            long val = pairCount[d];
            for (int m = 2 * d; m <= maxVal; m += d) {
                val -= exact[m];
            }
            exact[d] = val;
        }

        // prefix[d] = number of pairs whose GCD is <= d
        long[] prefix = new long[maxVal + 1];
        for (int d = 1; d <= maxVal; d++) {
            prefix[d] = prefix[d - 1] + exact[d];
        }

        int[] answer = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            long target = queries[i] + 1; // smallest d with prefix[d] >= target
            int lo = 1, hi = maxVal, res = maxVal;
            while (lo <= hi) {
                int mid = lo + (hi - lo) / 2;
                if (prefix[mid] >= target) {
                    res = mid;
                    hi = mid - 1;
                } else {
                    lo = mid + 1;
                }
            }
            answer[i] = res;
        }
        return answer;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) nums[i] = in.nextInt();

        int qn = in.nextInt();
        long[] queries = new long[qn];
        for (int i = 0; i < qn; i++) queries[i] = in.nextLong();

        Solution sol = new Solution();
        int[] output = sol.gcdValues(nums, queries);
        StringBuilder sb = new StringBuilder();
        for (int v : output) sb.append(v).append(",");
        System.out.println(sb.toString());
    }
}