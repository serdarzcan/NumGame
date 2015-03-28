package pro.onur.myviews;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;

public class MyButton extends Button {

    public MyButton(Context c, AttributeSet attrs) {
        super(c, attrs);
        this.setTypeface(Typeface.createFromAsset(c.getAssets(), "fonts/handwr.ttf"));
    }
}
