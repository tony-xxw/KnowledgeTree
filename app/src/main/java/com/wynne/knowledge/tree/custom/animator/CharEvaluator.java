package com.wynne.knowledge.tree.custom.animator;

import android.animation.TypeEvaluator;

/**
 * @author Wynne
 * @date 2018/10/23
 */

public class CharEvaluator implements TypeEvaluator<Character> {

    @Override
    public Character evaluate(float fraction, Character startValue, Character endValue) {
        int startInt = (int) startValue;
        int endInt = (int) endValue;
        int result = (int) (startInt + fraction * (endInt - startInt));
        return (char) result;
    }
}
