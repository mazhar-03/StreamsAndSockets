package task22;

import java.io.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        //2.2.1
//        LinkedList<Integer> ints = new LinkedList<>();
//        LinkedList<String> strings = new LinkedList<>();
//        LinkedList<Character> chars = new LinkedList<>();
//
//        for(var element : Arrays.asList(2, 1, 3, 7)){
//            ints.add(element);
//        }
//        for(var element : Arrays.asList("hello", "everyone")){
//            strings.add(element);
//        }
//        for(var element : "individual".chars().toArray()){
//            chars.add((char)element);
//        }
//
//        System.out.println("Ints: ");
//        ints.forEach(number -> System.out.print(number + " "));
//        System.out.println();
//
//        System.out.println("Strings: ");
//        strings.forEach(string -> System.out.print(string + " "));
//        System.out.println();
//
//        System.out.println("Chars: ");
//        chars.forEach(character -> System.out.print(character + " "));
//        System.out.println();

        ///getLifeSpan() usage
        LinkedList<String> strings = new LinkedList<>();
//
        strings.add("first");
//        Thread.sleep(1000);
        strings.add("middle");
//        Thread.sleep(1500);
        strings.add("last");
//        System.out.println(strings2.getLifeSpan().toMillis());

        //2.2.2
        try {
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("src/task22/data.bin"));
            output.writeObject(strings);
            output.close();

            // Deserialize the LinkedList
            ObjectInputStream input = new ObjectInputStream(new FileInputStream("src/task22/data.bin"));
            LinkedList<String> deserialized = (LinkedList<String>) input.readObject();

            // Display the results
            System.out.println("strings lifespan = " + strings.getLifespan().toMillis());
            System.out.println("deserialized lifespan = " + deserialized.getLifespan().toMillis());

            System.out.print("strings: ");
            strings.forEach(element -> System.out.print(element + " "));
            System.out.println();

            System.out.print("deserialized: ");
            deserialized.forEach(element -> System.out.print(element + " "));
            System.out.println();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
