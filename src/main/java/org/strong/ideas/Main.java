package org.strong.ideas;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.*;

public class Main {
    public static void firstFunction(String[] args) {
        Recognizer recognizer = new Recognizer();
        Generator generator = new Generator();
        boolean globalFlag = true;
        Scanner in = new Scanner(System.in);
        while (globalFlag) {
            System.out.println("Choose how to use program: ");
            System.out.println("0 - Use generator.");
            System.out.println("1 - Use recognizer. ");
            System.out.println("Or type 'q' to end program ");
            int answer = -1;
            String s1 = in.nextLine();
            String toCheck1 = StringUtils.substringBefore(s1, " ");
            if (toCheck1.matches("\\d+")) { //Убрать и добавить контроль исключения при попытке парсинга числа ниже
                int gotAnswer = Integer.parseInt(toCheck1);
                if (gotAnswer < 0 || gotAnswer > 1) {
                    System.out.println("Wrong input of chose how to create initial line. Out of range.");
                    /*
                    Надо создать класификатор значений и в соотвествии
                    с его решением вызывать функцию записи лога в консоль или вызывать "пустую" функцию
                     */
                    answer = gotAnswer;
                } else {
                    answer = gotAnswer; // Убрать полностью, т.к. присвоение происходит вне зависимости от выполнения условия
                }
            } else {
                if (toCheck1.equals("q")) { // Условие оставить, т.к. фактически это условие на break
                    globalFlag = false; // Поменять на break
                } else {
                    System.out.println("Wrong input of chose how to use program."); // вынести из else, т.к. выполнится если не случится break
                }
            }
            if (globalFlag) { // Убрать потому что нужно было из-за отсутствия break выше
                if (answer == 0) {
                    int len = -1;
                    System.out.println("Enter length of sequence: ");
                    String sLen = in.nextLine();
                    if (sLen.matches("\\d+")) {
                        int gotA = Integer.parseInt(sLen);
                        if (gotA < 1 || gotA > 100) {
                            System.out.println("Wrong input of length of sequence to create. Out of range. Needed range [1..100]");
                        } else {
                            len = gotA;
                            String stringToCheck = generator.generate(len);
                            System.out.println("Generated sequence:" + stringToCheck);
                            if (recognizer.nextState(stringToCheck.length(), 0, stringToCheck, 1) == 1) {
                            /*
                            Надо создать класификатор значений и в соотвествии
                            с его решением вызывать функцию записи лога в консоль или вызывать "пустую" функцию
                             */
                                System.out.println("Recognizer recognized generated sequence");
                            } else {
                                System.out.println("Something went wrong");
                            }
                        }
                    } else {
                        System.out.println("Wrong input of length of sequence to create. Not int input.");
                    }


                } else if (answer == 1) {

                    System.out.println("Enter sequence:");
                    String sequence = in.nextLine();
                    sequence = StringUtils.substringBefore(sequence, " ");
                    if (sequence.length() < 1 || sequence.length() > 100) {
                        System.out.println("Sequence can't be shorter than 1 symbol or longer than 100 symbols.");
                    } else { // Можно избавиться используя continue выше, ведь наша цель совершать опрос пользователя пропуская команды, которые не выполняются если введённые данные не валидные
                        if (recognizer.nextState(sequence.length(), 0, sequence, 1) == 1) {
                        /*
                        Надо создать класификатор значений и в соотвествии
                        с его решением вызывать функцию записи лога в консоль или вызывать "пустую" функцию
                         */
                            System.out.println("Your sequence was recognized");
                        } else if (recognizer.nextState(sequence.length(), 0, sequence, 1) == 0) {
                            System.out.println("Your sequence was not recognized");
                        } else if (recognizer.nextState(sequence.length(), 0, sequence, 1) == -1) {
                            System.out.println("Your sequence have characters not from your language");
                        } else {
                            System.out.println("Exception occurred");
                        }
                    }
                }
            }
        }
        System.out.println("Program ended successfully.");
    }

    public static final Map<Integer, Runnable> inputDictionary = new HashMap<>();
    public static final Map<Integer, Runnable> generatorDictionary = new HashMap<>();
    public static final Map<Integer, Runnable> recognizerDictionary = new HashMap<>();
    public static final BiFunction<Integer, Map<Integer, Runnable>, Runnable> writeConsoleLog = (key, map) -> map.get(key);

    static {
        inputDictionary.put(0, () -> {});
        inputDictionary.put(1, () -> System.out.println("Wrong input of chose how to create initial line. Out of range."));

        generatorDictionary.put(1, () -> System.out.println("Recognizer recognized generated sequence"));
        generatorDictionary.put(0, () -> System.out.println("Something went wrong"));

        recognizerDictionary.put(1, () -> System.out.println("Your sequence was recognized"));
        recognizerDictionary.put(0, () -> System.out.println("Your sequence was not recognized"));
        recognizerDictionary.put(-1, () -> System.out.println("Your sequence have characters not from your language"));
    }

    public static void firstFunctionModified(String[] args) {

        Recognizer recognizer = new Recognizer();
        Generator generator = new Generator();
        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.println("Choose how to use program:\n0 - Use generator.\n1 - Use recognizer.\nOr type 'q' to end program\n");
            var answer = -1;
            String s1 = in.nextLine();
            String toCheck1 = StringUtils.substringBefore(s1, " ");
            try {
                var gotAnswer = Integer.parseInt(toCheck1);

                writeConsoleLog.apply(gotAnswer, inputDictionary);

                answer = gotAnswer;
            } catch (NumberFormatException exception) {
                if (toCheck1.equals("q"))
                    break;
                System.out.println("Wrong input of chose how to use program.");
            }
            if (answer == 0) {
                int len;
                System.out.println("Enter length of sequence: ");
                String sLen = in.nextLine();
                try {
                    int gotA = Integer.parseInt(sLen);
                    if (gotA < 1 || gotA > 100) {
                        System.out.println("Wrong input of length of sequence to create. Out of range. Needed range [1..100]");
                    } else {
                        len = gotA;
                        String stringToCheck = generator.generate(len);
                        System.out.println("Generated sequence:" + stringToCheck);

                        var recognizationMarker = recognizer.nextState(stringToCheck.length(), 0, stringToCheck, 1);
                        writeConsoleLog.apply(recognizationMarker, generatorDictionary);
                    }
                } catch (NumberFormatException exception) {
                    System.out.println("Wrong input of length of sequence to create. Not int input.");
                }
            } else if (answer == 1) {
                System.out.println("Enter sequence:");
                String sequence = in.nextLine();
                sequence = StringUtils.substringBefore(sequence, " ");
                if (sequence.length() < 1 || sequence.length() > 100) {
                    System.out.println("Sequence can't be shorter than 1 symbol or longer than 100 symbols.");
                    continue;
                }
                var recognizeResult = recognizer.nextState(sequence.length(), 0, sequence, 1);
                writeConsoleLog.apply(recognizeResult, recognizerDictionary);
            }
        }
        System.out.println("Program ended successfully.");
    }
}