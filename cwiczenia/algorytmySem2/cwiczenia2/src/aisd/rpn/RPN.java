package aisd.rpn;

import aisd.queue.ArrayQueue;
import aisd.queue.EmptyQueueException;
import aisd.queue.FullQueueException;
import aisd.queue.IQueue;
import aisd.stack.ArrayStack;
import aisd.stack.EmptyStackException;
import aisd.stack.FullStackException;
import aisd.stack.IStack;

//reversed polish notation
public class RPN {
    public static double result(String eq) throws EmptyQueueException, FullQueueException {
        IQueue<Object> equasion = MyAnalyzer.analize(eq); // like (2+2)
        Double[] value = new Double[2];
        value[0] = null;
        value[1] = null;
        boolean isSimplified = false;
        while (equasion.size() != 1) {
            IQueue<Object> simplified = new ArrayQueue<>(equasion.size());
            isSimplified = false;
            while (!equasion.isEmpty()) {
                Object mathInput = equasion.dequeue();
                if (!isSimplified) {
                    if (mathInput instanceof Double k) {
                        if (value[1] != null) {
                            simplified.enqueue(value[1]);
                        }
                        Double temp = value[0];
                        value[0] = k;
                        value[1] = temp;

                    } else {
                        if (mathInput.toString().equals("+")) {
                            simplified.enqueue(value[1] + value[0]);
                        } else if (mathInput.toString().equals("-")) {
                            simplified.enqueue(value[1] - value[0]);
                        } else if (mathInput.toString().equals("*")) {
                            simplified.enqueue(value[1] * value[0]);
                        } else if (mathInput.toString().equals("/")) {
                            simplified.enqueue(value[1] / value[0]);
                        }
                        isSimplified = true;
                        value[0] = null;
                        value[1] = null;
                    }
                } else {
                    simplified.enqueue(mathInput);
                }

            }
            equasion = simplified;
        }
        return (double) equasion.dequeue();
    }

    public static double betterSolver(String eq) throws EmptyQueueException, FullStackException, EmptyStackException {
        IQueue<Object> equasion = MyAnalyzer.analize(eq); // like (2+2)
        IStack<Double> stack = new ArrayStack<>(equasion.size());
        while (!equasion.isEmpty()) {
            Object mathInput = equasion.dequeue();
            if (mathInput instanceof Double k) {
                stack.push(k);
            } else {
                Double[] value = new Double [2];
                value[0] = stack.pop();
                value[1] = stack.pop();
                if (mathInput.toString().equals("+")) {
                    stack.push(value[1] + value[0]);
                } else if (mathInput.toString().equals("-")) {
                    stack.push(value[1] - value[0]);
                } else if (mathInput.toString().equals("*")) {
                    stack.push(value[1] * value[0]);
                } else if (mathInput.toString().equals("/")) {
                    stack.push(value[1] / value[0]);
                }
            }

        }
        return (double) stack.pop();
    }

    public static void main(String[] args) throws Exception {
        String inputStr = "(2+4)*(3.7-9/3)";
        System.out.println(betterSolver(inputStr));
        // String input2 = "(2+2)";
        // System.out.println(result(input2));
    }
}
