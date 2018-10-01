package expansion.neto.com.mx.jefeapp.provider.autorizaProvider;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;

import com.google.gson.Gson;


import java.util.Calendar;

import expansion.neto.com.mx.jefeapp.constantes.RestUrl;
import expansion.neto.com.mx.jefeapp.modelView.autorizaModel.PorTerminar;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static expansion.neto.com.mx.jefeapp.provider.dashboardProvider.ProviderDatosDashboard.ANIO_ACTUAL;
import static expansion.neto.com.mx.jefeapp.provider.dashboardProvider.ProviderDatosDashboard.TIPO_APP;

/**
 * Created by marcosmarroquin on 21/03/18.
 */
public class ProviderDatosPorTerminar {

    private static ProviderDatosPorTerminar instance;
    Context context;
    String respuesta;
    PorTerminar callback = null;
    private final String ESTATUS_POR_TERMINAR  =   "1";
    private final String TIPO_CONSULTA_TIPO = "3";
    private final String TIPO_CONSULTA_SEMANA = "0";


    public ProviderDatosPorTerminar() {}

    public static ProviderDatosPorTerminar getInstance(Context context) {
        if(instance == null) {
            instance = new ProviderDatosPorTerminar();
        }
        instance.context = context;
        return instance;
    }

    Calendar fecha = Calendar.getInstance();
    final int meses = fecha.get(Calendar.MONTH) + 1;

    public void obtenerDatosAutorizadas(final String usuarioId,
                                        final String area, final String mes, final ProviderDatosPorTerminar.ConsultaDatosAutorizadas promise){
        final OkHttpClient client = new OkHttpClient();
        (new AsyncTask<Void, Void, PorTerminar>() {
            @Override
            protected PorTerminar doInBackground(Void... voids) {
                //TODO CONNECT AND GET DATA
                try {



                    FormBody.Builder formBuilder = new FormBody.Builder()
                            .add("estatus", ESTATUS_POR_TERMINAR)
                            .add("area", area)
                            .add("mes", mes)
                            .add("semana", TIPO_CONSULTA_SEMANA)
                            .add("anio", String.valueOf(ANIO_ACTUAL))
                            .add("tipoapp", TIPO_APP)
                            .add("tipoconsulta", TIPO_CONSULTA_TIPO)
                            .add("usuarioId", usuarioId);


                    RequestBody formBody = formBuilder.build();

                    Request request = new Request.Builder()
                            .url(RestUrl.REST_ACTION_CONSULTAR_AUTORIZADAS_LISTA)
                            .post(formBody)
                            .build();

                    Response response = client.newCall(request).execute();
                    respuesta = response.body().string();
                    Gson gson = new Gson();
                    String jsonInString = respuesta;

                    return callback = gson.fromJson(jsonInString, PorTerminar.class);

                }catch (Exception e){
                    e.printStackTrace();
                    if(e.getMessage().contains("Failed to connect to")){
                        callback = new PorTerminar();
                        callback.setCodigo(1);
                        return callback;
                    }else{
                        callback = new PorTerminar();
                        callback.setCodigo(404);
                        return callback;
                    }
                }
            }
            @Override
            protected void onPostExecute(PorTerminar datosSitio){
                promise.resolve(datosSitio);
            }
        }).execute();
    }

    public interface ConsultaDatosAutorizadas {
        void resolve(PorTerminar datosSitio);
        void reject(Exception e);
    }

}
