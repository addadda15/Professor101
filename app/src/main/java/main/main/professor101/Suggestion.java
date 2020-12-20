package main.main.professor101;

public class Suggestion {
    String title;
    String content;
    String id;

    public Suggestion() {
    }

    public Suggestion(String uid, String title, String content) {
        this.id = uid;
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
