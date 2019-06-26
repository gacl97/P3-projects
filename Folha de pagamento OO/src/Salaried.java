import java.util.ArrayList;

public class Salaried extends Employee {

    private double salary;

    public Salaried (String name, String adress, int id, double salary, String payMeth) {

        setName(name);
        setAdress(adress);
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
        newSalaried.setName(name);
        System.out.println("Endereço:");
        String adress = input.nextLine();
        newSalaried.setAdress(adress);
        System.out.println("Salário mensal:");

        boolean flag = true;
        while (flag) {

            try {

                newSalaried.salary = Double.parseDouble(input.nextLine());
                if (newSalaried.salary > 0) {
                    flag = false;
                } else {
                    System.out.println("Salário deve ser positivo");
                }
            } catch (NumberFormatException e) {
                System.out.println("Apenas números");
            }
        }
        System.out.println("Informe o método de pagamento: [Cheque por correios, Cheque em mãos, Depósito em conta bancaria]");
        String meth = input.nextLine();

        while (true) {

            if (meth.equals("Cheque por correios") || meth.equals("Cheque em mãos") || meth.equals("Depósito em conta bancaria")) {
                newSalaried.setPayMeth(meth);
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
        newSalaried.setPayDay(31);
        newSalaried.setPaymentSche("Mensal");
        newSalaried.setSyndicate(syndicate,employees);
        employees.add(newSalaried);
    }

    public double getSalary() {return salary;}

    public void setSalary(double salary) {this.salary = salary;}

}
