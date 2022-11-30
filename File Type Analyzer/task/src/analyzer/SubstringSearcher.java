package analyzer;

public class SubstringSearcher {
    private SearchAlgorithm searchAlgorithm;

    public void setSearchAlgorithm(SearchAlgorithm searchAlgorithm) {
        this.searchAlgorithm = searchAlgorithm;
    }

    public boolean textContainsPattern(String text, String pattern) {
        return searchAlgorithm.contains(text.toCharArray(), pattern.toCharArray());
    }
}
