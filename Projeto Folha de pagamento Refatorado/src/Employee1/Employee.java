package Employee1;

import java.util.Scanner;
import Syndicate1.*;

public abstract class Employee implements Person{

    protected String name; // Nome
    protected int id; // Identificação
    protected String address; // Endereço
    protected String payMethod; // Método de pagamento do empregado
    protected int payDay; // Dia do mes para pagamento
    protected String payWeek; // Dia da para pagamento
    protected String paySchedule;
    protected double salary;
    protected Scanner input = new Scanner(System.in);
    protected Syndicate syndicate;

}
