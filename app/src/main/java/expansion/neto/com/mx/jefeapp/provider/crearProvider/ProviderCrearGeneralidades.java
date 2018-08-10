package expansion.neto.com.mx.jefeapp.provider.crearProvider;

import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.Gson;

import expansion.neto.com.mx.jefeapp.constantes.RestUrl;
import expansion.neto.com.mx.jefeapp.modelView.crearModel.Codigos;
import expansion.neto.com.mx.jefeapp.modelView.crearModel.CrearGeneralidades;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static expansion.neto.com.mx.jefeapp.constantes.RestUrl.TIPO_APLICACION;

/**
 * Created by marcosmarroquin on 21/03/18.
 */
public class ProviderCrearGeneralidades {

    private static ProviderCrearGeneralidades instance;
    Context context;
    String respuesta;
    Codigos codigo = null;

    public ProviderCrearGeneralidades() {}

    public static ProviderCrearGeneralidades getInstance(Context context) {
        if(instance == null) {
            instance = new ProviderCrearGeneralidades();
        }
        instance.context = context;
        return instance;
    }

    public void guardarGeneralidades(final CrearGeneralidades crearGeneralidades, final InterfaceCrearDatosGeneralidades promise){
        final OkHttpClient client = new OkHttpClient();
        (new AsyncTask<Void, Void, Codigos>() {
            @Override
            protected Codigos doInBackground(Void... voids) {
                //TODO CONNECT AND GET DATA
                try {

                    FormBody.Builder formBuilder = new FormBody.Builder()
                            .add("mdId", crearGeneralidades.getMdId())
                            .add("usuarioId", crearGeneralidades.getUsuarioId())
                            .add("renta", crearGeneralidades.getRenta())
                            .add("disponibilidad", crearGeneralidades.getDisponibilidad())
                            .add("fechadisponible", crearGeneralidades.getFechadisponible())
                            .add("montoamortiza", crearGeneralidades.getPorcentajeamortiza())
                            .add("periodoamortizacion", crearGeneralidades.getPeriodoamortizacion())
                            .add("periodogracia", crearGeneralidades.getPeriodogracia())
                            .add("numtelefono", crearGeneralidades.getNumtelefono())
                            .add("tipoAplicacion", TIPO_APLICACION)
                            .add("versionapp", crearGeneralidades.getVersionapp());

                    RequestBody formBody = formBuilder.build();
                    Request request = new Request.Builder()
                            .url(RestUrl.REST_ACTION_CREAR_GENERALIDADES)
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

    public interface InterfaceCrearDatosGeneralidades {
        void resolve(Codigos codigo);
        void reject(Exception e);
    }

}
