package expansion.neto.com.mx.jefeapp.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.text.InputFilter;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import org.joda.time.DateTime;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import expansion.neto.com.mx.jefeapp.fragment.fragmentCreacion.FragmentAutoriza;
import expansion.neto.com.mx.jefeapp.ui.dashboard.ActivityLogin;

import static android.content.Context.TELEPHONY_SERVICE;


/**
 * Class that content methods for build app
 * Created by marcosmarroquin on 20/03/18.
 */
public class Util {

    public static String getFechaKk(){
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy kk:mm:ss");
        Date date = new Date();
        String fecha = dateFormat.format(date);
        return fecha;
    }

    public static final String md5(final String s) {
        final String MD5 = "MD5";
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest
                    .getInstance(MD5);
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < messageDigest.length; i++) {
                String h = Integer.toHexString(0xFF & messageDigest[i]);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getFechaFormat(Date date){
        SimpleDateFormat parseFormat = new SimpleDateFormat("dd MMMM yyyy");
        String s = "";
        try {
             s = parseFormat.format(date);
        }catch (Exception e){
            e.getStackTrace();
        }
        return s;
    }

    public static String getFechaFormatNoti(Date date){
        SimpleDateFormat parseFormat = new SimpleDateFormat("dd MMMM yyyy ~ HH:mm");
        String s = parseFormat.format(date);
        return s;
    }

    public static String fecha(String fecha){
        String fechaFormate = "";
        DateFormat sourceFormat = new SimpleDateFormat("dd/MM/yyyy kk:mm");
        Date date = null;
        try {
            date = sourceFormat.parse(fecha);
            String upperString = Util.getFechaDay(date).substring(0,1).toUpperCase() + Util.getFechaDay(date).substring(1);
            fechaFormate = upperString + ", " + Util.getFechaFormatNoti(date);
            return fechaFormate;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return fecha;
    }

    public static String getFechaDay(Date date){
        SimpleDateFormat parseFormat = new SimpleDateFormat("EEEE");
        String s = parseFormat.format(date);
        return s;
    }

    public interface IOnFocusListenable {
        public void onWindowFocusChanged(boolean hasFocus);
    }

    /**
     * Método que muestra el progress bar de manera horizontal programáticamente
     * @param context
     * @param view
     * @param index
     */
    public static void addProgressBar(Context context, ViewGroup view, int index) {

        ProgressBar progressBar = new ProgressBar(context, null, android.R.attr.progressBarStyleHorizontal);

        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );

        progressBar.setLayoutParams(layoutParams);
        progressBar.getIndeterminateDrawable().setColorFilter(0xffffffff, android.graphics.PorterDuff.Mode.MULTIPLY);
        progressBar.setIndeterminate(true);
        view.addView(progressBar, index);
    }


    /**
     * Método que regresa un Typeface con el tipo de letra según el caso
     * @param context
     * @param tipo
     * @return
     */
    public static Typeface changeFont(Context context, int tipo) {
        Typeface font = null;
        switch (tipo) {
            case 0:
                font = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Regular.ttf");
                break;
            case 1:
                font = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Bold.ttf");
                break;
            case 2:
                font = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Light.ttf");
                break;
        }
        return font;
    }

    /**
     * Convierte el callback httpEntity a String
     * @param inputStream
     * @return
     * @throws IOException
     */
    public static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while ((line = bufferedReader.readLine()) != null)
            result += line;
        inputStream.close();
        return result;
    }

    /**
     * Método para agregar icono en el menu del toolbar también agrega el titulo
     * @param r
     * @param title
     * @return
     */
    public static CharSequence menuIcon(Drawable r, String title) {
        r.setBounds(0, 0, r.getIntrinsicWidth(), r.getIntrinsicHeight());
        SpannableString sb = new SpannableString("    " + title);
        ImageSpan imageSpan = new ImageSpan(r, ImageSpan.ALIGN_BOTTOM);
        sb.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return sb;
    }

    public static boolean isOnline(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    //super.onBackPressed();

    public static void cerrarSesion(Context context) {
        SharedPreferences preferences = context.getApplicationContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();
        Intent main = new Intent(context.getApplicationContext(), ActivityLogin.class);
        main.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.getApplicationContext().startActivity(main);
    }


    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                activity.getCurrentFocus().getWindowToken(), 0);
    }

    public static String getNumero(Context context) {
        /*TelephonyManager mTelephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String deviceid = mTelephonyManager.getLine1Number();*/
        return "";
    }

    public static String getImei(Context context) {
        /*TelephonyManager mTelephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        @SuppressLint("MissingPermission") String imei = mTelephonyManager.getImei();*/
        return "0";
    }

    /*
     Por lo que yo sé, el número de teléfono devuelto es lo que está almacenado en el MSISDN de la tarjeta SIM,
     que no es llenado por algunos operadores. (Peor aún, según algunos informes, ¡el número podría ser incorrecto!)
     */
    public static String numberPhone(Context context){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            List<SubscriptionInfo> subscription = SubscriptionManager.from(context.getApplicationContext()).getActiveSubscriptionInfoList();
            for (int i = 0; i < subscription.size(); i++) {
                SubscriptionInfo info = subscription.get(i);
              //  Log.e("===", "number " + info.getNumber());
               // Log.e("===", "network name : " + info.getCarrierName());
               // Log.e("===", "country iso " + info.getCountryIso());
            }
        }
        return "";
    }


    public static String random() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 18) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }

    public static String getFecha(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String fecha = dateFormat.format(date);
        return fecha;
    }

    /**
     * Metodo regresa fecha
     * @return
     */
    public static String getFechita(){
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        String fecha = dateFormat.format(date);
        return fecha;
    }

    /**
     * Metodo regresa fecha con horas
     * @return
     */
    public static String getFechaHoras(){
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        String fecha = dateFormat.format(date);
        return fecha;
    }

    /**
     * Metodo regresa fecha con horas
     * @return
     */
    public static boolean getEntreFechas(){
        Boolean hora = false;
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("kk:mm");
        final String strDate = sdf.format(c.getTime());
        hora =  FragmentAutoriza.isHourInInterval(strDate, "07:00", "19:00");
        return hora;
    }

    /**
     *
     */
    public static class InputFilterMinMax implements InputFilter {
        private int min, max;

        public InputFilterMinMax(int min, int max) {
            this.min = min;
            this.max = max;
        }

        public InputFilterMinMax(String min, String max) {
            this.min = Integer.parseInt(min);
            this.max = Integer.parseInt(max);
        }


        private boolean isInRange(int a, int b, int c) {
            return b > a ? c >= a && c <= b : c >= b && c <= a;
        }

        @Override
        public CharSequence filter(CharSequence charSequence, int i, int i1, Spanned spanned, int i2, int i3) {
            try {
                int input = Integer.parseInt(spanned.toString() + charSequence.toString());
                if (isInRange(min, max, input))
                    return null;
            } catch (NumberFormatException nfe) { }
            return "";
        }
    }

    public static boolean isEmailValid(String email) {
        String regExpn =
                "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                        +"((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                        +"([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                        +"([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";

        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(regExpn,Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);

        if(matcher.matches())
            return true;
        else
            return false;
    }


    public static void getNum(Context context){
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP_MR1) {
            SubscriptionManager subscriptionManager = SubscriptionManager.from(context.getApplicationContext());
            List<SubscriptionInfo> subsInfoList = subscriptionManager.getActiveSubscriptionInfoList();

            //Log.e("Test", "Current list = " + subsInfoList);

            for (SubscriptionInfo subscriptionInfo : subsInfoList) {

                String number = subscriptionInfo.getNumber();

              //  Log.e("Test", " Number is  " + number);
            }
        }
    }
}

