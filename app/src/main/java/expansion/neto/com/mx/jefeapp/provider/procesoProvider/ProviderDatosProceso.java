package expansion.neto.com.mx.jefeapp.provider.procesoProvider;

import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.Gson;

import java.util.Calendar;

import expansion.neto.com.mx.jefeapp.constantes.RestUrl;
import expansion.neto.com.mx.jefeapp.modelView.loginModel.Usuario;
import expansion.neto.com.mx.jefeapp.modelView.procesoModel.Proceso;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ProviderDatosProceso {

    private static ProviderDatosProceso instance;
    Context context;
    String respuesta;
    Proceso callback = null;
    private final String ESTATUS_EN_PROCESO_APP_GERENTE  =   "2";
    private final String TIPO_CONSULTA_MD_POR_AUTORIZAR = "3";
    private final String SEMANA_0 = "0";

    public ProviderDatosProceso() {}

    public static ProviderDatosProceso getInstance(Context context) {
        if(instance == null) {
            instance = new ProviderDatosProceso();
        }
        instance.context = context;
        return instance;
    }

    public void obtenerDatosProceso(final String semana, final String mes, final String areaId, final ProviderDatosProceso.ConsultaDatosProceso promise){
        final OkHttpClient client = new OkHttpClient();
        (new AsyncTask<Void, Void, Proceso>() {
            @Override
            protected Proceso doInBackground(Void... voids) {
                //TODO CONNECT AND GET DATA
                try {

                    Calendar fecha = Calendar.getInstance();
                    int anio = fecha.get(Calendar.YEAR);

                    FormBody.Builder formBuilder = new FormBody.Builder()
                            .add("estatus", ESTATUS_EN_PROCESO_APP_GERENTE)
                            .add("area", areaId)
                            .add("mes", mes)
                            .add("semana", SEMANA_0)
                            .add("anio", String.valueOf(anio))
                            .add("tipoconsulta", TIPO_CONSULTA_MD_POR_AUTORIZAR)
                            .add("usuarioId", Usuario.sharedGet(context).getUsuario());

                    RequestBody formBody = formBuilder.build();

                    Request request = new Request.Builder()
                            .url(RestUrl.REST_ACTION_CONSULTAR_AUTORIZADAS_LISTA)
                            .post(formBody)
                            .build();

                    Response response = client.newCall(request).execute();
                    respuesta = response.body().string();
                    Gson gson = new Gson();
                    String jsonInString = respuesta;

                    return callback = gson.fromJson(jsonInString, Proceso.class);

                }catch (Exception e){
                    e.printStackTrace();
                    if(e.getMessage().contains("Failed to connect to")){
                        callback = new Proceso();
                        callback.setCodigo(1);
                        return callback;
                    }else{
                        callback = new Proceso();
                        callback.setCodigo(404);
                        return callback;
                    }
                }
            }
            @Override
            protected void onPostExecute(Proceso datosSitio){
                promise.resolve(datosSitio);
            }
        }).execute();
    }

    public interface ConsultaDatosProceso {
        void resolve(Proceso memorias);
        void reject(Exception e);
    }
}