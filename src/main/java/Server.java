import java.io.FileInputStream;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import NLlogic.*;
import opennlp.tools.chunker.ChunkerME;
import opennlp.tools.chunker.ChunkerModel;
import opennlp.tools.ml.model.Sequence;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import org.apache.commons.io.IOUtils;

public class Server {

    static LinkedList<Sentance> processedText = new LinkedList<Sentance>();

    static List<Relation> relationList = new LinkedList<>();
    static ChunkerME chunker;

    public static void main(String[] args) {
//        final String text = "Hey you! Mr. Jon what are you doing with Mrs. Jon. Fuck you all. All folks.";
        try {
            InputStream inputText = new FileInputStream("src/main/resources/text.txt");
            StringWriter writer = new StringWriter();
            IOUtils.copy(inputText, writer, "UTF-8");
            String text = writer.toString();

            InputStream inputStream = new FileInputStream("src/main/resources/en-sent.bin");
            SentenceModel model = new SentenceModel(inputStream);
            SentenceDetectorME detector = new SentenceDetectorME(model);

            InputStream modelInto = new FileInputStream("src/main/resources/en-token.bin");
            TokenizerModel tocmodel = new TokenizerModel(modelInto);
            Tokenizer tokenizer = new TokenizerME(tocmodel);

            InputStream modelIn = new FileInputStream("src/main/resources/en-pos-maxent.bin");
            POSModel posmodel = new POSModel(modelIn);
            POSTaggerME tagger = new POSTaggerME(posmodel);

            String sentences[] = detector.sentDetect(text);
//            double[] probs = detector.getSentenceProbabilities();

            InputStream modelInCh = new FileInputStream("src/main/resources/en-chunker.bin");
            ChunkerModel modelCh = new ChunkerModel(modelInCh);
            chunker = new ChunkerME(modelCh);

            for (String sent : sentences
            ) {
                String tokens[] = tokenizer.tokenize(sent);
                String tags[] = tagger.tag(tokens);
                String topSequences[] = chunker.chunk(tokens, tags);

                processedText.add(assembleSentance(tokens, tags, topSequences));
            }


            for (Sentance s : processedText
            ) {
                System.out.println(s.toString());
            }

            for (Sentance s : processedText
            ) {
                for (Relation r:s.senses
                     ) {
                    System.out.println(r.toString());
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        findSence();
    }

    private static Sentance assembleSentance(String[] tokens, String[] tags, String[] chunks) {
        Sentance sentance = new Sentance();
        for (int i = 0; i < tokens.length; i++) {
            Word word = new Word(tokens[i], tags[i], chunks[i]);
            sentance.words.add(word);
        }
        return sentance;
    }

    public void parseString(String text) {

    }


    public static void findSence() {
        RelationExtractor re = new RelationExtractor();
        RelationTemplate rt = new RelationTemplate("NN", "VB", "");

        for (Sentance s : processedText
        ) {
            Relation relation = re.getRelation(s, rt);
            if (relation != null) {
                relationList.add(relation);
                s.senses.add(relation);
                System.out.println(s);
                System.out.println(relation);


            }

        }

    }
}
