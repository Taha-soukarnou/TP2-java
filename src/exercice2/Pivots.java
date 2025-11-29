package exercice2;

public class Pivots {

    public static void afficherPivots(int[] t) {

        int n = t.length;
        if (n < 3) {
            System.out.println("Aucun pivot");
            return;
        }

        int[] prefixMax = new int[n];
        prefixMax[0] = t[0];
        for (int i = 1; i < n; i++) {
            prefixMax[i] = Math.max(prefixMax[i - 1], t[i]);
        }
        int[] suffixMin = new int[n];
        suffixMin[n - 1] = t[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            suffixMin[i] = Math.min(suffixMin[i + 1], t[i]);
        }
        boolean found = false;
        System.out.print("Pivots : ");

        for (int i = 1; i < n - 1; i++) {
            if (prefixMax[i - 1] <= t[i] && suffixMin[i + 1] >= t[i]) {
                found = true;
                System.out.print(t[i] + " ");
            }
        }

        if (!found) {
            System.out.println("Aucun pivot");
        } else {
            System.out.println();
        }
    }

    public static void main(String[] args) {
    	int[] t1 = {2, 4, 3, 5, 6};
    	afficherPivots(t1);
    	
    	int[] t2 = {1, 2, 3, 4, 5};
    	afficherPivots(t2);
    	
    	int[] t3 = {5, 4, 3, 2, 1};
    	afficherPivots(t3);
    	
    	int[] t4 = {3, 3, 3, 3};
    	afficherPivots(t4);
    	

    }
}

