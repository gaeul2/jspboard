package Util;

import model1.BoardDTO;

public class TypeValidate {
    public void makeSentence(BoardDTO bean, String[] type) {
        StringBuilder types = new StringBuilder();
        String sentence;
        for (String s : type) {
            types.append(s).append(" ");
        }
        sentence = types.toString().trim().replace(" ", ", ");
        bean.setType(sentence);
    }
}
