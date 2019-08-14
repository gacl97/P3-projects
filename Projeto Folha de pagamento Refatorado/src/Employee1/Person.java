package Employee1;

import Exceptions1.*;
import Syndicate1.*;
import java.util.ArrayList;

public interface Person {
    Exceptions exceptions = new Exceptions();
    void setName(String name);
    void setId(int id);
    void setAddress(String address);
    void setPayMethod(String payMethod);
    void setPayDay(int payDay);
    void setPayWeek(String payWeek);
    void setPaySchedule(String paySchedule);
    void setSalary (double salary);
    void setDatas(Person employee, int id, ArrayList<Person> employees);
    String getName();
    String getPayWeek();
    String getAddress();
    String getPayMethod();
    String getPaySchedule();
    int getId();
    int getPayDay();
    double getSalary ();

    //void listEmployees (ArrayList<Person> employees);

    void setSyndicate(boolean syndicate);
    void setSyndicate (int syndicateId, double monthlyFee, double serviceCharges);
    Syndicate getSyndicate ();
}
