package app;

import java.util.*;

public class Main {

    // Check the priority
    public static int getPriority(String string) {
         if (string.equals("*") || string.equals("/") || string.equals("%"))
            return 2;
         else  if (string.equals("+") || string.equals("-"))
            return 1;
         else
            return 0 ;
    }

    public static void main(String[] args) {
        // "(2+3)*5"
        System.out.print("Enter the expression: ");
        Scanner scanner  = new Scanner(System.in);

        String bieuthuc  = scanner.nextLine();

        Stack<String>      stack = new Stack();
        LinkedList<String> queue = new LinkedList();

        for (int i = 0; i < bieuthuc.length(); i++){
            String item = String.valueOf(bieuthuc.charAt(i));
            if (item.equals("(")){
                stack.push(item);
            }else if(item.equals(")")){
                boolean loop = true;
                while (loop){
                    String check = stack.pop();
                    if (check.equals("(")){
                        loop = false;
                    }else{
                        queue.addLast(check);
                    }
                }
            }else{
                int checkPriorityNew = getPriority(item);
                if (checkPriorityNew == 0){
                    queue.addLast(item);
                }else{
                    if (stack.size() > 0){
                        int len = stack.size()-1;
                        int checkPriorityOld = getPriority(stack.get(len));

                        if(checkPriorityOld >= checkPriorityNew){
                            queue.addLast(stack.pop());
                        }
                    }
                    stack.push(item);
                }
            }
        }

        while (stack.size() > 0){
            queue.addLast(stack.pop());
        }



        // Count
        while(queue.size() > 0){
            String item = queue.pop();
            int checkPriority = getPriority(item);
            if (checkPriority == 0){
                stack.push(item);
            }else{
                int first  = Integer.valueOf(stack.pop());
                int second = Integer.valueOf(stack.pop());
                int resultInt;
                String result;
                switch (item){
                    case "+":
                        resultInt = second + first;
                        stack.push(String.valueOf(resultInt));
                        break;

                    case "-":
                        resultInt = second - first;
                        stack.push(String.valueOf(resultInt));
                        break;

                    case "*":
                        resultInt = second * first;
                        stack.push(String.valueOf(resultInt));
                        break;

                    case "/":
                        resultInt = second / first;
                        stack.push(String.valueOf(resultInt));
                        break;

                    case "%":
                        resultInt = second % first;
                        stack.push(String.valueOf(resultInt));
                        break;
                }
            }
        }

        System.out.println("Result: " + stack.pop());
    }
}
