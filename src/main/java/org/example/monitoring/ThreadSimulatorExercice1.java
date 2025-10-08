package org.example.monitoring;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

public class ThreadSimulatorExercice1 {
    private static final AtomicLong operationCounter = new AtomicLong(0);
    private static final Random random = new Random();

    public static void main(String[] args) {
        System.out.println("=== Démarrage du simulateur de threads pour JFR ===");
        System.out.println("Nombre de threads: 5");
        System.out.println("Durée recommandée: 30 secondes\n");

        List<Thread> threads = new ArrayList<>();

        // Thread 1: Calculs intensifs CPU
        threads.add(new Thread(new CpuIntensiveTask(), "CPU-Intensive-Thread"));

        // Thread 2: Allocations mémoire importantes
        threads.add(new Thread(new MemoryAllocationTask(), "Memory-Allocation-Thread"));

        // Thread 3: Thread avec attentes aléatoires
        threads.add(new Thread(new RandomSleepTask(), "Random-Sleep-Thread"));

        // Thread 4: Thread avec synchronisation
        threads.add(new Thread(new SynchronizedTask(), "Synchronized-Thread"));

        // Thread 5: Thread mixte (CPU + mémoire + attentes)
        threads.add(new Thread(new MixedTask(), "Mixed-Task-Thread"));

        // Démarrer tous les threads
        for (Thread thread : threads) {
            thread.start();
            System.out.println("Thread démarré: " + thread.getName());
        }

        // Attendre que tous les threads se terminent
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("\n=== Simulateur terminé ===");
        System.out.println("Total d'opérations effectuées: " + operationCounter.get());
    }

    // Tâche 1: Calculs intensifs CPU
    static class CpuIntensiveTask implements Runnable {
        @Override
        public void run() {
            System.out.println("[CPU-Intensive] Démarrage");
            long endTime = System.currentTimeMillis() + 30000; // 30 secondes

            while (System.currentTimeMillis() < endTime) {
                // Calculs mathématiques intensifs
                double result = 0;
                for (int i = 0; i < 100000; i++) {
                    result += Math.sqrt(i) * Math.sin(i) * Math.cos(i);
                }
                operationCounter.incrementAndGet();
            }
            System.out.println("[CPU-Intensive] Terminé");
        }
    }

    // Tâche 2: Allocations mémoire importantes
    static class MemoryAllocationTask implements Runnable {
        @Override
        public void run() {
            System.out.println("[Memory-Allocation] Démarrage");
            long endTime = System.currentTimeMillis() + 30000;

            while (System.currentTimeMillis() < endTime) {
                // Créer des objets pour générer des allocations mémoire
                List<byte[]> memoryHog = new ArrayList<>();
                for (int i = 0; i < 100; i++) {
                    memoryHog.add(new byte[10240]); // 10 KB chacun
                }
                // Les objets seront garbage collectés
                memoryHog.clear();

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    break;
                }
                operationCounter.incrementAndGet();
            }
            System.out.println("[Memory-Allocation] Terminé");
        }
    }

    // Tâche 3: Attentes aléatoires
    static class RandomSleepTask implements Runnable {
        @Override
        public void run() {
            System.out.println("[Random-Sleep] Démarrage");
            long endTime = System.currentTimeMillis() + 30000;

            while (System.currentTimeMillis() < endTime) {
                try {
                    // Sleep aléatoire entre 10 et 500 ms
                    int sleepTime = random.nextInt(490) + 10;
                    Thread.sleep(sleepTime);

                    // Petit calcul rapide
                    double x = Math.random() * 1000;
                    Math.pow(x, 2);

                    operationCounter.incrementAndGet();
                } catch (InterruptedException e) {
                    break;
                }
            }
            System.out.println("[Random-Sleep] Terminé");
        }
    }

    // Tâche 4: Avec synchronisation (peut causer des blocages)
    static class SynchronizedTask implements Runnable {
        private static final Object lock = new Object();

        @Override
        public void run() {
            System.out.println("[Synchronized] Démarrage");
            long endTime = System.currentTimeMillis() + 30000;

            while (System.currentTimeMillis() < endTime) {
                synchronized (lock) {
                    // Travail dans la section critique
                    for (int i = 0; i < 10000; i++) {
                        Math.sqrt(i);
                    }
                }

                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    break;
                }
                operationCounter.incrementAndGet();
            }
            System.out.println("[Synchronized] Terminé");
        }
    }

    // Tâche 5: Mixte (CPU + mémoire + attentes)
    static class MixedTask implements Runnable {
        @Override
        public void run() {
            System.out.println("[Mixed] Démarrage");
            long endTime = System.currentTimeMillis() + 30000;

            while (System.currentTimeMillis() < endTime) {
                // CPU
                double result = 0;
                for (int i = 0; i < 5000; i++) {
                    result += Math.sqrt(i);
                }

                // Mémoire
                String[] strings = new String[100];
                for (int i = 0; i < 100; i++) {
                    strings[i] = "String-" + random.nextInt(1000);
                }

                // Attente
                try {
                    Thread.sleep(random.nextInt(100) + 50);
                } catch (InterruptedException e) {
                    break;
                }

                operationCounter.incrementAndGet();
            }
            System.out.println("[Mixed] Terminé");
        }
    }
}