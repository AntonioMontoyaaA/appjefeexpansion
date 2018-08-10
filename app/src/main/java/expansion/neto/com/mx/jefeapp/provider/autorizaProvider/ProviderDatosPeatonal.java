package expansion.neto.com.mx.jefeapp.provider.autorizaProvider;

import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.Gson;


import expansion.neto.com.mx.jefeapp.constantes.RestUrl;
import expansion.neto.com.mx.jefeapp.modelView.autorizaModel.Peatonales;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by marcosmarroquin on 21/03/18.
 */
public class ProviderDatosPeatonal {

    private static ProviderDatosPeatonal instance;
    Context context;
    String respuesta;
    Peatonales callback = null;

    public ProviderDatosPeatonal() {}

    public static ProviderDatosPeatonal getInstance(Context context) {
        if(instance == null) {
            instance = new ProviderDatosPeatonal();
        }
        instance.context = context;
        return instance;
    }

    public void obtenerDatosPeatonal(final String mdId, final String usuarioId, final ConsultaPeatonal promise){
        final OkHttpClient client = new OkHttpClient();
        (new AsyncTask<Void, Void, Peatonales>() {
            @Override
            protected Peatonales doInBackground(Void... voids) {
                //TODO CONNECT AND GET DATA
                try {

                    FormBody.Builder formBuilder = new FormBody.Builder()
                            .add("mdId", /*usuario.getUsuario()*/mdId)
                            .add("usuarioId", /*usuario.getContra()*/usuarioId);

                    RequestBody formBody = formBuilder.build();
                    Request request = new Request.Builder()
                            .url(RestUrl.REST_ACTION_CONSULTAR_CONTEO_PEATONAL)
                            .post(formBody)
                            .build();

                    Response response = client.newCall(request).execute();
                    respuesta = response.body().string();
                    Gson gson = new Gson();
                    String jsonInString = respuesta;
                    return callback = gson.fromJson(jsonInString, Peatonales.class);

                }catch (Exception e){
                    e.printStackTrace();
                    if(e.getMessage().contains("Failed to connect to")){
                        callback = new Peatonales();
                        callback.setCodigo(1);
                        return callback;
                    }else{
                        callback = new Peatonales();
                        callback.setCodigo(404);
                        return callback;
                    }
                }
            }
            @Override
            protected void onPostExecute(Peatonales peatonal){
                promise.resolve(peatonal);
            }
        }).execute();
    }

    public interface ConsultaPeatonal {
        void resolve(Peatonales peatonal);
        void reject(Exception e);
    }

}
