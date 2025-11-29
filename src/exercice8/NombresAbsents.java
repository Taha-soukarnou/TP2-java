package exercice8;


public class NombresAbsents {
    public static void afficherElementsManquants(int[] t) {
        if (t == null || t.length == 0) {
            System.out.println("Tableau vide → tous les éléments 1..n sont manquants.");
            return;
        }

        int n = t.length;
        boolean[] vu = new boolean[n + 1]; 

 
        for (int x : t) {
            if (x >= 1 && x <= n) {
                vu[x] = true;
            }
        }

        boolean aucun = true;
        System.out.print("Éléments manquants : ");
        for (int k = 1; k <= n; k++) {
            if (!vu[k]) {
                System.out.print(k + " ");
                aucun = false;
            }
        }

        if (aucun) {
            System.out.print("Aucun");
        }

        System.out.println();
    }

    public static void main(String[] args) {
        int[][] tests = {
            {1, 3, 3, 5},     
            {1, 2, 3, 4},     
            {3, 3, 3},     
            {1, 1, 1, 1},     
            {4, 2, 2, 1, 5},  
            {1},              
            {},               
            {1, 2, 3, 6}      
        };

        for (int[] t : tests) {
            afficherTableau(t);
            afficherElementsManquants(t);
            System.out.println("-----------------------------");
        }
    }


    public static void afficherTableau(int[] t) {
        System.out.print("Tableau : [");
        for (int i = 0; i < t.length; i++) {
            System.out.print(t[i]);
            if (i < t.length - 1) System.out.print(", ");
        }
        System.out.println("]");
    }
}
