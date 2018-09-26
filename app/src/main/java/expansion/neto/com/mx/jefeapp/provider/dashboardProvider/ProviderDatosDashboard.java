package expansion.neto.com.mx.jefeapp.provider.dashboardProvider;

import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.Gson;

import expansion.neto.com.mx.jefeapp.constantes.RestUrl;
import expansion.neto.com.mx.jefeapp.modelView.dashboardModel.Dashboard;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Clase singleton que regresa la validación del usuario así como también regresa el modelo de la persona
 * Created by marcosmarroquin on 21/03/18.
 */
public class ProviderDatosDashboard {

    private static ProviderDatosDashboard instance;
    Context context;
    String respuesta;
    Dashboard callback = null;
    public static String STATUS_AREA = "1";
    public static String TIPO_CONSULTA = "1";
    public static String ANIO_ACTUAL = "2018";
    public static String TIPO_APP = "1";
    public static String VER_MAS = "1";


    public ProviderDatosDashboard() {}

    public static ProviderDatosDashboard getInstance(Context context) {
        if(instance == null) {
            instance = new ProviderDatosDashboard();
        }
        instance.context = context;
        return instance;
    }

    public void obtenerDatosAutorizadas(final String semana,
                                        final String mes,
                                        final String usuarioId,
                                        final String area, final ConsultaDatosDashboard promise){
        final OkHttpClient client = new OkHttpClient();
        (new AsyncTask<Void, Void, Dashboard>() {
            @Override
            protected Dashboard doInBackground(Void... voids) {
                //TODO CONNECT AND GET DATA
                try {

                    FormBody.Builder formBuilder = new FormBody.Builder()
                            .add("estatus", STATUS_AREA)
                            .add("area", area)
                            .add("mes", mes)
                            .add("semana", semana)
                            .add("anio", ANIO_ACTUAL)
                            .add("tipoconsulta", TIPO_CONSULTA)
                            .add("tipoapp", TIPO_APP)
                            .add("usuarioId", usuarioId);

                    RequestBody formBody = formBuilder.build();

                    Request request = new Request.Builder()
                            .url(RestUrl.REST_ACTION_CONSULTAR_DASHBOARD)
                            .post(formBody)
                            .build();

                    Response response = client.newCall(request).execute();
                    respuesta = response.body().string();
                    Gson gson = new Gson();
                    String jsonInString = respuesta;
                    return callback = gson.fromJson(jsonInString, Dashboard.class);

                }catch (Exception e){
                    e.printStackTrace();
                    if(e.getMessage().contains("Failed to connect to")){
                        callback = new Dashboard();
                        callback.setCodigo(1);
                        return callback;
                    }else{
                        callback = new Dashboard();
                        callback.setCodigo(404);
                        return callback;
                    }
                }
            }
            @Override
            protected void onPostExecute(Dashboard dashboard){
                promise.resolve(dashboard);
            }
        }).execute();
    }

    public interface ConsultaDatosDashboard {
        void resolve(Dashboard dashboard);
        void reject(Exception e);
    }

}
