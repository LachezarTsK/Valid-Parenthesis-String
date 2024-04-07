
class Solution {

    companion object {
        const val OPEN_PARENTHESIS: Char = '(';
        const val CLOSE_PARENTHESIS: Char = ')';
        const val ASTERISK: Char = '*';
    }

    fun checkValidString(input: String): Boolean {

        val reverseIteration: Boolean = true;

        return hasValidBalanceParenthesis(input, !reverseIteration)
                && hasValidBalanceParenthesis(input, reverseIteration);
    }

    private fun hasValidBalanceParenthesis(input: String, reverseIteration: Boolean): Boolean {
        val range = if (!reverseIteration) input.indices else input.indices.reversed();

        var balance = 0;
        var countAsterisk = 0;
        val unitChange = if (!reverseIteration) 1 else -1;

        for (i in range) {
            val current: Char = input[i];
            balance += when (current) {
                OPEN_PARENTHESIS -> unitChange
                CLOSE_PARENTHESIS -> -unitChange
                else -> 0
            }

            countAsterisk += if (current == ASTERISK) 1 else 0;

            if (balance < 0 && countAsterisk == 0) {
                return false;
            }
            if (balance < 0 && countAsterisk > 0) {
                --countAsterisk;
                ++balance;
            }
        }

        return balance in 0..countAsterisk;
    }
}
