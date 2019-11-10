package NLlogic;

import java.util.Arrays;
import java.util.LinkedList;

public class Sentance {
    public LinkedList<Word> words = new LinkedList<Word>();
    public LinkedList<Relation> senses = new LinkedList<Relation>();


    public Sentance(Word[] words) {
        this.words.addAll(Arrays.asList(words));
    }

    public Sentance() {
    }

    @Override
    public String toString() {
        String result = "";
        for (Word w : words
        ) {
            result += w.toString() + System.lineSeparator();
        }
        return result;
    }

    public String [] geRawWords(){
        String [] result = new String [words.size()];;

        for (int i = 0; i < words.size(); i++) {
            result[i]=words.get(i).word;
        }
        return result;
    }

    public String [] geRawPosTags(){
        String [] result = new String [words.size()];

        for (int i = 0; i < words.size(); i++) {
            result[i]=words.get(i).pos;
        }
        return result;
    }
}
