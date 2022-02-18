import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws IOException {
        Path path = Path.of("D:/temp");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Write a key-word: ");
        String keyword = scanner.nextLine();
        System.out.println("\nWrite an extension: ");
        String extension = scanner.nextLine();
        if (extension.contains("."))
            extension.substring(extension.indexOf(".")+1);

        List<String> result = null;
        try (Stream<Path> walk = Files.walk(path)) {
            result = walk
                    .filter(p -> !Files.isDirectory(p))
                    .map(p -> p.toString())
                    .filter(f -> f.contains(keyword))
                    .filter(f -> f.endsWith(extension))
                    .collect(Collectors.toList());
        }
        catch (IOException e){
            e.fillInStackTrace();
        }
        result.stream().forEach(System.out::println);
    }
}
