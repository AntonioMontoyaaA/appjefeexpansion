package expansion.neto.com.mx.jefeapp.provider.crearProvider;

import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.Gson;

import expansion.neto.com.mx.jefeapp.constantes.RestUrl;
import expansion.neto.com.mx.jefeapp.modelView.crearModel.HorasPeatonales;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static expansion.neto.com.mx.jefeapp.constantes.RestUrl.FACTOR_ID_PEATONALES;

/**
 * Created by marcosmarroquin on 21/03/18.
 */
public class ProviderHorasPeatonales {

    private static ProviderHorasPeatonales instance;
    Context context;
    String respuesta;
    HorasPeatonales codigo = null;

    public ProviderHorasPeatonales() {}

    public static ProviderHorasPeatonales getInstance(Context context) {
        if(instance == null) {
            instance = new ProviderHorasPeatonales();
        }
        instance.context = context;
        return instance;
    }

    public void obtenerHoras(final String mdId, final String usuarioId, final InterfaceObtieneHoras promise){
        final OkHttpClient client = new OkHttpClient();
        (new AsyncTask<Void, Void, HorasPeatonales>() {
            @Override
            protected HorasPeatonales doInBackground(Void... voids) {
                //TODO CONNECT AND GET DATA
                try {

                    FormBody.Builder formBuilder = new FormBody.Builder()
                            .add("mdId", mdId)
                            .add("usuarioId", usuarioId)
                            .add("factorId", FACTOR_ID_PEATONALES);

                    RequestBody formBody = formBuilder.build();
                    Request request = new Request.Builder()
                            .url(RestUrl.REST_ACTION_CONSULTA_HORAS_PEATONAL)
                            .post(formBody)
                            .build();

                    Response response = client.newCall(request).execute();
                    respuesta = response.body().string();
                    Gson gson = new Gson();
                    String jsonInString = respuesta;
                    return codigo = gson.fromJson(jsonInString, HorasPeatonales.class);

                }catch (Exception e){
                    e.printStackTrace();
                    if(e.getMessage().contains("Failed to connect to")){
                        codigo = new HorasPeatonales();
                        codigo.setCodigo(1);
                        return codigo;
                    }else{
                        codigo = new HorasPeatonales();
                        codigo.setCodigo(404);
                        return codigo;
                    }
                }
            }
            @Override
            protected void onPostExecute(HorasPeatonales horasPeatonales){
                promise.resolve(horasPeatonales);
            }
        }).execute();
    }

    public interface InterfaceObtieneHoras {
        void resolve(HorasPeatonales horasPeatonales);
        void reject(Exception e);
    }

}
