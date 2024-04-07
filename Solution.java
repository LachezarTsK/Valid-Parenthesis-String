
public class Solution {

    private static final char OPEN_PARENTHESIS = '(';
    private static final char CLOSE_PARENTHESIS = ')';
    private static final char ASTERISK = '*';

    public boolean checkValidString(String input) {

        boolean reverseIteration = true;

        return hasValidBalanceParenthesis(input, !reverseIteration)
                && hasValidBalanceParenthesis(input, reverseIteration);
    }

    private boolean hasValidBalanceParenthesis(String input, boolean reverseIteration) {
        int startIndex = (!reverseIteration) ? 0 : input.length() - 1;
        int end = (!reverseIteration) ? input.length() : -1;

        int balance = 0;
        int countAsterisk = 0;
        int unitChange = (!reverseIteration) ? 1 : -1;

        for (int i = startIndex; i != end; i += unitChange) {
            char current = input.charAt(i);
            balance += (current == OPEN_PARENTHESIS) ? unitChange : (current == CLOSE_PARENTHESIS) ? -unitChange : 0;
            countAsterisk += (current == ASTERISK) ? 1 : 0;

            if (balance < 0 && countAsterisk == 0) {
                return false;
            }
            if (balance < 0 && countAsterisk > 0) {
                --countAsterisk;
                ++balance;
            }
        }

        return balance >= 0 && balance <= countAsterisk;
    }
}
