import java.util.ArrayList;

public class Undo implements undoRedo {

    @Override
    public ArrayList add (ArrayList <Employee> employees) {

        ArrayList<Employee> newEmployees = new ArrayList<Employee>();

        for (Employee e: employees) {

            if (e instanceof Hourly) {
                Hourly newHourly = new Hourly();
                newHourly.setId(e.getId());
                newHourly.setName(e.getName());
                newHourly.setAdress(e.getAdress());
                newHourly.setPayMeth(e.getPayMeth());
                newHourly.setPaymentSche(e.getPaymentSche());
                newHourly.setPayDay(e.getPayDay());
                newHourly.setPayWeek(e.getPayWeek());
                newHourly.setHourSalary(((Hourly) e).getHourSalary());
                newHourly.setTotalSalary(((Hourly) e).getTotalSalary());
                newHourly.setTimeCard(((Hourly) e).isTimeCard());
                if (e.getSyndicate() == null) {
                    newHourly.setSyndicate("N",employees);
                } else {
                    newHourly.setSyndicate(e.getSyndicate().getId(), e.getSyndicate().getMonthlyFee(),e.getSyndicate().getServiceCharges());
                }
                newEmployees.add(newHourly);
            } else if (e instanceof Salaried) {
                Salaried newSalaried = new Salaried();
                newSalaried.setId(e.getId());
                newSalaried.setName(e.getName());
                newSalaried.setAdress(e.getAdress());
                newSalaried.setPayMeth(e.getPayMeth());
                newSalaried.setPaymentSche(e.getPaymentSche());
                newSalaried.setPayDay(e.getPayDay());
                newSalaried.setPayWeek(e.getPayWeek());
                newSalaried.setSalary(((Salaried) e).getSalary());
                if (e.getSyndicate() == null) {
                    newSalaried.setSyndicate("N",employees);
                } else {
                    newSalaried.setSyndicate(e.getSyndicate().getId(), e.getSyndicate().getMonthlyFee(),e.getSyndicate().getServiceCharges());
                }
                newEmployees.add(newSalaried);
            } else if (e instanceof  Commissioned) {
                Commissioned newCommissioned = new Commissioned();
                newCommissioned.setId(e.getId());
                newCommissioned.setName(e.getName());
                newCommissioned.setAdress(e.getAdress());
                newCommissioned.setPayMeth(e.getPayMeth());
                newCommissioned.setPaymentSche(e.getPaymentSche());
                newCommissioned.setPayDay(e.getPayDay());
                newCommissioned.setPayWeek(e.getPayWeek());
                newCommissioned.setSalary(((Commissioned) e).getSalary());
                newCommissioned.setAmountCommission(((Commissioned) e).getAmountCommission());
                newCommissioned.setSalesResult(((Commissioned) e).getSalesResult());
                if (e.getSyndicate() == null) {
                    newCommissioned.setSyndicate("N",employees);
                } else {
                    newCommissioned.setSyndicate(e.getSyndicate().getId(), e.getSyndicate().getMonthlyFee(),e.getSyndicate().getServiceCharges());
                }
                newEmployees.add(newCommissioned);
            }
        }
        return newEmployees;
    }

    @Override
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

    @Override
    public void undoOrRedoToEmployee(ArrayList<Employee> employees, ArrayList <Employee> undo) {

        employees.clear();
        employees.addAll(undo);
    }

}
