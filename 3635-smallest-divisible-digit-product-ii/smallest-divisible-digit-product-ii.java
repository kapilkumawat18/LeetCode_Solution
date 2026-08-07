import java.util.Arrays;

class Solution {
    public String smallestNumber(String num, long t) {
        int a = 0, b = 0, c = 0, d = 0;
        long tt = t;
        while (tt % 2 == 0) { a++; tt /= 2; }
        while (tt % 3 == 0) { b++; tt /= 3; }
        while (tt % 5 == 0) { c++; tt /= 5; }
        while (tt % 7 == 0) { d++; tt /= 7; }
        if (tt != 1) return "-1"; // t has a prime factor other than 2,3,5,7

        final int AMAX = 47, BMAX = 30; // 2^46, 3^29 <= 1e14 < 2^47, 3^30
        int[][] f = new int[AMAX][BMAX];
        int[][] opts = {{1,0},{0,1},{2,0},{1,1},{3,0},{0,2}}; // digits 2,3,4,6,8,9
        for (int[] row : f) Arrays.fill(row, Integer.MAX_VALUE / 2);
        f[0][0] = 0;
        for (int aa = 0; aa < AMAX; aa++) {
            for (int bb = 0; bb < BMAX; bb++) {
                if (aa == 0 && bb == 0) continue;
                int best = Integer.MAX_VALUE / 2;
                for (int[] opt : opts) {
                    int na = Math.max(0, aa - opt[0]);
                    int nb = Math.max(0, bb - opt[1]);
                    if (na == aa && nb == bb) continue; // no progress, skip
                    int val = 1 + f[na][nb];
                    if (val < best) best = val;
                }
                f[aa][bb] = best;
            }
        }

        int[] cA = new int[10], cB = new int[10], cC = new int[10], cD = new int[10];
        cA[2]=1; cA[4]=2; cA[6]=1; cA[8]=3;
        cB[3]=1; cB[6]=1; cB[9]=2;
        cC[5]=1;
        cD[7]=1;

        int L = num.length();
        char[] digits = num.toCharArray();

        int P = L;
        for (int i = 0; i < L; i++) {
            if (digits[i] == '0') { P = i; break; }
        }

        // prefix deficits (before position i), valid for i = 0..P
        int[] dA = new int[P+1], dB = new int[P+1], dC = new int[P+1], dD = new int[P+1];
        dA[0]=a; dB[0]=b; dC[0]=c; dD[0]=d;
        for (int i = 0; i < P; i++) {
            int g = digits[i]-'0';
            dA[i+1] = Math.max(0, dA[i]-cA[g]);
            dB[i+1] = Math.max(0, dB[i]-cB[g]);
            dC[i+1] = Math.max(0, dC[i]-cC[g]);
            dD[i+1] = Math.max(0, dD[i]-cD[g]);
        }

        // num itself is zero-free and already satisfies the requirement
        if (P == L && dA[L]==0 && dB[L]==0 && dC[L]==0 && dD[L]==0) {
            return num;
        }

        // find rightmost position we can bump a digit and still finish within remaining length
        int startI = (P < L) ? P : L-1;
        int bestI = -1, bestG = -1, bna=0, bnb=0, bnc=0, bnd=0;
        for (int i = startI; i >= 0; i--) {
            int lowG = (i < P) ? (digits[i]-'0'+1) : 1;
            int remaining = L-1-i;
            for (int g = lowG; g <= 9; g++) {
                int na = Math.max(0, dA[i]-cA[g]);
                int nb = Math.max(0, dB[i]-cB[g]);
                int nc = Math.max(0, dC[i]-cC[g]);
                int nd = Math.max(0, dD[i]-cD[g]);
                int need = f[na][nb] + nc + nd;
                if (need <= remaining) {
                    bestI = i; bestG = g; bna=na; bnb=nb; bnc=nc; bnd=nd;
                    break;
                }
            }
            if (bestI != -1) break;
        }

        StringBuilder sb = new StringBuilder();
        if (bestI != -1) {
            sb.append(digits, 0, bestI);
            sb.append((char)('0'+bestG));
            fillSmallest(sb, bna, bnb, bnc, bnd, L-1-bestI, f, cA, cB, cC, cD);
        } else {
            // length L impossible -> use length M = max(L+1, minDigitsNeeded)
            int minDig = f[a][b] + c + d;
            int M = Math.max(L+1, minDig);
            fillSmallest(sb, a, b, c, d, M, f, cA, cB, cC, cD);
        }
        return sb.toString();
    }

    // append lexicographically-smallest length-R digit string (1-9) clearing deficit (a,b,c,d)
    private void fillSmallest(StringBuilder sb, int a, int b, int c, int d, int R,
                               int[][] f, int[] cA, int[] cB, int[] cC, int[] cD) {
        for (int pos = 0; pos < R; pos++) {
            int remaining = R-1-pos;
            for (int g = 1; g <= 9; g++) {
                int na = Math.max(0, a-cA[g]);
                int nb = Math.max(0, b-cB[g]);
                int nc = Math.max(0, c-cC[g]);
                int nd = Math.max(0, d-cD[g]);
                int need = f[na][nb] + nc + nd;
                if (need <= remaining) {
                    sb.append((char)('0'+g));
                    a=na; b=nb; c=nc; d=nd;
                    break;
                }
            }
        }
    }
}