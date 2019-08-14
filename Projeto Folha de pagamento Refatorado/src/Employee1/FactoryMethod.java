package Employee1;

public class FactoryMethod {


     public Person getEmployeeType (String type)  {

        if (type.equals("hourly")) {
            return new HourlyBuilder().builder();
        } else if (type.equals("salaried")) {
            return new SalariedBuilder().builder();
        } else if (type.equals("commissioned")) {
            return new CommissionedBuilder().builder();
        }
        return null;
    }
    public FactoryMethod() {}
}
