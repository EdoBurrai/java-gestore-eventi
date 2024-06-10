package it.java.gestore.eventi;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Evento {
    private String Title;
    private LocalDate Date;
    private int TotSeats;
    private int TakenSeats;

  //Costruttore
    public Evento(String Title, LocalDate Date, int TotSeats) {
        this.Title = Title;
        this.Date = Date;
        this.TotSeats = TotSeats;
        this.TakenSeats = 0;
    }

    //Getter
    public String getTitolo() {
        return Title;
    }
    //Setter
    public void setTitolo(String Title) {
        this.Title = Title;
    }

  //Getter
    public LocalDate getData() {
        return Date;
    }
    //Setter
    public void setData(LocalDate Date) {
        this.Date = Date;
    }

  //Getter
    public int getPostiTotali() {
        return TotSeats;
    }
  //Getter
    public int getPostiPrenotati() {
        return TakenSeats;
    }

    
    public void Check() {
        if (Date.isBefore(LocalDate.now())) {
            System.out.println("Evento già passato");
        } else if (TotSeats > TakenSeats) {
            TakenSeats += 1;
        } else {
            System.out.println("!! Posti esauriti !! ");
        }
    }

   
    public void Cancel() {
        if (Date.isBefore(LocalDate.now())) {
            System.out.println("Evento già passato");
        } else if (TotSeats > TakenSeats) {
            TakenSeats -= 1;
        } else {
            System.out.println("Errore: non ci sono prenotazioni da disdire");
        }
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return Date.format(formatter) + " - " + "\"\033[1;94m" + Title + "\"\033[0m";
    }
}
