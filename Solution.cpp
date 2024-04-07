
#include <string>
#include <string_view>
using namespace std;

class Solution {

    static const char OPEN_PARENTHESIS = '(';
    static const char CLOSE_PARENTHESIS = ')';
    static const char ASTERISK = '*';

public:
    bool checkValidString(const string& input) const {

            bool reverseIteration = true;

            return hasValidBalanceParenthesis(input, !reverseIteration)
                    && hasValidBalanceParenthesis(input, reverseIteration);
    }

private:
    bool hasValidBalanceParenthesis(string_view input, bool reverseIteration) const {
        size_t startIndex = (!reverseIteration) ? 0 : input.length() - 1;
        size_t end = (!reverseIteration) ? input.length() : variant_npos;

        int balance = 0;
        int countAsterisk = 0;
        int unitChange = (!reverseIteration) ? 1 : -1;

        for (size_t i = startIndex; i != end; i += unitChange) {
            char current = input[i];
            balance += (current == OPEN_PARENTHESIS) ? unitChange
                     : (current == CLOSE_PARENTHESIS) ? -unitChange
                     : 0;

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
};
