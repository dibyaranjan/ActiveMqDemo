package com.dibya.event.vo;

import java.util.List;

public class Confirmation {
    private String pnrLocator;
    private List<Passenger> passengers;

    public String getPnrLocator() {
        return pnrLocator;
    }

    public void setPnrLocator(String pnrLocator) {
        this.pnrLocator = pnrLocator;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }

    @Override
    public String toString() {
        return "Confirmation{" +
                "pnrLocator='" + pnrLocator + '\'' +
                ", passengers=" + passengers +
                '}';
    }
}
