package Service;

import java.io.Serializable;
import java.util.Date;

public class Appointment implements Serializable {

    private Date dateOfAppointment;
    private int feeOfAppointment;

    public Appointment(Date dateOfAppointment, int feeOfAppointment) {
        this.dateOfAppointment = dateOfAppointment;
        this.feeOfAppointment = feeOfAppointment;
    }

    public Date getDateOfAppointment() {
        return dateOfAppointment;
    }

    public void setDateOfAppointment(Date dateOfAppointment) {
        this.dateOfAppointment = dateOfAppointment;
    }

    public int getFeeOfAppointment() {
        return feeOfAppointment;
    }

    public void setFeeOfAppointment(int feeOfAppointment) {
        this.feeOfAppointment = feeOfAppointment;
    }

    @Override
    public String toString() {
        return "Date of appointment: " + dateOfAppointment +
                "\tFee: " + feeOfAppointment;

    }
}
