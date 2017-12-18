package temp;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * Created by giosa on 18-Dec-17.
 */
public class Mocker {


    public String text = "Ignore the people who say there are legal issues here. There aren’t. You bought a used computer, as is, with whatever software and files were on it. It’s yours now. So is the copy of Photoshop, MS Office, or whatever else it came with.\n" +
            "\n" +
            "This is essentially the same situation as if you buy a house or car and later find something hidden inside it. A man once found an original van Gogh painting in his attic. Another found a collection of comic books valued at $3,000. A California man once found a rare aluminum penny under his carpet - that penny was worth $200,000. Another found $150,000 in cash buried in his back yard. Countless other people have found rare coins, cash, and all sorts of valuable things in attics, crawl spaces, or hidden inside wall panels. One time a guy even found $20,000 cash tucked inside a book he purchased at a used book store. " +
            "I was so hurt that she said that, I felt like time stopped. \n" +
            "I probably stopped breathing for a good minute. \n" +
            "When I came to, I was numb. \n" +
            "I was so numb, even I started to pack her things. \n" +
            "I was a robot. \n" +
            "Just going through the motions. \n" +
            "After she packed the last of her things and left... she looked at me in the eyes. \n" +
            "She said the one thing that made me not believe in love anymore. \n" +
            "She said the one thing that finally made me understand that the relationship was no longer. "+
            "I felt my stomach go to my throat. \n" +
            "The colors in the world disappeared. \n" +
            "My world crumbled. \n" +
            "As she closed the door, I heard her drive off in the car I gave her. \n" +
            "I sat on the couch. \n" +
            "I stared at the floor for a good hour before I could comprehend what she said.";

    public List<LemaWord> words;
    public List<Integer> conceptOffset;
    public List<Relation> relations;

    public Mocker(){
        words = new ArrayList<>();
        conceptOffset = new ArrayList<>();
        relations = new ArrayList<>();
        String[] tempWords = text.split(" ");

        for (int i = 0; i< tempWords.length ; i++){
            words.add(new LemaWord(tempWords[i]));
        }

        for (int i=0;i < words.size() ; i ++){
            if (new Random().nextInt(words.size())%7 == 0){
                conceptOffset.add(i);
            }
        }

        for (int i=0;i < words.size() ; i ++){
            for (int j=0;j < words.size() ; j ++){
                if ((new Random().nextInt(words.size()))%5 == 0 && i!=j){
                    relations.add(new Relation(i,j));
                    relations.add(new Relation(j,i));
                }
            }
        }

    }

    public Relation hasRelation(Integer offset1){
        for ( Relation i : relations  ) {
            if(i.offset1 == offset1){
                return  i;
            }
        }
        return null;
    }


}
