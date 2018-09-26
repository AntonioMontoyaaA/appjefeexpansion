package expansion.neto.com.mx.jefeapp.radios.provider.radiosProvider;

import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.Gson;

import expansion.neto.com.mx.jefeapp.constantes.RestUrl;
import expansion.neto.com.mx.jefeapp.radios.modelView.radiosModel.Radios;
import expansion.neto.com.mx.jefeapp.radios.modelView.radiosModel.SinSitios;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ProvaiderDatosRadios {

    private static ProvaiderDatosRadios instance;
    Context context;
    String respuesta;
    String respuesta1;
    Radios radios = null;
    SinSitios sinSitios;

    public ProvaiderDatosRadios () {}

    public static ProvaiderDatosRadios getInstance(Context context) {
        if (instance == null){
            instance = new ProvaiderDatosRadios();
        }
        instance.context = context;
        return instance;
    }

    public void obtenerDatosRadios (final String idUsuario, final ConsultaDatosRadios promise){
        final OkHttpClient client = new OkHttpClient();
        (new AsyncTask<Void, Void, Radios>(){
            @Override
            protected Radios doInBackground(Void... voids) {
                try {
                    FormBody.Builder formBuilder = new FormBody.Builder()
                            .add("usuarioId",idUsuario);

                    RequestBody formBody = formBuilder.build();
                    Request request = new Request.Builder()
                            .url(RestUrl.REST_ACTION_CONSULTAR_RADIOS)
                            .post(formBody)
                            .build();
                    Response response = client.newCall(request).execute();
                    respuesta = response.body().string();
                    Gson gson = new Gson();
                    String jsonInString = respuesta;
                    return radios = gson.fromJson(jsonInString,Radios.class);
                }catch (Exception e) {
                    if (e.getMessage().contains("Failed to connect to")) {
                        radios = new Radios();
                        radios.setCodigo(1);
                        return radios;
                    } else {
                        radios = new Radios();
                        radios.setCodigo(404);
                        return radios;
                    }
                }
            }
            @Override
            protected void onPostExecute(Radios radios) {
                promise.resolve(radios);
            }
        }).execute();
    }

    public void sinSitiosDisponibles (final String idUsuario, final String raidoId, final SinSitiosI promise){
        final OkHttpClient client = new OkHttpClient();
        (new AsyncTask<Void, Void, SinSitios>(){
            @Override
            protected SinSitios doInBackground(Void... voids) {
                try{
                    FormBody.Builder formBuilder = new FormBody.Builder()
                            .add("usuarioId", idUsuario)
                            .add("radioId",raidoId)
                            .add("estatusId", "4");

                    RequestBody formBody = formBuilder.build();
                    Request request = new Request.Builder()
                            .url(RestUrl.REST_ACTION_CAMBIA_ESTATUS_RADIOS)
                            .post(formBody)
                            .build();
                    Response response = client.newCall(request).execute();
                    respuesta1 = response.body().string();
                    Gson gson = new Gson();
                    String jsonInString = respuesta1;
                    return sinSitios = gson.fromJson(jsonInString, SinSitios.class);

                }catch (Exception e){
                    if (e.getMessage().contains("Failed to connect to")) {
                        sinSitios = new SinSitios();
                        sinSitios.setCodigo(1);
                        return sinSitios;
                    } else {
                        sinSitios = new SinSitios();
                        sinSitios.setCodigo(404);
                        return sinSitios;
                    }
                }
            }

            @Override
            protected void onPostExecute(SinSitios sinSitios) {
                promise.resolve(sinSitios);
            }
        }).execute();
    }

    public interface ConsultaDatosRadios {
        void resolve(Radios datosSitio);
        void reject(Exception e);
    }

    public interface SinSitiosI {
        void resolve(SinSitios sinSitios);
        void reject(Exception e);
    }
}
