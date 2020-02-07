package homework3.phone_book;

import java.util.*;

public class PhoneBook {
    // TreeSet - чтобы телефонные номера были отсортированы
    private Map<String, TreeSet<String>> records = new TreeMap<>(Comparator.naturalOrder());

    public void add(String surname, String phoneNumber) {
        TreeSet<String> numbers = records.get(surname);
        if (numbers == null) {
            numbers = new TreeSet<>();
            numbers.add(phoneNumber);
            records.put(surname, numbers);
        } else {
            numbers.add(phoneNumber);
        }
    }

    public String get(String surname) {
        String[] phoneNumbers = getPhoneNumbers(surname);

        if (phoneNumbers.length == 0)
            return "";

        StringBuilder sb = new StringBuilder(phoneNumbers[0]);

        for (int i = 1; i < phoneNumbers.length; i++) {
            sb.append(", ").append(phoneNumbers[i]);
        }

        return sb.toString();
    }

    public String[] getPhoneNumbers(String surname) {
        String[] result = new String[]{};
        Set<String> phoneNumbers = records.get(surname);

        if (phoneNumbers != null) {
            result = phoneNumbers.toArray(result);
        }

        return result;
    }

    public String[] getAllSurnames() {
        String[] result = new String[]{};
        result = records.keySet().toArray(result);
        return result;
    }
}