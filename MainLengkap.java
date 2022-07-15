class Main {
    public static void main(String[] args) {
        /*System.out.println(encrypt("rais_zainuri_rzr", "46262"));
        System.out.println(decrypt("pdn~R}nhk}wmUq}q", "46262"));*/
        start("rais_zainuri_rzr", "46262");
    }

    static void start(String plain, String key) {
        ksa(plain, key);
        System.out.println("\n");
        encrypt(plain, key);
    }

    static void ksa(String plain, String key) {
        int temp, j = 0;

        int s[] = new int[plain.length()];
        int k[] = new int[plain.length()];
        int ind[] = new int[plain.length()];

        for (int i = 0; i < plain.length(); i++) {
            s[i] = i;
            ind[i] = i;

            k[i] = key.charAt(i % key.length());
        }

        for (int i = 0; i < 16; i++) {
            j = (j + s[i] + k[i]) % 16;

            String isi =
                    "\n" +
                            "i = \t\t" + i + "\n" +
                            "j = \t(\tj\t'+\ts[" + i + "]\t'+\tk[" + i + "]\t)\tmod\t16" + "\n" +
                            "j = \t(\t" + j + "\t'+\t" + s[i] + "\t'+\t" + Character.toString(k[i]) + "\t)\tmod\t16" + "\n" +
                            "j = \t\t" + j;


            System.out.println(isi);

            temp = s[i];
            s[i] = s[j];
            s[j] = temp;

            System.out.println();

            System.out.print("Array S\t\t\t");
            for (int l = 0; l < ind.length; l++) {
                System.out.print(ind[l] + "\t");
            }
            System.out.println();

            System.out.print("Array K\t\t\t");
            for (int l = 0; l < s.length; l++) {
                System.out.print(s[l] + "\t");
            }
            System.out.println();
        }
    }

    static String encrypt(String plain, String key) {
        int temp;
        int i = 0, j = 0, t, K;

        int s[] = new int[plain.length()];
        int k[] = new int[plain.length()];
        for (int ii = 0; ii < plain.length(); ii++) {
            s[ii] = ii;
            k[ii] = key.charAt(ii % key.length());
        }

        String[] ciphertext = new String[plain.length()];
        String[] plaintext = new String[plain.length()];
        String[] keytext = new String[plain.length()];
        StringBuilder hasilcipher = new StringBuilder();

        for (int index = 0; index < (plain.length()); index++) {
            i = (i + 1) % plain.length();
            j = (j + s[i]) % plain.length();

            temp = s[i];
            s[i] = s[j];
            s[j] = temp;

            t = (s[i] + s[j]) % plain.length();
            K = s[t];

            System.out.println();
            System.out.println();
            System.out.println("i=\t(\t" + i + "\t'+\t1\t)\tmod\t16\n" +
                    "j=\t(\t" + j + "\t'+\t" + s[i] + "\t)\tmod\t16\n" +
                    "\t\tswap\t(\ts[i]\t,\ts[j]\t)\n" +
                    "t=\t(\t" + s[i] + "\t'+\t" + s[j] + "\t)\tmod\t16\n" +
                    "k=\t\t" + s[t] + "\t\t\t\t\t\n" +
                    "c=\t\t" + plain.charAt(index) + "\txor\t" + K + "\t\t\t");
            System.out.println("c=\t\t" + String.format("%8s", Integer.toBinaryString((byte) (plain.charAt(index) ^ K) & 0xFF)).replace(' ', '0'));

            ciphertext[index] = String.format("%8s", Integer.toBinaryString((byte) (plain.charAt(index) ^ K) & 0xFF)).replace(' ', '0');
            plaintext[index] = String.format("%8s", Integer.toBinaryString((byte) (plain.charAt(index)) & 0xFF)).replace(' ', '0');
            keytext[index] = String.format("%8s", Integer.toBinaryString((byte) (k[index]) & 0xFF)).replace(' ', '0');

            hasilcipher.append(Character.toString(Integer.parseInt(ciphertext[index], 2)));
        }

        System.out.println();
        System.out.println();

        System.out.print("Plain \t:\t" + plain + "\n\t\t\t");
        for (int l = 0; l < plain.length(); l++) {
            System.out.print(plaintext[l] + " ");
        }
        System.out.println();

        StringBuilder keyString = new StringBuilder();
        for (int l = 0; l < k.length; l++) {
            keyString.append(Character.toString(k[l]));
        }

        System.out.print("Key \t:\t" + keyString + "\n\t\t\t");
        for (int l = 0; l < k.length; l++) {
            System.out.print(keytext[l] + " ");
        }
        System.out.println();

        System.out.print("Cipher \t:\t" + hasilcipher + "\n\t\t\t");
        for (int l = 0; l < ciphertext.length; l++) {
            System.out.print(ciphertext[l] + " ");
        }

        return "0";
    }

    static String decrypt(String plain, String key) {
        return encrypt(plain, key);
    }
}
