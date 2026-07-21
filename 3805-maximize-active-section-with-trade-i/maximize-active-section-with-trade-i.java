import java.util.*;

class Solution {
    public int maxActiveSectionsAfterTrade(String s) {
        String s2 = "1";
        String s3 = "1";
        String S = s2 + s + s3;   // augmented string, e.g. "1" + s + "1"
        int n = S.length();

        // 1. Break S into runs of consecutive identical characters (blocks).
        //    Since S starts and ends with '1', blocks alternate:
        //    1-block, 0-block, 1-block, 0-block, ..., 1-block
        List<Integer> blocks = new ArrayList<>();
        int i = 0;
        while (i < n) {
            int j = i;
            while (j < n && S.charAt(j) == S.charAt(i)) {
                j++;
            }
            blocks.add(j - i);
            i = j;
        }

        int m = blocks.size();

        // 2. Count total '1's in S (sum of blocks at even indices: 0, 2, 4, ...)
        int onesTotal = 0;
        for (int k = 0; k < m; k += 2) {
            onesTotal += blocks.get(k);
        }

        int base = onesTotal - 2; // remove the 2 artificial '1's we added

        // 3. Try every INTERIOR 1-block (skip index 0 and index m-1,
        //    since those touch the boundary and aren't surrounded by 0s).
        //    Interior 1-blocks are at indices 2, 4, ..., m-3.
        int maxGain = 0;
        for (int k = 2; k <= m - 3; k += 2) {
            int zeroLeft = blocks.get(k - 1);
            int zeroRight = blocks.get(k + 1);
            maxGain = Math.max(maxGain, zeroLeft + zeroRight);
        }

        return base + maxGain;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        in.nextLine();
        String s = in.next();

        Solution sol = new Solution();
        System.out.println(sol.maxActiveSectionsAfterTrade(s));
    }
}