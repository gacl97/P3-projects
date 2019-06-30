import java.util.Scanner;

public class Exceptions {

    private Scanner input = new Scanner(System.in);

    public double doubleNumber () {

        double number = 0;
        boolean flag = true;
        while (flag) {

            try {

                number = Double.parseDouble(input.nextLine());
                if (number > 0) {
                    flag = false;
                } else {
                    System.out.println("Numero deve ser maior que 0.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Apenas números, informe novamente.");
            }
        }
        return number;
    }

    public int intNumber () {

        int number = 0;
        boolean flag = true;
        while (flag) {

            try {

                number = Integer.parseInt(input.nextLine());
                if (number < 0) {
                    System.out.println("Numero deve ser positivo.");
                } else {

                    flag = false;
                }
            } catch (NumberFormatException e) {
                System.out.println("Apenas números.");
            }
        }
        return number;
    }

    public int intNumber (String num) {

        int number = 0;
        boolean flag = true;
        while (flag) {

            try {

                number = Integer.parseInt(num);
                if (number > 0) {
                    flag = false;
                    continue;
                } else {
                    System.out.println("Número deve ser positivo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Apenas números.");
            }
            num = input.nextLine();
        }
        return number;
    }
}
