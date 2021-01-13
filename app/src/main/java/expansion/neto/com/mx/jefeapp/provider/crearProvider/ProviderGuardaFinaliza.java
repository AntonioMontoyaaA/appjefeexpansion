package expansion.neto.com.mx.jefeapp.provider.crearProvider;

import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.Gson;

import expansion.neto.com.mx.jefeapp.constantes.RestUrl;
import expansion.neto.com.mx.jefeapp.modelView.crearModel.Codigos;
import expansion.neto.com.mx.jefeapp.modelView.crearModel.GuardarFinalizar;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by marcosmarroquin on 21/03/18.
 */
public class ProviderGuardaFinaliza {

    private static ProviderGuardaFinaliza instance;
    Context context;
    String respuesta;
    Codigos codigo = null;

    public ProviderGuardaFinaliza() {}

    public static ProviderGuardaFinaliza getInstance(Context context) {
        if(instance == null) {
            instance = new ProviderGuardaFinaliza();
        }
        instance.context = context;
        return instance;
    }

    public void finalizaGuardaMd(final GuardarFinalizar guardarFinalizar, final InterfaceGuardarFinalizar promise){
        final OkHttpClient client = new OkHttpClient();
        (new AsyncTask<Void, Void, Codigos>() {
            @Override
            protected Codigos doInBackground(Void... voids) {
                //TODO CONNECT AND GET DATA
                try {

                    FormBody.Builder formBuilder = new FormBody.Builder()
                            .add("mdId", guardarFinalizar.getMdId())
                            .add("usuarioId", guardarFinalizar.getUsuarioId())
                            .add("tipoguardado", guardarFinalizar.getTipoguardado());

                    RequestBody formBody = formBuilder.build();
                    Request request = new Request.Builder()
                            .url(RestUrl.REST_ACTION_CONSULTA_GUARDA_FINALIZA)
                            .post(formBody)
                            .build();

                    Response response = client.newCall(request).execute();
                    respuesta = response.body().string();
                    Gson gson = new Gson();
                    String jsonInString = respuesta;
                    return codigo = gson.fromJson(jsonInString, Codigos.class);

                }catch (Exception e){
                    e.printStackTrace();
                    if(e.getMessage().contains("Failed to connect to")){
                        codigo = new Codigos();
                        codigo.setCodigo(1);
                        return codigo;
                    }else{
                        codigo = new Codigos();
                        codigo.setCodigo(404);
                        return codigo;
                    }
                }
            }
            @Override
            protected void onPostExecute(Codigos returncodigo){
                promise.resolve(returncodigo);
            }
        }).execute();
    }

    public interface InterfaceGuardarFinalizar {
        void resolve(Codigos codigo);
        void reject(Exception e);
    }

}
