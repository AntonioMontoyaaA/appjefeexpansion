package expansion.neto.com.mx.jefeapp.provider.autorizaProvider;

import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.Gson;

import expansion.neto.com.mx.jefeapp.constantes.RestUrl;
import expansion.neto.com.mx.jefeapp.modelView.autorizaModel.DatosPredial;
import expansion.neto.com.mx.jefeapp.modelView.crearModel.Amortizacion;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by marcosmarroquin on 21/03/18.
 */
public class ProviderDatosAmortizacion {

    private static ProviderDatosAmortizacion instance;
    Context context;
    String respuesta;
    Amortizacion usuarioCallback = null;

    public ProviderDatosAmortizacion() {}

    public static ProviderDatosAmortizacion getInstance(Context context) {
        if(instance == null) {
            instance = new ProviderDatosAmortizacion();
        }
        instance.context = context;
        return instance;
    }

    public void obtenerDatosAmortizacion(final String mdId, final String usuarioId, final ConsultaDatosAmortizacion promise){
        final OkHttpClient client = new OkHttpClient();
        (new AsyncTask<Void, Void, Amortizacion>() {
            @Override
            protected Amortizacion doInBackground(Void... voids) {
                //TODO CONNECT AND GET DATA
                try {

                    FormBody.Builder formBuilder = new FormBody.Builder()
                            .add("usuarioId", usuarioId)
                            .add("mdId", mdId);

                    RequestBody formBody = formBuilder.build();
                    Request request = new Request.Builder()
                            .url(RestUrl.REST_ACTION_CONSULTAR_PERIODOS)
                            .post(formBody)
                            .build();

                    Response response = client.newCall(request).execute();
                    respuesta = response.body().string();
                    Gson gson = new Gson();
                    String jsonInString = respuesta;
                    return usuarioCallback = gson.fromJson(jsonInString, Amortizacion.class);

                }catch (Exception e){
                    if(e.getMessage().contains("Failed to connect to")){
                        usuarioCallback = new Amortizacion();
                        usuarioCallback.setCodigo(1);
                        return usuarioCallback;
                    }else{
                        usuarioCallback = new Amortizacion();
                        usuarioCallback.setCodigo(404);
                        return usuarioCallback;
                    }
                }
            }
            @Override
            protected void onPostExecute(Amortizacion datosPredial){
                promise.resolve(datosPredial);
            }
        }).execute();
    }

    public interface ConsultaDatosAmortizacion {
        void resolve(Amortizacion datosPredial);
        void reject(Exception e);
    }

}
