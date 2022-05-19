package struct;

public class Row {

    private String path1;
    private String path2;
    private String score;
    private int index;


    public Row(String path1, String path2, String score, int index) {
        this.path1 = path1;
        this.path2 = path2;
        this.score = score;
        this.index = index;
    }

    @Override
    public String toString() {
        return "Row{" +
                "path1='" + path1 + '\'' +
                ", path2='" + path2 + '\'' +
                ", score='" + score + '\'' +
                '}';
    }

    public String getPath1() {
        return path1;
    }

    public String getPath2() {
        return path2;
    }

    public String getScore() {
        return score;
    }

    public void setPath1(String path1) {
        this.path1 = path1;
    }

    public void setPath2(String path2) {
        this.path2 = path2;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
