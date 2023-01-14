package Service;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Date;

public class Appointment implements Serializable {
    private LocalTime startAppointment;
    private LocalTime endAppointment;
    private boolean isBooked;
    private Date dateOfAppointment;
    private int feeOfAppointment;


    public Appointment(boolean isBooked, LocalTime startAppointment, LocalTime endAppointment) {
        this.isBooked = isBooked;
        this.startAppointment = startAppointment;
        this.endAppointment = endAppointment;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void setBooked(boolean booked) {
        isBooked = booked;
    }

    public LocalTime getStartAppointment() {
        return startAppointment;
    }

    public void setStartAppointment(LocalTime startAppointment) {
        this.startAppointment = startAppointment;
    }

    public LocalTime getEndAppointment() {
        return endAppointment;
    }

    public void setEndAppointment(LocalTime endAppointment) {
        this.endAppointment = endAppointment;
    }

    @Override
    public String toString() {
        return "Date of appointment: " + dateOfAppointment +
                "\tFee: " + feeOfAppointment;

    }
}

