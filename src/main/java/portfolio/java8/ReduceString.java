package portfolio.java8;

import java.util.List;

public class ReduceString {


    public static void main(String[] args) {

        List<String> words = List.of("Java", "is", "powerful");

        String sentence = words.stream()
                .reduce("", (a, b) -> a + " " + b);

        System.out.println(sentence.trim());

    }


}
