package Model;

import java.io.Serializable;

public class Specialization implements Serializable {

    String name;

    public Specialization() {

        this.name = "";
    }

    public Specialization(String name) {

        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "{ Specialization - " +
                "name: " + name + " }";
    }
}