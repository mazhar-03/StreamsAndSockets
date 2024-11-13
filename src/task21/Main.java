package task21;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        //2.1
//        Path path = Path.of("src/task1/input.txt");
//        try(Stream<String> lines = Files.lines(path)){
//            lines.map(line -> line.split(" ", 2))
//                    .map(nameAndNumbers -> new Object[]{
//                            nameAndNumbers[0],
//                            sumNumbers(nameAndNumbers[1])
//                    })
//                    .forEach(nameAndSum -> System.out.println(
//                            nameAndSum[0] + " " + nameAndSum[1]
//                        )
//                    );
//        }catch (IOException e){
//            System.err.println("Something went wrong!" + e);
//        }

        //modify to 2.1.1
//        String filePath = "src/task1/input.txt";
//        try (Scanner scanner = new Scanner(new File(filePath))) {
//            while (scanner.hasNextLine()) {
//
//
//                String line = scanner.nextLine();
//                String[] nameAndNumbers = line.split(" ", 2);
//                String name = nameAndNumbers[0];
//                String numbers = nameAndNumbers[1];
//
//                int sum = sumNumbers(numbers);
//                System.out.println(name + " " + sum);
//            }
//        } catch (IOException e) {
//            System.err.println("Something went wrong" + e);
//        }

        //modify to 2.1.2
//        String filePath = "src/task1/input.txt";
//        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
//            String line;
//            while ((line = reader.readLine()) != null) {
//                String[] nameAndNumbers = line.split(" ", 2);
//                String name = nameAndNumbers[0];
//                String numbers = nameAndNumbers[1];
//
//                int sum = sumNumbers(numbers);
//                System.out.println(name + " " + sum);
//            }
//        } catch (IOException e) {
//            System.err.println("Something went wrong" + e);
//        }

    }

    private static Integer sumNumbers(String numbers) {
        return Arrays.stream(numbers.split(" "))
                .mapToInt(Integer::parseInt)
                .sum();
    }
}
