package expansion.neto.com.mx.jefeapp.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class StartMyServiceAtBootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent serviceIntent = new Intent(context, ServicioRutas.class);
        context.startService(serviceIntent);
    }
}
