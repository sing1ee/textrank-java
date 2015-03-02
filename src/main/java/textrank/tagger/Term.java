package textrank.tagger;

/**
 * Created by zhangcheng on 15/3/2.
 */
public class Term {

    private String text;
    private String pos;

    public Term() {
    }

    public Term(String text, String pos) {
        this.text = text;
        this.pos = pos;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }
}
