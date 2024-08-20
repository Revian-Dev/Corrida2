package Corrida;

import java.util.Random;

class Carro extends Thread {
    private static final int MAX_DISTANCIA = 300;
    private static boolean fimCorrida = false;  
    private String carrinho;
    private int distancia = 0;
    private static Random random = new Random();

    public Carro(String carrinho) {
        this.carrinho = carrinho;
    }

    @Override
    public void run() {
        while (distancia < MAX_DISTANCIA && !isfimCorrida()) {
            int aceleracao = random.nextInt(100) + 1; //aleatorio entre 1 a 70
            distancia += aceleracao;

              System.out.println(carrinho + " percorreu " + distancia + " metros.");

            
            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

           
            if (distancia >= MAX_DISTANCIA && !isfimCorrida()) {
                setfimCorrida();
                System.out.println(carrinho + " VENCEU, PERCORRENDO " + distancia + " METROS!!!");
            }
        }
    }

    private synchronized boolean isfimCorrida() {
        return fimCorrida;
    }

    private synchronized void setfimCorrida() {
        fimCorrida = true;
    }

    public int getdistancia() {
        return distancia;
    }
}

public class Main {
    public static void main(String[] args) {
        System.out.println("A CORRIDA COMEÇOU!!!");

        // Corrida Malucaaa
        Carro carro1 = new Carro("Quadrilha da Morte");
        Carro carro2 = new Carro("Dick Vigarista");
        Carro carro3 = new Carro("Penelope Charmosa");
        Carro carro4 = new Carro("Pombo");
        Carro carro5 = new Carro("Muttley");

        carro1.start();
        carro2.start();
        carro3.start();
        carro4.start();
        carro5.start();

        try {
            carro1.join();
            carro2.join();
            carro3.join();
            carro4.join();
            carro5.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("A CORRIDA CHEGOU AO FIM!");
    }
}