package it.java.gestore.eventi;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("       <-- Crea un evento --> ");
        System.out.println("\nQuale tipo di evento vuoi creare? \n  "
        		+ "Scegli tra questi:\n  "
        		+ "[1] Concerto\n  "
        		+ "[2] Spettacolo\n  "
        		+ "[3] Conferenza\n");

        int EventType = input.nextInt();
        input.nextLine(); 
        String EventTypeNome = ""; 
       

     
        System.out.println("\nTitolo dell'evento:\033[1;94m");
        String TitleInput = input.nextLine();

        
        LocalDate Date = null;
        DateTimeFormatter FormDate = DateTimeFormatter.ofPattern("dd/MM/yyyy"); 
        boolean DateCheck = false;
        while (!DateCheck) {
            System.out.printf(
                    "\n\033[0mData dell'evento \033[1;94m\"%s\"\033[0m (formato dd/MM/yyyy):\n",
                    TitleInput);

            String DateInput = input.nextLine();

            try {
                Date = LocalDate.parse(DateInput, FormDate);
                if (Date.isBefore(LocalDate.now())) {
                    System.out.println("  !!  Data non valida !!");
                } else {
                    DateCheck = true;
                }
            } catch (DateTimeParseException e) {
                System.out.println(
                        " !! Formato data non valido, riprova !!");
            }
        }
  
        String DateOutput = Date.format(FormDate);

        // Time
        LocalTime Time = null;
        boolean TimeOk = false;
        while (!TimeOk) {
            try {
                System.out.printf("Orario evento \033[1;94m\"%s\"\033[0m\n",TitleInput);
                String TimeInput = input.nextLine();
                
                Time = LocalTime.parse(TimeInput);
                TimeOk = true;
                
            } catch (DateTimeParseException e) {
                System.out.println("    \033[0;91m !! Formato Time non valido !! \033[0m");
            }
        }
        DateTimeFormatter TimeFormatter = DateTimeFormatter.ofPattern("HH:mm");
     
        String TimeFormat = Time.format(TimeFormatter);

 
        System.out.println("Prezzo del biglietto(€):");
        double PriceInput = input.nextDouble();
        
        
        NumberFormat PriceFormatter = NumberFormat.getCurrencyInstance(Locale.ITALY);
        String PriceFormat = PriceFormatter.format(PriceInput);

        
        System.out.println("N° Posti: ");
        int TotSeatsInput = input.nextInt();
        input.nextLine();

        

        Evento Event = null;

        switch (EventType) {
            case 1:
                Event = new Concerto(TitleInput, Date, Time, TotSeatsInput, PriceInput);
                EventTypeNome = "Concerto";
                break;
            case 2:
                Event = new Spettacolo(TitleInput, Date, Time, TotSeatsInput, PriceInput);
                EventTypeNome = "Spettacolo";
                break;
            case 3:
                Event = new Conferenza(TitleInput, Date, Time, TotSeatsInput, PriceInput);
                EventTypeNome = "Conferenza";
                break;
            default:
                System.out.println("\033[0mEvento inserito non valido\033[0m");
                break;
        }
    
        System.out.println("N° posti da prenotare?");
        int TakenSeats = input.nextInt();
        input.nextLine();

        for (int i = 0; i < TakenSeats; i++) {
            Event.Check();
        }

    
        System.out.printf("   N° posti prenotati: \n", Event.getPostiPrenotati());
        System.out.printf("   N° posti ancora disponibili:  \n",
                (Event.getPostiTotali() - Event.getPostiPrenotati()));

        
        System.out.printf("\n Disdire uno o più posti?\n");
        String Answr = input.nextLine();
        
        if (Answr.equalsIgnoreCase("si")) {
            System.out.println("Quanti posti vuoi disdire?");
            int Canc = input.nextInt();
            
            input.nextLine();
            for (int i = 0; i < Canc; i++) {
                Event.Cancel();
            }
            
            System.out.printf("   Hai disdetto n° %d posti.\n", Canc);
        } else if (Answr.equalsIgnoreCase("no")) {
            System.out.printf("   Nessun posto disdetto \n");
        }

       
        System.out.printf("   N° posti prenotati: \n", Event.getPostiPrenotati());
        System.out.printf("   N° posti ancora disponibili: \n",
                (Event.getPostiTotali() - Event.getPostiPrenotati()));

     
        System.out.printf("\n\n\033[1;97mRIEPILOGO:\033[0m\n\n   %s\n",
                Event.toString());

     
        System.out.printf(
                "\n   Evento creato: %s \033[1;94m\"%s\"\033[0m \n"
                + "\n Data: %s "
                + "\n Ora: %s "
                + "\n Prezzo: %s"
                + "\n N° Posti: %s. \n",
                EventTypeNome, TitleInput, DateOutput, TimeFormat, PriceFormat, TakenSeats);

       

     //   ProgrammEventi myProgrammEventi = new ProgrammEventi(
       //         "\n\n\033[0;102m\033[1;90mPROGRAMMA EVENTI(ordinati per Date):\033[0m\n");

        
        //Event.setTitolo("\033[1;94m" + TitleInput + "\033[0m");

    
       // myProgrammEventi.aggiungiEvento(Event);


    }
}