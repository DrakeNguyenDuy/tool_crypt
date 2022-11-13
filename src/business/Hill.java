package business;

import helppers.EnToVi;
import helppers.Templates;

//APPLIDE FOR MATRIX 2*2
public class Hill extends ACryptSymmetric {
	private final int SIZE_STRING_KEY = 4;// STRING LENGTH KEY
	private final int SIZE_BLOCK = 2;// SIZE OF BLOCK
	private static Hill hill = null;
	private final String CHAR_PADDING = "=";

	// instance
	public static Hill getInstance() {
		if (hill == null) {
			hill = new Hill();
		}
		return hill;
	}

	public String encrypt(String plainText, String key) {
		plainText = EnToVi.translateEn2Vi(plainText).toUpperCase();
		key = EnToVi.translateEn2Vi(key).toUpperCase();
		if (key.length() == SIZE_STRING_KEY) {// check format key is matrix 2*2 (length is 4)
			String result = "";
			int[][] keyMatrix = createKeyMatrix(key);
			String[] blocks = cutBlock(plainText, SIZE_STRING_KEY / 2);
			for (int i = 0; i < blocks.length; i++) {
				int[] block = new int[SIZE_BLOCK];
				for (int j = 0; j < block.length; j++) {
					// convert block type string to a matrix 1*2 (integer)
					block[j] = Templates.templateEN.indexOf(Character.toString(blocks[i].toCharArray()[j]));
				}
				result += multiplicationMatrix(keyMatrix, block, blocks[i]);
			}
//			System.out.println("=> " + result);
			return result;
		}
		System.out.println("Khóa không đúng định dạng ");
		return null;
	}

	/*
	 * this method will convert string with length is 4 to matrix 2*2 (integer)
	 */
	public int[][] createKeyMatrix(String key) {
		int haft = SIZE_STRING_KEY / 2;
		String template = Templates.templateEN;
		int[][] matrix = new int[haft][haft];
		char[] charKey = key.toCharArray();
		int count = 0;// position of elements in key
		for (int i = 0; i < haft; i++) {
			for (int j = 0; j < haft; j++) {
				// get index of character in template
				int index = template.indexOf(Character.toString(charKey[count]));

				matrix[i][j] = index;// assign for positions in matrix
				count++;
			}
		}
		return matrix;
	}

	public String[] cutBlock(String text, int length) {
		// every block has a length
		// listBlock will has total: text.length() / length + text.length() % length
		// block
		String[] listBlock = new String[text.length() / length + text.length() % length];
		for (int i = 0; i < listBlock.length; i++) {
			if ((i == listBlock.length - 1) && text.length() % 2 == 1) {
				listBlock[i] = text.substring((i * 2), (i * 2) + 1);
				listBlock[i] += CHAR_PADDING;
			} else {
				listBlock[i] = text.substring(i * 2, length + (i * 2));
			}
		}
		return listBlock;
	}

	public String decrypt(String cipherText, String key) {
		cipherText = EnToVi.translateEn2Vi(cipherText).toUpperCase();
		key = EnToVi.translateEn2Vi(key).toUpperCase();
		String result = "";
		// check last char is equal with CHAR PADDING
		if (key.length() != SIZE_STRING_KEY) {
			return "Key not avalid";
		} else {
			if (cipherText.substring(cipherText.length() - 1, cipherText.length()).equals(CHAR_PADDING)) {
//			cipherText = cipherText.substring(0, cipherText.length() - 1);// remove last character in
			}
			int[][] keyMatrix = createKeyMatrix(key);
			int matrixDeterminant = matrixDeterminant(keyMatrix);
			int[][] matrixPosition = convert2MatrixTransposition(keyMatrix); // cipher text
			int[][] auxiliaryMatrix = convert2AuxiliaryMatrix(matrixPosition);
			for (int i = 0; i < auxiliaryMatrix.length; i++) {
				for (int j = 0; j < auxiliaryMatrix.length; j++) {
					System.out.print(auxiliaryMatrix[i][j]);
				}
				System.out.println();
			}
			int modulo = findModulo(Math.abs(matrixDeterminant), 26);
			// convert array if value in array smaller 0 and sub with modulo
			for (int i = 0; i < SIZE_BLOCK; i++) {
				for (int j = 0; j < SIZE_BLOCK; j++) {
					if (auxiliaryMatrix[i][j] < 0) {
						auxiliaryMatrix[i][j] = (auxiliaryMatrix[i][j] + 26) * modulo;
					} else {
						auxiliaryMatrix[i][j] = auxiliaryMatrix[i][j] * modulo;
					}
				}
			}
			String[] blocks = cutBlock(cipherText, SIZE_STRING_KEY / 2);
			for (int i = 0; i < blocks.length; i++) {
				int[] block = new int[SIZE_BLOCK];
				for (int j = 0; j < block.length; j++) {
					// convert block type string to a matrix 1*2 (integer)
					block[j] = Templates.templateEN.indexOf(Character.toString(blocks[i].toCharArray()[j]));
				}
				result += multiplicationMatrixDe(keyMatrix, block, blocks[i]);
			}
		}
		return result;
	}

	/*
	 * multiplication Matrix: key matrix and block matrix
	 */
	public String multiplicationMatrix(int[][] keyMatrix, int[] blockMatrix, String blockString) {
		String result = "";
		for (int i = 0; i < blockMatrix.length; i++) {
			int index = (keyMatrix[i][0] * blockMatrix[0]) + (keyMatrix[i][1] * blockMatrix[1]);
			int mod = Math.abs(index % 26);
			String charCurrent = Character.toString(blockString.toCharArray()[i]);
			result += super.moveSingleCharEncrypt(mod, charCurrent);
		}
		return result;
	}

	public String multiplicationMatrixDe(int[][] keyMatrix, int[] blockMatrix, String blockString) {
		String result = "";
		for (int i = 0; i < blockMatrix.length; i++) {
			int index = ((keyMatrix[i][0] * blockMatrix[0]) + (keyMatrix[i][1] * blockMatrix[1]))%26;
			int mod = Math.abs(index % 26);
			String charCurrent = Character.toString(blockString.toCharArray()[i]);
			result += super.moveSingleCharDecrypt(mod, charCurrent);
		}
		return result;
	}

	// caculate matrix determinant
	public int matrixDeterminant(int[][] key) {
		return (key[0][0] * key[1][1]) - (key[0][1] * key[1][0]);
	}

	// convert to Matrix Transposition (matrix 2*2)
	public int[][] convert2MatrixTransposition(int[][] key) {
		int temp = key[0][1];// temp value in position row 1 column 1
		key[0][1] = key[1][0];// assign new value for key[0][1] from key[1][1]
		key[1][0] = temp;// assign new value for key[01][1] from temp
		return key;
	}

	// convert to auxiliary matrix from matrix transposition
	public int[][] convert2AuxiliaryMatrix(int[][] matrixPostion) {
		int temp = matrixPostion[0][0];
		matrixPostion[0][0] = matrixPostion[1][1];
		matrixPostion[1][1] = temp;
		temp = matrixPostion[1][0] * -1;
		matrixPostion[1][0] = matrixPostion[0][1] * -1;
		matrixPostion[0][1] = temp;
		return matrixPostion;
	}

	// fint modulo target
	public int findModulo(int a, int m) {
		for (int i = 1; i < m; i++) {
			if ((a % m) * (i % m) % m == 1) {
				return i;
			}
		}
		return 1;
	}

	public static void main(String[] args) {
		Hill hill = new Hill();
//		hill.encrypt("DHNONGLAM", "LIDW");
		System.out.println(hill.findModulo(441, 26));
	}
}
