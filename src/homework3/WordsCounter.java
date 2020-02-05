package homework3;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class WordsCounter {

    public static void main(String[] args) {
        String[] words = initStringsArray();

        Map<String, Integer> countedWords = countWords(words);

        countedWords.forEach((word, number) -> System.out.println(word + ": " + number));
    }

    private static Map<String, Integer> countWords(String[] words) {
        // TreeMap - чтобы слова были отсортированы по алфавиту
        Map<String, Integer> wordMap = new TreeMap<>(Comparator.naturalOrder());

        for (String word : words) {
            wordMap.put(word, wordMap.getOrDefault(word, 0) + 1);
        }

        return wordMap;
    }

    private static String[] initStringsArray() {
        return new String[] {
                "яблоко",
                "машина",
                "кенгуру",
                "яблоко",
                "сарделька",
                "бегемот",
                "самокат",
                "стол",
                "стол",
                "электричество",
                "стоп-сигнал",
                "ересь",
                "глобус",
                "разнообразие",
                "стол",
                "стул",
                "стол",
                "яблоко",
                "машина",
                "кенгуру"
        };
    }
}
