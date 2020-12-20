package main.main.professor101;

public class Score {
    Double score;
    int pid;

    public Score() {
    }

    public Score(String score, int pid) {
        this.score = Double.parseDouble(score);
        this.pid = pid;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }
}
