package NLlogic;

public class Relation {
    Word left;
    LinkType link;
    Word right;

    public Relation(Word left, Word right, LinkType linkType) {
        this.left = left;
        this.right = right;
        this.link = linkType;
    }
    public Relation(Word left, Word right) {
        this.left = left;
        this.right = right;
    }
    public Relation() {
    }

    @Override
    public String toString() {
        return "Relation{" +
                "left=" + left +
                ", right=" + right +
                '}';
    }


}
