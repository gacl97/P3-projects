import java.util.ArrayList;

public class Hourly extends Employee{

    private Exceptions exceptions = new Exceptions();
    private double hourSalary;
    private double totalSalary;
    private boolean timeCard;

    public Hourly (String name, String adress, int id, double hourSalary, String payMeth) {
        setName(name);
        setAddress(adress);
        setId(id);
        setPayMeth(payMeth);
        setHourSalary(hourSalary);
        setPaymentSche("Semanal");
        setPayWeek("Sexta-feira");
    }
    public Hourly () {}

    public void createEmployee (ArrayList<Employee> employees, int id) {


        Hourly newHourly = new Hourly();
        newHourly.setId(id);
        System.out.println("######## Adicionar Horista ########");
        System.out.println();
        System.out.println("Nome: ");
        String name = input.nextLine();
        while (name.equals("") || name.equals(" ")) {
            System.out.println("Campo nome não pode ser vazio.");
            name = input.nextLine();
        }
        newHourly.setName(name);
        System.out.println("Endereço:");
        String address = input.nextLine();
        newHourly.setAddress(address);
        System.out.println("Salário por hora:");
        newHourly.hourSalary = exceptions.doubleNumber();

        System.out.println("Método de pagamento:");
        System.out.println();
        System.out.println("[1] - Cheque por correios");
        System.out.println("[2] - Cheque em mãos");
        System.out.println("[3] - Depósito em conta bancária");
        String meth = input.nextLine();

        while (!meth.equals("1") && !meth.equals("2") && !meth.equals("3")) {
            System.out.println("Opção inválida, escolha novamente!");
            meth = input.nextLine();
        }

        if (meth.equals("1")) {

            newHourly.setPayMeth("Cheque por correios");

        } else if (meth.equals("2")) {

            newHourly.setPayMeth("Cheque em mãos");

        } else {

            newHourly.setPayMeth("Depósito em conta bancária");

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

    public boolean pointCard (ArrayList <Employee> employees) {

        int hourIn = 0;
        int minuteIn = 0;
        int hourOut = 0;
        int minuteOut = 0;
        System.out.println("ID do empregado a bater ponto: ");
        int employeeId = exceptions.intNumber();

        for (Employee e: employees) {

            if (e.getId() == employeeId && e instanceof Hourly) {

                if (((Hourly) e).isTimeCard()) {
                    System.out.println("Empregado já bateu ponto hoje.");
                    return false;
                }

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
                return true;
            }
        }
        System.out.println("Empregado não existe ou não é horista.");
        return false;
    }

    public void resetPointCard (ArrayList<Employee> employees) {

        for (Employee e: employees) {

            if (e instanceof Hourly) {
                ((Hourly) e).setTimeCard(false);
            }
        }
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
}
