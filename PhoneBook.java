package Java.Phonebook;

import java.io.*;
import java.util.*;

public class PhoneBook {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, List<String>> phoneBook = new HashMap<>();

        System.out.println("Введите имя нажмите Enter и номер телефона нажмите Enter (или \"end\" для завершения ввода):");
        while (true) {
            String name = scanner.nextLine();
            if (name.equals("end")) {
                break;
            }
            String phoneNumber = scanner.nextLine();

            if (phoneBook.containsKey(name)) {
                List<String> numbers = phoneBook.get(name);
                numbers.add(phoneNumber);
                phoneBook.put(name, numbers);
            } else {
                List<String> numbers = new ArrayList<>();
                numbers.add(phoneNumber);
                phoneBook.put(name, numbers);
            }

            System.out.println("Введите имя и номер телефона (или \"end\" для завершения ввода):");
        }

        // Создание отсортированного списка по количеству номеров телефонов
        List<Map.Entry<String, List<String>>> sortedEntries = new ArrayList<>(phoneBook.entrySet());
        sortedEntries.sort((entry1, entry2) -> Integer.compare(entry2.getValue().size(), entry1.getValue().size()));

        System.out.println("Телефонная книга, отсортированная по убыванию числа телефонов:");
        for (Map.Entry<String, List<String>> entry : sortedEntries) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }

        // Запись в файл txt
        try (FileWriter writer = new FileWriter("phonebook.txt")) {
            writer.write("Телефонная книга, отсортированная по убыванию числа телефонов:\n");
            for (Map.Entry<String, List<String>> entry : sortedEntries) {
                writer.write(entry.getKey() + " - " + entry.getValue() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Ошибка записи в файл: " + e.getMessage());
        }
    }
}
