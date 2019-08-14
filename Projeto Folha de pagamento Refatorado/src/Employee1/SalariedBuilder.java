package Employee1;

public class SalariedBuilder implements EmployeeBuilder {

    private Employee salaried;

    public SalariedBuilder () {
        this.salaried = new Salaried();
    }

    @Override
    public EmployeeBuilder name(String name) {
        this.salaried.setName(name);
        return this;
    }

    @Override
    public EmployeeBuilder id(int id) {
        this.salaried.setId(id);
        return this;
    }

    @Override
    public EmployeeBuilder address(String address) {
        this.salaried.setAddress(address);
        return this;
    }

    @Override
    public EmployeeBuilder payMethod(String payMethod) {
        this.salaried.setPayMethod(payMethod);
        return this;
    }

    @Override
    public EmployeeBuilder payDay(int payDay) {
        this.salaried.setPayDay(payDay);
        return this;
    }

    @Override
    public EmployeeBuilder payWeek(String payWeek) {
        this.salaried.setPayWeek(payWeek);
        return this;
    }

    @Override
    public EmployeeBuilder paySchedule(String paySchedule) {
        this.salaried.setPaySchedule(paySchedule);
        return this;
    }

    @Override
    public EmployeeBuilder salary(double salary) {
        this.salaried.setSalary(salary);
        return this;
    }

    @Override
    public Person builder() {
        return salaried;
    }
}
