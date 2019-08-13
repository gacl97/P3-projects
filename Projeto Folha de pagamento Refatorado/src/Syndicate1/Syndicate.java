package Syndicate1;

import Employee1.Person;
import Exceptions1.Exceptions;

import java.util.ArrayList;
import java.util.Scanner;

public class Syndicate {

    private Exceptions exceptions = new Exceptions();
    private Scanner input = new Scanner(System.in);
    private int id; // Identificação
    private double monthlyFee; // Taxa mensal
    private double serviceCharges; // Taxas de serviços

    public void syndicateDatas(ArrayList<Person> employees) {


        System.out.println("######## Sindicato ########");
        System.out.println();
        System.out.println("ID do sindicato: ");
        int syndicateId = exceptions.intNumber();

        while (verifyId(employees, syndicateId)) {
            System.out.println("Já existe um empregado com essa ID, escolha novamente!");
            syndicateId = exceptions.intNumber();
        }

        System.out.println("Taxa mensal em [%]: ");
        double monthlyFee = exceptions.doubleNumber();

        while (monthlyFee > 100) {

            System.out.println("Valor invalido, informe novamente.");
            monthlyFee = exceptions.doubleNumber();
        }
        monthlyFee /= 100;

        this.id = syndicateId;
        this.monthlyFee = monthlyFee;
    }

    public boolean serviceCharges (ArrayList <Person> employees, int id) {

        for (Person e: employees) {

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

    public boolean verifyId (ArrayList <Person> employees, int id) {

        for (Person e: employees) {

            if (e.getSyndicate() != null) {
                if (e.getSyndicate().id == id) {
                    return true;
                }
            }
        }
        return false;
    }

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public double getMonthlyFee() {return monthlyFee;}

    public void setMonthlyFee(double monthlyFee) {this.monthlyFee = monthlyFee;}

    public double getServiceCharges() {return serviceCharges;}

    public void setServiceCharges(double serviceCharges) {this.serviceCharges = serviceCharges;}
}