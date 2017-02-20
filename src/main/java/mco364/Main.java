package mco364;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class nextState extends Thread{
GameOfLife gol;

public nextState(GameOfLife o){
gol = o;
}
    
@Override
public void run() {
synchronized("keyObject"){
System.out.println("\n"+this.getName()+ "is running");    
gol.nextBoardIteration();
Main.sleep(500);
System.out.println("\n"+this.getName()+ "is finished");    
}
}
}

public class Main{
    /**
     * @param args the command line arguments
     * @throws java.lang.InterruptedException
     * @throws java.util.concurrent.ExecutionException
     */
    public static void main(String[] args) throws InterruptedException, ExecutionException {
    final int POOL_SIZE = 10; 
    final int THREAD_LIMIT = 22; 
    ExecutorService executor = Executors.newFixedThreadPool(POOL_SIZE);  
    Scanner kb = new Scanner(System.in);
    GameOfLife gol = new GameOfLife();
    Thread t;
    String message =
    "would you like to transition to the next generation "
    + "automatically or manually?"
    + "\nPress A for auto or M for manual,or any other to abort";
    String nextMove;
    Main.sleep(1000);
    clearConsole();
    System.out.println(message);
    nextMove = kb.next();
    
    while(nextMove.equalsIgnoreCase("m")){
    clearConsole();
    t = new nextState(gol);
    executor.execute(t);
    Main.sleep(1000);
    System.out.println(message);
    nextMove = kb.next();
    }    

    if(nextMove.equalsIgnoreCase("a")){    
    for (int i = 0; i < THREAD_LIMIT; i++) {
    executor.execute(new nextState(gol));
    }
    }
    executor.shutdown();
    while (!executor.isTerminated()){ 
    }        
}
    public final static void clearConsole() {
        for (int i = 0; i < 50; i++) { // safety net since next code only works on console not Netbeans output
            System.out.println();
        }
        try {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                Runtime.getRuntime().exec("cls");
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                Runtime.getRuntime().exec("clear");
            }
        } catch (final Exception e) {
        }
    }

    public static void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ex) {
        }
    }
}
