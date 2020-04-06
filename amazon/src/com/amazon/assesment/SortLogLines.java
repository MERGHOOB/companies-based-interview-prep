package com.amazon.assesment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/*
 You have ben given a task of reordering some data from a log file. In the log file every line is a space-delimited list of string,
 all lines begin with an alphanumeric identifier. There will be no lines consisting of an identifier.
After the alphanumeric identifier a line will consist of either:
- a list of words using English Letters.
- or list of only integers
You have to reorder the data such that all thee lines with words are at the top if the log filee. The lines with words are ordered lexicographically, ignoring the identifier except in the case of ties. In the case of the ties(if there are two lines that are identical except for the identifier), the identifier is used to order lexicographically. Alphanumeric should be sorted ASCII order (numbers come before letters). The identifiers must still be part of the lines in the output strings. Lines with integers must be left in the original order they were in the file.
Write an algorithm to reorder the daya in the log file, according to the rules above.
 */
public class SortLogLines {


    private static final String SPACE = " ";

    private List<String> sortLogFiles(List<String> logLines) {

        logLines.sort((o1, o2) -> {
            String[] split = o1.split(SPACE);
            List<String> firstData = getData(split);
            String firstIdentifier = split[0];

            split = o2.split(SPACE);
            List<String> secondData = getData(split);
            String secondIdentifier = split[1];

            int compareList = compareList(firstData, secondData);
            if (compareList != 0) {
                return compareList;
            } else if (!isWord(firstData) && !isWord(secondData)) {
                return 0;
            }
            return firstIdentifier.compareTo(secondIdentifier);
        });
        return logLines;
    }

    private int compareList(List<String> firstData, List<String> secondData) {
        boolean isFirstWord = isWord(firstData);
        boolean isSecondWord = isWord(secondData);

        if (isFirstWord != isSecondWord) {
            return isFirstWord ? -1 : 1;
        } else if (!isFirstWord) {
            return 0;
        } else {
            return concatenate(firstData).compareTo(concatenate(secondData));
        }

    }

    private String concatenate(List<String> firstData) {
        StringBuilder stringBuilder = new StringBuilder();
        firstData.forEach(stringBuilder::append);
        return stringBuilder.toString();
    }

    private boolean isWord(List<String> firstData) {
        AtomicBoolean isWord = new AtomicBoolean(false);
        firstData.forEach(val -> {
            try {
                Integer.parseInt(val);
            } catch (NumberFormatException e) {
                isWord.set(true);
            }
        });

        return isWord.get();
    }

    private List<String> getData(String[] split) {

        return Arrays.asList(split).subList(1, split.length);
    }


    /*
     Example:
 Input:
 logFileSize = 5
 logLines -
 [a1 9 2 3 1]
 [g1 Act car]
 [zo4 4 7]
 [ab1 off KEY dog]
 [a8 act zoo]

 Output:
 [g1 Act car]
 [a8 act zoo]
 [ab1 off KEY dog]
 [a1 9 2 3 1]
 [z04 4 7]
     */

    public static void main(String[] args) {
        List<String> logLines = new ArrayList<>();
        logLines.add("a1 9 2 3 1");
        logLines.add("g1 Act car");
        logLines.add("zo4 4 7");
        logLines.add("ab1 off KEY dog");
        logLines.add("a8 act zoo");

        List<String> strings = new SortLogLines().sortLogFiles(logLines);
        strings.forEach(System.out::println);

    }

}
