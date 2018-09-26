package expansion.neto.com.mx.jefeapp.provider.crearProvider;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;

import com.google.gson.Gson;

import expansion.neto.com.mx.jefeapp.constantes.RestUrl;
import expansion.neto.com.mx.jefeapp.modelView.crearModel.Codigos;
import expansion.neto.com.mx.jefeapp.modelView.crearModel.CrearDatosSitio;
import expansion.neto.com.mx.jefeapp.utils.Util;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static expansion.neto.com.mx.jefeapp.constantes.RestUrl.NUM_TELEFONO;
import static expansion.neto.com.mx.jefeapp.constantes.RestUrl.TIPO_APLICACION;
import static expansion.neto.com.mx.jefeapp.constantes.RestUrl.VERSION_APP;

/**
 * Created by marcosmarroquin on 21/03/18.
 */
public class ProviderCrearDatosSitio {

    private static ProviderCrearDatosSitio instance;
    Context context;
    String respuesta;
    Codigos codigo = null;

    public ProviderCrearDatosSitio() {}

    public static ProviderCrearDatosSitio getInstance(Context context) {
        if(instance == null) {
            instance = new ProviderCrearDatosSitio();
        }
        instance.context = context;
        return instance;
    }

    public void guardarMd(final CrearDatosSitio datosSitio,  final InterfaceCrearDatosSitio promise){
        final OkHttpClient client = new OkHttpClient();
        (new AsyncTask<Void, Void, Codigos>() {
            @Override
            protected Codigos doInBackground(Void... voids) {
                //TODO CONNECT AND GET DATA
                try {

                    final SharedPreferences preferences = context.getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
                    String mdIdZ = preferences.getString("mdIdterminar", "");
                    if(mdIdZ.equals("")){
                        mdIdZ = "0";
                    }


                    String nombreSitioFormat = datosSitio.getNombreSitio().substring(0, 1).toUpperCase() + datosSitio.getNombreSitio().substring(1);


                    FormBody.Builder formBuilder = new FormBody.Builder()
                            .add("usuarioId", datosSitio.getUsuarioId())
                            .add("nombreSitio", nombreSitioFormat)
                            .add("direccion", datosSitio.getDireccion())
                            .add("estado", datosSitio.getEstado())
                            .add("latitud", datosSitio.getLatitud())
                            .add("longitud", datosSitio.getLongitud())
                            .add("pais", datosSitio.getPais())
                            .add("tipoubicacion", datosSitio.getTipoubicacion())
                            .add("numtelefono", NUM_TELEFONO)
                            .add("versionapp", VERSION_APP)
                            .add("tipoAplicacion", TIPO_APLICACION)
                            .add("municipio", datosSitio.getMunicipio())
                            .add("mdId", String.valueOf(mdIdZ))
                            .add("radioid", datosSitio.getRadio());

                    RequestBody formBody = formBuilder.build();
                    Request request = new Request.Builder()
                            .url(RestUrl.REST_ACTION_CREAR_MD)
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

    public interface InterfaceCrearDatosSitio {
        void resolve(Codigos codigo);
        void reject(Exception e);
    }

}
