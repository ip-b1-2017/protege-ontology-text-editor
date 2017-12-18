package utilities;

import javax.swing.*;
import java.awt.*;

public class WordButton extends JButton{

    public WordButton(String word){
        this.setOpaque(false);
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
        this.setText(word);
        setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createLineBorder(Color.WHITE, 0),
        BorderFactory.createLineBorder(Color.WHITE, 0)
                ));
        if (isSelected()) {
            setBorder(BorderFactory.createEmptyBorder());
        } else {
            setBorder(BorderFactory.createLoweredBevelBorder());
        }
    }

}
