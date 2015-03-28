package pro.onur.numgame;

import java.lang.reflect.Field;
import android.content.Context;
import android.graphics.Typeface;

/**
 * Created by kasirga on 3/28/2015.
 */
public final class FontsOverride {

    public static void setDefaultFont(Context context,
                                      String staticTypefaceFieldName, String fontAssetName) {
        final Typeface regular = Typeface.createFromAsset(context.getAssets(),
                fontAssetName);
        replaceFont(staticTypefaceFieldName, regular);
    }

    protected static void replaceFont(String staticTypefaceFieldName,
                                      final Typeface newTypeface) {
        try {
            final Field staticField = Typeface.class
                    .getDeclaredField(staticTypefaceFieldName);
            staticField.setAccessible(true);
            staticField.set(null, newTypeface);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void setAllFonts(Context c) {
        FontsOverride.setDefaultFont(c, "DEFAULT", "fonts/HANDWR.TTF");
        /*FontsOverride.setDefaultFont(c, "MONOSPACE", "fonts/ARIAL.TTF");
        FontsOverride.setDefaultFont(c, "SANS_SERIF", "fonts/ARIAL.TTF");
        FontsOverride.setDefaultFont(c, "DEFAULT_BOLD", "fonts/ARIALBD.TTF");*/
    }
}
