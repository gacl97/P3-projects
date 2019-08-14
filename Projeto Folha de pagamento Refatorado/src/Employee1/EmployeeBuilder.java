package Employee1;
import Exceptions1.*;

public interface EmployeeBuilder {

    EmployeeBuilder name(String name);
    EmployeeBuilder id(int id);
    EmployeeBuilder address(String address);
    EmployeeBuilder payMethod(String payMethod);
    EmployeeBuilder payDay(int payDay);
    EmployeeBuilder payWeek(String payWeek);
    EmployeeBuilder paySchedule(String paySchedule);
    EmployeeBuilder salary(double salary);

    Person builder ();
}
