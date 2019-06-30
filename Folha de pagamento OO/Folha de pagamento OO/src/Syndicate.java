import java.util.ArrayList;
import java.util.Scanner;

public class Syndicate {

    private Exceptions exceptions = new Exceptions();
    private Scanner input = new Scanner(System.in);
    private boolean belongsTo = true;
    private int id; // Identificação
    private double monthlyFee; // Taxa mensal
    private double serviceCharges; // Taxas de serviços


    public Syndicate () {}

    public boolean serviceCharges (ArrayList <Employee> employees, int id) {

        for (Employee e: employees) {

            if (e.getId() == id && e.getSyndicate() != null) {
                System.out.println("Informe o valor da taxa de serviço [%]");
                double charges = exceptions.doubleNumber();
                charges /= 100;
                e.getSyndicate().serviceCharges += charges;
                System.out.println("#############################");
                System.out.println();
                System.out.println("Taxas atuais: " + e.getSyndicate().getServiceCharges());
                System.out.println();
                System.out.println("#############################");
                System.out.println();
                System.out.println("Taxa adicionada com sucesso.");
                return true;
            }
        }
        System.out.println("Empregado não existe ou não pertece a um sindicato.");
        return false;
    }

    public boolean verifyId (ArrayList <Employee> employees, int id) {

        for (Employee e: employees) {

            if (e.getSyndicate() != null) {
                if (e.getSyndicate().id == id) {
                    return true;
                }
            }
        }
        return false;
    }


    public boolean isBelongsTo() {
        return belongsTo;
    }

    public void setBelongsTo(boolean belongsTo) {
        this.belongsTo = belongsTo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getMonthlyFee() {
        return monthlyFee;
    }

    public void setMonthlyFee(double monthlyFee) {
        this.monthlyFee = monthlyFee;
    }

    public double getServiceCharges() {
        return serviceCharges;
    }

    public void setServiceCharges(double serviceCharges) {
        this.serviceCharges = serviceCharges;
    }
}
