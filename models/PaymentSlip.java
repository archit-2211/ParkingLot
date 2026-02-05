package models;



public class PaymentSlip {

    private final Ticket ticket;
    private final double amount;

    public PaymentSlip(Ticket ticket, double amount) {
        this.ticket = ticket;
        this.amount = amount;
    }

    public void print() {
        System.out.println("Vehicle: " + ticket.getVehicle().getVehicleNumber());
        System.out.println("Amount Paid: ₹" + amount);
    }
}
