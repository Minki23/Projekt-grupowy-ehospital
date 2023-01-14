package Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Workday {

    private List<Appointment> appointments;

    private LocalDate date;

    private LocalTime startTime;

    private LocalTime endTime;


    public Workday(){
        date=LocalDate.of(2023,2,1);
        startTime=LocalTime.of(12,0);
        endTime=LocalTime.of(18,0);
        appointments=createAppointments();
    }

    public List<Appointment> createAppointments(){
        for(LocalTime tmp = startTime; tmp.isBefore(endTime);tmp=tmp.plusHours(1)){
            appointments.add(new Appointment(false,startTime,startTime=startTime.plusHours(1)));
        }
        return appointments;
    }

    public Workday(LocalDate date, LocalTime startTime, LocalTime endTime) {
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.appointments = createAppointments();


    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "Workday{" +
                "appointments=" + appointments +
                ", date=" + date +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
