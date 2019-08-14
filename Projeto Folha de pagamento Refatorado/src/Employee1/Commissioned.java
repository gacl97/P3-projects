package Employee1;

import Syndicate1.Syndicate;

import java.util.ArrayList;

public class Commissioned extends Employee {

    private double salesResult;
    private double amountCommission;

    @Override
    public void setDatas(Person employee, int id, ArrayList<Person> allEmployees) {

        employee.setId(id);
        System.out.println("######## Adicionar Comissionado ########");
        System.out.println();
        new EmployeeDatas().basicDatas(employee,allEmployees);
        System.out.println("Salário mensal:");
        employee.setSalary(exceptions.doubleNumber());
        System.out.println("Porcentagem da comissão: [%]");
        double amountCommission = exceptions.doubleNumber();

        while (amountCommission > 100) {

            System.out.println("Valor invalido, informe novamente.");
            amountCommission = exceptions.doubleNumber();
        }
        amountCommission /= 100;
        ((Commissioned)employee).setAmountCommission(amountCommission);
        employee.setPayWeek("Sexta-feira");
        employee.setPaySchedule("Bi-semanal");
        allEmployees.add(employee);
    }

    public void setSalesResult(double salesResult) {this.salesResult = salesResult;}

    public void setAmountCommission (double amountCommission) {this.amountCommission = amountCommission;}

    @Override
    public void setName(String name) {this.name = name;}

    @Override
    public void setId(int id) {this.id = id;}

    @Override
    public void setAddress(String address) {this.address = address;}

    @Override
    public void setPayMethod(String payMethod) {this.payMethod = payMethod;}

    @Override
    public void setPayDay(int payDay) {this.payDay = payDay;}

    @Override
    public void setPayWeek(String payWeek) {this.payWeek = payWeek;}

    @Override
    public void setPaySchedule(String paySchedule) {this.paySchedule = paySchedule;}

    @Override
    public void setSalary(double salary) {this.salary = salary;}

    @Override
    public String getName() {return this.name;}

    @Override
    public int getId() {return this.id;}

    @Override
    public String getAddress() {return this.address;}

    @Override
    public String getPayMethod() {return this.payMethod;}

    @Override
    public int getPayDay() {return this.payDay;}

    @Override
    public String getPayWeek() {return this.payWeek;}

    @Override
    public String getPaySchedule() {return this.paySchedule;}

    @Override
    public double getSalary() {return this.salary;}

    public double getAmountCommission() {return this.amountCommission;}

    public double getSalesResult() {return this.salesResult;}

    public void setSyndicate(boolean syndicate) {
        if (syndicate) {this.syndicate = new Syndicate();}
        else {this.syndicate = null;}
    }
    public void setSyndicate (int syndicateId, double monthlyFee, double serviceCharges) {
        if (this.syndicate == null) {this.syndicate = new Syndicate();}
        this.syndicate.setId(syndicateId);
        this.syndicate.setMonthlyFee(monthlyFee);
        this.syndicate.setServiceCharges(serviceCharges);
    }

    public Syndicate getSyndicate () {return this.syndicate;}
}
