package Employee1;

public class HourlyBuilder implements EmployeeBuilder{

    private Employee hourly;

    HourlyBuilder() {
        this.hourly = new Hourly();
    }

    @Override
    public EmployeeBuilder name(String name) {
        this.hourly.setName(name);
        return this;
    }

    @Override
    public EmployeeBuilder id(int id) {
        this.hourly.setId(id);
        return this;
    }

    @Override
    public EmployeeBuilder address(String address) {
        this.hourly.setAddress(address);
        return this;
    }

    @Override
    public EmployeeBuilder payMethod(String payMethod) {
        this.hourly.setPayMethod(payMethod);
        return this;
    }

    @Override
    public EmployeeBuilder payDay(int payDay) {
        this.hourly.setPayDay(payDay);
        return this;
    }

    @Override
    public EmployeeBuilder payWeek(String payWeek) {
        this.hourly.setPayWeek(payWeek);
        return this;
    }

    @Override
    public EmployeeBuilder paySchedule(String paySchedule) {
        this.hourly.setPaySchedule(paySchedule);
        return this;
    }


    @Override
    public EmployeeBuilder salary(double salary) {
        this.hourly.setSalary(salary);
        return this;
    }

    @Override
    public Person builder() {
        return hourly;
    }
}
