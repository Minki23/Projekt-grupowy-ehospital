package Service;

import java.io.Serializable;

public class Opinion implements Serializable {

    private final int value;
    private final String content;

    public Opinion(int value, String content) {
        this.value = value;
        this.content = content;
    }

    public int getValue() {
        return value;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "Value: " + value + "\tContent: " + content;
    }

}
