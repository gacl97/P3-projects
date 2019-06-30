import java.util.ArrayList;

public class Salaried extends Employee {

    private Exceptions exceptions = new Exceptions();
    private double salary;

    public Salaried (String name, String adress, int id, double salary, String payMeth) {

        setName(name);
        setAddress(adress);
        setId(id);
        setPayMeth(payMeth);
        setSalary(salary);
        setPaymentSche("Mensal");
        setPayDay(31);
    }

    public Salaried () {}

    public void createEmployee (ArrayList<Employee> employees, int id) {

        Salaried newSalaried = new Salaried();
        newSalaried.setId(id);
        System.out.println("######## Adicionar Assalariado ########");
        System.out.println();
        System.out.println("Nome: ");
        String name = input.nextLine();
        while (name.equals("") || name.equals(" ")) {
            System.out.println("Campo nome não pode ser vazio.");
            name = input.nextLine();
        }
        newSalaried.setName(name);
        System.out.println("Endereço:");
        String address = input.nextLine();
        newSalaried.setAddress(address);
        System.out.println("Salário mensal:");
        newSalaried.salary = exceptions.doubleNumber();

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

            newSalaried.setPayMeth("Cheque por correios");

        } else if (meth.equals("2")) {

            newSalaried.setPayMeth("Cheque em mãos");

        } else {

            newSalaried.setPayMeth("Depósito em conta bancária");

        }
        System.out.println("Pertence a um sindicato: [S/N]");

        String syndicate = input.nextLine();

        while (!syndicate.equals("S") && !syndicate.equals("N")) {

            System.out.println("Opção invalida, escolha novamente!!");
            syndicate = input.nextLine();
        }
        newSalaried.setPayDay(31);
        newSalaried.setPaymentSche("Mensal");
        newSalaried.setSyndicate(syndicate,employees);
        employees.add(newSalaried);
    }

    public double getSalary() {return salary;}

    public void setSalary(double salary) {this.salary = salary;}

}
