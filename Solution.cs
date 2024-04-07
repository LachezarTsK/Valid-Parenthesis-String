
using System;

public class Solution
{
    private static readonly char OPEN_PARENTHESIS = '(';
    private static readonly char CLOSE_PARENTHESIS = ')';
    private static readonly char ASTERISK = '*';

    public bool CheckValidString(string input)
    {
        bool reverseIteration = true;

        return HasValidBalanceParenthesis(input, !reverseIteration)
                && HasValidBalanceParenthesis(input, reverseIteration);
    }

    private bool HasValidBalanceParenthesis(String input, bool reverseIteration)
    {
        int startIndex = (!reverseIteration) ? 0 : input.Length - 1;
        int end = (!reverseIteration) ? input.Length : -1;

        int balance = 0;
        int countAsterisk = 0;
        int unitChange = (!reverseIteration) ? 1 : -1;

        for (int i = startIndex; i != end; i += unitChange)
        {
            char current = input[i];
            balance += (current == OPEN_PARENTHESIS) ? unitChange
                     : (current == CLOSE_PARENTHESIS) ? -unitChange
                     : 0;

            countAsterisk += (current == ASTERISK) ? 1 : 0;

            if (balance < 0 && countAsterisk == 0)
            {
                return false;
            }
            if (balance < 0 && countAsterisk > 0)
            {
                --countAsterisk;
                ++balance;
            }
        }

        return balance >= 0 && balance <= countAsterisk;
    }
}
