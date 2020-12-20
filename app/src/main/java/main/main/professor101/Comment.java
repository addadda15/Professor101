package main.main.professor101;

public class Comment {
    private String id;
    private String comment;
    private float ratingScore;

    public Comment() {
    }

    public Comment(String id, String comment,float ratingScore) {
        this.id = id;
        this.comment = comment;
        this.ratingScore = ratingScore;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public float getRatingScore() {
        return ratingScore;
    }

    public void setRatingScore(float ratingScore) {
        this.ratingScore = ratingScore;
    }
}
