
function checkValidString(input: string): boolean {
    this.OPEN_PARENTHESIS = '(';
    this.CLOSE_PARENTHESIS = ')';
    this.ASTERISK = '*';

    let reverseIteration = true;

    return hasValidBalanceParenthesis(input, !reverseIteration)
        && hasValidBalanceParenthesis(input, reverseIteration);
};

function hasValidBalanceParenthesis(input: string, reverseIteration: boolean): boolean {
    let startIndex = (!reverseIteration) ? 0 : input.length - 1;
    let end = (!reverseIteration) ? input.length : -1;

    let balance = 0;
    let countAsterisk = 0;
    let unitChange = (!reverseIteration) ? 1 : -1;

    for (let i = startIndex; i !== end; i += unitChange) {
        let current = input.charAt(i);
        balance += (current === this.OPEN_PARENTHESIS) ? unitChange : (current === this.CLOSE_PARENTHESIS) ? -unitChange : 0;
        countAsterisk += (current === this.ASTERISK) ? 1 : 0;

        if (balance < 0 && countAsterisk === 0) {
            return false;
        }
        if (balance < 0 && countAsterisk > 0) {
            --countAsterisk;
            ++balance;
        }
    }

    return balance >= 0 && balance <= countAsterisk;
}
