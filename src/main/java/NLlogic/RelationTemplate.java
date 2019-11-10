package NLlogic;

public class RelationTemplate {
    public String first;
    public String link;
    public String second;
    String current;

    public RelationTemplate(String first, String second, String link) {
        this.first = first;
        this.second = second;
        this.link = link;
        this.current = first;
    }

    public void next() {
        if (current == first) {
            current = link;
        } else if (current == link) {
            current = second;
        }
    }

    public String getCurrent() {
        return current;
    }
}
