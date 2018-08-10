package expansion.neto.com.mx.jefeapp.provider.agendaProvider;

import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.Gson;

import expansion.neto.com.mx.jefeapp.constantes.RestUrl;
import expansion.neto.com.mx.jefeapp.modelView.agendaModel.Eventos;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by marcosmarroquin on 21/03/18.
 */
public class ProviderAgendaTareas {

    private static ProviderAgendaTareas instance;
    Context context;
    String respuesta;
    Eventos usuarioCallback = null;

    public ProviderAgendaTareas() {}

    public static ProviderAgendaTareas getInstance(Context context) {
        if(instance == null) {
            instance = new ProviderAgendaTareas();
        }
        instance.context = context;
        return instance;
    }

    public void obtenerEventos(final String usuarioId, final InterfaceObtieneEventos promise){
        final OkHttpClient client = new OkHttpClient();
        (new AsyncTask<Void, Void, Eventos>() {
            @Override
            protected Eventos doInBackground(Void... voids) {
                //TODO CONNECT AND GET DATA
                try {

                    FormBody.Builder formBuilder = new FormBody.Builder()
                            .add("usuarioId", usuarioId);

                    RequestBody formBody = formBuilder.build();
                    Request request = new Request.Builder()
                            .url(RestUrl.REST_ACTION_CONSULTAR_EVENTOS)
                            .post(formBody)
                            .build();

                    Response response = client.newCall(request).execute();
                    respuesta = response.body().string();
                    Gson gson = new Gson();
                    String jsonInString = respuesta;
                    return usuarioCallback = gson.fromJson(jsonInString, Eventos.class);

                }catch (Exception e){
                    if(e.getMessage().contains("Failed to connect to")){
                        usuarioCallback = new Eventos();
                        usuarioCallback.setCodigo(1);
                        return usuarioCallback;
                    }else{
                        usuarioCallback = new Eventos();
                        usuarioCallback.setCodigo(404);
                        return usuarioCallback;
                    }
                }
            }
            @Override
            protected void onPostExecute(Eventos eventos){
                promise.resolve(eventos);
            }
        }).execute();
    }

    public interface InterfaceObtieneEventos {
        void resolve(Eventos eventos);
        void reject(Exception e);
    }

}
