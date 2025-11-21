public static int exercice1(int[] t) {
    int n = t.length;
    int[] dp = new int[n];
    int max = 1;

    for (int i = 0; i < n; i++) dp[i] = 1;

    for (int i = 1; i < n; i++) {
        for (int j = 0; j < i; j++) {
            if (t[j] < t[i] && dp[j] + 1 > dp[i]) {
                dp[i] = dp[j] + 1;
            }
        }
        if (dp[i] > max) max = dp[i];
    }
    return max;
}

// ---------------------------
// Exercice 2 – Pivots
// ---------------------------
public static int[] exercice2(int[] t) {
    int n = t.length;
    boolean[] isPivot = new boolean[n];

    int[] leftMax = new int[n];
    int[] rightMin = new int[n];

    leftMax[0] = t[0];
    for (int i = 1; i < n; i++)
        leftMax[i] = Math.max(leftMax[i - 1], t[i]);

    rightMin[n - 1] = t[n - 1];
    for (int i = n - 2; i >= 0; i--)
        rightMin[i] = Math.min(rightMin[i + 1], t[i]);

    int count = 0;
    for (int i = 0; i < n; i++) {
        if (t[i] == leftMax[i] && t[i] == rightMin[i]) {
            isPivot[i] = true;
            count++;
        }
    }

    int[] result = new int[count];
    int idx = 0;
    for (int i = 0; i < n; i++)
        if (isPivot[i]) result[idx++] = t[i];

    return result;
}

// ---------------------------
// Exercice 3 – Spiral Matrix
// ---------------------------
public static int[][] exercice3(int n) {
    int[][] m = new int[n][n];
    int num = 1;

    int top = 0, bottom = n - 1;
    int left = 0, right = n - 1;

    while (top <= bottom && left <= right) {
        for (int j = left; j <= right; j++) m[top][j] = num++;
        top++;

        for (int i = top; i <= bottom; i++) m[i][right] = num++;
        right--;

        if (top <= bottom) {
            for (int j = right; j >= left; j--) m[bottom][j] = num++;
            bottom--;
        }

        if (left <= right) {
            for (int i = bottom; i >= top; i--) m[i][left] = num++;
            left++;
        }
    }
    return m;
}

// ---------------------------
// Exercice 4 – Largest rectangle of 1s (histogram method)
// ---------------------------
public static int exercice4(int[][] m) {
    int rows = m.length;
    int cols = m[0].length;

    int[] heights = new int[cols];
    int maxArea = 0;

    for (int i = 0; i < rows; i++) {

        for (int j = 0; j < cols; j++)
            heights[j] = (m[i][j] == 1) ? heights[j] + 1 : 0;

        int area = maxHistogramArea(heights);
        if (area > maxArea) maxArea = area;
    }
    return maxArea;
}

private static int maxHistogramArea(int[] h) {
    int n = h.length;
    int max = 0;

    for (int i = 0; i < n; i++) {
        int height = h[i];
        if (height == 0) continue;

        int left = i, right = i;

        while (left >= 0 && h[left] >= height) left--;
        while (right < n && h[right] >= height) right++;

        int area = height * (right - left - 1);
        if (area > max) max = area;
    }
    return max;
}

// ---------------------------
// Exercice 5 – Circular permutation
// ---------------------------
public static boolean exercice5(int[] t) {
    int n = t.length;

    int start = -1;
    for (int i = 0; i < n; i++)
        if (t[i] == 1) start = i;

    if (start == -1) return false;

    for (int k = 0; k < n; k++)
        if (t[(start + k) % n] != k + 1)
            return false;

    return true;
}

// ---------------------------
// Exercice 6 – Kadane
// ---------------------------
public static int exercice6(int[] t) {
    int maxSoFar = t[0];
    int curr = t[0];

    for (int i = 1; i < t.length; i++) {
        curr = Math.max(t[i], curr + t[i]);
        maxSoFar = Math.max(maxSoFar, curr);
    }
    return maxSoFar;
}

// ---------------------------
// Exercice 7 – Majority element
// ---------------------------
public static int exercice7(int[] t) {
    int count = 0, candidate = 0;

    for (int x : t) {
        if (count == 0) {
            candidate = x;
            count = 1;
        } else if (x == candidate) {
            count++;
        } else {
            count--;
        }
    }

    int freq = 0;
    for (int x : t)
        if (x == candidate) freq++;

    return (freq > t.length / 2) ? candidate : -1;
}

// ---------------------------
// Exercice 8 – Missing numbers
// ---------------------------
public static int[] exercice8(int[] t) {
    int n = t.length;
    int[] seen = new int[n + 1];

    for (int i = 0; i < n; i++)
        if (t[i] >= 1 && t[i] <= n)
            seen[t[i]] = 1;

    int missingCount = 0;
    for (int i = 1; i <= n; i++)
        if (seen[i] == 0) missingCount++;

    int[] result = new int[missingCount];
    int idx = 0;

    for (int i = 1; i <= n; i++)
        if (seen[i] == 0)
            result[idx++] = i;

    return result;
}

// ---------------------------
// Exercice 9 – Diagonal difference
// ---------------------------
public static int exercice9(int[][] m) {
    int n = m.length;
    int d1 = 0, d2 = 0;

    for (int i = 0; i < n; i++) {
        d1 += m[i][i];
        d2 += m[i][n - 1 - i];
    }
    return Math.abs(d1 - d2);
}

// ---------------------------
// Exercice 10 – Magic 3×3
// ---------------------------
public static boolean exercice10(int[][] m) {
    int magic = m[0][0] + m[0][1] + m[0][2];

    for (int i = 0; i < 3; i++) {
        int row = m[i][0] + m[i][1] + m[i][2];
        if (row != magic) return false;
    }

    for (int j = 0; j < 3; j++) {
        int col = m[0][j] + m[1][j] + m[2][j];
        if (col != magic) return false;
    }

    int d1 = m[0][0] + m[1][1] + m[2][2];
    int d2 = m[0][2] + m[1][1] + m[2][0];

    return (d1 == magic && d2 == magic);
}

public class main {
    public static void main(String[] args) {
        int[] t1 = {2, 1, 4, 2, 3, 5, 1, 7};
        System.out.println("Exercice 1 (LIS) : " + exercice1(t1));

        int[] t2 = {2, 4, 3, 5, 6};
        int[] pivots = exercice2(t2);
        System.out.print("Exercice 2 (Pivots) : ");
        for (int x : pivots) System.out.print(x + " ");
        System.out.println();

        int[][] spiral = exercice3(3);
        System.out.println("Exercice 3 (Spirale 3x3) :");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++)
                System.out.print(spiral[i][j] + " ");
            System.out.println();
        }

        int[][] mat = {
                {1, 0, 1, 1},
                {1, 1, 1, 1},
                {1, 1, 1, 0}
        };
        System.out.println("Exercice 4 (Plus grand rectangle de 1) : " + exercice4(mat));

        int[] t5 = {4, 5, 1, 2, 3};
        System.out.println("Exercice 5 (Permutation circulaire ?) : " + exercice5(t5));

        int[] t6 = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println("Exercice 6 (Kadane) : " + exercice6(t6));

        int[] t7 = {1, 2, 1, 1, 3, 1, 1};
        System.out.println("Exercice 7 (Majoritaire) : " + exercice7(t7));

        int[] t8 = {1, 3, 3, 5};
        int[] missing = exercice8(t8);
        System.out.print("Exercice 8 (Manquants) : ");
        for (int x : missing) System.out.print(x + " ");
        System.out.println();


        int[][] mat9 = {
                {1, 2, 3},
                {4, 5, 6},
                {9, 8, 9}
        };
        System.out.println("Exercice 9 (Diff diagonale) : " + exercice9(mat9));


        int[][] magic = {
                {8, 1, 6},
                {3, 5, 7},
                {4, 9, 2}
        };
        System.out.println("Exercice 10 (Carré magique ?) : " + exercice10(magic));
    }
}
}

