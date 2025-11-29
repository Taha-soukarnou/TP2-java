package exercice7;

import java.util.HashMap;
import java.util.Map;

public class ExerciceMajoritaire {

    public static int elementMajoritaireNaif(int[] t) {
        if (t == null || t.length == 0) return -1;

        Map<Integer, Integer> freq = new HashMap<>();
        int n = t.length;


        for (int x : t) {
            freq.put(x, freq.getOrDefault(x, 0) + 1);
        }

        for (Map.Entry<Integer, Integer> e : freq.entrySet()) {
            if (e.getValue() > n / 2) {
                return e.getKey();
            }
        }

        return -1;
    }

    public static int elementMajoritaire(int[] t) {
        if (t == null || t.length == 0) return -1;


        int candidat = 0;
        int compteur = 0;

        for (int x : t) {
            if (compteur == 0) {
                candidat = x;
                compteur = 1;
            } else if (x == candidat) {
                compteur++;
            } else {
                compteur--;
            }
        }

        int count = 0;
        for (int x : t) {
            if (x == candidat) count++;
        }

        return (count > t.length / 2) ? candidat : -1;
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
            {3,3,4,3,5},         
            {2,2,1,2,3,2,2},     
            {1,1,1,1},           
            {7},                 
            {1,2,3,4},            
            {1,2,2,3},            
            {1,1,2,2},            
            {-1,-1,-1,2,3},       
            {-2,-2,-2,-2,1,3},    
            {}                    
        };

        for (int[] t : tests) {
            System.out.print("Tableau : ");
            afficher(t);

            int resNaif = elementMajoritaireNaif(t);
            int resBM = elementMajoritaire(t);

            System.out.println("Méthode naïve      → " + resNaif);
            System.out.println("Méthode Boyer-Moore → " + resBM);
            System.out.println("----------------------------------");
        }
    }
}

