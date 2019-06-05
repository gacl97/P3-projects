import java.util.Scanner;

public class Main {

    public static void resetHitPoint (String [][]employees) {

        for (int i = 0; i < employees.length; i++) {

            if (employees[i][0] != null && employees[i][3].equals("Hourly")) {
                employees[i][8] = "false";
            }
        }
    }

    public static void reset (String [][]employees, int index) {

        employees[index][18] = "0";
        employees[index][10] = "0";
        employees[index][5] = "0";

        if (employees[index][3].equals("Hourly")) {
            employees[index][4] = "0";
        }
    }
    public static void generate (String [][]employees, int index, double salary, double rates, double syndicalRates, double totalRates) {

        System.out.println("-------------------------------");
        System.out.println("id: " + employees[index][0]);
        System.out.println("Nome: " + employees[index][1]);
        System.out.println("Forma de pagamento: " + employees[index][6]);
        if (employees[index][13].equals("mensal")) {
            System.out.println("Dia do pagamento: " + employees[index][12]);
        }
        System.out.println("Dia da semana do pagamento: " + employees[index][14]);
        System.out.println("Forma de pagamento: " + employees[index][13]);
        System.out.println("Salario liquido: " + salary);
        System.out.println("Rates: " + rates);
        System.out.println("Syndical rates: " + syndicalRates);
        System.out.println("Total rates: " + totalRates);
        System.out.println("Salario bruto: " + employees[index][5]);
        System.out.println("-------------------------------");
    }

    public static void charges (String [][]employees, int index) {


        double salary = Double.valueOf(employees[index][4]);
        if (employees[index][13].equals("Bi-semanal")) {

            if (!employees[index][3].equals("Hourly")) {

                salary = salary/2;
                salary += Double.valueOf(employees[index][10]);
            }
        }
        double rates = salary * Double.valueOf(employees[index][18]);
        double syndicateRates = 0;
        double totalRates = rates;

        if (employees[index][17].equals("true")) {

            totalRates += (salary * Double.valueOf(employees[index][16]));
            syndicateRates = salary * Double.valueOf(employees[index][16]);
        }
        employees[index][5] = String.valueOf(salary - totalRates);
        generate(employees,index,salary,rates,syndicateRates,totalRates);
    }


    public static void payment (String [][]employees, int day, int week) {

        String[] weeksName = {"segunda","terça","quarta","quinta","sexta", "sabado", "domingo"};
        String currentWeekDay = weeksName[(day-1) % 7];
        String dayAux = String.valueOf(day);

        for (int i = 0; i < employees.length; i++) {

            if (employees[i][0] != null) {

                if (employees[i][12] != null && employees[i][13].equals("Mensal") && employees[i][12].equals(dayAux)) {

                    charges(employees,i);
                    reset(employees,i);
                }
                if (employees[i][13].equals("Semanal") && employees[i][14].equals(currentWeekDay)) {
                    charges(employees,i);
                    reset(employees,i);
                }

                if (employees[i][13].equals("Bi-semanal") && employees[i][14].equals(currentWeekDay)) {

                    if (week % 2 == 0) {
                        charges(employees,i);
                        reset(employees,i);
                    }
                }
            }
        }
    }


    public static void changePaymentDay (String [][]employees, String id) {

        Scanner input = new Scanner(System.in);

        for (int i = 0; i < employees.length; i++) {

            if (employees[i][0] != null && employees[i][0].equals(id)) {

                System.out.println("Informe a nova agenda de pagamento: [Semanal,Bi-semanal,Mensal]");
                String option3;

                while(true) {

                    option3 = input.nextLine();
                    if (option3.equals("Semanal") || option3.equals("Bi-semanal") || option3.equals("Mensal")) {
                        break;
                    }
                    System.out.println("Opção invalida, escolha novamente");
                }


                if(option3.equals("Semanal") || option3.equals("Bi-semanal")) {
                    employees[i][13] = option3;
                    employees[i][12] = null;
                    System.out.println("Digite o dia da semana do novo pagamento: [segunda,terça,quarta,quinta,sexta]");

                    while (true) {

                        option3 = input.nextLine();
                        if (option3.equals("segunda") || option3.equals("terça") || option3.equals("quarta")
                                || option3.equals("quinta") || option3.equals("sexta")) {
                            employees[i][14] = option3;
                            return;
                        }
                        System.out.println("Opção invalida, escolha novamente");
                    }

                } else {
                    employees[i][13] = option3;
                    employees[i][14] = null;
                    System.out.println("Informe o dia do pagamento entre dia 1 a dia 30: ");
                    int day;

                    while (true) {
                        day = input.nextInt();
                        if (day > 0 && day < 31) {
                            employees[i][12] = String.valueOf(day);
                            return;
                        }
                        System.out.println("Dia invalido!! escolha novamente!!");
                    }

                }
            }
        }
        System.out.println("Não existe um empregado com essa id!");
    }

    public static void changeDatas (String [][]employees, String id, String [][][]undo, String [][][]redo, int sizeUndo, int sizeRedo, int day, int week, int month) {


        int option2 ;

        Scanner input = new Scanner(System.in);

       for (int i = 0; i < employees.length; i++) {

           if (employees[i][0] != null && employees[i][0].equals(id)) {

               while (true) {

                   System.out.println("[1] - Nome.");
                   System.out.println("[2] - Endereço.");
                   System.out.println("[3] - Tipo de empregado.");
                   System.out.println("[4] - Método de pagamento.");
                   System.out.println("[5] - Sindicato.");
                   System.out.println("[6] - Identificação do sindicato.");
                   System.out.println("[7] - Taxa sindical.");
                   System.out.println("[8] - Listar empregados.");
                   System.out.println("[9] - Retornar ao menu principal.");

                   option2 = input.nextInt();
                   input.nextLine();

                   if (option2 == 9) {
                       return;
                   }

                   if (option2 != 8) {
                       addInUndoAndRedo(employees,undo,day,week,month);
                       resetRedo(redo);
                       sizeRedo = 0;
                       sizeUndo += 1;
                   }

                   if (option2 == 1) {

                       System.out.println("Novo nome: ");
                       String name = input.nextLine();
                       employees[i][1] = name;
                   } else if (option2 == 2) {

                       System.out.println("Novo endereço: ");
                       String adress = input.nextLine();
                       employees[i][2] = adress;
                   } else if (option2 == 3) {

                       System.out.println("Informe qual será o novo tipo do empregado: [Hourly,Salaried]");

                       String in;
                       while (true) {

                           in = input.nextLine();

                           if (in.equals("Hourly") || in.equals("Salaried")) {
                               break;
                           }
                           System.out.println("Opção invalida, escolha uma das duas opções acima!");
                       }

                       if (employees[i][3].equals("Hourly") && in.equals("Hourly")) {
                           System.out.println("Você já é a opção selecionada!!");
                           System.out.println("-- > " + employees[i][3]);
                           continue;
                       }
                       employees[i][18] = "0";
                       employees[i][10] = "0";
                       employees[i][5] = "0";
                       if (employees[i][3].equals("Hourly")) {

                           employees[i][4] = "0";
                       }

                       employees[i][3] = in;

                       if (employees[i][3].equals("Hourly")) {

                           System.out.println("Informe o valor da hora: ");
                           employees[i][7] = input.nextLine();
                           employees[i][13] = "Semanal";
                           employees[i][4] = "0";
                           employees[i][14] = "sexta";
                           employees[i][8] = "false";
                           employees[i][12] = null;
                           employees[i][11] = "false";
                       } else {

                           System.out.println("Empregado é comissionado? [S/N]");
                           String option = input.nextLine();
                           if (employees[i][11] != null) {
                               while ((employees[i][11].equals("true") && option.equals("S")) ||
                                       (employees[i][11].equals("false") && option.equals("N"))) {
                                   System.out.println("Empregado já é a opção selecionada!");
                                   option = input.nextLine();
                               }
                           }
                           System.out.println("Informe o salario do empregado: ");
                           employees[i][4] = input.nextLine();

                           if (option.equals("S")) {

                               employees[i][11] = "true";
                               System.out.println("Informe o valor do percentual da comissão em (%): ");
                               double commission = input.nextDouble();
                               input.nextLine();
                               commission /= 100;
                               employees[i][9] = String.valueOf(commission);
                               employees[i][13] = "Bi-semanal";
                               employees[i][14] = "sexta";
                               employees[i][10] = "0";
                               employees[i][12] = null;
                           } else if (option.equals("N")){

                               employees[i][11] = "false";
                               employees[i][14] = null;
                               employees[i][12] = "30";
                               employees[i][13]= "Mensal";
                           }
                       }


                   } else if (option2 == 4) {

                       System.out.println("Novo método de pagamento: [Cheque por correios, Cheque em mãos, Depósito em conta bancaria]");

                       while (true) {

                           String meth = input.nextLine();
                           if (meth.equals("Cheque por correios") || meth.equals("Cheque em mãos") || meth.equals("Depósito em conta bancaria")) {
                               employees[i][6] = meth;
                               break;
                           }
                           System.out.println("Opção invalida, escolha novamente!!");
                       }

                   } else if (option2 == 5) {

                       System.out.println("Deseja participar de um sindicato? [S/N]");

                       String option3;
                       while (true) {

                           option3 = input.nextLine();
                           if (option3.equals("S") || option3.equals("N")) {
                               break;
                           }
                           System.out.println("Opção invalida, informe novamente!!");
                       }
                       if (option3.equals("S")) {

                           if (employees[i][17].equals("true")) {
                               System.out.println("Já pertence a um sindicato!!");
                               continue;
                           }
                           employees[i][17] = "true";
                           System.out.println("Informe o valor da taxa sindical: ");
                           employees[i][16] = input.nextLine();
                           System.out.println("Escolha a nova id do sindicato:");
                           String newId = input.nextLine();


                           while (true) {

                               boolean flag = true;
                               for (int j = 0; j < employees.length; j++) {

                                   if (employees[j][0] != null && employees[j][15].equals(newId)) {
                                       flag = false;
                                       break;
                                   }
                               }
                               if (flag) break;

                               System.out.println("Id já existente, escolha novamente!");
                               newId = input.nextLine();
                           }
                           employees[i][15] = newId;
                           System.out.println("Nova id selecionada com sucesso");
                       } else {

                           employees[i][17] = "false";
                           employees[i][16] = "0";
                       }
                   } else if (option2 == 6) {

                       if (employees[i][17].equals("true")) {

                           System.out.println("Escolha a nova id do sindicato:");
                           String newId = input.nextLine();


                           while (true) {

                               boolean flag = true;
                               for (int j = 0; j < employees.length; j++) {

                                   if (employees[j][0] != null && employees[j][15].equals(newId)) {
                                       flag = false;
                                       break;
                                   }
                               }
                               if (flag) break;
                               System.out.println("Id já existente, escolha novamente!");
                               newId = input.nextLine();
                           }
                           employees[i][15] = newId;
                           System.out.println("Nova id selecionada com sucesso");
                           continue;
                       }
                       System.out.println("Usuario não pertence a um sindicato!!");
                   } else if (option2 == 7) {

                       if (employees[i][17].equals("true")) {

                           System.out.println("Qual o valor da taxa sindical em (%)? ");
                           double sindicalTax = input.nextDouble();
                           input.nextLine();
                           sindicalTax /= 100;
                           employees[i][16] = String.valueOf(sindicalTax);
                           continue;
                       }
                       System.out.println("Empregado não pertence a um sindicato!!");
                   } else if (option2 == 8) {
                       listEmployees(employees);
                   }
               }
           }
       }
        System.out.println("Não existe um empregado com essa id!!");
    }

    public static void addServiceTax (String [][]employees , String id) {

        Scanner input = new Scanner(System.in);

        for (int i = 0; i < employees.length; i++) {

            if (employees[i][0] != null  && employees[i][0].equals(id)) {
                System.out.println("Informe o valor da taxa de serviço em (%): ");
                double tax = input.nextInt();
                tax = tax/100;
                double currentTax = Double.valueOf(employees[i][18]);
                currentTax += tax;
                employees[i][18] = String.valueOf(currentTax);
                System.out.println("Taxas atuais: " + employees[i][18]);
                return;
            }
        }
            System.out.println("Id invalida ou empregado inexistente!!");
    }

    public static void sales (String [][]employees, String id) {

        Scanner input = new Scanner(System.in);

        for (int i = 0; i < employees.length; i++) {

            if (employees[i][0] != null && employees[i][11].equals("true") && employees[i][0].equals(id)) {

                System.out.println("id: " + id);
                System.out.println("Informe o valor da venda: ");
                double saleValue = input.nextDouble();
                double currentSales = Double.valueOf(employees[i][10]);
                double comValue = Double.valueOf(employees[i][9]);
                currentSales += (saleValue * comValue);
                employees[i][10] = String.valueOf(currentSales);
                System.out.println("Total de comissao atual: " + employees[i][10]);
                return;
            } else if (employees[i][0] != null && employees[i][11].equals("false") && employees[i][0].equals(id)) {

                System.out.println("Empregado não comissionado!!");
                return;
            }
        }
        System.out.println("Não existe um comissionado com essa id!!!");
    }


    public static void listEmployees (String [][]employees) {

       boolean flag = false;

        for (int i = 0; i < employees.length; i++) {

            if (employees[i][0] != null) {
                flag = true;
                System.out.println("---------------------------------");
                System.out.println("Id: " + employees[i][0]);
                System.out.println("Nome: " + employees[i][1]);
                System.out.println("Endereço: " + employees[i][2]);
                System.out.println("Tipo: " + employees[i][3]);
                System.out.println("Dia do pagamento: " + employees[i][12]);
                System.out.println("Dia da semana do pagamento: " + employees[i][14]);
                System.out.println("Método de pagamento: " + employees[i][6]);
                System.out.println("Pagamento: " + employees[i][13]);

                if (employees[i][3].equals("Hourly")) {
                    System.out.println("Salario por hora: " + employees[i][7]);
                } else {
                    System.out.println("Salario: " + employees[i][4]);
                    System.out.println("Comissionado: " + employees[i][11]);
                    if (employees[i][11].equals("true")) {
                        System.out.println("Taxa de comissão: " + employees[i][9]);
                    }
                }
                System.out.println("Pertence a um sindicato: " + employees[i][17]);
                if (employees[i][17].equals("true")) {
                    System.out.println("Id sindicato: " + employees[i][15]);
                    System.out.println("Taxa sincical: " + employees[i][16]);
                }
                System.out.println("---------------------------------");
            }
        }
        if (!flag) {
            System.out.println("Não existe nenhum empregado cadastrado no sistema.");
        }
    }

    public static void hitPoint (String [][]employees) {

        Scanner input = new Scanner(System.in);

        int hourIn;
        int minuteIn;
        int hourOut;
        int minuteOut;

        System.out.println("Informe a id do empregado a bater ponto: ");
        String id = input.nextLine();
        int i;
        while (true) {
            boolean flag = false;
            for (i = 0; i < employees.length; i++) {

                if (employees[i][0] != null && employees[i][0].equals(id) && employees[i][3].equals("Hourly")) {
                    flag = true;
                    break;
                }
            }
            if (flag) break;
            System.out.println("Empregado invalido, escolha novamente!!");
            id = input.nextLine();
        }
        if (employees[i][8].equals("true")) {
            System.out.println("Empregado já bateu ponto hoje!");
            return;
        }
        System.out.println("Informe a hora em formato 24 hrs que o empregado entrou :  ");
        hourIn = input.nextInt();
        System.out.println("Informe os minutos em que o empregado entrou: ");
        minuteIn = input.nextInt();
        System.out.println("Informe a hora em formato 24 hrs em que o empregado saiu: ");
        hourOut = input.nextInt();
        System.out.println("Informe os minutos em que o empregado saiu: ");
        minuteOut = input.nextInt();

        double totalTime = Math.abs((hourOut * 60 + minuteOut) - (hourIn * 60 + minuteIn));

        while (totalTime < 480) {
            System.out.println("Empregado não cumpriu a quantidade minima de 8 hrs de trabalho!! Informe novamente");
            System.out.println("Informe a hora em formato 24 hrs em que o empregado saiu: ");
            hourOut = input.nextInt();
            System.out.println("Informe os minutos em que o empregado saiu: ");
            minuteOut = input.nextInt();
            totalTime = Math.abs((hourOut * 60 + minuteOut) - (hourIn * 60 + minuteIn));
        }

        double extraHour = 0;
        if (totalTime > 480) {
            extraHour = totalTime - 480;
            totalTime -= extraHour;
        }
        double hourSalary = Double.valueOf(employees[i][7]);
        System.out.println("Salario por hora: " + hourSalary);
        System.out.println("Hora extra: " + extraHour);
        System.out.println("Tempo total: " + totalTime);
        totalTime = totalTime/60;
        extraHour = extraHour/60;
        double total = (hourSalary * totalTime) + (extraHour * hourSalary * 1.5);
        double currentSalary = Double.valueOf(employees[i][4]);
        total += currentSalary;
        employees[i][4] = String.valueOf(total);
        employees[i][8] = "true";
        System.out.println("Empregado bateu ponto com sucesso!!");
    }

    public static void removeEmployee (String [][]employees, String id) {

        for (int i = 0; i < employees.length; i++) {

            if (employees[i][0] != null && employees[i][0].equals(id)) {

                for (int j = 0; j < 20; j++) {
                    employees[i][j] = null;
                }
                System.out.println("Removido com sucesso!!");
                return;
            }
        }
        System.out.println("Não existe um empregado com essa id!!");

    }


    public static void addEmployee (String [][]employees, String id) {

        Scanner input = new Scanner(System.in);
        int i;
        for (i = 0; i < employees.length; i++) {

            if (employees[i][0] == null) {
                break;
            }
        }
        employees[i][0] = id;
        System.out.println("Informe o nome do empregado: ");
        employees[i][1] = input.nextLine();
        System.out.println("Informe o endereço do empregado: ");
        employees[i][2]= input.nextLine();
        System.out.println("Informe o tipo de empregado: [Hourly/Salaried] ");

        while (true) {
            String in = input.nextLine();
            if (in.equals("Hourly") || in.equals("Salaried")) {
                employees[i][3] = in;
                break;
            }
            System.out.println("Opção invalida, escolha uma das duas opções acima!");
        }
        employees[i][5] = "0";

        if (employees[i][3].equals("Hourly")) {

            System.out.println("Informe o valor da hora: ");
            employees[i][7] = input.nextLine();
            employees[i][13] = "Semanal";
            employees[i][4] = "0";
            employees[i][14] = "sexta";
            employees[i][8] = "false";
            employees[i][10] = "0";
        } else {

            System.out.println("Informe o salario do empregado: ");
            employees[i][4] = input.nextLine();
            System.out.println("Empregado é comissionado? [S/N]");
            String option = input.nextLine();

            if (option.equals("S")) {

                employees[i][11] = "true";
                System.out.println("Informe o valor do percentual da comissão em (%): ");
                double commission = input.nextDouble();
                input.nextLine();
                commission /= 100;
                employees[i][9] = String.valueOf(commission);
                employees[i][13] = "Bi-semanal";
                employees[i][14] = "sexta";
                employees[i][10] = "0";
            } else if (option.equals("N")){

                employees[i][11] = "false";
                employees[i][12] = "30";
                employees[i][13]= "Mensal";
            }
        }

        System.out.println("Informe o método de pagamento do empregado: [Cheque por correios, Cheque em mãos, Depósito em conta bancaria] ");

        while (true) {

            String meth = input.nextLine();
            if (meth.equals("Cheque por correios") || meth.equals("Cheque em mãos") || meth.equals("Depósito em conta bancaria")) {
                employees[i][6] = meth;
                break;
            }
            System.out.println("Opção invalida, escolha novamente!!");
        }

        System.out.println("Empregado pertence a um sindicato? [S/N]");
        String option = input.nextLine();

        if (option.equals("S")) {

            employees[i][17] = "true";
            System.out.println("Qual o valor da taxa sindical em (%)? ");
            double sindicalTax = input.nextDouble();
            input.nextLine();
            sindicalTax /= 100;
            employees[i][16] = String.valueOf(sindicalTax);
            System.out.println("Identificação do sindicato do empregado: ");
            String syndicateId = input.nextLine();

            while (true) {
                boolean flag = true;
                for (int j = 0; j < employees.length; j++) {

                    if (employees[j][15] != null && employees[j][15].equals(syndicateId)) {
                        flag = false;
                        System.out.println("Já existe essa id, escolha novamente!");
                        break;
                    }
                }
                if (flag) {
                    break;
                }
                syndicateId = input.nextLine();
            }
            employees[i][15] = syndicateId;

        } else if (option.equals("N")) {

            employees[i][17] = "false";
            employees[i][16] = "0";
        }
        employees[i][18] = "0";
        System.out.println("Empregado cadastrado com sucesso!!");
    }

    public static void createPayment (String [][]employees, String id) {

        Scanner input = new Scanner(System.in);

        for (int i = 0; i < employees.length; i++) {

            if (employees[i][0] != null && employees[i][0].equals(id)) {

                System.out.println("Informe a nova forma de pagamento na seguinte forma: ");
                System.out.println("Se mensal digite: ['Mensal' dia escolhido ] ex: Mensal 7");
                System.out.println("Se semanal digite: ['Semanal' (1- 'Semanal', 2- 'Bi-semanal'), dia da semana] ex: [Semanal 1 sexta]");


                while (true) {
                    boolean flag = false;
                    String opt = input.nextLine();
                    String []aux = opt.split(" ");

                    if (aux[0].equals("Mensal")) {

                        int day = Integer.valueOf(aux[1]);
                        while (true) {

                            if (day > 0 && day < 31) {
                                employees[i][12] = String.valueOf(day);
                                break;
                            }
                            System.out.println("Dia invalido!! escolha novamente!!");
                            day = input.nextInt();
                        }
                        employees[i][13] = aux[0];
                        employees[i][14] = null;
                        flag = true;
                    } else if (aux[0].equals("Semanal")){

                        while (!aux[1].equals("1") && !aux[1].equals("2")) {

                            System.out.println("Formato invalido, escolha novamente: [1 ou 2]");
                            aux[1] = input.nextLine();
                        }
                        if (aux[1].equals("1")) {

                            employees[i][13] = "Semanal";
                        } else {
                            employees[i][13] = "Bi-semanal";
                        }
                        while (true) {

                            if (aux[2].equals("segunda") || aux[2].equals("terça") || aux[2].equals("quarta")
                                    || aux[2].equals("quinta") || aux[2].equals("sexta")) {
                                employees[i][14] = aux[2];
                                break;
                            }
                            System.out.println("Dia invalido, escolha novamente no seguinte formato: [segunda,terça,quarta,quinta,sexta]");
                            aux[2] = input.nextLine();
                        }
                        employees[i][12] = null;
                        flag = true;
                    }

                    if (flag) {
                        System.out.println("Agenda criado com sucesso");
                        return;
                    }
                    System.out.println("Formato invalido!!");
                }
            }
        }
        System.out.println("Não existe nenhum usuario com a id informada!!");
    }

    public static void addInUndoAndRedo (String [][]employees, String [][][] matriz, int day, int week, int month) {


        for (int i = 0; i < 100; i++) {

            if (matriz[i][0][0] == null) {

                for (int j = 0; j < 40; j++) {

                    for (int z = 0; z < 19; z++) {
                        matriz[i][j][z] = employees[j][z];
                    }
                    matriz[i][j][19] = String.valueOf(day);
                    matriz[i][j][20] = String.valueOf(week);
                    matriz[i][j][21] = String.valueOf(month);
                }
                break;
            }
        }
    }


    public static void undoAndRedoToEmployees (String [][]employees, String [][][]matriz, int day, int week, int month) {

        for (int i = 0; i < 100; i++) {

            if (matriz[i+1][0][0] == null) {

                for (int j = 0; j < 40; j++) {
                    for (int h = 0; h < 19; h++) {
                        employees[j][h] = matriz[i][j][h];
                    }
                }
                break;
            }
        }
    }

    public static void eraseLastIndex (String [][][]matriz) {

        for (int i = 0; i < 100; i++) {

            if (matriz[i+1][0][0] == null) {

                for (int j = 0; j < 40; j++) {
                    for (int h = 0; h < 22; h++) {
                        matriz[i][j][h] = null;
                    }
                }
                break;
            }
        }
    }

    public static void resetRedo (String [][][] redo) {

        for (int i = 0; i < 100; i++) {

            for (int j = 0; j < 40; j++) {

                for (int h = 0; h < 22; h++) {
                    redo[i][j][h] = null;
                }
            }
        }

    }

    public static void undo (String [][]employees, String [][][]undo, String [][][]redo, int day, int week, int month) {

        addInUndoAndRedo(employees,redo,day,week,month);
        undoAndRedoToEmployees(employees,undo,day,week,month);
        eraseLastIndex(undo);

    }

    public static void redo (String [][]employees, String [][][]undo, String [][][]redo, int day, int week, int month) {

        addInUndoAndRedo(employees,undo,day,week,month);
        undoAndRedoToEmployees(employees,redo,day,week,month);
        eraseLastIndex(redo);
    }

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        String [][] employees = new String [40][21];

        String [][][] undo = new String [100][40][22];
        String [][][] redo = new String [100][40][22];
        int sizeUndo = 0, sizeRedo = 0, qnt_employees = 0;
        String[] weeksName = {"segunda","terça","quarta","quinta","sexta", "sabado", "domingo"};
        String[] months = {"Janeiro", "Fevereiro", "Março", "Abril", "Maio","Junho","Julho","Agosto","Setembro","Outubro","Novembro","Dezembro"};

        int option, id = 1, days = 1, month = 1, week = 1;

        while (true) {

            if (days > 30) {
                days = 1;
                month += 1;
            }
            if (days % 7 == 0) {
                week += 1;
            }
            if (week == 5) {
                week = 1;
            }


            String currentWeekDay = weeksName[(days-1) % 7];
            String currentMonth = months[(month-1) % 12];
            System.out.println();
            System.out.println("-----------------------------------");
            System.out.println("Mês: " + currentMonth);
            System.out.println("Dia da semana: " + currentWeekDay);
            System.out.println("Dia do mês: " + days);
            System.out.println("Semanda atual: " + week);
            System.out.println("-----------------------------------");
            System.out.println();
            System.out.println("[1] - Adicionar empregado.");
            System.out.println("[2] - Remover empregado.");
            System.out.println("[3] - Lançar cartão de ponto (Somente Horistas).");
            System.out.println("[4] - Lançar um ponto de venda (Somente Comissionados).");
            System.out.println("[5] - Lançar uma taxa de serviço.");
            System.out.println("[6] - Alterar dados do empregado.");
            System.out.println("[7] - Rodar folha de pagamento.");
            System.out.println("[8] - Undo/Redo");
            System.out.println("[9] - Trocar agenda de pagamento.");
            System.out.println("[10] - Criar uma agenda de pagamento.");
            System.out.println("[11] - Listar empregados cadastrados no sistema.");
            System.out.println("[0] - Encerrar programa.");

            option = input.nextInt();
            input.nextLine();
            if (option == 0) {
                break;
            }

            if (option != 8 && option != 11 && qnt_employees != 0) {
                addInUndoAndRedo(employees,undo,days,week,month);
                resetRedo(redo);
                sizeRedo = 0;
                sizeUndo += 1;
            }

            if (option == 1) {

                addEmployee(employees,String.valueOf(id));
                id += 1;
                sizeUndo += 1;
                qnt_employees += 1;
            } else if (option == 2) {

                if (qnt_employees == 0) {
                    System.out.println("Não existe nenhum empregado cadastrado!!");
                    break;
                }
                System.out.println("Informe o id do empregado a remover");
                String removeId = input.nextLine();
                removeEmployee(employees,removeId);
                qnt_employees -= 1;

            } else if (option == 3) {

                hitPoint(employees);

            } else if (option == 4) {

                System.out.println("Informe o id do usuario a lançar um resultado de venda:");
                String userId = input.nextLine();
                sales(employees,userId);

            } else if (option == 5) {

                System.out.println("Informe o id do usuario a lançar uma taxa de serviço:");
                String userId = input.nextLine();
                addServiceTax(employees,userId);

            } else if (option == 6) {

                System.out.println("Informe o id do usuario a alterar os dados:");
                String userId = input.nextLine();
                changeDatas(employees,userId,undo,redo,sizeUndo,sizeRedo,days,week,month);

            } else if (option == 7) {

                if (qnt_employees == 0) {
                    System.out.println("Não há nenhum usuario cadastrado no sistema!!");
                    continue;
                }
                resetHitPoint(employees);
                payment(employees,days,week);
                System.out.println("Folha rodada para hoje com sucesso!!");
                days += 1;

            } else if (option == 8) {

                System.out.println("[1] - Undo");
                System.out.println("[2] - Redo");
                int op = input.nextInt();

                while (op != 1 && op != 2) {

                    System.out.println("Opção invalida, escolha novamente!!");
                    op = input.nextInt();
                }

                if (op == 1) {
                    if (sizeUndo == 0) {
                        System.out.println("Não é possivel!");
                        continue;
                    }
                    int days1 = 1,week1 = 1,month1 = 1;
                    for (int i = 0; i < 100; i++) {

                        if (undo[i+1][0][0] == null && undo[i] != null) {
                            days1 = Integer.valueOf(undo[i][0][19]);
                            week1 = Integer.valueOf(undo[i][0][20]);
                            month1 = Integer.valueOf(undo[i][0][21]);
                            break;
                        }
                    }
                    undo(employees,undo,redo,days,week,month);
                    days = days1;
                    week = week1;
                    month = month1;
                    sizeRedo += 1;
                    sizeUndo -= 1;
                } else {
                    if (sizeRedo == 0) {
                        System.out.println("Não é possivel!");
                        continue;
                    }
                    int days1 = 1,week1 = 1,month1 = 1;
                    for (int i = 0; i < 100; i++) {

                        if (redo[i+1][0][0] == null) {
                            days1 = Integer.valueOf(redo[i][0][19]);
                            week1 = Integer.valueOf(redo[i][0][20]);
                            month1 = Integer.valueOf(redo[i][0][21]);
                            break;
                        }
                    }
                    redo(employees,undo,redo,days,week,month);
                    days = days1;
                    week = week1;
                    month = month1;
                    sizeUndo += 1;
                    sizeRedo -= 1;
                }

            } else if (option == 9){

                System.out.println("Informe o id do usuario a alterar a agenda de pagamento:");
                String userId = input.nextLine();
                changePaymentDay(employees,userId);

            } else if (option == 10) {

                System.out.println("Informe o id do usuario a criar uma agenda de pagamento:");
                String userId = input.nextLine();
                createPayment(employees,userId);
            } else if (option == 11) {

                listEmployees(employees);
            }
            System.out.println();
            System.out.println("Pressione qualquer tecla para continuar...");
            input.nextLine();

        }

    }
}
