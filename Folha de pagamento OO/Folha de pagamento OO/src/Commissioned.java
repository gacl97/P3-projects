import java.util.ArrayList;

public class Commissioned extends Employee {

    private Exceptions exceptions = new Exceptions();
    private double salary;
    private double amountCommission;
    private double salesResult;


    public Commissioned (String name, String adress, int id, double salary, String payMeth, double Commission) {

        setName(name);
        setAddress(adress);
        setId(id);
        setPayMeth(payMeth);
        setSalary(salary);
        setAmountCommission(Commission);
        setPaymentSche("Bi-semanal");
        setPayWeek("Sexta-feira");
    }

    public void createEmployee (ArrayList<Employee> employees, int id) {

        Commissioned newCommissioned = new Commissioned();
        newCommissioned.setId(id);
        System.out.println("######## Adicionar Comissionado ########");
        System.out.println();
        System.out.println("Nome: ");
        String name = input.nextLine();
        while (name.equals("") || name.equals(" ")) {
            System.out.println("Campo nome não pode ser vazio.");
            name = input.nextLine();
        }
        newCommissioned.setName(name);
        System.out.println("Endereço:");
        String address = input.nextLine();
        newCommissioned.setAddress(address);
        System.out.println("Salário mensal:");
        newCommissioned.salary = exceptions.doubleNumber();
        System.out.println("Porcentagem da comissão: [%]");
        newCommissioned.amountCommission = exceptions.doubleNumber();

        while (newCommissioned.amountCommission > 100) {

            System.out.println("Valor invalido, informe novamente.");
            newCommissioned.amountCommission = exceptions.doubleNumber();

        }
        newCommissioned.amountCommission /= 100;

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

            newCommissioned.setPayMeth("Cheque por correios");

        } else if (meth.equals("2")) {

            newCommissioned.setPayMeth("Cheque em mãos");

        } else {

            newCommissioned.setPayMeth("Depósito em conta bancária");

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

    public boolean salesResults (ArrayList<Employee> employees, int id) {

        for (Employee e: employees) {

            if (e.getId() == id && e instanceof Commissioned) {

                System.out.println("Informe o valor da venda: ");
                double saleValue = exceptions.doubleNumber();
                ((Commissioned) e).salesResult += saleValue * ((Commissioned) e).getAmountCommission();
                System.out.println("######################################");
                System.out.println();
                System.out.println("Valor total da venda: " + saleValue);
                System.out.println("Total ganho com a comissão: " + saleValue * ((Commissioned) e).getAmountCommission());
                System.out.println("Total atual: " + ((Commissioned) e).getSalesResult());
                System.out.println("Resultado de venda confirmado.");
                System.out.println();
                System.out.println("######################################");
                return true;
            }
        }
        System.out.println("Empregado não existe ou não é comissionado.");
        return false;
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
}
