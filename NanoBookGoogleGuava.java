import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import java.util.*;

public class NanoBookGoogleGuava {
    private enum Entry {
        BOOK("book", "noun", "A written work published in printed or electronic form."),
        BOOK2("book", "verb", "To arrange for someone to have a seat on a plane."),
        BOOKABLE("bookable", "adjective", "Can be ordered in advance."),
        BOOKBINDER("bookbinder", "noun", "A person who fastens the pages of books."),
        BOOKCASE("bookcase", "noun", "A piece of furniture with shelves."),
        CSC220_2("CSC220", "verb", "To create data structures."),
        CSC220("CSC220", "noun", "Data Structures."),
        CSC220_1("CSC220", "adjective", "Ready to create complex data structures.");

        private String key;
        private String[] value;

        private Entry(String key, String type, String value) {
            this.key = key;
            this.value = new String[] {type, value};
        }

        public String getKey() {
            return this.key;
        }

        public String getValue() {
            return this.value[0] + "|||" + this.value[1];
        }

        @Override
        public String toString() {
            return Character.toUpperCase(this.getKey().charAt(0)) +
                    this.getKey().substring(1) +
                    ": " + this.getValue();
        }
    }

    public static String getCapitalCase (String str) {
        return Character.toUpperCase(str.charAt(0)) + str.substring(1);
    }

    static Scanner scannerObj = new Scanner(System.in);

    public static String getUpperCasedInput() {
        return scannerObj.nextLine().toUpperCase();
    }

    public static boolean isValidPartOfSpeech (String wordType) {
        switch (wordType) {
            case "adjective":
            case "verb":
            case "noun":
            case "adverb":
            case "pronoun":
            case "conjunction":
            case "interjection":
            case "preposition":
            case "determiner":
                return true;
            default:
                return false;
        }
    }

    public static void main(String[] args) {
        Multimap<String, String> nanoBookGoogleGuava = ArrayListMultimap.create();

        // Adds all enum objects into multi-map
        for (Entry entry: Entry.values()) {
            nanoBookGoogleGuava.put(entry.getKey(), entry.getValue());
        }

        System.out.printf("- DICTIONARY 220 JAVA Professional -----%n-----\t   by Google Guava -%n%n");

        String input;
        do {
            System.out.print("Search: ");
            input = getUpperCasedInput().trim();

            String[] splitString = input.split(" ");

            input = splitString[0];

            if (input.equals("!Q")) {
                break;
            } else if (input.length() == 0) {
                System.out.println("\tPlease insert a valid string.");
                continue;
            }

            List<String> definitions = (List<String>) nanoBookGoogleGuava.get(input);
            if (definitions.isEmpty()) {
                input = input.toLowerCase();
                definitions = (List<String>) nanoBookGoogleGuava.get(input);
            }

            Collections.sort(definitions);

            if (definitions.isEmpty()) {
                System.out.println("\t|");
                System.out.println("\t <Not found>");
                System.out.println("\t|");
                continue;
            }

//            System.out.println("\t|");
            String term = NanoBookGoogleGuava.getCapitalCase(input);
            System.out.println("\t|");

            Boolean validOutput = false;
            Boolean matchesAtLeastOneInputType = false;
            Boolean hasAtLeastOneDefinition = false;

            Iterator<String> definitionsIt = definitions.iterator();
            while(definitionsIt.hasNext()) {
                hasAtLeastOneDefinition = true;
                String[] verbAndDef = definitionsIt.next().split("\\|\\|\\|", 2);

                String type = verbAndDef[0].toLowerCase();
                String def = verbAndDef[1];
                String inputType;
                Boolean matchesInputType = false;
                if (splitString.length == 2) {
                    inputType = splitString[1].toLowerCase();
                    if (inputType.equals(type)) {
                        matchesInputType = true;
                    }
                }

                if (splitString.length == 1 || matchesInputType) {
                    validOutput = true;
                    System.out.println("\t " + term + " [" + type + "] : " + def);
                }
            }

            if (splitString.length == 2 && !isValidPartOfSpeech(splitString[1].toLowerCase())) {
                System.out.println("\t <2nd argument must be a part of speech or \"distinct\">");
                System.out.println("\t|");
                continue;
            }

            if (!validOutput) {
                System.out.println("\t <Not found>");
                System.out.println("\t|");
                continue;
            }

            System.out.println("\t|");
        } while (!input.equals("!Q"));

        System.out.printf("%n-----THANK YOU-----");
    }
}
