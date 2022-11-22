//Portfolio del 2
package com.company;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        //Opretter objekter med de forskellige havnes værdier

        Port Mombasa = new Port("Mombasa", 2500, 7000);

        Port DarEsSalaam = new Port("DerEsSalaam", 10000, 5000);

        Port JawaharlalNehru = new Port("JawaharlalNehru", 4000, 3000);

        Port TanjungPelepas = new Port("TanjungPelepas", 24000, 5000);

        Port Zanzibar = new Port("Zanzibar", 0, 2000);

        Port Salalah = new Port("Salalah", 0, 9000);

        Port JebelAliDubai = new Port("JebelAliDubai", 0, 9500);

        //Printer objekternes overskud eller underskud vha. containerAmount();
        System.out.println("Surplus or lack of containers in Mombasa: " + Mombasa.containerAmount());
        System.out.println("Surplus or lack of containers in Dar Es Salaam: " + DarEsSalaam.containerAmount());
        System.out.println("Surplus or lack of containers in Jawaharlal Nehru: " + JawaharlalNehru.containerAmount());
        System.out.println("Surplus or lack of containers in TanjungPelepas: " + TanjungPelepas.containerAmount());
        System.out.println("Surplus or lack of containers in Zanzibar: " + Zanzibar.containerAmount());
        System.out.println("Surplus or lack of containers in Salalah: " + Salalah.containerAmount());
        System.out.println("Surplus or lack of containers in JebelAliDubai: " + JebelAliDubai.containerAmount());

        //Opretter en liste med de forskellige havnes containerAmount(); så de kan sorteres
        ArrayList<Integer> portArray = new ArrayList<>();

        portArray.add(0, Mombasa.containerAmount());
        portArray.add(1, DarEsSalaam.containerAmount());
        portArray.add(2, JawaharlalNehru.containerAmount());
        portArray.add(3, TanjungPelepas.containerAmount());
        portArray.add(4, Zanzibar.containerAmount());
        portArray.add(5, Salalah.containerAmount());
        portArray.add(6, JebelAliDubai.containerAmount());

        bubbleSort(portArray);

        // Ny liste med navne, der sættes til at have samme index som det dertilhørende containerAmount();
        // Bruges til holde øje med hvem der sender til hvem
        ArrayList<String> nameArray = new ArrayList<>();

        nameArray.add(portArray.indexOf(JebelAliDubai.containerAmount()), JebelAliDubai.havn);
        nameArray.add(portArray.indexOf(Salalah.containerAmount()), Salalah.havn);
        nameArray.add(portArray.indexOf(Mombasa.containerAmount()), Mombasa.havn);
        nameArray.add(portArray.indexOf(Zanzibar.containerAmount()), Zanzibar.havn);
        nameArray.add(portArray.indexOf(JawaharlalNehru.containerAmount()), JawaharlalNehru.havn);
        nameArray.add(portArray.indexOf(DarEsSalaam.containerAmount()), DarEsSalaam.havn);
        nameArray.add(portArray.indexOf(TanjungPelepas.containerAmount()), TanjungPelepas.havn);

        System.out.println("\nList after sorting first time: ");
        System.out.println(portArray+ "\n");

        returnAllContainers(portArray,nameArray); // sender alle containers med overskud til havne med plads
    }
    private static void returnAllContainers(ArrayList<Integer> portArray, ArrayList<String> nameArray) {
        int i = 0;
        int j = portArray.size() - 1;
        int price = 0;

        while (i < j) {
            if (portArray.get(i) > 0 && portArray.get(j) < 0) {

                if (portArray.get(i) < portArray.get(j) * (-1)) { // Hvis der er plads til at sende det hele
                    //Printer værdierne inden de opdateres
                    System.out.println(nameArray.get(i) + " sends " + portArray.get(i) + " containers to " + nameArray.get(j));
                    //Opdatere price og printer den
                    System.out.println("Cost of the transfer " + price + "$ + " + (portArray.get (i)*100) + "$");
                    price = price + (portArray.get(i)*100);
                    //Opdaterer værdierne
                    portArray.set(j, portArray.get(j) + portArray.get(i));
                    portArray.set(i, 0);
                }

                if (portArray.get(i) >= portArray.get(j) * (-1)) { // hvis der ikke plads til at sende det hele
                    //printer inden opdatering
                    System.out.println(nameArray.get(i) + " sends: " + (portArray.get(j) * (-1)) + " containers to " + nameArray.get(j));
                    //Opdaterer price og printer den
                    System.out.println("Cost of the transfer " + price + "$ + " + ((portArray.get(j) * (-1))*100) + "$");
                    price = price + (portArray.get(j) * (-1)*100);

                    //opdaterer værdierne
                    portArray.set(i, portArray.get(i) - (portArray.get(j) * (-1)));
                    portArray.set(j, portArray.get(j) + (portArray.get(j) * (-1)));
                }

                if (portArray.get(i) == 0) { // tæller i op hvis der ikke er flere containerer
                    i++;
                }
                if (portArray.get(j) == 0) { // tæller j ned hvis de ikke mangler flere containerer
                    j--;
                }
            }
            System.out.println(portArray + "\n");
        }
        System.out.println("All ports are back to original amount");
        System.out.println("The total price of : " + price + "$");
    }

    private static void bubbleSort(ArrayList<Integer> portArray) {

        int n = portArray.size();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (portArray.get(j) < (portArray.get(j + 1))) {
                    int h = portArray.get(j);
                    portArray.set(j, portArray.get(j + 1));
                    portArray.set(j + 1, h);
                }
            }
        }
    }
}
class Port { //Sætter parametre til Port
    private final int shippedContainers;
    private final int receivedContainers;
    public final String havn;

    public int containerAmount() { // funktion til containerAmount
        return receivedContainers - shippedContainers;
    }
    public Port(String havn, int shippedContainers, int receivedContainers) { //constructor til Port
        this.havn = havn;
        this.shippedContainers = shippedContainers;
        this.receivedContainers = receivedContainers;
    }
}