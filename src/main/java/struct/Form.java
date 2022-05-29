package struct;


public class Form {
    private boolean shingles = false;
    private boolean levenshtein = false;
    private int shingleLen;
    private boolean module = false;
    private int moduleVal;
    private int ngramLength;
    private int windowLength;
    private boolean checkXml = false;
    private boolean showZeroScore = false;

    public boolean isShingles() {
        return shingles;
    }

    public void setShingles(boolean shingles) {
        this.shingles = shingles;
    }

    public boolean isLevenshtein() {
        return levenshtein;
    }

    public void setLevenshtein(boolean levenshtein) {
        this.levenshtein = levenshtein;
    }

    public int getShingleLen() {
        return shingleLen;
    }

    public void setShingleLen(int shingleLen) {
        this.shingleLen = shingleLen;
    }

    public boolean isModule() {
        return module;
    }

    public void setModule(boolean module) {
        this.module = module;
    }

    public int getModuleVal() {
        return moduleVal;
    }

    public void setModuleVal(int moduleVal) {
        this.moduleVal = moduleVal;
    }

    public int getNgramLength() {
        return ngramLength;
    }

    public void setNgramLength(int ngramLength) {
        this.ngramLength = ngramLength;
    }

    public int getWindowLength() {
        return windowLength;
    }

    public void setWindowLength(int windowLength) {
        this.windowLength = windowLength;
    }

    public boolean isCheckXml() {
        return checkXml;
    }

    public void setCheckXml(boolean checkXml) {
        this.checkXml = checkXml;
    }

    public boolean isShowZeroScore() {
        return showZeroScore;
    }

    public void setShowZeroScore(boolean showZeroScore) {
        this.showZeroScore = showZeroScore;
    }

    @Override
    public String toString() {
        return "Form{" +
                "shingles=" + shingles +
                ", levenshtein=" + levenshtein +
                ", shingleLen=" + shingleLen +
                ", module=" + module +
                ", moduleVal=" + moduleVal +
                ", ngramLength=" + ngramLength +
                ", windowLength=" + windowLength +
                ", checkXml=" + checkXml +
                ", showZeroScore=" + showZeroScore +
                '}';
    }
}
