public class MyRunnable implements Runnable {

    private String name;
    public MyRunnable(String name) { this.name = name; }
    public void run() {
        for (int i = 1; i <= 5; i++) {
            if (Thread.interrupted()) break;
            System.out.println(name+":"+i);
            try {
                Thread.sleep(1000); // endort le thread une seconde.
            } catch (InterruptedException e) {
// Un autre thread demande à l'exécution
// du thread courant de s'arrêter.
                break; // on sort de la boucle.
            }
        }
    }
}

