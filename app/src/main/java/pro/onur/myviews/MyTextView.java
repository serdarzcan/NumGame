package pro.onur.myviews;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by kasirga on 3/28/2015.
 */
public class MyTextView extends TextView{

    public MyTextView(Context c, AttributeSet attrs) {
        super(c, attrs);
        this.setTypeface(Typeface.createFromAsset(c.getAssets(),"fonts/handwr.ttf"));
    }
}

