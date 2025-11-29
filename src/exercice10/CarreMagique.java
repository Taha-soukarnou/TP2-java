package exercice10;

public class CarreMagique {

    /**
     * Vérifie si la matrice 3x3 m est un carré magique.
     *
     * @param m matrice 3x3 d'entiers
     * @return true si m est magique, false sinon
     */
    public static boolean estCarreMagique(int[][] m) {

        // Somme de référence = somme de la première ligne
        int ref = m[0][0] + m[0][1] + m[0][2];

        // Vérifier les lignes restantes
        for (int i = 1; i < 3; i++) {
            int sommeLigne = m[i][0] + m[i][1] + m[i][2];
            if (sommeLigne != ref) return false;
        }

        // Vérifier les colonnes
        for (int j = 0; j < 3; j++) {
            int sommeCol = m[0][j] + m[1][j] + m[2][j];
            if (sommeCol != ref) return false;
        }

        // Vérifier diagonale principale
        int diag1 = m[0][0] + m[1][1] + m[2][2];
        if (diag1 != ref) return false;

        // Vérifier diagonale secondaire
        int diag2 = m[0][2] + m[1][1] + m[2][0];
        if (diag2 != ref) return false;

        return true; // Toutes les conditions sont validées
    }


    public static void main(String[] args) {

        // Test 1 : Carré magique classique
        int[][] m1 = {
                {8, 1, 6},
                {3, 5, 7},
                {4, 9, 2}
        };

        // Test 2 : Pas un carré magique
        int[][] m2 = {
                {2, 7, 6},
                {9, 5, 1},
                {4, 3, 7}   // <--- 8 remplacé par 7 pour casser le carré magique
        };

        // Test 3 : Toutes valeurs identiques
        int[][] m3 = {
                {1, 1, 1},
                {1, 1, 1},
                {1, 1, 1}
        };

        System.out.println("Test 1 : " + (estCarreMagique(m1) ? "Carré magique" : "Pas un carré magique"));
        System.out.println("Test 2 : " + (estCarreMagique(m2) ? "Carré magique" : "Pas un carré magique"));
        System.out.println("Test 3 : " + (estCarreMagique(m3) ? "Carré magique" : "Pas un carré magique"));
    }
}

