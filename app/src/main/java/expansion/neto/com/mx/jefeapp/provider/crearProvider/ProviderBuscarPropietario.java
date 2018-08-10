package expansion.neto.com.mx.jefeapp.provider.crearProvider;

import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.Gson;

import expansion.neto.com.mx.jefeapp.constantes.RestUrl;
import expansion.neto.com.mx.jefeapp.modelView.crearModel.PropietarioBusqueda;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by marcosmarroquin on 21/03/18.
 */
public class ProviderBuscarPropietario {

    private static ProviderBuscarPropietario instance;
    Context context;
    String respuesta;
    PropietarioBusqueda callback = null;

    public ProviderBuscarPropietario() {}

    public static ProviderBuscarPropietario getInstance(Context context) {
        if(instance == null) {
            instance = new ProviderBuscarPropietario();
        }
        instance.context = context;
        return instance;
    }

    public void obtenerPropietario(final String usuarioId, final String nombrePropietario, final ConsultaPropietario promise){
        final OkHttpClient client = new OkHttpClient();
        (new AsyncTask<Void, Void, PropietarioBusqueda>() {
            @Override
            protected PropietarioBusqueda doInBackground(Void... voids) {
                //TODO CONNECT AND GET DATA
                try {

                    FormBody.Builder formBuilder = new FormBody.Builder()
                            .add("usuarioId", usuarioId)
                            .add("nombrePropietario", nombrePropietario);

                    RequestBody formBody = formBuilder.build();
                    Request request = new Request.Builder()
                            .url(RestUrl.REST_ACTION_CONSULTAR_BUSCAR)
                            .post(formBody)
                            .build();

                    Response response = client.newCall(request).execute();
                    respuesta = response.body().string();
                    Gson gson = new Gson();
                    String jsonInString = respuesta;
                    return callback = gson.fromJson(jsonInString, PropietarioBusqueda.class);

                }catch (Exception e){
                    e.printStackTrace();
                    if(e.getMessage().contains("Failed to connect to")){
                        callback = new PropietarioBusqueda();
                        callback.setCodigo(1);
                        return callback;
                    }else{
                        callback = new PropietarioBusqueda();
                        callback.setCodigo(404);
                        return callback;
                    }
                }
            }
            @Override
            protected void onPostExecute(PropietarioBusqueda propietario){
                promise.resolve(propietario);
            }
        }).execute();
    }

    public interface ConsultaPropietario {
        void resolve(PropietarioBusqueda propietario);
        void reject(Exception e);
    }

}
