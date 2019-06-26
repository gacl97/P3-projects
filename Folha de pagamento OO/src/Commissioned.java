import java.util.ArrayList;

public class Commissioned extends Employee {

    private double salary;
    private double amountCommission;
    private double salesResult;


    public Commissioned (String name, String adress, int id, double salary, String payMeth, double Commission) {

        setName(name);
        setAdress(adress);
        setId(id);
        setPayMeth(payMeth);
        setSalary(salary);
        setAmountCommission(Commission);
        setPaymentSche("Bi-semanal");
        setPayWeek("Sexta-feira");
    }

    public Commissioned () {}

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public double getAmountCommission() {
        return amountCommission;
    }

    public void setAmountCommission(double amountCommission) {
        this.amountCommission = amountCommission;
    }

    public double getSalesResult() {
        return salesResult;
    }

    public void setSalesResult(double salesResult) {
        this.salesResult = salesResult;
    }

    public void createEmployee (ArrayList<Employee> employees, int id) {

        Commissioned newCommissioned = new Commissioned();
        newCommissioned.setId(id);
        System.out.println("######## Adicionar Comissionado ########");
        System.out.println();
        System.out.println("Nome: ");
        String name = input.nextLine();
        newCommissioned.setName(name);
        System.out.println("Endereço:");
        String adress = input.nextLine();
        newCommissioned.setAdress(adress);
        boolean flag = true;
        System.out.println("Salário mensal:");
        while (flag) {

            try {

                newCommissioned.salary = Double.parseDouble(input.nextLine());
                if (newCommissioned.salary > 0) {
                    flag = false;
                } else {
                    System.out.println("Salário deve ser positivo");
                }
            } catch (NumberFormatException e) {
                System.out.println("Apenas números");
            }
        }
        flag = true;
        System.out.println("Porcentagem da comissão: [%]");
        while (flag) {

            try {

                newCommissioned.amountCommission = Double.parseDouble(input.nextLine());
                if (newCommissioned.amountCommission > 0) {
                    newCommissioned.amountCommission /= 100;
                    flag = false;
                } else {
                    System.out.println("Comissão deve ser positivo");
                }
            } catch (NumberFormatException e) {
                System.out.println("Apenas números");
            }
        }

        System.out.println("Informe o método de pagamento: [Cheque por correios, Cheque em mãos, Depósito em conta bancaria]");
        String meth = input.nextLine();

        while (true) {

            if (meth.equals("Cheque por correios") || meth.equals("Cheque em mãos") || meth.equals("Depósito em conta bancaria")) {
                newCommissioned.setPayMeth(meth);
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
        newCommissioned.setPayWeek("Sexta-feira");
        newCommissioned.setPaymentSche("Bi-semanal");
        newCommissioned.setSyndicate(syndicate,employees);
        employees.add(newCommissioned);
    }

    public void salesResults (ArrayList<Employee> employees, int id) {

        for (Employee e: employees) {

            if (e.getId() == id && e instanceof Commissioned) {

                System.out.println("Informe o valor da venda: ");
                double saleValue = 0;
                boolean flag = true;
                while (flag) {

                    try {

                        saleValue = Double.parseDouble(input.nextLine());
                        if (saleValue > 0) {
                            flag = false;
                        } else {
                            System.out.println("Valor da venda deve ser positivo");
                        }
                    } catch (NumberFormatException ee) {
                        System.out.println("Apenas números");
                    }
                }

                ((Commissioned) e).salesResult += saleValue * ((Commissioned) e).getAmountCommission();
                System.out.println("######################################");
                System.out.println();
                System.out.println("Valor total da venda: " + saleValue);
                System.out.println("Total ganho com a comissão: " + saleValue * ((Commissioned) e).getAmountCommission());
                System.out.println("Total atual: " + ((Commissioned) e).getSalesResult());
                System.out.println("Resultado de venda confirmado.");
                System.out.println();
                System.out.println("######################################");
                return;
            }
        }
        System.out.println("Empregado não existe ou não é comissionado.");
    }
}
