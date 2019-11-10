package NLlogic;

import java.util.Objects;

public class Word {
    public String word;
    public String pos;
    public String chunk;


    public Word(String word, String pos, String chunk) {
        this.word = word;
        this.pos = pos;
        this.chunk=chunk;
    }

    @Override
    public String toString() {
        return "Word{" +
                "word='" + word + '\'' +
                ", pos='" + pos + '\'' +
                ", chunk='" + chunk + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Word)) {
            return false;
        }
        Word word = (Word) o;
        return this.word == word.word &&
                Objects.equals(pos, word.pos) &&
                Objects.equals(chunk, word.chunk);
    }

    @Override
    public int hashCode() {
        super.hashCode();
        return Objects.hash(word, pos, chunk);
    }
}
