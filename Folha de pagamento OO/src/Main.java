import java.util.Scanner;
import java.util.ArrayList;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {

        Exceptions exceptions = new Exceptions();
        Scanner input = new Scanner(System.in);
        Calendar calendar = new Calendar();
        ArrayList <Employee> employees = new ArrayList<Employee>();
        int id = 1;

        Stack<ArrayList<Employee>> undo = new Stack<ArrayList<Employee>>();
        Stack<ArrayList<Employee>> redo = new Stack<ArrayList<Employee>>();
        Stack<Calendar> undoCalendar = new Stack<Calendar>();
        Stack<Calendar> redoCalendar = new Stack<Calendar>();

        UndoRedo undoRedo = new UndoRedo();

        while (true) {

            calendar.showCurrentInfos();
            System.out.println("[1] - Adicionar empregado.");
            System.out.println("[2] - Remover empregado.");
            System.out.println("[3] - Bater ponto [Somente Horistas].");
            System.out.println("[4] - Resultado de venda [Somente Comissionados].");
            System.out.println("[5] - Lançar taxa de serviço [Somente pertencentes ao sindicato].");
            System.out.println("[6] - Alterar dados do empregado.");
            System.out.println("[7] - Rodar folha de pagamento.");
            System.out.println("[8] - Undo/Redo.");
            System.out.println("[9] - Mudar dia do pagamento.");
            System.out.println("[10] - Criar agenda de pagamento");
            System.out.println("[11] - Listar empregados cadastrados.");
            System.out.println("[12] - Listar agendas de pagamento disponiveis.");
            System.out.println("[0] - Finalizar programa!");
            String op = input.nextLine();

            if (op.equals("0")) {
                break;
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

                System.out.println("[1] - Horista.");
                System.out.println("[2] - Assalariado.");
                System.out.println("[3] - Comissionado.");
                System.out.println("[4] - Voltar ao menu principal.");

                String opAdd = input.nextLine();

                if (opAdd.equals("1")) {

                    Hourly newHourly = new Hourly();
                    newHourly.createEmployee(employees, id);
                    id += 1;

                } else if (opAdd.equals("2")) {

                    Salaried newSalaried = new Salaried();
                    newSalaried.createEmployee(employees, id);
                    id += 1;

                } else if (opAdd.equals("3")) {

                    Commissioned newCommissioned = new Commissioned();
                    newCommissioned.createEmployee(employees, id);
                    id += 1;

                } else if (opAdd.equals("4")) {

                    undoCalendar.pop();
                    undo.pop();
                    System.out.println("Voltando ao menu principal... ");
                }

            } else if(op.equals("2")) {

                Hourly remove = new Hourly();

                if (employees.size() == 0) {

                    undoCalendar.pop();
                    undo.pop();
                    System.out.println("Não há nenhum empregado cadastrado.");

                } else {

                    System.out.println("[1] - ID do empregado a ser removido: ");
                    System.out.println("[2] - Voltar ao menu principal.");
                    String op1 = input.nextLine();

                    while (!op1.equals("1") && !op1.equals("2")) {
                        System.out.println("Opção invalida, escolha novamente.");
                        op1 = input.nextLine();
                    }
                    if (op1.equals("1")) {

                        int employeeId = exceptions.intNumber();
                        boolean flag = remove.removeEmployee(employees,employeeId);

                        if (!flag) {
                            undoCalendar.pop();
                            undo.pop();
                        }

                    } else {

                        undo.pop();
                        undoCalendar.pop();
                        System.out.println("Voltando ao menu principal... ");
                    }
                }

            } else if (op.equals("3")) {

                if (employees.size() == 0) {

                    undoCalendar.pop();
                    undo.pop();
                    System.out.println("Não há nenhum empregado cadastrado.");

                } else {

                    Hourly hitPoint = new Hourly();
                    boolean flag = hitPoint.pointCard(employees);

                    if (!flag) {

                        undoCalendar.pop();
                        undo.pop();
                    }
                }

            } else if (op.equals("4")) {

                if (employees.size() == 0) {

                    undoCalendar.pop();
                    undo.pop();
                    System.out.println("Não há nenhum empregado cadastrado.");

                } else {

                    Commissioned salesResult = new Commissioned();
                    System.out.println("ID do empregado a submeter um resultado de venda: ");;
                    int employeeId = exceptions.intNumber();
                    boolean flag = salesResult.salesResults(employees,employeeId);

                    if (!flag) {

                        undoCalendar.pop();
                        undo.pop();
                    }
                }

            } else if (op.equals("5")) {

                if (employees.size() == 0) {

                    undoCalendar.pop();
                    undo.pop();
                    System.out.println("Não há nenhum empregado cadastrado.");

                } else {

                    Syndicate service = new Syndicate();
                    System.out.println("ID do empregado a submeter uma taxa de serviço: ");
                    int employeeId = exceptions.intNumber();
                    boolean flag = service.serviceCharges(employees,employeeId);

                    if (!flag) {

                        undoCalendar.pop();
                        undo.pop();
                    }
                }

            } else if (op.equals("6")) {

                if (employees.size() == 0) {

                    undoCalendar.pop();
                    undo.pop();
                    System.out.println("Não há nenhum empregado cadastrado.");

                } else {

                    System.out.println("ID do empregado a alterar os dados: ");
                    int employeeId = exceptions.intNumber();
                    Hourly change = new Hourly();
                    boolean flag = change.changeDatas(employees,employeeId,undoCalendar,undo,redoCalendar,redo,calendar);

                    if (!flag) {

                        undoCalendar.pop();
                        undo.pop();
                    }
                }

            } else if (op.equals("7")) {

                if (employees.size() == 0) {

                    undoCalendar.pop();
                    undo.pop();
                    System.out.println("Não há nenhum empregado cadastrado.");

                } else {

                    Hourly reset = new Hourly();
                    reset.resetPointCard(employees);
                    calendar.payRoll(employees);
                    calendar.toSpendDay();
                    System.out.println("Folha de pagamento rodada para hoje.");
                }

            } else if (op.equals("8")) {

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

                        redo.push(undoRedo.add(employees));
                        undoRedo.undoOrRedoToEmployee(employees,undo.peek());
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

                        undo.push(undoRedo.add(employees));
                        undoRedo.undoOrRedoToEmployee(employees,redo.peek());
                        redo.pop();
                    }

                } else {

                    undoCalendar.pop();
                    undo.pop();
                    System.out.println("Voltando ao menu principal... ");

                }

            } else if (op.equals("9")) {


                if (employees.size() == 0) {

                    undoCalendar.pop();
                    undo.pop();
                    System.out.println("Não há nenhum empregado cadastrado.");

                } else {

                    System.out.println("ID do empregado a alterar os dados: ");
                    int employeeId = exceptions.intNumber();
                    boolean flag = calendar.changePaymentSchedule(employees,employeeId);

                    if (!flag) {

                        undoCalendar.pop();
                        undo.pop();
                    }

                }
            } else if (op.equals("10")) {

                calendar.createPaymentSchedule();

            } else if (op.equals("11")) {

                Hourly list = new Hourly();
                list.listEmployees(employees);

            } else if (op.equals("12")){

                calendar.listPaymentSchedule();

            }

            System.out.println();
            System.out.println("Pressione qualquer tecla para continuar...");
            System.out.print("-> ");
            input.nextLine();

        }
    }
}
