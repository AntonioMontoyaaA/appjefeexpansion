package expansion.neto.com.mx.jefeapp.provider.autorizaProvider;

import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.Gson;

import expansion.neto.com.mx.jefeapp.constantes.RestUrl;
import expansion.neto.com.mx.jefeapp.modelView.autorizaModel.Propietario;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by marcosmarroquin on 21/03/18.
 */
public class ProviderDatosPropietario {

    private static ProviderDatosPropietario instance;
    Context context;
    String respuesta;
    Propietario callback = null;

    public ProviderDatosPropietario() {}

    public static ProviderDatosPropietario getInstance(Context context) {
        if(instance == null) {
            instance = new ProviderDatosPropietario();
        }
        instance.context = context;
        return instance;
    }

    public void obtenerDatosPropietario(final String mdid, final String usuarioId, final ConsultaDatosPropietario promise){
        final OkHttpClient client = new OkHttpClient();
        (new AsyncTask<Void, Void, Propietario>() {
            @Override
            protected Propietario doInBackground(Void... voids) {
                //TODO CONNECT AND GET DATA
                try {

                    FormBody.Builder formBuilder = new FormBody.Builder()
                            .add("mdId", mdid)
                            .add("usuarioId", usuarioId);

                    RequestBody formBody = formBuilder.build();
                    Request request = new Request.Builder()
                            .url(RestUrl.REST_ACTION_CONSULTAR_DATOS_PROPIETARIO)
                            .post(formBody)
                            .build();

                    Response response = client.newCall(request).execute();
                    respuesta = response.body().string();
                    Gson gson = new Gson();
                    String jsonInString = respuesta;
                    return callback = gson.fromJson(jsonInString, Propietario.class);

                }catch (Exception e){
                    e.printStackTrace();
                    if(e.getMessage().contains("Failed to connect to")){
                        callback = new Propietario();
                        callback.setCodigo(1);
                        return callback;
                    }else{
                        callback = new Propietario();
                        callback.setCodigo(404);
                        return callback;
                    }
                }
            }
            @Override
            protected void onPostExecute(Propietario datosSitio){
                promise.resolve(datosSitio);
            }
        }).execute();
    }

    public interface ConsultaDatosPropietario {
        void resolve(Propietario propietario);
        void reject(Exception e);
    }

}
