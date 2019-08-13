package Employee1;

public class CommissionedBuilder implements EmployeeBuilder {

    private Employee commissioned;

    public CommissionedBuilder () {
        this.commissioned = new Commissioned();
    }

    @Override
    public EmployeeBuilder name(String name) {
        this.commissioned.setName(name);
        return this;
    }

    @Override
    public EmployeeBuilder id(int id) {
        this.commissioned.setId(id);
        return this;
    }

    @Override
    public EmployeeBuilder address(String address) {
        this.commissioned.setAddress(address);
        return this;
    }

    @Override
    public EmployeeBuilder payMethod(String payMethod) {
        this.commissioned.setPayMethod(payMethod);
        return this;
    }

    @Override
    public EmployeeBuilder payDay(int payDay) {
        this.commissioned.setPayDay(payDay);
        return this;
    }

    @Override
    public EmployeeBuilder payWeek(String payWeek) {
        this.commissioned.setPayWeek(payWeek);
        return this;
    }

    @Override
    public EmployeeBuilder paySchedule(String paySchedule) {
        this.commissioned.setPaySchedule(paySchedule);
        return this;
    }

    @Override
    public EmployeeBuilder salary(double salary) {
        this.commissioned.setSalary(salary);
        return this;
    }

    @Override
    public Person builder() {
        return commissioned;
    }
}
