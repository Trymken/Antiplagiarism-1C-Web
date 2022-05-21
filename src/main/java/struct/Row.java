package struct;

import java.util.List;

public class Row {

    private String path1;
    private String path2;
    private List<String> score;
    private int index;


    public Row(String path1, String path2, List<String> score, int index) {
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
                ", score=" + score +
                ", index=" + index +
                '}';
    }

    public String getPath1() {
        return path1;
    }

    public String getPath2() {
        return path2;
    }

    public void setPath1(String path1) {
        this.path1 = path1;
    }

    public void setPath2(String path2) {
        this.path2 = path2;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public List<String> getScore() {
        return score;
    }

    public void setScore(List<String> score) {
        this.score = score;
    }
}
