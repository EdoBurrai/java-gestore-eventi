package it.java.gestore.eventi;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Spettacolo extends Evento {
    private LocalTime Time;
    private double Price;

  //Costruttore
    public Spettacolo(String Title, LocalDate Data, LocalTime Time, int TotSeats, double Price) {
        super(Title, Data, TotSeats);
        this.Time = Time;
        this.Price = Price;
    }

    //Getter
    public LocalTime getOra() {
        return Time;
    }
    //Setter
    public void setOra(LocalTime Time) {
        this.Time = Time;
    }
    //Getter
    public double getPrezzo() {
        return Price;
    }
    //Setter
    public void setPrezzo(double Price) {
        this.Price = Price;
    }

  //Formattare data e ora
    public String getDataOraFormattata() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        return getData().format(dateFormatter) + " " + Time.format(timeFormatter);
    }

    // Formattare il prezzo
    public String getPrezzoFormattato() {
        return String.format(Locale.ITALY, "%.2f€", Price);
    }


    @Override
    public String toString() {
        return getDataOraFormattata() + " - " + "\033[1;94m\"" + getTitolo() + "\"\033[0m" + " - "
                + getPrezzoFormattato();
    }
}
