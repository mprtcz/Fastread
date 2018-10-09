package com.mprtcz.fastread.reader;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author mprtcz
 */
public class Reader {

    Map<String, Integer> wordsToSyllablesNumber = new HashMap<>();
    List<String> words = new ArrayList<>();
    String wholeText;
    SyllablesResovler syllablesResovler;
    Labeler labeler;
    private static final int INITIAL_DELAY = 500;
    private int delay = INITIAL_DELAY;
    private boolean stopped;

    public Reader(String wholeText, SyllablesResovler syllablesResovler, Labeler labeler) {
        this.wholeText = wholeText;
        this.syllablesResovler = syllablesResovler;
        this.labeler = labeler;
        if(wholeText == null) {
            wholeText = WORDS;
        }
        mapSyllablesForWords(wholeText);
    }

    private void mapSyllablesForWords(String wholeText) {
        words = Arrays.asList(wholeText.split(" "));
        wordsToSyllablesNumber = words.parallelStream()
                .map(String::trim)
                .collect(Collectors.toMap(
                        c -> c,
                        c -> syllablesResovler.countSyllables(c),
                        (i, i2) -> i));
    }

    public void startReading() {
        stopped = false;
        for (int i = 0; i < words.size(); i++) {
            if (i > 0) {
                labeler.setPreviousWord(words.get(i-1));
            }
            String currentWord = words.get(i);
            labeler.setCurrentWord(currentWord);
            if (i < words.size() - 1) {
                labeler.setNextWord(words.get(i+1));
            } else {
                labeler.setNextWord("");
            }
            delayWord(currentWord);
            if (stopped) {
                break;
            }
        }
    }

    private void delayWord(String currentWord) {
        int baseAdditionalDelay = 50;
        int calculatedAdditionalDelay =
                baseAdditionalDelay * wordsToSyllablesNumber.get(currentWord);
        if(currentWord.contains(".")) {
            calculatedAdditionalDelay += 100;
        }
        try {
            Thread.sleep(delay + calculatedAdditionalDelay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static final String WORDS = "In the summer of 1592, an episodic outbreak of the plague" +
            " swept through London. Theatres were among the public gathering places to be shut down." +
            " William Shakespeare decided to stay in London rather than follow a theatrical company on tour. " +
            "Shakespeare needed a way to earn a wage until the theatres reopened. He also desired" +
            " to be taken seriously as a writer. Playwrights of the era were considered little more " +
            "than populist hacks, writing largely disposable entertainment. Shakespeare instead found" +
            " a way to earn both money and acclaim through the patronage of the third Earl of Southampton, Henry Wriothesley. " +
            "Poetry was the art of nobles and gentlemen, and Shakespeare—a rustic interloper " +
            "without the usual college-educated wit—lucratively introduced himself between1593 and " +
            "1594. Venus and Adonis would become Shakespeare's most widely printed work during his " +
            "lifetime. The following year, Shakespeare published The Rape of Lucrece. Both were poems" +
            " calculated to bolster Shakespeare's reputation and wallet. " +
            "On the opposite end of that spectrum is the body of poetry that comprises Shakespeare's" +
            " more mysterious and controversial work. If Venus and Adonis and The Rape of Lucrece " +
            "represent Shakespeare's quest for immortality, his sonnets of the early 1590s represent " +
            "the passion and introspection behind it.";

    public void changeDelay(double value) {
        this.delay = (int) value;
    }

    public void stop() {
        stopped = true;
    }
}
