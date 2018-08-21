package expansion.neto.com.mx.jefeapp.utils;

import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.widget.EditText;

import java.text.NumberFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomTextWatcher implements InputFilter {

    Pattern pattern;

    public CustomTextWatcher(int digitsBeforeZero,int digitsAfterZero) {
        pattern=Pattern.compile("[0-9]{0," + (digitsBeforeZero-1) + "}+((\\.[0-9]{0," + (digitsAfterZero-1) + "})?)||(\\.)?");
    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned destination, int destinationStart, int destinationEnd) {

        Matcher matcher=pattern.matcher(destination);
        if(!matcher.matches())
            return "";
        return null;
    }
}