import java.util.Arrays;

class Main {
    public static void main(String[] args) {
        System.out.println(encrypt("rais_zainuri_rzr", "46262"));
        System.out.println(decrypt("pdn~R}nhk}wmUq}q", "46262"));
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

            System.out.println(
                    "\n" +
                            "i = iterasi ke-" + i + "\n" +
                            "j = ( j + s[" + i + "] + k[" + i + "]) mod 16" + "\n" +
                            "j = ( " + j + " + " + s[i] + " + " + k[i] + ") mod 16" + "\n" +
                            "j = " + j);

            temp = s[i];
            s[i] = s[j];
            s[j] = temp;

            System.out.println(Arrays.toString(ind));
            System.out.println(Arrays.toString(s));
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

            ciphertext[index] = String.format("%8s", Integer.toBinaryString((byte) (plain.charAt(index) ^ K) & 0xFF)).replace(' ', '0');
            plaintext[index] = String.format("%8s", Integer.toBinaryString((byte) (plain.charAt(index)) & 0xFF)).replace(' ', '0');
            keytext[index] = String.format("%8s", Integer.toBinaryString((byte) (k[index]) & 0xFF)).replace(' ', '0');

            hasilcipher.append(Character.toString(Integer.parseInt(ciphertext[index], 2)));
        }

        return hasilcipher.toString();

        /*System.out.print("Plain \t:\t" + plain + "\n\t\t\t");
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
        }*/
    }

    static String decrypt(String plain, String key) {
        return encrypt(plain, key);
    }
}
