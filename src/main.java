import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * Created by Alexander on 11.08.2017.
 */
public class main {
    public static void main(String[] args) throws Exception {
        String solvedTasks = "H:\\disk\\Dropbox\\project\\javarush\\JavaRushTasks";   //решенные задачи
        String allTasks = "H:\\disk\\Dropbox\\project\\javarush\\JavaRushTasks-master"; // все задачи
        ArrayList solvedTasksList = getTasks(solvedTasks);
        ArrayList allTasksList = getTasks(allTasks);

        System.out.println("Решено задач = " + solvedTasksList.size());
        System.out.println("Всего задач = " + allTasksList.size());

        DecimalFormat df = new DecimalFormat("#.##");
        System.out.println("Выполнено = " +  (df.format(((double) solvedTasksList.size() / (double) allTasksList.size())*100)) + " %");
    }

    public static ArrayList<String> getTasks(String filePath) throws IOException{
        ArrayList pathsSolwed = new ArrayList();
        try (Stream<Path> paths = Files.walk(Paths.get(filePath))) {
            paths
                    .filter(p -> p.toString().matches("(.*)task(\\d){4}(.*)"))
                    .forEach(s -> pathsSolwed.add(s));
        }
        ArrayList tasks = new ArrayList();
        for(int i = 0; i < pathsSolwed.size(); i++){
            String pattern = Pattern.quote(File.separator);
            String[] array = pathsSolwed.get(i).toString().split(pattern);
            // String[] array = "H:\\disk\\Dropbox\\project\\javarush\\JavaRushTasks\\1.JavaSyntax\\src\\com\\javarush\\task\\task01\\task0101".split(System.getProperty("file.separator"));
            for (int j = 0; j < array.length; j++){
                if(array[j].toString().matches("(.*)task(\\d){4}(.*)")){
                    tasks.add(array[j]);
                }
            }
        }
        return tasks;
    }
}


