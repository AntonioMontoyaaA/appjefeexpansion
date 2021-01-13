package expansion.neto.com.mx.jefeapp.provider.procesoProvider.chat;

import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.Gson;

import expansion.neto.com.mx.jefeapp.constantes.RestUrl;
import expansion.neto.com.mx.jefeapp.modelView.procesoModel.ChatProceso;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ProviderChatProcesoEstatus {

    private static ProviderChatProcesoEstatus instance;
    Context context;
    String respuesta;
    ChatProceso callback = null;

    public ProviderChatProcesoEstatus() {}

    public static ProviderChatProcesoEstatus getInstance(Context context) {
        if(instance == null) {
            instance = new ProviderChatProcesoEstatus();
        }
        instance.context = context;
        return instance;
    }

    public void obtenerChatProcesoEstatus(final String mdId, final int areaId, final String usuario ,
                                          final ConsultaChatProceso promise){
        final OkHttpClient client = new OkHttpClient();
        (new AsyncTask<Void, Void, ChatProceso>() {
            @Override
            protected ChatProceso doInBackground(Void... voids) {
                //TODO CONNECT AND GET DATA
                try {

                    FormBody.Builder formBuilder = new FormBody.Builder()
                            .add("mdId", mdId)
                            .add("nivelesEstatus", String.valueOf(areaId))
                            .add("usuarioId", usuario);

                    RequestBody formBody = formBuilder.build();

                    Request request = new Request.Builder()
                            .url(RestUrl.REST_ACTION_CONSULTAR_CHAT_ESTATUS)
                            .post(formBody)
                            .build();

                    Response response = client.newCall(request).execute();
                    respuesta = response.body().string();
                    Gson gson = new Gson();
                    String jsonInString = respuesta;

                    return callback = gson.fromJson(jsonInString, ChatProceso.class);

                }catch (Exception e){
                    e.printStackTrace();
                    if(e.getMessage().contains("Failed to connect to")){
                        callback = new ChatProceso();
                        callback.setCodigo(1);
                        return callback;
                    }else{
                        callback = new ChatProceso();
                        callback.setCodigo(404);
                        return callback;
                    }
                }
            }
            @Override
            protected void onPostExecute(ChatProceso chat){
                promise.resolve(chat);
            }
        }).execute();
    }

    public interface ConsultaChatProceso {
        void resolve(ChatProceso chat);
        void reject(Exception e);
    }
}
