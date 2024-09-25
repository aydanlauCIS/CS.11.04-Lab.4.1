public class Main {

    // 1. parenthesesCheck
    public static boolean parenthesesCheck(String parentheses) {
        int valid = 0;
        for (int i = 0; i < parentheses.length(); i++) {
            if (parentheses.charAt(i) == '(') {
                valid += 1;
            } else {
                valid -= 1;
            }
        }

        return valid == 0;
    }

    // 2. reverseInteger
    public static String reverseInteger(int x) {
        String x_str = Integer.toString(x);
        StringBuilder ans = new StringBuilder();
        for (int i = x_str.length() - 1; i >= 0; i--) {
            ans.append(x_str.charAt(i));
        }

        return ans.toString();
    }

    // 3. encryptThis
    public static String encryptThis(String message) {
        StringBuilder ans = new StringBuilder();
        int word_start = 0;
        String ascii;
        int offset = 0;
        char temp;

        for (int i = 0; i <= message.length(); i++) {
            if (i == message.length() || message.charAt(i) == ' ') {
                // Swap 2nd and last letters in word
                temp = message.charAt(word_start - offset + 1);
                ans.replace(word_start + 1, word_start + 2, Character.toString(message.charAt(i - 1)));
                ans.replace(i + offset - 1, i + offset, Character.toString(temp));

                // Replace first char with ascii (do this last to not mess up indexes)
                ascii = Integer.toString(message.charAt(word_start - offset));
                ans.replace(word_start, word_start + 1, ascii);
                offset += (ascii.length() - 1);

                word_start = i + offset + 1; // account for space character & ascii number length
            }

            if (i < message.length()) ans.append(message.charAt(i));
        }

        return ans.toString();
    }

    // 4. decipherThis
    public static String decipherThis(String cipher) {
        StringBuilder ans = new StringBuilder();
        StringBuilder ascii = new StringBuilder();
        int word_len = 0;
        char last = 'a';
        int offset = 0;
        int second;

        for (int i = 0; i <= cipher.length(); i++) {
            if (i == cipher.length() || cipher.charAt(i) == ' ') {
                second = i - offset - word_len;
                ans.replace(second, second + 1, Character.toString(cipher.charAt(i - 1)));
                ans.replace(i - offset - 1, i - offset, Character.toString(last));

                word_len = -1;
            }

            if (word_len == 0) {
                if (Character.isDigit(cipher.charAt(i))) {
                    ascii.append(cipher.charAt(i));
                    offset++;
                } else {
                    offset--;
                    ans.append((char) Integer.valueOf(ascii.toString()).intValue());
                    ascii.setLength(0);
                    word_len++;
                    last = cipher.charAt(i);
                    ans.append(cipher.charAt(i));
                }
            } else if (i < cipher.length()) {
                ans.append(cipher.charAt(i));
                word_len++;
            }
        }

        return ans.toString();
    }
}