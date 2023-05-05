package org.strong.ideas;

import java.util.Random;

public class Generator {
    private String generateIntSymbols(int num) {
        StringBuilder ret = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < num; i++) {
            ret.append(random.nextInt(2));
        }
        return ret.toString();
    }

    String generate(int length) {
        Random random = new Random();
        String generatedLine = "";
        String s = "";
        int fp = random.nextInt(length) + 1;
        s += generateIntSymbols(fp);
        if (random.nextInt(2) == 1 && length - fp > 1) {
            s += ".";
            s += generateIntSymbols(length - fp - 1);
        } else {
            if (length - fp > 0)
                s += generateIntSymbols(length - fp);
        }
        generatedLine = s;
        return generatedLine;
    }
}