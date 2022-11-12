package business;
//this class be applied with 2by2 matrix
import java.util.Locale;

import helppers.Templates;

public class Hill extends ACryptSymmetric {
    public String encrypt(String plainText, String key) {
        if (key.length() % 2 == 0) {
            String result = "";
            int[][] keyMatrix = createKeyMatrix(key);
            String[] blocks = cutBlock(plainText, key.length() / 2);
            for (int i = 0; i < blocks.length; i++) {
                int[] block = new int[blocks[i].length()];
                for (int j = 0; j < block.length; j++) {
                    block[j] = Templates.templateEN.toUpperCase().indexOf(Character.toString(blocks[i].toCharArray()[j]));
                }
                result += multiplicationMatrix(keyMatrix, block);
            }
            System.out.println("=> " + result);
            return result;
        }
        System.out.println("Khóa không đúng định dạng ");
        return null;
    }

    public int[][] createKeyMatrix(String key) {
        int haft = key.length() / 2;
        String template = Templates.templateEN.toUpperCase(Locale.ROOT);
        int[][] matrix = new int[haft][haft];
        char[] charKey = key.toCharArray();
        int count = 0;
        for (int i = 0; i < haft; i++) {
            for (int j = 0; j < haft; j++) {
                int index = template.indexOf(Character.toString(charKey[count]));
                matrix[i][j] = index;
                count++;
            }
        }
        return matrix;
    }

    public String[] cutBlock(String text, int length) {
        String[] listBlock = new String[Math.round(text.length() / length + text.length() % length)];
        for (int i = 0; i < listBlock.length; i++) {
            if ((i == listBlock.length - 1) && text.length() % 2 == 1) {
                listBlock[i] = text.substring((i * 2), (i * 2) + 1);
                listBlock[i] += "*";
            } else {
                listBlock[i] = text.substring(i * 2, length + (i * 2));
            }
        }
        return listBlock;
    }

    public String decrypt(String cipherText, String key) {
        if (key.length() % 2 == 0) {
            String result = "";
            int[][] keyMatrix = createKeyMatrix(key);
            int k = (keyMatrix[0][0] * keyMatrix[1][1]) - (keyMatrix[0][1] * keyMatrix[1][0]);
            int detK = (k/ 26 + 1);
        }
        System.out.println("Khóa không đúng định dạng");
        return null;
    }

    public String multiplicationMatrix(int[][] keyMatrix, int[] blockMatrix) {
        String result = "";
        for (int i = 0; i < blockMatrix.length; i++) {
            int index = (keyMatrix[i][0] * blockMatrix[0]) + (keyMatrix[i][1] * blockMatrix[1]);
            int mod = index % 26;
            result += super.moveSingleCharEncrypt(mod, 'A');
        }
        return result;
    }

//    public static void main(String[] args) {
//        Hill hill = new Hill();
//        hill.encrypt("DHNONGLAM", "LIDH");
//        System.out.println(-3%26);
//    }
}
