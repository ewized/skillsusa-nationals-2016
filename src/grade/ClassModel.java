package grade;

import common.Common;

import java.util.*;
import java.util.stream.Collectors;

/** The module of a class that stores the scores and generates the needed values */
public class ClassModel {
    private String name;
    private List<Double> scores = new ArrayList<>();

    public ClassModel(String name) {
        this.name = Objects.requireNonNull(Common.nullString(name), "name");
    }

    /** Get the name of the class */
    public String name() {
        return name;
    }

    /** Reset the grades and clear the scores */
    public void reset() {
        scores.clear();
    }

    /** Generate the high score of this class */
    public double highScore() {
        if (scores.size() == 0) {
            return 0;
        }
        List<Double> sortedScores = scores.stream().sorted().collect(Collectors.toList());
        return sortedScores.get(sortedScores.size() - 1);
    }

    /** Generate the lowest score of this class */
    public double lowScore() {
        if (scores.size() == 0) {
            return 0;
        }
        List<Double> sortedScores = scores.stream().sorted().collect(Collectors.toList());
        return sortedScores.get(0);
    }

    /** Calculate the average score of the class */
    public double averageScore() {
        if (scores.size() == 0) {
            return 0;
        }
        return scores.stream().mapToDouble(a -> a).sum() / scores.size();
    }

    /** Add the scores to the list */
    public void addScore(double score) {
        scores.add(score);
    }
}
