package expansion.neto.com.mx.jefeapp.provider.autorizaProvider;

import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.Gson;

import expansion.neto.com.mx.jefeapp.constantes.RestUrl;
import expansion.neto.com.mx.jefeapp.modelView.autorizaModel.GeneralidadesSitio;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by marcosmarroquin on 21/03/18.
 */
public class ProviderDatosGeneralidadesSitio {

    private static ProviderDatosGeneralidadesSitio instance;
    Context context;
    String respuesta;
    GeneralidadesSitio callback = null;
    private final String FACTOR_ID_DATOS_GENERALES = "6";

    public ProviderDatosGeneralidadesSitio() {}

    public static ProviderDatosGeneralidadesSitio getInstance(Context context) {
        if(instance == null) {
            instance = new ProviderDatosGeneralidadesSitio();
        }
        instance.context = context;
        return instance;
    }

    public void obtenerDatosGeneralidades(final String mdId, final String usuarioId, final ConsultaGeneralidadesSitio promise){
        final OkHttpClient client = new OkHttpClient();
        (new AsyncTask<Void, Void, GeneralidadesSitio>() {
            @Override
            protected GeneralidadesSitio doInBackground(Void... voids) {
                //TODO CONNECT AND GET DATA
                try {

                    FormBody.Builder formBuilder = new FormBody.Builder()
                            .add("mdId", mdId)
                            .add("usuarioId", usuarioId)
                            .add("factorId", FACTOR_ID_DATOS_GENERALES);

                    RequestBody formBody = formBuilder.build();
                    Request request = new Request.Builder()
                            .url(RestUrl.REST_ACTION_CONSULTAR_DATOS_GENERALIDADES_SITIO)
                            .post(formBody)
                            .build();

                    Response response = client.newCall(request).execute();
                    respuesta = response.body().string();
                    Gson gson = new Gson();
                    String jsonInString = respuesta;
                    return callback = gson.fromJson(jsonInString, GeneralidadesSitio.class);

                }catch (Exception e){
                    e.printStackTrace();
                    if(e.getMessage().contains("Failed to connect to")){
                        callback = new GeneralidadesSitio();
                        callback.setCodigo(1);
                        return callback;
                    }else{
                        callback = new GeneralidadesSitio();
                        callback.setCodigo(404);
                        return callback;
                    }
                }
            }
            @Override
            protected void onPostExecute(GeneralidadesSitio datosSitio){
                promise.resolve(datosSitio);
            }
        }).execute();
    }

    public interface ConsultaGeneralidadesSitio {
        void resolve(GeneralidadesSitio datosSitio);
        void reject(Exception e);
    }

}
