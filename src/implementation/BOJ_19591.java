package implementation;

// https://www.acmicpc.net/problem/19591

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class BOJ_19591 {

    private static final char PLUS = '+';

    private static final char MINUS = '-';

    private static final char MULTIPLE = '*';

    private static final char DIVIDE = '/';

    private static final int FIRST = -1;

    private static final int SAME = 0;

    private static final int LAST = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] sequence = br.readLine()
                .toCharArray();

        Deque<Character> sequenceDeque = new ArrayDeque<>();
        Deque<Character> operands = new ArrayDeque<>();
        Deque<Long> nums = new ArrayDeque<>();

        for (char character: sequence) {
            sequenceDeque.offerLast(character);
        }

        offerFirstNegativeNum(sequenceDeque, nums);

        offerAllSequence(sequenceDeque, operands, nums);

        long num = calculateAllExpressions(operands, nums);

        System.out.println(num);
    }

    private static long calculateAllExpressions(Deque<Character> operands, Deque<Long> nums) {
        while (!operands.isEmpty()) {
            int nextPriority = getNextPriority(operands);
            if (nextPriority == FIRST) {
                long value = calculateFirstExpression(operands, nums);
                calculateFirst(operands, nums, value);
            }
            else if (nextPriority == LAST) {
                long value = calculateLastExpression(operands, nums);
                calculateLast(operands, nums, value);
            }
            else {
                long firstValue = calculateFirstExpression(operands, nums);
                long lastValue = calculateLastExpression(operands, nums);
                calculate(operands, nums, firstValue, lastValue);
            }
        }

        return nums.getFirst();
    }

    private static void calculate(Deque<Character> operands, Deque<Long> nums, long firstValue, long lastValue) {
        if (firstValue >= lastValue) {
            calculateFirst(operands, nums, firstValue);
        }
        else {
            calculateLast(operands, nums, lastValue);
        }
    }

    private static void calculateLast(Deque<Character> operands, Deque<Long> nums, long lastValue) {
        pollLast(operands, nums);
        nums.offerLast(lastValue);
    }

    private static void calculateFirst(Deque<Character> operands, Deque<Long> nums, long value) {
        pollFirst(operands, nums);
        nums.offerFirst(value);
    }

    private static void pollLast(Deque<Character> operands, Deque<Long> nums) {
        nums.pollLast();
        nums.pollLast();
        operands.pollLast();
    }

    private static void pollFirst(Deque<Character> operands, Deque<Long> nums) {
        nums.pollFirst();
        nums.pollFirst();
        operands.pollFirst();
    }

    private static void offerAllSequence(Deque<Character> sequenceDeque, Deque<Character> operands, Deque<Long> nums) {
        while (!sequenceDeque.isEmpty()) {
            char value = sequenceDeque.getFirst();
            if (isOperand(value)) {
                sequenceDeque.pollFirst();
                operands.offerLast(value);
            }
            else {
                Deque<Character> temp = new ArrayDeque<>();
                offerNum(sequenceDeque, nums, temp);
            }
        }
    }

    private static void offerFirstNegativeNum(Deque<Character> sequenceDeque, Deque<Long> nums) {
        if (sequenceDeque.getFirst() == MINUS) {
            ArrayDeque<Character> temp = new ArrayDeque<>();

            temp.offerLast(sequenceDeque.pollFirst());

            offerNum(sequenceDeque, nums, temp);
        }
    }

    private static void offerNum(Deque<Character> sequenceDeque, Deque<Long> nums, Deque<Character> temp) {
        while (!sequenceDeque.isEmpty() && !isOperand(sequenceDeque.getFirst())) {
            char value = sequenceDeque.pollFirst();
            temp.offerLast(value);
        }

        StringBuilder sb = new StringBuilder();

        while (!temp.isEmpty()) {
            sb.append(temp.pollFirst());
        }

        long num = Long.parseLong(sb.toString());

        nums.offerLast(num);
    }

    private static boolean isOperand(char character) {
        if (character == PLUS) {
            return true;
        }
        if (character == MINUS) {
            return true;
        }
        if (character == MULTIPLE) {
            return true;
        }
        if (character == DIVIDE) {
            return true;
        }
        return false;
    }

    private static long calculate(long firstNum, long secondNum, char operand) {
        if (operand == PLUS) {
            return firstNum + secondNum;
        }
        if (operand == MINUS) {
            return firstNum - secondNum;
        }
        if (operand == MULTIPLE) {
            return firstNum * secondNum;
        }
        if (operand == DIVIDE) {
            return firstNum / secondNum;
        }
        return 0;
    }

    private static int getNextPriority(Deque<Character> operands) {
        char firstOperand = operands.getFirst();
        char lastOperand = operands.getLast();

        int firstPriority = getPriority(firstOperand);
        int lastPriority = getPriority(lastOperand);

        if (firstPriority == lastPriority) {
            return SAME;
        }
        if (firstPriority < lastPriority) {
            return FIRST;
        }
        return LAST;
    }

    private static int getPriority(char operand) {
        if (operand == PLUS || operand == MINUS) {
            return 1;
        }
        return 0;
    }

    private static long calculateFirstExpression(Deque<Character> operands, Deque<Long> nums) {
        long firstNum = nums.pollFirst();
        long secondNum = nums.pollFirst();
        char operand = operands.pollFirst();

        long value = calculate(firstNum, secondNum, operand);

        nums.offerFirst(secondNum);
        nums.offerFirst(firstNum);
        operands.offerFirst(operand);

        return value;
    }

    private static long calculateLastExpression(Deque<Character> operands, Deque<Long> nums) {
        long secondNum = nums.pollLast();
        long firstNum = nums.pollLast();
        char operand = operands.pollLast();

        long value = calculate(firstNum, secondNum, operand);

        nums.offerLast(firstNum);
        nums.offerLast(secondNum);
        operands.offerLast(operand);

        return value;
    }
}
