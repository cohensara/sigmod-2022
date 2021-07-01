package edu.cs.maximalcliques.diversity;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SizePrefixMeasure implements Measure{

    DecimalFormat df = new DecimalFormat("#.###");
    int numCliques = 0;
    int totalSize = 0;
    String diversityString = null;
    int[] prefixSizes;

    public SizePrefixMeasure(int[] prefixSizes) {
        this.prefixSizes = prefixSizes;
    }

    @Override
    public void addClique(HashSet<Integer> clique) {
        numCliques++;
        totalSize += clique.size();

        if (Arrays.stream(prefixSizes).anyMatch(i-> i == numCliques)) {
            if (diversityString == null) {
                diversityString = "";
            }
            else diversityString += ", ";
            diversityString += df.format((float)totalSize/numCliques);
        }
    }

    @Override
    public String getDiversityValue() {
        return diversityString;
    }

    @Override
    public void reset() {
        diversityString = null;
        numCliques = 0;
        totalSize = 0;
    }

    @Override
    public String getHeader() {
        /*String header = "UnionPrefix (" + prefixSizes[0] + ")";
        for (int i = 1 ; i < prefixSizes.length ; i++) {
            header += ", UnionPrefix (" + prefixSizes[i] + ")";
        }*/
        return IntStream.of(prefixSizes).mapToObj(Integer::toString).collect(Collectors.joining(", "));
    }
}
