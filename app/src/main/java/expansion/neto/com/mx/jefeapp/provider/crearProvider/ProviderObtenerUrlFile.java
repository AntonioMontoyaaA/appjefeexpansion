package expansion.neto.com.mx.jefeapp.provider.crearProvider;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;

import com.google.gson.Gson;

import java.io.File;

import expansion.neto.com.mx.jefeapp.constantes.RestUrl;
import expansion.neto.com.mx.jefeapp.modelView.crearModel.Codigos;
import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static expansion.neto.com.mx.jefeapp.constantes.RestUrl.FORMATO_FOTO;

/**
 * Created by marcosmarroquin on 21/03/18.
 */
public class ProviderObtenerUrlFile {

    private static ProviderObtenerUrlFile instance;
    Context context;
    String respuesta;
    Codigos callback = null;

    public ProviderObtenerUrlFile() {}

    public static ProviderObtenerUrlFile getInstance(Context context) {
        if(instance == null) {
            instance = new ProviderObtenerUrlFile();
        }
        instance.context = context;
        return instance;
    }

    public static String TIPO_ARCHIVO = "1";

    public void obtenerUrl(final String mdId, final String nombreImg, final File file, final ConsultaUrl promise){
        final OkHttpClient client = new OkHttpClient();
        (new AsyncTask<Void, Void, Codigos>() {
            @Override
            protected Codigos doInBackground(Void... voids) {
                //TODO CONNECT AND GET DATA
                try {

                    SharedPreferences preferences = context.getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
                    String usuarioId = preferences.getString("usuario","");

                    RequestBody requestBody = new MultipartBody.Builder()
                            .setType(MultipartBody.FORM)
                            .addFormDataPart("mdId", mdId)
                            .addFormDataPart("nombreArc", nombreImg)
                            .addFormDataPart("archivo",
                                    nombreImg, RequestBody.create(MediaType.parse("application/octet-stream"), file))
                            .addFormDataPart("formato", FORMATO_FOTO)
                            .addFormDataPart("usuarioId", usuarioId)
                            .addFormDataPart("tipoArchivo", TIPO_ARCHIVO).build();

                    Request request = new Request.Builder()
                            .url(RestUrl.REST_ACTION_CONSULTAR_DATOS_COMPETENCIA_CLOUDINARY)
                            .post(requestBody)
                            .build();

                    Response response = client.newCall(request).execute();
                    respuesta = response.body().string();
                    Gson gson = new Gson();
                    String jsonInString = respuesta;
                    return callback = gson.fromJson(jsonInString, Codigos.class);

                }catch (Exception e){
                    e.printStackTrace();
                    if(e.getMessage().contains("Failed to connect to")){
                        callback = new Codigos();
                        callback.setCodigo(1);
                        return callback;
                    }else{
                        callback = new Codigos();
                        callback.setCodigo(404);
                        return callback;
                    }
                }
            }
            @Override
            protected void onPostExecute(Codigos codigos){
                promise.resolve(codigos);
            }
        }).execute();
    }

    public interface ConsultaUrl {
        void resolve(Codigos codigo);
        void reject(Exception e);
    }

}
