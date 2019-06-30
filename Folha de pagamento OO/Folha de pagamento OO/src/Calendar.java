import java.util.Scanner;
import java.util.ArrayList;

public class Calendar {

    private Exceptions exceptions = new Exceptions();
    private Scanner input = new Scanner(System.in);
    private int []daysMonth = {31,28,31,30,31,30,31,31,30,31,30,31};
    private String []weeksName = {"Segunda-feira","Terça-feira","Quarta-feira","Quinta-feira","Sexta-feira","Sábado","Domingo"};
    private String []months = {"Janeiro","Fevereiro","Março","Abril","Maio","Junho","Julho","Agosto","Setembro","Outubro","Novembro","Dezembro"};
    private int currentDay = 1, currentMonth = 0, currentWeek = 1, currentDayAux = 1, year = 2019;

    private ArrayList <Integer> payDay = new ArrayList<Integer>() {
        {
            add(31);
            add(0);
            add(0);
        }
    };
    private ArrayList <String> payWeek = new ArrayList<String>(){
        {
            add("Invalido");
            add("Sexta-feira");
            add("Sexta-feira");
        }
    };
    private ArrayList <String> paySche = new ArrayList<String>(){
        {
            add("Mensal");
            add("Semanal");
            add("Bi-semanal");
        }
    };


    /*Função para passar o dia*/
    public void toSpendDay () {

        this.currentDay += 1;
        this.currentDayAux += 1;

        if (this.currentDay > this.daysMonth[this.currentMonth]) {
            this.currentDay = 1;
            this.currentMonth += 1;
        }
        if (this.currentDay % 7 == 0){ this.currentWeek += 1;}
        if (this.currentWeek == 5) {this.currentWeek = 1;}
        if (this.currentMonth > 11) {this.currentMonth = 0; this.year += 1;}
    }

    /*Mostrar as informações do dia atual*/
    public void showCurrentInfos () {

        System.out.println("-----------------------------");
        System.out.println(this.currentDay+"/"+(this.currentMonth+1) +"/"+this.year);
        System.out.println("Dia da semana: " + this.weeksName[(this.currentDayAux-1)%7]);
        System.out.println("Mês: " + this.months[this.currentMonth % 12]);
        System.out.println("Semana: " + this.currentWeek);
        System.out.println("-----------------------------");
        System.out.println();
    }


    public void listPaymentSchedule () {

        System.out.println("######### Formas de pagamento #########");
        System.out.println();
        for (int i = 0; i < payDay.size(); i++) {

            System.out.print("["+ i + "] ");
            if (paySche.get(i).equals("Semanal") || paySche.get(i).equals("Bi-semanal")) {
                System.out.println("Forma de pagamento: " + paySche.get(i));
                System.out.println("Dia da semana: " + payWeek.get(i));
            } else if (paySche.get(i).equals("Mensal")) {
                System.out.println("Forma de pagamento: " + paySche.get(i));
                System.out.println("Dia do mês: " + payDay.get(i));
            }
            System.out.println();
        }

        System.out.println("----------------------------------------");
    }

    public boolean changePaymentSchedule (ArrayList <Employee> employees, int id) {

        for (Employee e: employees) {

            if (e.getId() == id) {

                listPaymentSchedule();
                int op2 = exceptions.intNumber();

                while (op2 > getPayDay().size()) {

                    System.out.println("Não existe essa opção, escolha novamente.");
                    op2 = exceptions.intNumber();

                }
                e.setPayDay(getPayDay().get(op2));
                e.setPaymentSche(getPaySche().get(op2));
                e.setPayWeek(getPayWeek().get(op2));
                System.out.println("Agenda alterada.");
                return true;
            }
        }
        System.out.println("Não existe um empregado cadastrado com essa ID.");
        return false;
    }

    /*Função para verificar se já existe criado a agenda de pagamento*/
    public boolean checkIfBelongs (int day, String week, String Schedule) {

        for (int i = 0; i < payDay.size(); i++) {

            if (payDay.get(i) == day && paySche.get(i).equals(Schedule) && payWeek.get(i).equals(week)) {

                return false;
            }
        }
        return true;
    }
    /*Função para criar agenda de pagamento*/
    public void createPaymentSchedule () {

        System.out.println("Informe a nova forma de pagamento na seguinte formato: ");
        System.out.println();
        System.out.println("Se mensal digite: ['Mensal' dia escolhido ] ex: Mensal 7");
        System.out.println();
        System.out.println("Se semanal digite: ['Semanal' (1- 'Semanal', 2- 'Bi-semanal'), dia da semana] ex: [Semanal 1 Sexta-feira]");
        System.out.println();
        System.out.println("Formato do dia da semana: [Segunda-feira, Terça-feira, Quarta-feira, Quinta-feira, Sexta-feira]");

        while (true) {
            boolean flag = false;
            String opt = input.nextLine();
            String []aux = opt.split(" ");

            if (aux[0].equals("Mensal") && aux.length == 2) {

                int day = exceptions.intNumber(aux[1]);

                while (day == 0 || day > 31) {

                    System.out.println("Dia invalido, escolha um dia entre 1 a 31.");
                    day = exceptions.intNumber();

                }

                if(checkIfBelongs(day,"Invalido","Mensal")) {

                    this.payDay.add(day);
                    this.payWeek.add("Invalido");
                    this.paySche.add("Mensal");

                } else {

                    System.out.println("Já existe essa forma de pagamento.");
                    return;

                }
                flag = true;

            } else if (aux[0].equals("Semanal") && aux.length == 3){

                while (!aux[1].equals("1") && !aux[1].equals("2")) {

                    System.out.println("Formato invalido, escolha novamente: [1 ou 2]");
                    aux[1] = input.nextLine();
                }
                String aux1;

                while (true) {

                    if (aux[2].equals("Segunda-feira") || aux[2].equals("Terça-feira") || aux[2].equals("Quarta-feira")
                            || aux[2].equals("Quinta-feira") || aux[2].equals("Sexta-feira")) {

                        this.payWeek.add(aux[2]);
                        break;
                    }
                    System.out.println("Dia invalido, escolha novamente no seguinte formato: ");
                    System.out.println("[Segunda-feira, Terça-feira, Quarta-feira, Quinta-feira, Sexta-feira]");

                    aux[2] = input.nextLine();
                }

                if (aux[1].equals("1")) {

                    aux1 = "Semanal";

                } else {

                    aux1 = "Bi-semanal";

                }
                if (checkIfBelongs(0,aux[2],aux1)) {

                    this.paySche.add(aux1);
                    this.payWeek.add(aux[2]);
                    this.payDay.add(0);

                } else {

                    System.out.println("Já existe essa forma de pagamento.");
                    return;

                }
                flag = true;
            }

            if (flag) {

                System.out.println("Agenda criado com sucesso");
                return;

            }
            System.out.println("Formato invalido!!");
        }

    }

    /*Gerar folha de pagamento*/
    public static void generatePayment (Employee e, double salary, double serviceCharges, double syndicalRates, double totalRates) {

        System.out.println("############### Contra-cheque ###############");
        System.out.println();
        System.out.println("ID: " + e.getId());
        System.out.println("Nome: " + e.getName());
        System.out.println("Forma de pagamento: " + e.getPayMeth());

        if (e.getPaymentSche().equals("Mensal")) {

            System.out.println("Dia do pagamento: " + e.getPayDay());

        } else {

            System.out.println("Dia da semana do pagamento: " + e.getPayWeek());

        }
        if (e instanceof Commissioned) {

            System.out.println("Total de comissão: " + ((Commissioned) e).getSalesResult());

        }

        System.out.println("Forma de pagamento: " + e.getPaymentSche());
        System.out.println("Salario bruto: " + salary);
        salary -= totalRates;
        System.out.println("Taxas de serviços: " + serviceCharges);
        System.out.println("Taxa do sindicato: " + syndicalRates);
        System.out.println("Taxas totais: " + totalRates);
        System.out.println("Salario liquido: " + salary);
        System.out.println();
        System.out.println("#############################################");
        System.out.println();
    }

    /*Zerar dados de pagamento*/
    public void reset (Employee e) {

        if (e.getSyndicate() != null) {

            e.getSyndicate().setServiceCharges(0);

        }
        if (e instanceof Hourly) {

            ((Hourly) e).setTotalSalary(0);

        }
        if (e instanceof Commissioned) {

            ((Commissioned) e).setSalesResult(0);

        }
    }

    /*Calculo das taxas*/
    public static void charges (Employee e) {

        double salary = 0;

        if (e instanceof Hourly) {

            salary = ((Hourly) e).getTotalSalary();

        } else if (e instanceof Commissioned){

            salary = ((Commissioned) e).getSalary();

        } else if (e instanceof Salaried) {

            salary = ((Salaried) e).getSalary();

        }
        if (e.getPaymentSche().equals("Bi-semanal")) {

            salary = salary/2;

            if (e instanceof Commissioned) {

                salary += ((Commissioned) e).getSalesResult();

            }
        }
        double serviceCharges = 0;
        double syndicateRates = 0;
        double totalRates = 0;

        if (e.getSyndicate() != null) {

            serviceCharges = salary * e.getSyndicate().getServiceCharges();
            totalRates += (salary * e.getSyndicate().getMonthlyFee());
            totalRates += serviceCharges;
            syndicateRates = salary * e.getSyndicate().getMonthlyFee();
        }

        generatePayment(e,salary,serviceCharges,syndicateRates,totalRates);
    }

    /*Folha de pagamento*/
    public void payRoll (ArrayList <Employee> employees) {

        if (this.currentDay == this.daysMonth[this.currentMonth]) {

            for (Employee e: employees) {

                if (e.getPaymentSche().equals("Mensal") && e.getPayDay() > this.currentDay) {

                    charges(e);
                    reset(e);
                }
            }
        }
        for (Employee e : employees) {

            if (e.getPaymentSche().equals("Mensal") && e.getPayDay() == this.currentDay) {

                charges(e);
                reset(e);
            }

            if (e.getPaymentSche().equals("Semanal") && e.getPayWeek().equals(this.weeksName[(this.currentDayAux-1)%7])) {

                charges(e);
                reset(e);
            }

            if (e.getPaymentSche().equals("Bi-semanal") && e.getPayWeek().equals(this.weeksName[(this.currentDayAux-1)%7])) {

                if (this.currentWeek % 2 == 0) {
                    charges(e);
                    reset(e);
                }
            }
        }
    }

    public ArrayList<Integer> getPayDay() {
        return payDay;
    }

    public void setPayDay(ArrayList<Integer> payDay) {
        this.payDay = payDay;
    }

    public ArrayList<String> getPayWeek() {
        return payWeek;
    }

    public void setPayWeek(ArrayList<String> payWeek) {
        this.payWeek = payWeek;
    }

    public ArrayList<String> getPaySche() {
        return paySche;
    }

    public void setPaySche(ArrayList<String> paySche) {
        this.paySche = paySche;
    }

    public int getCurrentDay() {
        return currentDay;
    }

    public void setCurrentDay(int currentDay) {
        this.currentDay = currentDay;
    }

    public int getCurrentMonth() {
        return currentMonth;
    }

    public void setCurrentMonth(int currentMonth) {
        this.currentMonth = currentMonth;
    }

    public int getCurrentWeek() {
        return currentWeek;
    }

    public void setCurrentWeek(int currentWeek) {
        this.currentWeek = currentWeek;
    }

    public int getCurrentDayAux() {
        return currentDayAux;
    }

    public void setCurrentDayAux(int currentDayAux) {
        this.currentDayAux = currentDayAux;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
