import Calendar1.Calendar;
import Employee1.*;
import Exceptions1.Exceptions;
import Syndicate1.*;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Stack;

public class Execute {

    private Scanner input = new Scanner(System.in);
    private Exceptions exceptions = new Exceptions();
    private WorkFunction workFunction = new WorkFunction();
    private Syndicate service = new Syndicate();
    private UndoRedo undoRedo = new UndoRedo();
    private Calendar calendar = new Calendar();

    public void mainMenu () {

        Stack<ArrayList<Person>> undo = new Stack<ArrayList<Person>>();
        Stack<ArrayList<Person>> redo = new Stack<ArrayList<Person>>();
        Stack<Calendar> undoCalendar = new Stack<Calendar>();
        Stack<Calendar> redoCalendar = new Stack<Calendar>();


        int id = 1;
        ArrayList<Person> allEmployees = new ArrayList<Person>();
        String operation;
        FactoryMethod factoryMethod = new FactoryMethod();
        Person employee;

        while (true) {

            calendar.showCurrentInfos();
            new Menu().menuOptions();
            operation = input.nextLine();

            if (operation.equals("0")) {
                System.out.println("O programa será finalizado...");
                return;
            }

            if (operation.equals("1") || operation.equals("2") || operation.equals("3") || operation.equals("4")
                    || operation.equals("5") || operation.equals("6") || operation.equals("7")) {

                undoCalendar.push(undoRedo.addCalendar(calendar));
                undo.push(undoRedo.add(allEmployees));

                while (!redoCalendar.empty()) {
                    redoCalendar.pop();
                    redo.pop();
                }
            }

            if (operation.equals("1")) {

                System.out.println("Empregados disponíveis: ");
                System.out.println("[1] - Hourly.");
                System.out.println("[2] - Salaried.");
                System.out.println("[3] - Commissioned.");
                System.out.println("[4] - Voltar ao menu principal.");

                String opAdd = input.nextLine();

                if (opAdd.equals("1")) {

                    employee = factoryMethod.getEmployeeType("hourly");
                    employee.setDatas(employee,id,allEmployees);
                } else if (opAdd.equals("2")) {

                    employee = factoryMethod.getEmployeeType("salaried");
                    employee.setDatas(employee,id,allEmployees);
                } else if (opAdd.equals("3")) {

                    employee = factoryMethod.getEmployeeType("commissioned");
                    employee.setDatas(employee,id,allEmployees);
                } else if (opAdd.equals("4")) {

                    undoCalendar.pop();
                    undo.pop();
                    System.out.println("Voltando ao menu principal... ");
                    continue;
                }
                id += 1;
            } else if(operation.equals("2")) {

                if (allEmployees.size() == 0) {

                    undoCalendar.pop();
                    undo.pop();
                    System.out.println("Não há nenhum empregado cadastrado.");
                } else {

                    System.out.println("[1] - Remover empregado. ");
                    System.out.println("[2] - Voltar ao menu principal.");
                    String op1 = input.nextLine();

                    while (!op1.equals("1") && !op1.equals("2")) {
                        System.out.println("Opção invalida, escolha novamente.");
                        op1 = input.nextLine();
                    }
                    if (op1.equals("1")) {
                        System.out.println("Informe o id do empregado a ser removido: ");
                        int employeeId = exceptions.intNumber();
                        boolean flag = false;

                        for (Person e : allEmployees) {

                            if (e.getId() == employeeId) {
                                flag = true;
                                allEmployees.remove(e);
                                System.out.println("Empregado removido com sucesso!");
                                break;
                            }
                        }
                        if (!flag) {
                            undoCalendar.pop();
                            undo.pop();
                            System.out.println("Empregado não cadastrado.");
                        }
                    } else {

                        undo.pop();
                        undoCalendar.pop();
                        System.out.println("Voltando ao menu principal... ");
                    }
                }
            }else if (operation.equals("3")) {

                if (allEmployees.size() == 0) {

                    undoCalendar.pop();
                    undo.pop();
                    System.out.println("Não há nenhum empregado cadastrado.");
                } else {

                    System.out.println("Informe a id do empregado a bater ponto: ");
                    int idAux = exceptions.intNumber();

                    boolean flag = true;
                    for (Person e : allEmployees) {

                        if (e.getId() == idAux && e instanceof Hourly) {
                            flag = workFunction.pointCard((Hourly)e);
                            break;
                        }
                    }
                    if (!flag) {

                        undoCalendar.pop();
                        undo.pop();
                    }
                }
            } else if (operation.equals("4")) {

                if (allEmployees.size() == 0) {

                    undoCalendar.pop();
                    undo.pop();
                    System.out.println("Não há nenhum empregado cadastrado.");
                } else {

                    System.out.println("ID do empregado a submeter um resultado de venda: ");
                    int employeeId = exceptions.intNumber();
                    boolean flag = false;
                    for (Person e : allEmployees) {

                        if (e.getId() == employeeId && e instanceof Commissioned) {
                            flag = workFunction.salesResults((Commissioned)e);
                            break;
                        }
                    }
                    if (!flag) {
                        undoCalendar.pop();
                        undo.pop();
                        System.out.println("Empregado não comissionado ou não existe.");
                    }
                }
            } else if (operation.equals("5")) {

                if (allEmployees.size() == 0) {

                    undoCalendar.pop();
                    undo.pop();
                    System.out.println("Não há nenhum empregado cadastrado.");

                } else {

                    System.out.println("ID do empregado a submeter uma taxa de serviço: ");
                    int employeeId = exceptions.intNumber();
                    boolean flag = service.serviceCharges(allEmployees,employeeId);

                    if (!flag) {

                        undoCalendar.pop();
                        undo.pop();
                    }
                }

            } else if (operation.equals("6")) {

                if (allEmployees.size() == 0) {

                    undoCalendar.pop();
                    undo.pop();
                    System.out.println("Não há nenhum empregado cadastrado.");

                } else {

                    System.out.println("ID do empregado a alterar os dados: ");
                    int employeeId = exceptions.intNumber();
                    Hourly change = new Hourly();
                    boolean flag = false;
                    for (Person e: allEmployees) {

                        if (e.getId() == employeeId) {
                            new Menu().menuChangeDatas();
                            String op1 = input.nextLine();
                            flag = change.changeDatas(e,op1,allEmployees);
                            break;
                        }
                    }

                    if (!flag) {

                        undoCalendar.pop();
                        undo.pop();
                    }
                }
            } else if (operation.equals("7")) {

                if (allEmployees.size() == 0) {

                    undoCalendar.pop();
                    undo.pop();
                    System.out.println("Não há nenhum empregado cadastrado.");
                } else {

                    for (Person e: allEmployees) {

                        if (e instanceof Hourly) {
                            ((Hourly) e).setTimeCard(false);
                        }
                    }
                    calendar.payRoll(allEmployees);
                    calendar.toSpendDay();
                    System.out.println("Folha de pagamento rodada para hoje.");
                }
            } else if (operation.equals("8")) {

                System.out.println("[1] - Undo.");
                System.out.println("[2] - Redo.");
                System.out.println("[3] - Retornar ao menu principal.");
                String op1 = input.nextLine();

                while (!op1.equals("1") && !op1.equals("2") && !op1.equals("3")) {

                    System.out.println("Opção inválida, escolha novamente.");
                    op1 = input.nextLine();
                }

                if (op1.equals("1")) {

                    if (undo.empty()) {

                        System.out.println("Não é possivel.");
                    } else {

                        Calendar aux = undoCalendar.pop();
                        redoCalendar.push(undoRedo.addCalendar(calendar));

                        calendar.setPayWeek(aux.getPayWeek());
                        calendar.setPaySche(aux.getPaySche());
                        calendar.setPayDay(aux.getPayDay());
                        calendar.setCurrentDay(aux.getCurrentDay());
                        calendar.setCurrentDayAux(aux.getCurrentDayAux());
                        calendar.setCurrentMonth(aux.getCurrentMonth());
                        calendar.setCurrentWeek(aux.getCurrentWeek());
                        calendar.setYear(aux.getYear());

                        redo.push(undoRedo.add(allEmployees));
                        undoRedo.undoOrRedoToEmployee(allEmployees,undo.peek());
                        undo.pop();
                    }
                } else if (op1.equals("2")){

                    if (redo.empty()) {

                        System.out.println("Não é possivel.");

                    } else {

                        Calendar aux = redoCalendar.pop();

                        undoCalendar.push(undoRedo.addCalendar(calendar));

                        calendar.setPayWeek(aux.getPayWeek());
                        calendar.setPaySche(aux.getPaySche());
                        calendar.setPayDay(aux.getPayDay());
                        calendar.setCurrentDay(aux.getCurrentDay());
                        calendar.setCurrentDayAux(aux.getCurrentDayAux());
                        calendar.setCurrentMonth(aux.getCurrentMonth());
                        calendar.setCurrentWeek(aux.getCurrentWeek());
                        calendar.setYear(aux.getYear());

                        undo.push(undoRedo.add(allEmployees));
                        undoRedo.undoOrRedoToEmployee(allEmployees,redo.peek());
                        redo.pop();
                    }
                } else {
                    undoCalendar.pop();
                    undo.pop();
                    System.out.println("Voltando ao menu principal... ");
                }
            } else if (operation.equals("9")) {

                if (allEmployees.size() == 0) {

                    undoCalendar.pop();
                    undo.pop();
                    System.out.println("Não há nenhum empregado cadastrado.");
                } else {

                    System.out.println("ID do empregado a alterar os dados: ");
                    int employeeId = exceptions.intNumber();
                    boolean flag = calendar.changePaymentSchedule(allEmployees,employeeId);

                    if (!flag) {
                        undoCalendar.pop();
                        undo.pop();
                    }
                }
            } else if (operation.equals("10")) {

                calendar.createPaymentSchedule();
            } else if (operation.equals("11")) {

                employee = factoryMethod.getEmployeeType("hourly");
                employee.listEmployees(allEmployees);
            } else if (operation.equals("12")){

                calendar.listPaymentSchedule();
            }
            System.out.println();
            System.out.println("Pressione qualquer tecla para continuar...");
            System.out.print("-> ");
            input.nextLine();
        }
    }
}
