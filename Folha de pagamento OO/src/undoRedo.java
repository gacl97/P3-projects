import java.util.ArrayList;

public interface undoRedo {

    ArrayList add (ArrayList<Employee> employees);

    Calendar addCalendar (Calendar calendar1);

    void undoOrRedoToEmployee (ArrayList<Employee> employees, ArrayList <Employee> undoOrRedo);
}
