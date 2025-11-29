package exercice5;

public class PermutationCirculaire {

    public static boolean estPermutationCirculaireNaive(int[] t) {
        if (t == null || t.length == 0) return false;

        int n = t.length;

  
        boolean[] vu = new boolean[n + 1];
        for (int x : t) {
            if (x < 1 || x > n) return false;
            if (vu[x]) return false; 
            vu[x] = true;
        }

        int[] ref = new int[n];
        for (int i = 0; i < n; i++) ref[i] = i + 1;

        for (int rot = 0; rot < n; rot++) {
            boolean ok = true;

            for (int i = 0; i < n; i++) {
                int idx = (rot + i) % n;
                if (t[i] != ref[idx]) {
                    ok = false;
                    break;
                }
            }

            if (ok) return true;
        }

        return false;
    }

    public static boolean estPermutationCirculaire(int[] t) {
        if (t == null || t.length == 0) return false;

        int n = t.length;
        boolean[] vu = new boolean[n + 1];

        for (int x : t) {
            if (x < 1 || x > n) return false;
            if (vu[x]) return false;
            vu[x] = true;
        }


        int pos1 = -1;
        for (int i = 0; i < n; i++) {
            if (t[i] == 1) {
                pos1 = i;
                break;
            }
        }

        if (pos1 == -1) return false;

        for (int k = 0; k < n; k++) {
            int idx = (pos1 + k) % n;
            int attendu = k + 1;

            if (t[idx] != attendu) {
                return false;
            }
        }

        return true;
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
            {1},
            {1,2,3,4,5},
            {2,3,4,5,1},
            {3,4,5,1,2},
            {4,5,1,2,3},
            {5,1,2,3,4},

            {3,1,2,4,5},
            {2,1,3,4,5},
            {4,1,2,3,5},

            {0,1,2,3,4},
            {1,2,2,3,4},
            {1,2,3,4,6}
        };

        for (int[] t : tests) {
            afficher(t);
            System.out.println("Naive  : " + estPermutationCirculaireNaive(t));
            System.out.println("Optime : " + estPermutationCirculaire(t));
            System.out.println("--------------------------------------");
        }
    }
}

