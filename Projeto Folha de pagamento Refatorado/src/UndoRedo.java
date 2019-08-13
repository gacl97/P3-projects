import Calendar1.Calendar;
import Employee1.Commissioned;
import Employee1.Hourly;
import Employee1.Person;
import Employee1.Salaried;

import java.util.ArrayList;

public class UndoRedo {

    public ArrayList<Person> add (ArrayList <Person> employees) {

        ArrayList<Person> newEmployees = new ArrayList<Person>();
        for (Person e: employees) {

            if (e instanceof Hourly) {
                Hourly newHourly = new Hourly();
                newHourly.setId(e.getId());
                newHourly.setName(e.getName());
                newHourly.setAddress(e.getAddress());
                newHourly.setPayMethod(e.getPayMethod());
                newHourly.setPaySchedule(e.getPaySchedule());
                newHourly.setPayDay(e.getPayDay());
                newHourly.setPayWeek(e.getPayWeek());
                newHourly.setSalary(e.getSalary());
                newHourly.setTotalSalary(((Hourly) e).getTotalSalary());
                newHourly.setTimeCard(((Hourly) e).isTimeCard());
                if (e.getSyndicate() == null) {
                    newHourly.setSyndicate(false);
                } else {
                    newHourly.setSyndicate(e.getSyndicate().getId(), e.getSyndicate().getMonthlyFee(),e.getSyndicate().getServiceCharges());
                }
                newEmployees.add(newHourly);
            } else if (e instanceof Salaried) {
                Salaried newSalaried = new Salaried();
                newSalaried.setId(e.getId());
                newSalaried.setName(e.getName());
                newSalaried.setAddress(e.getAddress());
                newSalaried.setPayMethod(e.getPayMethod());
                newSalaried.setPaySchedule(e.getPaySchedule());
                newSalaried.setPayDay(e.getPayDay());
                newSalaried.setPayWeek(e.getPayWeek());
                newSalaried.setSalary(e.getSalary());
                if (e.getSyndicate() == null) {
                    newSalaried.setSyndicate(false);
                } else {
                    newSalaried.setSyndicate(e.getSyndicate().getId(), e.getSyndicate().getMonthlyFee(),e.getSyndicate().getServiceCharges());
                }
                newEmployees.add(newSalaried);
            } else if (e instanceof  Commissioned) {
                Commissioned newCommissioned = new Commissioned();
                newCommissioned.setId(e.getId());
                newCommissioned.setName(e.getName());
                newCommissioned.setAddress(e.getAddress());
                newCommissioned.setPayMethod(e.getPayMethod());
                newCommissioned.setPaySchedule(e.getPaySchedule());
                newCommissioned.setPayDay(e.getPayDay());
                newCommissioned.setPayWeek(e.getPayWeek());
                newCommissioned.setSalary(e.getSalary());
                newCommissioned.setAmountCommission(((Commissioned) e).getAmountCommission());
                newCommissioned.setSalesResult(((Commissioned) e).getSalesResult());
                if (e.getSyndicate() == null) {
                    newCommissioned.setSyndicate(false);
                } else {
                    newCommissioned.setSyndicate(e.getSyndicate().getId(), e.getSyndicate().getMonthlyFee(),e.getSyndicate().getServiceCharges());
                }
                newEmployees.add(newCommissioned);
            }
        }
        return newEmployees;
    }


    public Calendar addCalendar (Calendar calendar1) {

        Calendar calendarAux = new Calendar();
        calendarAux.setPayWeek(calendar1.getPayWeek());
        calendarAux.setPaySche(calendar1.getPaySche());
        calendarAux.setPayDay(calendar1.getPayDay());
        calendarAux.setCurrentDay(calendar1.getCurrentDay());
        calendarAux.setCurrentDayAux(calendar1.getCurrentDayAux());
        calendarAux.setCurrentMonth(calendar1.getCurrentMonth());
        calendarAux.setCurrentWeek(calendar1.getCurrentWeek());
        calendarAux.setYear(calendar1.getYear());
        return calendarAux;
    }

    public void undoOrRedoToEmployee(ArrayList<Person> employees, ArrayList <Person> undoOrRedo) {

        employees.clear();
        employees.addAll(undoOrRedo);
    }

}
