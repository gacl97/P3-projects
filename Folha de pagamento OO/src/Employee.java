import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public abstract class Employee {

    Scanner input = new Scanner(System.in);
    private Exceptions exceptions = new Exceptions();
    private String name; // Nome
    private int id; // Identificação
    private String address; // Endereço
    private String payMeth; // Método de pagamento do empregado
    private int payDay; // Dia do mes para pagamento
    private String payWeek; // Dia da para pagamento
    private String paymentSche; // Semanal/Bi-semanal/Mensal
    private Syndicate syndicate = new Syndicate();

    public void setSyndicate() {
    }
    /*Função cria um objeto sindicato caso o empregado queira participar, e caso não queira, sindicato será nulo */
    public void setSyndicate (int syndicateId, double monthlyFee, double serviceCharges) {

        this.syndicate.setId(syndicateId);
        this.syndicate.setMonthlyFee(monthlyFee);
        this.syndicate.setServiceCharges(serviceCharges);
    }
    public void setSyndicate(String op, ArrayList <Employee> employees) {

        if (op.equals("S")) {
            System.out.println("######## Sindicato ########");
            System.out.println();

            if (this.syndicate == null) {
                this.syndicate = new Syndicate();
            }
            System.out.println("ID do sindicato: ");
            int syndicateId = exceptions.intNumber();

            while (this.syndicate.verifyId(employees, syndicateId)) {
                System.out.println("Já existe um empregado com essa ID, escolha novamente!");
                syndicateId = exceptions.intNumber();
            }

            System.out.println("Taxa mensal em [%]: ");
            double monthlyFee = exceptions.doubleNumber();

            while (monthlyFee > 100) {

                System.out.println("Valor invalido, informe novamente.");
                monthlyFee = exceptions.doubleNumber();

            }
            monthlyFee /= 100;

            this.syndicate.setId(syndicateId);
            this.syndicate.setMonthlyFee(monthlyFee);
        }
        else if (op.equals("N")) {this.syndicate = null;}
    }

    public abstract void createEmployee (ArrayList<Employee> employees, int id);

    public void listEmployees (ArrayList<Employee> employees) {

        if (employees.size() == 0) {
            System.out.println("Não há empregados cadastrados.");
            return;
        }
        for (Employee e: employees) {

            System.out.println("____________________________________");
            System.out.println();
            System.out.println("ID: " + e.getId());
            System.out.println("Nome: " + e.getName());
            System.out.println("Endereço: " + e.getAddress());
            System.out.println("Método de pagamento: " + e.getPayMeth());
            if (e instanceof Hourly) {

                System.out.println("Tipo: Horista.");
                System.out.println("Salario por hora: R$" + ((Hourly) e).getHourSalary());
            } else if (e instanceof Salaried) {

                System.out.println("Tipo: Assalariado.");
                System.out.println("Salario mensal: R$" + ((Salaried) e).getSalary());
            } else if (e instanceof Commissioned) {

                System.out.println("Tipo: Comissionado.");
                System.out.println("Salario mensal: R$" + ((Commissioned) e).getSalary());
                System.out.println("Porcentagem da comissão: " + ((Commissioned) e).getAmountCommission());
            }
            if (e.paymentSche.equals("Bi-semanal") || e.paymentSche.equals("Semanal")) {

                System.out.println("Dia da semana do pagamento: " + e.getPayWeek());
                System.out.println("Forma de pagamento: " + e.getPaymentSche());
            } else if (e.paymentSche.equals("Mensal")) {

                System.out.println("Dia do pagamento: " + e.getPayDay());
                System.out.println("Forma de pagamento: " + e.getPaymentSche());
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

    public boolean removeEmployee (ArrayList <Employee> employees, int id) {

        for (Employee e: employees) {

            if (e.getId() == id) {
                employees.remove(e);
                System.out.println("Empregado removido.");
                return true;
            }
        }
        System.out.println("Não existe um empregado com essa id.");
        return false;
    }

    public boolean changeDatas (ArrayList<Employee> employees, int id, Stack<Calendar> undoCalendar, Stack<ArrayList<Employee>> undo, Stack<Calendar> redoCalendar, Stack<ArrayList<Employee>> redo, Calendar calendar) {

        UndoRedo undoRedo = new UndoRedo();

        for (Employee e: employees) {

            if (e.getId() == id) {

                while (true) {

                    System.out.println("############# Alterar dados #############");
                    System.out.println();
                    System.out.println("[1] - Nome.");
                    System.out.println("[2] - Endereço.");
                    System.out.println("[3] - Tipo de empregado.");
                    System.out.println("[4] - Método de pagamento.");
                    System.out.println("[5] - Sindicato.");
                    System.out.println("[6] - Identificação do sindicato.");
                    System.out.println("[7] - Taxa sindical.");
                    System.out.println("[8] - Listar empregados.");
                    System.out.println("[9] - Retornar ao menu principal.");

                    String op = input.nextLine();

                    if (op.equals("9")) {
                        return true;
                    }

                    if (op.equals("1") || op.equals("2") || op.equals("3") || op.equals("4") || op.equals("5") || op.equals("6") || op.equals("7")) {

                        undoCalendar.push(undoRedo.addCalendar(calendar));
                        undo.push(undoRedo.add(employees));

                        while (!redoCalendar.empty()) {
                            redoCalendar.pop();
                            redo.pop();
                        }
                    }

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

                        System.out.println("Informe qual será o novo tipo do empregado: [Hourly,Salaried,Commissioned]");
                        String in = input.nextLine();

                        while (!in.equals("Hourly") && !in.equals("Salaried") && !in.equals("Commissioned")) {

                            System.out.println("Opção invalida, escolha uma das duas opções acima!");
                            in = input.nextLine();
                        }
                        if ((e instanceof Hourly && in.equals("Hourly"))) {

                            System.out.println("Você já é a opção selecionada!!");
                            continue;

                        } else if (e instanceof Commissioned && in.equals("Commissioned")) {

                            System.out.println("Você já é a opção selecionada!!");
                            continue;

                        } else if (e instanceof Salaried && in.equals("Salaried")) {

                            System.out.println("Você já é a opção selecionada!!");
                            continue;
                        }

                        if (in.equals("Hourly")) {

                            System.out.println("Informe o valor da hora: ");
                            double hourSalary = exceptions.doubleNumber();

                            Hourly newHourly = new Hourly(e.getName(),e.getAddress(),e.getId(),hourSalary,e.getPayMeth());

                            if (e.getSyndicate() == null) {

                                newHourly.setSyndicate("N",employees);

                            } else {

                                newHourly.setSyndicate(e.getSyndicate().getId(), e.getSyndicate().getMonthlyFee(),e.getSyndicate().getServiceCharges());

                            }
                            employees.remove(e);
                            employees.add(newHourly);
                            System.out.println("Empregado alterado.");
                            return true;

                        } else if (in.equals("Commissioned")) {

                            System.out.println("Informe o salario do empregado: ");
                            double salary = exceptions.doubleNumber();

                            System.out.println("Porcentagem da comissão: [%]");
                            double commission = exceptions.doubleNumber();

                            while (commission > 100) {

                                System.out.println("Valor invalido, informe novamente.");
                                commission = exceptions.doubleNumber();

                            }
                            commission /= 100;

                            Commissioned newCommissioned = new Commissioned(e.getName(),e.getAddress(),e.getId(),salary,e.getPayMeth(),commission);

                            if (e.getSyndicate() == null) {

                                newCommissioned.setSyndicate("N",employees);

                            } else {

                                newCommissioned.setSyndicate(e.getSyndicate().getId(), e.getSyndicate().getMonthlyFee(),e.getSyndicate().getServiceCharges());

                            }
                            employees.remove(e);
                            employees.add(newCommissioned);
                            System.out.println("Empregado alterado.");
                            return true;

                        } else {

                            System.out.println("Informe o salario do empregado: ");
                            double salary = exceptions.doubleNumber();
                            Salaried newSalaried = new Salaried(e.getName(),e.getAddress(),e.getId(),salary,e.getPayMeth());

                            if (e.getSyndicate() == null) {

                                newSalaried.setSyndicate("N",employees);

                            } else {

                                newSalaried.setSyndicate(e.getSyndicate().getId(), e.getSyndicate().getMonthlyFee(),e.getSyndicate().getServiceCharges());

                            }
                            employees.remove(e);
                            employees.add(newSalaried);
                            System.out.println("Empregado alterado.");
                            return true;
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

                            e.setPayMeth("Cheque por correios");

                        } else if (meth.equals("2")) {

                            e.setPayMeth("Cheque em mãos");

                        } else {

                            e.setPayMeth("Depósito em conta bancária");

                        }
                        System.out.println("Método de pagamento alterado.");

                    } else if (op.equals("5")) {

                        System.out.println("Deseja participar de um sindicato? [S/N].");

                        String op1;
                        op1 = input.nextLine();

                        while (!op1.equals("S") && !op1.equals("N")) {

                            System.out.println("Opção invalida, informe novamente.");
                            op1 = input.nextLine();
                        }

                        if (op1.equals("S") && e.getSyndicate() != null) {

                            undoCalendar.pop();
                            undo.pop();
                            System.out.println("Empregado já pertence ao sindicato.");

                        } else if (op1.equals("N") && e.getSyndicate() == null) {

                            undoCalendar.pop();
                            undo.pop();
                            System.out.println("Empregado já não pertence ao sindicato.");

                        } else {

                            e.setSyndicate(op1, employees);

                        }

                    } else if (op.equals("6")) {

                        if (e.getSyndicate() != null) {

                            System.out.println("ID do sindicato: ");
                            int syndicateId = exceptions.intNumber();

                            while (this.syndicate.verifyId(employees, syndicateId)) {
                                System.out.println("Já existe um empregado com essa ID, escolha novamente!");
                                syndicateId = exceptions.intNumber();
                            }
                            e.syndicate.setId(syndicateId);
                            System.out.println("ID alterado.");

                        } else {

                            undoCalendar.pop();
                            undo.pop();
                            System.out.println("Empregado não pertence ao sindicato.");

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
                            e.syndicate.setMonthlyFee(monthlyFee);
                            System.out.println("Taxa alterada.");

                        } else {

                            undoCalendar.pop();
                            undo.pop();
                            System.out.println("Empregado não pertence ao sindicato.");

                        }

                    } else if (op.equals("8")) {

                        listEmployees(employees);

                    }
                    System.out.println();
                    System.out.println("Pressione qualquer tecla para continuar...");
                    System.out.println("-> ");
                    input.nextLine();
                }

            }
        }
        System.out.println("Não existe um empregado com essa id.");
        return false;
    }


    public String getPayMeth() {
        return payMeth;
    }

    public void setPayMeth(String payMeth) {
        this.payMeth = payMeth;
    }

    public int getPayDay() {
        return payDay;
    }

    public void setPayDay(int payDay) {
        this.payDay = payDay;
    }

    public String getPayWeek() {
        return payWeek;
    }

    public void setPayWeek(String payWeek) {
        this.payWeek = payWeek;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String adress) {
        this.address = adress;
    }

    public Syndicate getSyndicate() {
        return syndicate;
    }

    public String getPaymentSche() {
        return paymentSche;
    }

    public void setPaymentSche(String paymentSche) {
        this.paymentSche = paymentSche;
    }

}
