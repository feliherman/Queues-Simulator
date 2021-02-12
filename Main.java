import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        File file = new File("E:\\JAVA TP TEME!!\\PT2020_30229_Felician-Nicu_Herman_Assignment_2\\src\\main\\java\\In-Test.txt");
        File file2 = new File("E:\\JAVA TP TEME!!\\PT2020_30229_Felician-Nicu_Herman_Assignment_2\\src\\main\\java\\In-Test2.txt");
        File file3 = new File("E:\\JAVA TP TEME!!\\PT2020_30229_Felician-Nicu_Herman_Assignment_2\\src\\main\\java\\In-Test3.txt");
        String outfis = "E:\\JAVA TP TEME!!\\PT2020_30229_Felician-Nicu_Herman_Assignment_2\\src\\main\\java\\Out_Test.txt";
        String outfis2 = "E:\\JAVA TP TEME!!\\PT2020_30229_Felician-Nicu_Herman_Assignment_2\\src\\main\\java\\Out_Test2.txt";
        String outfis3 = "E:\\JAVA TP TEME!!\\PT2020_30229_Felician-Nicu_Herman_Assignment_2\\src\\main\\java\\Out_Test3.txt";
        /*SimulationManager gen = new SimulationManager(100,10,2,2,4,file,outfis);
        Thread t = new Thread(gen);
        t.start();*/
        /*SimulationManager gen = new SimulationManager(100,10,2,2,4,file2,outfis2);
        Thread t = new Thread(gen);
        t.start();*/
        SimulationManager gen = new SimulationManager(100,10,2,2,4,file3,outfis3);
        Thread t = new Thread(gen);
        t.start();
    }
}
