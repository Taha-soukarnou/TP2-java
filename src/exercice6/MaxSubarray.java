package exercice6;

public class MaxSubarray {

    public static int maxSubarraySumNaive(int[] t) {
        if (t == null || t.length == 0) return 0;

        int n = t.length;
        int maxSum = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int sum = 0;
                for (int k = i; k <= j; k++) {
                    sum += t[k];
                }
                maxSum = Math.max(maxSum, sum);
            }
        }
        return maxSum;
    }

    public static int maxSubarraySum(int[] t) {
        if (t == null || t.length == 0) return 0;

        int currentSum = t[0];
        int maxSum = t[0];

        for (int i = 1; i < t.length; i++) {
            currentSum = Math.max(t[i], currentSum + t[i]);
            maxSum = Math.max(maxSum, currentSum);
        }

        return maxSum;
    }

    public static int[] kadaneAvecIndices(int[] t) {
        if (t == null || t.length == 0) return null;

        int currentSum = t[0];
        int maxSum = t[0];

        int start = 0;
        int bestStart = 0;
        int bestEnd = 0;

        for (int i = 1; i < t.length; i++) {
  
            if (t[i] > currentSum + t[i]) {
                currentSum = t[i];
                start = i; 
            } else {
                currentSum += t[i];
            }

            if (currentSum > maxSum) {
                maxSum = currentSum;
                bestStart = start;
                bestEnd = i;
            }
        }

        return new int[]{maxSum, bestStart, bestEnd};
    }

   
    public static void afficher(int[] t) {
        System.out.print("[");
        for (int i = 0; i < t.length; i++) {
            System.out.print(t[i]);
            if (i < t.length - 1) System.out.print(", ");
        }
        System.out.println("]");
    }

    public static void main(String[] args) {

        int[][] tests = {
            {-2, 1, -3, 4, -1, 2, 1, -5, 4},  
            {1, 2, 3, 4},                    
            {-1, -2, -3},                    
            {5},                            
            {-7},                            
            {-2, -1, 3, 4, -5},             
            {1, -1, 1, -1, 1}                
        };

        for (int[] t : tests) {
            System.out.print("Tableau : ");
            afficher(t);

            System.out.println("Naive (O³)     : " + maxSubarraySumNaive(t));
            System.out.println("Kadane (O(n))  : " + maxSubarraySum(t));

            int[] r = kadaneAvecIndices(t);
            System.out.println("Kadane + indices : Somme = " + r[0] +
                               ", debut = " + r[1] +
                               ", fin = " + r[2]);

            System.out.println("--------------------------------------\n");
        }
    }
}
