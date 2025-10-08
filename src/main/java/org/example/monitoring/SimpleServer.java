package org.example.monitoring;

public class SimpleServer {
    public static void main(String[] args) {
        System.out.println("=== Serveur démarré ===");
        int counter = 0;

        while (true) {
            System.out.println("Message #" + (++counter) + " - " + System.currentTimeMillis());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}