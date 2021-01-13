package expansion.neto.com.mx.jefeapp.provider.crearProvider;

import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.Gson;

import expansion.neto.com.mx.jefeapp.constantes.RestUrl;
import expansion.neto.com.mx.jefeapp.modelView.crearModel.Codigos;
import expansion.neto.com.mx.jefeapp.modelView.crearModel.CrearDatosSuperficie;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static expansion.neto.com.mx.jefeapp.constantes.RestUrl.NUM_TELEFONO;
import static expansion.neto.com.mx.jefeapp.constantes.RestUrl.VERSION_APP;

/**
 * Created by marcosmarroquin on 21/03/18.
 */
public class ProviderCrearSuperficie {

    private static ProviderCrearSuperficie instance;
    Context context;
    String respuesta;
    Codigos codigo = null;

    public ProviderCrearSuperficie() {}

    public static ProviderCrearSuperficie getInstance(Context context) {
        if(instance == null) {
            instance = new ProviderCrearSuperficie();
        }
        instance.context = context;
        return instance;
    }

    public void guardarSuperficie(final CrearDatosSuperficie datosSuperficie, final InterfaceCrearDatosSuperficie promise){
        final OkHttpClient client = new OkHttpClient();
        (new AsyncTask<Void, Void, Codigos>() {
            @Override
            protected Codigos doInBackground(Void... voids) {
                //TODO CONNECT AND GET DATA
                try {

//                    if(datosSuperficie.getFechaPredial()==null){
//                        datosSuperficie.setFechaPredial(" ");
//                    }
//
//                    if(datosSuperficie.getImgPredial().equals("")){
//                        datosSuperficie.setImgPredial(" ");
//                    }

                    FormBody.Builder formBuilder = new FormBody.Builder()
                            .add("usuarioId", datosSuperficie.getUsuarioid())
                            .add("mdId", datosSuperficie.getMdId())
                            .add("frente", datosSuperficie.getFrente())
                            .add("fondo", datosSuperficie.getFondo())
                            .add("imgEntorno2Id", datosSuperficie.getImgLateral2()) //Cambiar el servicio
                            .add("imgEntorno1Id", datosSuperficie.getImgLateral1()) //Cambiar el servicio
                            .add("imgFrenteId", datosSuperficie.getImgFrenteId())
                            .add("latitud", datosSuperficie.getLatitud())
                            .add("longitud", datosSuperficie.getLongitud())
                            .add("numTelefono", NUM_TELEFONO)
                            .add("versionApp", VERSION_APP)
                            .add("fechaFrente", datosSuperficie.getFechaFrente())
                            .add("fechaEntorno1", datosSuperficie.getFechaLateral1())
                            .add("esquina", datosSuperficie.getEsquina())
                            .add("fechaEntorno2", datosSuperficie.getFechaLateral2())
                            .add("imgPredial", datosSuperficie.getImgPredial())
                            .add("fechaPredial", datosSuperficie.getFechaPredial())
                            .add("imgEnt1", datosSuperficie.getImgEntorno1())
                            .add("fechaEnt1", datosSuperficie.getFechaEntorno1())
                            .add("imgEnt2", datosSuperficie.getImgEntorno2())
                            .add("fechaEnt2", datosSuperficie.getFechaEntorno2())
                            .add("imgEnt3", datosSuperficie.getImgEntorno3())
                            .add("fechaEnt3", datosSuperficie.getFechaEntorno3())
                            .add("imgReciboAgua", datosSuperficie.getImgReciboAgua())
                            .add("fechaReciboAgua", datosSuperficie.getFechaReciboAgua())
                            .add("imgReciboLuz", datosSuperficie.getImgReciboLuz())
                            .add("fechaReciboLuz", datosSuperficie.getFechaReciboLuz())
                            .add("drenaje", datosSuperficie.getDrenaje());

                    RequestBody formBody = formBuilder.build();
                    Request request = new Request.Builder()
                            .url(RestUrl.REST_ACTION_CREAR_SUPERFICIE)
                            .post(formBody)
                            .build();

                    Response response = client.newCall(request).execute();
                    respuesta = response.body().string();
                    Gson gson = new Gson();
                    String jsonInString = respuesta;
                    System.out.println( "respuesta Superficie "+ respuesta );
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

    public interface InterfaceCrearDatosSuperficie {
        void resolve(Codigos codigo);
        void reject(Exception e);
    }

}
