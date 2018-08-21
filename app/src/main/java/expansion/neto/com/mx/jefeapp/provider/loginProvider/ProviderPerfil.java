package expansion.neto.com.mx.jefeapp.provider.loginProvider;

import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.Gson;

import expansion.neto.com.mx.jefeapp.constantes.RestUrl;
import expansion.neto.com.mx.jefeapp.modelView.crearModel.Codigos;
import expansion.neto.com.mx.jefeapp.modelView.dashboardModel.Perfil;
import expansion.neto.com.mx.jefeapp.modelView.loginModel.Usuario;
import expansion.neto.com.mx.jefeapp.modelView.loginModel.UsuarioLogin;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static expansion.neto.com.mx.jefeapp.constantes.RestUrl.NUM_TELEFONO;
import static expansion.neto.com.mx.jefeapp.constantes.RestUrl.TIPO_LOG;

/**
 * Clase singleton que regresa la validación del usuario así como también regresa el modelo de la persona
 * Created by marcosmarroquin on 21/03/18.
 */
public class ProviderPerfil {

    private static ProviderPerfil instance;
    Context context;
    String respuesta;
    Codigos usuarioCallback = null;

    public ProviderPerfil() {}

    public static ProviderPerfil getInstance(Context context) {
        if(instance == null) {
            instance = new ProviderPerfil();
        }
        instance.context = context;
        return instance;
    }

    public void cambiarPerfil(final Perfil perfil, final CambiarPerfil promise){
        final OkHttpClient client = new OkHttpClient();
        (new AsyncTask<Void, Void, Codigos>() {
            @Override
            protected Codigos doInBackground(Void... voids) {
                //TODO CONNECT AND GET DATA
                try {

                    FormBody.Builder formBuilder = new FormBody.Builder()
                            .add("usuarioId", perfil.getUsuarioId())
                            .add("nombre", perfil.getNombre())
                            .add("apellidoPaterno", perfil.getApellidoPaterno())
                            .add("apellidoMaterno", perfil.getApellidoMaterno())
                            .add("numTelefono", NUM_TELEFONO)
                            .add("correo", perfil.getCorreo())
                            .add("contrasena", perfil.getContrasena())
                            .add("imagen", perfil.getImagen());

                    RequestBody formBody = formBuilder.build();
                    Request request = new Request.Builder()
                            .url(RestUrl.REST_ACTION_CONSULTAR_LOGIN)
                            .post(formBody)
                            .build();

                    Response response = client.newCall(request).execute();
                    respuesta = response.body().string();
                    Gson gson = new Gson();
                    String jsonInString = respuesta;
                    return usuarioCallback = gson.fromJson(jsonInString, Codigos.class);

                }catch (Exception e){
                    e.printStackTrace();
                    if(e.getMessage().contains("Failed to connect to")){
                        usuarioCallback = new Codigos();
                        usuarioCallback.setCodigo(1);
                        return usuarioCallback;
                    }else{
                        usuarioCallback = new Codigos();
                        usuarioCallback.setCodigo(404);
                        return usuarioCallback;
                    }
                }
            }
            @Override
            protected void onPostExecute(Codigos codigos){
                promise.resolve(codigos);
            }
        }).execute();
    }

    public interface CambiarPerfil {
        void resolve(Codigos codigos);
        void reject(Exception e);
    }
}

