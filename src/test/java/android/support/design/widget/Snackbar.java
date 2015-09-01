package android.support.design.widget;

import android.view.View;

public class Snackbar {
    public static final int LENGTH_LONG = 0;

    private static View sView;
    private static String sText;
    private static Integer sLength;
    private static boolean sShow;

    public static Snackbar make(final View view, final CharSequence text, final int length) {
        sView = view;
        sText = (String) text;
        sLength = length;
        return new Snackbar();
    }

    public static boolean verifyMakeWith(final View view, final String text, final int length) {
        return view.equals(sView) && text.equals(sText) && length == sLength;
    }

    public void show() {
        sShow = true;
    }

    public static boolean verifyShowCalled() {
        return sShow;
    }

    public static void reset() {
        sView = null;
        sText = null;
        sLength = null;
        sShow = false;
    }
}
