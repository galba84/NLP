import NLlogic.Relation;
import NLlogic.RelationTemplate;
import NLlogic.Sentance;
import NLlogic.Word;

public class RelationExtractor {
    int firstWordPosition = 0;
    int secondWordPosition = 0;

    public Relation getRelation(Sentance sentance, RelationTemplate template) {
        Word second = null;
        String searchWord = template.first;
        Relation relation = new Relation();
        Word first = getFirstWord(sentance, template.first);
        if (first != null) {
            second = getSecondWord(sentance, template.second, firstWordPosition);
        }
        if (second != null) {
            return new Relation(first, second);
        }
        return null;
    }

    private Word getFirstWord(Sentance sentance, String pos) {
        int count = 0;
        for (Word w : sentance.words
        ) {
            count++;
            if (w.pos.equals(pos)) {
                firstWordPosition = count;
                return w;
            }
        }
        return null;
    }

    private Word getSecondWord(Sentance sentance, String pos, int startPosition) {
        int count = 0;
        for (int i = startPosition + 1; i < sentance.words.size(); i++) {
            if (sentance.words.get(i).pos.startsWith(pos.substring(0,2))) {
                secondWordPosition = i;
                return sentance.words.get(i);
            }
        }
        return null;
    }
}
