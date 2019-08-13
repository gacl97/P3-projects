import Employee1.Commissioned;
import Employee1.Hourly;
import Exceptions1.Exceptions;

public class WorkFunction {

    private Exceptions exceptions = new Exceptions();

    public boolean pointCard (Hourly employee) {

        if (employee.isTimeCard()) {
            System.out.println("Empregado já bateu ponto hoje.");
            return false;
        }
        int hourIn = 0;
        int minuteIn = 0;
        int hourOut = 0;
        int minuteOut = 0;
        double totalTime = 0;

        System.out.println("Informe a hora em formato 24 hrs que o empregado entrou :  ");
        hourIn = exceptions.intNumber();
        while (hourIn > 23) {
            System.out.println("Formato invalido.");
            hourIn = exceptions.intNumber();
        }
        System.out.println("Informe os minutos em que o empregado entrou: ");
        minuteIn = exceptions.intNumber();
        while (minuteIn > 59) {
            System.out.println("Formato invalido.");
            minuteIn = exceptions.intNumber();
        }
        System.out.println("Informe a hora em formato 24 hrs em que o empregado saiu: ");
        hourOut = exceptions.intNumber();
        while (hourOut > 23) {
            System.out.println("Formato invalido.");
            hourOut = exceptions.intNumber();
        }
        System.out.println("Informe os minutos em que o empregado saiu: ");
        minuteOut = exceptions.intNumber();
        while (minuteOut > 59) {
            System.out.println("Formato invalido.");
            minuteOut = exceptions.intNumber();
        }

        if (hourOut < hourIn) {
            int aux = Math.abs(hourIn-24);
            int totalHours = (aux + hourOut) * 60;
            totalTime = Math.abs(minuteIn - minuteOut);
            totalTime += totalHours;
        } else {
            totalTime = Math.abs((hourOut * 60 + minuteOut) - (hourIn * 60 + minuteIn));
        }

        double extraHour = 0;
        if (totalTime > 480) {
            extraHour = totalTime - 480;
            totalTime -= extraHour;
        }
        double hourSalary = employee.getSalary();
        System.out.println("##########################################");
        System.out.println();
        System.out.println("Salário por hora: " + hourSalary);
        System.out.println("Horas extras: " + extraHour);
        System.out.println("Horas totais: " + totalTime);
        totalTime = totalTime/60;
        extraHour = extraHour/60;
        double total = (hourSalary * totalTime) + (extraHour * hourSalary * 1.5);
        System.out.println("Total no dia: " + total);
        double currentSalary = employee.getTotalSalary();
        currentSalary += total;
        employee.setTotalSalary(currentSalary);
        employee.setTimeCard(true);
        System.out.println("Salario total atual: " + employee.getTotalSalary());
        System.out.println();
        System.out.println("##########################################");
        System.out.println();
        System.out.println("Empregado bateu ponto com sucesso!!");
        return true;
    }

     boolean salesResults (Commissioned employee) {

        System.out.println("Informe o valor da venda: ");
        double saleValue = exceptions.doubleNumber();
        double currentSales = employee.getSalesResult();
        currentSales = saleValue * employee.getAmountCommission();
        employee.setSalesResult(currentSales);
        System.out.println("######################################");
        System.out.println();
        System.out.println("Valor total da venda: " + saleValue);
        System.out.println("Total ganho com a comissão: " + saleValue * employee.getAmountCommission());
        System.out.println("Total atual: " + employee.getSalesResult());
        System.out.println("Resultado de venda confirmado.");
        System.out.println();
        System.out.println("######################################");
        return true;
    }
}
