package expansion.neto.com.mx.jefeapp.provider.crearProvider;

import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.Gson;

import expansion.neto.com.mx.jefeapp.constantes.RestUrl;
import expansion.neto.com.mx.jefeapp.modelView.crearModel.Tips;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static expansion.neto.com.mx.jefeapp.constantes.RestUrl.TIPO_APLICACION;

/**
 * Created by marcosmarroquin on 21/03/18.
 */
public class ProviderConsultaTips {

    private static ProviderConsultaTips instance;
    Context context;
    String respuesta;
    Tips callback = null;


    public ProviderConsultaTips() {}

    public static ProviderConsultaTips getInstance(Context context) {
        if(instance == null) {
            instance = new ProviderConsultaTips();
        }
        instance.context = context;
        return instance;
    }

    public void obtenerTips(final String pantalla, final ConsultaTips promise){
        final OkHttpClient client = new OkHttpClient();
        (new AsyncTask<Void, Void, Tips>() {
            @Override
            protected Tips doInBackground(Void... voids) {
                //TODO CONNECT AND GET DATA
                try {

                    FormBody.Builder formBuilder = new FormBody.Builder()
                            .add("modulo", pantalla)
                            .add("tipoaplicacion", TIPO_APLICACION);

                    RequestBody formBody = formBuilder.build();
                    Request request = new Request.Builder()
                            .url(RestUrl.REST_ACTION_TIPS)
                            .post(formBody)
                            .build();

                    Response response = client.newCall(request).execute();
                    respuesta = response.body().string();
                    Gson gson = new Gson();
                    String jsonInString = respuesta;
                    return callback = gson.fromJson(jsonInString, Tips.class);

                }catch (Exception e){
                    e.printStackTrace();
                    if(e.getMessage().contains("Failed to connect to")){
                        callback = new Tips();
                        callback.setCodigo(1);
                        return callback;
                    }else{
                        callback = new Tips();
                        callback.setCodigo(404);
                        return callback;
                    }
                }
            }
            @Override
            protected void onPostExecute(Tips tip){
                promise.resolve(tip);
            }
        }).execute();
    }

    public interface ConsultaTips {
        void resolve(Tips tip);
        void reject(Exception e);
    }

}
