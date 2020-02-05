package homework3.phone_book;

public class PhoneBookTest {

    public static void main(String[] args) {
        // В условиях задачи не сказано, в каком формате нужно выводить список номеров для фамилии
        // По умолчанию я сделал метод, который выдает все номера одной строкой: String get(String surname)
        // String[] getPhoneNumbers(String surname) - вернет массив номеров

        PhoneBook phoneBook = new PhoneBook();
        populatePhoneBook(phoneBook);

        for (String surname :
                phoneBook.getAllSurnames()) {
            System.out.println(surname + ", тел.: " + phoneBook.get(surname));
        }

        System.out.println();
        System.out.println("Test for surname not in phonebook...");
        System.out.println("Aaaaa" + ", тел.: " + phoneBook.get("Aaaaa"));
    }

    private static void populatePhoneBook(PhoneBook phoneBook) {
        phoneBook.add("Иванов", "+7(963)885-21-47");
        phoneBook.add("Петров", "+7(963)885-21-88");
        phoneBook.add("Сидоров", "+7(963)885-21-99");
        phoneBook.add("Вассерман", "+7(963)885-21-77");
        phoneBook.add("Иванов", "+7(963)885-21-00");
        phoneBook.add("Иванов", "+7(963)885-21-47");
        phoneBook.add("Петров", "+7(963)885-21-47");
        phoneBook.add("Иванов", "+7(963)885-21-47");
    }


}
