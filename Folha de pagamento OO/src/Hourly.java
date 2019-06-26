import javax.print.DocFlavor;
import java.util.ArrayList;

public class Hourly extends Employee{

    private double hourSalary;
    private double totalSalary;
    private boolean timeCard;

    public Hourly (String name, String adress, int id, double hourSalary, String payMeth) {
        setName(name);
        setAdress(adress);
        setId(id);
        setPayMeth(payMeth);
        setHourSalary(hourSalary);
        setPaymentSche("Semanal");
        setPayWeek("Sexta-feira");
    }
    public Hourly () {

    }

    public double getHourSalary() {
        return hourSalary;
    }

    public void setHourSalary(double hourSalary) {
        this.hourSalary = hourSalary;
    }

    public boolean isTimeCard() {
        return timeCard;
    }

    public void setTimeCard(boolean timeCard) {
        this.timeCard = timeCard;
    }

    public double getTotalSalary() {
        return totalSalary;
    }

    public void setTotalSalary(double totalSalary) {
        this.totalSalary = totalSalary;
    }

    public void createEmployee (ArrayList<Employee> employees, int id) {

        Hourly newHourly = new Hourly();
        newHourly.setId(id);
        System.out.println("######## Adicionar Horista ########");
        System.out.println();
        System.out.println("Nome: ");
        String name = input.nextLine();
        newHourly.setName(name);
        System.out.println("Endereço:");
        String adress = input.nextLine();
        newHourly.setAdress(adress);
        System.out.println("Salário por hora:");
        boolean flag = true;
        while (flag) {

            try {

                newHourly.hourSalary = Double.parseDouble(input.nextLine());
                if (newHourly.hourSalary > 0) {
                    flag = false;
                } else {
                    System.out.println("Salário deve ser positivo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Apenas números.");
            }
        }

        System.out.println("Informe o método de pagamento: [Cheque por correios, Cheque em mãos, Depósito em conta bancaria]");
        String meth = input.nextLine();

        while (true) {

            if (meth.equals("Cheque por correios") || meth.equals("Cheque em mãos") || meth.equals("Depósito em conta bancaria")) {
                newHourly.setPayMeth(meth);
                break;
            }
            System.out.println("Opção invalida, escolha novamente!!");
            meth = input.nextLine();
        }
        System.out.println("Pertence a um sindicato: [S/N]");

        String syndicate = input.nextLine();

        while (!syndicate.equals("S") && !syndicate.equals("N")) {

            System.out.println("Opção invalida, escolha novamente!!");
            syndicate = input.nextLine();
        }
        newHourly.setPayWeek("Sexta-feira");
        newHourly.setPaymentSche("Semanal");
        newHourly.setSyndicate(syndicate,employees);
        employees.add(newHourly);
    }

    public void pointCard (ArrayList <Employee> employees) {

        int hourIn = 0;
        int minuteIn = 0;
        int hourOut = 0;
        int minuteOut = 0;
        System.out.println("ID do empregado a bater ponto: ");
        int employeeId = 0;
        boolean flag = true;
        while (flag) {

            try {

                employeeId = Integer.parseInt(input.nextLine());
                if (employeeId > 0) {
                    flag = false;
                } else {
                    System.out.println("Id deve ser positivo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Apenas números.");
            }
        }

        for (Employee e: employees) {

            if (e.getId() == employeeId && e instanceof Hourly) {

                if (((Hourly) e).isTimeCard()) {
                    System.out.println("Empregado já bateu ponto hoje.");
                    return;
                }
                double totalTime = 0;

                while (totalTime < 480) {
                    boolean flag1 = true;
                    while (flag1) {

                        try {
                            System.out.println("Informe a hora em formato 24 hrs que o empregado entrou :  ");
                            hourIn = Integer.parseInt(input.nextLine());
                            System.out.println("Informe os minutos em que o empregado entrou: ");
                            minuteIn = Integer.parseInt(input.nextLine());
                            System.out.println("Informe a hora em formato 24 hrs em que o empregado saiu: ");
                            hourOut = Integer.parseInt(input.nextLine());
                            System.out.println("Informe os minutos em que o empregado saiu: ");
                            minuteOut = Integer.parseInt(input.nextLine());


                            if ((hourIn > 0 && minuteIn > 0 && hourOut > 0 && minuteOut > 0)  && (hourIn < 24 && minuteIn < 60 && hourOut < 24 && minuteOut < 60)) {
                                flag1 = false;
                            } else {
                                System.out.print("Horario não está dentro dos padrões, ou ");
                                System.out.println("empregado não cumpriu a quantidade minima de 8 hrs de trabalho!! Informe novamente");
                            }
                        } catch (NumberFormatException ee) {
                            System.out.println("Apenas números.");
                        }
                    }

                    totalTime = Math.abs((hourOut * 60 + minuteOut) - (hourIn * 60 + minuteIn));
                }

                double extraHour = 0;
                if (totalTime > 480) {
                    extraHour = totalTime - 480;
                    totalTime -= extraHour;
                }
                double hourSalary = ((Hourly) e).getHourSalary();
                System.out.println("##########################################");
                System.out.println();
                System.out.println("Salário por hora: " + hourSalary);
                System.out.println("Horas extras: " + extraHour);
                System.out.println("Horas totais: " + totalTime);
                totalTime = totalTime/60;
                extraHour = extraHour/60;
                double total = (hourSalary * totalTime) + (extraHour * hourSalary * 1.5);
                System.out.println("Total no dia: " + total);
                double currentSalary = ((Hourly) e).getTotalSalary();
                ((Hourly) e).setTotalSalary(currentSalary+total);
                ((Hourly) e).setTimeCard(true);
                System.out.println("Salario total atual: " + ((Hourly) e).getTotalSalary());
                System.out.println();
                System.out.println("##########################################");
                System.out.println();
                System.out.println("Empregado bateu ponto com sucesso!!");
                return;
            }
        }
        System.out.println("Empregado não existe ou não é horista.");
    }

    public void resetPointCard (ArrayList<Employee> employees) {

        for (Employee e: employees) {

            if (e instanceof Hourly) {
                ((Hourly) e).setTimeCard(false);
            }
        }
    }
}
