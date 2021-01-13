package expansion.neto.com.mx.jefeapp.provider.crearProvider;

import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.Gson;

import expansion.neto.com.mx.jefeapp.modelView.crearModel.Codigos;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static expansion.neto.com.mx.jefeapp.constantes.RestUrl.JSON;
import static expansion.neto.com.mx.jefeapp.constantes.RestUrl.REST_ACTION_CREAR_ZONIFICACION;

/**
 * Created by marcosmarroquin on 21/03/18.
 */
public class ProviderCrearZonificacion {

    private static ProviderCrearZonificacion instance;
    Context context;
    String respuesta;
    Codigos callback = null;

    public ProviderCrearZonificacion() {}

    public static ProviderCrearZonificacion getInstance(Context context) {
        if(instance == null) {
            instance = new ProviderCrearZonificacion();
        }
        instance.context = context;
        return instance;
    }

    public void crearDatosZonificacion(final String crearZonificacion, final InterfaceCrearDatosZonificacion promise){
        final OkHttpClient client = new OkHttpClient();
        (new AsyncTask<Void, Void, Codigos>() {
            @Override
            protected Codigos doInBackground(Void... voids) {
                //TODO CONNECT AND GET DATA
                try {

                    RequestBody body = RequestBody.create(JSON, crearZonificacion);
                    Request request = new Request.Builder()
                            .url(REST_ACTION_CREAR_ZONIFICACION)
                            .post(body)
                            .build();

                    Response response = client.newCall(request).execute();
                    respuesta = response.body().string();
                    Gson gson = new Gson();
                    String jsonInString = respuesta;
                    System.out.println( "respuesta al crear una zonificación : " + respuesta );
                    return callback = gson.fromJson(jsonInString, Codigos.class);

                }catch (Exception e){
                    e.printStackTrace();
                    if(e.getMessage().contains("Failed to connect to")){
                        callback = new Codigos();
                        callback.setCodigo(1);
                        return callback;
                    }else{
                        callback = new Codigos();
                        callback.setCodigo(404);
                        return callback;
                    }
                }
            }

            @Override
            protected void onPostExecute(Codigos codigo){
                promise.resolve(codigo);
            }
        }).execute();
    }

    public interface InterfaceCrearDatosZonificacion {
        void resolve(Codigos codigo);
        void reject(Exception e);
    }

}
