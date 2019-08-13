package Employee1;

import java.util.ArrayList;
import java.util.Scanner;
import Syndicate1.*;

public abstract class Employee implements Person{

    protected String name; // Nome
    protected int id; // Identificação
    protected String address; // Endereço
    protected String payMethod; // Método de pagamento do empregado
    protected int payDay; // Dia do mes para pagamento
    protected String payWeek; // Dia da para pagamento
    protected String paySchedule;
    protected double salary;
    protected Scanner input = new Scanner(System.in);
    protected Syndicate syndicate;

    void basicDatas (Person employee, ArrayList<Person> allEmployees) {

        System.out.println("Nome: ");
        String name = input.nextLine();
        while (name.equals("") || name.equals(" ")) {
            System.out.println("Campo nome não pode ser vazio.");
            name = input.nextLine();
        }
        employee.setName(name);
        System.out.println("Endereço:");
        String address = input.nextLine();
        employee.setAddress(address);
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
            employee.setPayMethod("Cheque por correios");
        } else if (meth.equals("2")) {
            employee.setPayMethod("Cheque em mãos");
        } else {
            employee.setPayMethod("Depósito em conta bancária");
        }

        System.out.println("Pertence a um sindicato: [S/N]");
        String syndicateop = input.nextLine();
        syndicateop = syndicateop.toLowerCase();

        while (!syndicateop.equals("s") && !syndicateop.equals("n")) {

            System.out.println("Opção invalida, escolha novamente!!");
            syndicateop = input.nextLine();
        }
        if (syndicateop.equals("s")) {

            setSyndicate(true);
            syndicate.syndicateDatas(allEmployees);
        } else {
            setSyndicate(false);
        }
    }

    public void listEmployees (ArrayList<Person> employees) {

        if (employees.size() == 0) {
            System.out.println("Não há empregados cadastrados.");
            return;
        }
        for (Person e: employees) {

            System.out.println("____________________________________");
            System.out.println();
            System.out.println("ID: " + e.getId());
            System.out.println("Nome: " + e.getName());
            System.out.println("Endereço: " + e.getAddress());
            System.out.println("Método de pagamento: " + e.getPayMethod());
            if (e instanceof Hourly) {

                System.out.println("Tipo: Horista.");
                System.out.println("Salario por hora: R$" + e.getSalary());
            } else if (e instanceof Salaried) {

                System.out.println("Tipo: Assalariado.");
                System.out.println("Salario mensal: R$" + e.getSalary());
            } else if (e instanceof Commissioned) {

                System.out.println("Tipo: Comissionado.");
                System.out.println("Salario mensal: R$" + e.getSalary());
                System.out.println("Porcentagem da comissão: " + ((Commissioned)e).getAmountCommission());
            }
            if (e.getPaySchedule().equals("Bi-semanal") || e.getPaySchedule().equals("Semanal")) {

                System.out.println("Dia da semana do pagamento: " + e.getPayWeek());
                System.out.println("Forma de pagamento: " + e.getPaySchedule());
            } else if (e.getPaySchedule().equals("Mensal")) {

                System.out.println("Dia do pagamento: " + e.getPayDay());
                System.out.println("Forma de pagamento: " + e.getPaySchedule());
            }
            System.out.println();
            System.out.println("------ Sindicato -------");
            System.out.println();
            if (e.getSyndicate() != null) {
                System.out.println("Id sindicato: " + e.getSyndicate().getId());
                System.out.println("Taxa mensal: " + e.getSyndicate().getMonthlyFee());
            } else {
                System.out.println("Não pertence ao sindicato.");
            }
            System.out.println();
            System.out.println("____________________________________");
        }
    }

    public void generateNewEmployee (Person employee, Person e, double newSalary, ArrayList <Person> allEmployees) {

        employee.setId(e.getId());
        employee.setName(e.getName());
        employee.setAddress(e.getAddress());
        employee.setPayMethod(e.getPayMethod());
        employee.setPayDay(e.getPayDay());
        employee.setPaySchedule(e.getPaySchedule());
        employee.setPayWeek(e.getPayWeek());
        employee.setSalary(newSalary);

        if (employee instanceof Commissioned) {

            System.out.println("Porcentagem da comissão: [%]");
            double commission = exceptions.doubleNumber();

            while (commission > 100) {

                System.out.println("Valor invalido, informe novamente.");
                commission = exceptions.doubleNumber();
            }
            commission /= 100;
            ((Commissioned)employee).setAmountCommission(commission);
        }

        if (e.getSyndicate() == null) {
            employee.setSyndicate(false);
        } else {
            employee.setSyndicate(e.getSyndicate().getId(),e.getSyndicate().getMonthlyFee(),e.getSyndicate().getServiceCharges());
        }
        allEmployees.remove(e);
        allEmployees.add(employee);
    }

    public boolean changeDatas (Person e, String op, ArrayList<Person> allEmployees) {

        if (op.equals("1")) {

            System.out.println("Novo nome: ");
            String name = input.nextLine();
            while (name.equals("") || name.equals(" ")) {
                System.out.println("Campo nome não pode ser vazio.");
                name = input.nextLine();
            }
            e.setName(name);
            System.out.println("Nome alterado.");
        } else if (op.equals("2")) {

            System.out.println("Novo endereço: ");
            String address = input.nextLine();
            e.setAddress(address);
            System.out.println("Endereço alterado.");
        } else if (op.equals("3")) {

                System.out.println("Informe qual será o novo tipo do empregado: [Employee.Hourly,Employee.Salaried,Employee.Commissioned]");
                String in = input.nextLine();
                in = in.toLowerCase();

                while (!in.equals("hourly") && !in.equals("salaried") && !in.equals("commissioned")) {

                    System.out.println("Opção invalida, escolha uma das duas opções acima!");
                    in = input.nextLine();
                }
                if ((e instanceof Hourly && in.equals("hourly"))) {

                    System.out.println("Você já é a opção selecionada!!");
                    return false;
                } else if (e instanceof Commissioned && in.equals("commissioned")) {

                    System.out.println("Você já é a opção selecionada!!");
                    return false;
                } else if (e instanceof Salaried && in.equals("salaried")) {

                    System.out.println("Você já é a opção selecionada!!");
                    return false;
                }

                if (in.equals("hourly")) {

                    System.out.println("Informe o valor da hora: ");
                    double hourSalary = exceptions.doubleNumber();
                    Person newHourly = new FactoryMethod().getEmployeeType("hourly");
                    generateNewEmployee(newHourly,e,hourSalary,allEmployees);
                    System.out.println("Empregado alterado.");
                } else if (in.equals("commissioned")) {

                    System.out.println("Informe o salario do empregado: ");
                    double salary = exceptions.doubleNumber();
                    Person newCommissioned = new FactoryMethod().getEmployeeType("commissioned");
                    generateNewEmployee(newCommissioned,e,salary,allEmployees);
                    System.out.println("Empregado alterado.");
                } else {

                    System.out.println("Informe o salario do empregado: ");
                    double salary = exceptions.doubleNumber();
                    Person newSalaried = new FactoryMethod().getEmployeeType("salaried");
                    generateNewEmployee(newSalaried,e,salary,allEmployees);
                    System.out.println("Empregado alterado.");
                }
        } else if (op.equals("4")) {

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
                    e.setPayMethod("Cheque por correios");
                } else if (meth.equals("2")) {
                    e.setPayMethod("Cheque em mãos");
                } else {
                    e.setPayMethod("Depósito em conta bancária");
                }

                System.out.println("Método de pagamento alterado.");
        } else if (op.equals("5")) {

                System.out.println("Deseja participar de um sindicato? [S/N].");

                String op1;
                op1 = input.nextLine();
                op1 = op1.toLowerCase();
                while (!op1.equals("s") && !op1.equals("n")) {

                    System.out.println("Opção invalida, informe novamente.");
                    op1 = input.nextLine();
                }

                if (op1.equals("s") && e.getSyndicate() != null) {

                    System.out.println("Empregado já pertence ao sindicato.");
                    return false;
                } else if (op1.equals("n") && e.getSyndicate() == null) {

                    System.out.println("Empregado já não pertence ao sindicato.");
                    return false;
                } else if (op1.equals("s")) {

                    e.setSyndicate(true);
                    e.getSyndicate().syndicateDatas(allEmployees);
                } else {
                    e.setSyndicate(false);
                }
        } else if (op.equals("6")) {

                if (e.getSyndicate() != null) {

                    System.out.println("ID do sindicato: ");
                    int syndicateId = exceptions.intNumber();

                    while (new Syndicate().verifyId(allEmployees, syndicateId)) {
                        System.out.println("Já existe um empregado com essa ID, escolha novamente!");
                        syndicateId = exceptions.intNumber();
                    }
                    e.getSyndicate().setId(syndicateId);
                    System.out.println("ID alterado.");
                } else {
                    System.out.println("Empregado não pertence ao sindicato.");
                    return false;
                }
        } else if (op.equals("7")) {

                if (e.getSyndicate() != null) {

                    System.out.println("Informe nova taxa sindical: ");
                    double monthlyFee = exceptions.doubleNumber();

                    while (monthlyFee > 100) {

                        System.out.println("Valor invalido, informe novamente.");
                        monthlyFee = exceptions.doubleNumber();

                    }
                    monthlyFee /= 100;
                    e.getSyndicate().setMonthlyFee(monthlyFee);
                    System.out.println("Taxa alterada.");
                } else {
                    System.out.println("Empregado não pertence ao sindicato.");
                    return false;
                }
        }
        return true;
    }
}
