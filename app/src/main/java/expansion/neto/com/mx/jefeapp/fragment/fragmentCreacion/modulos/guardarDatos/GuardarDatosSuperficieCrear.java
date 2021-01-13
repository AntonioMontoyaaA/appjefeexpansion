package expansion.neto.com.mx.jefeapp.fragment.fragmentCreacion.modulos.guardarDatos;

import android.content.Context;
import android.content.SharedPreferences;

import expansion.neto.com.mx.jefeapp.modelView.crearModel.CrearDatosSuperficie;

public class GuardarDatosSuperficieCrear {

    public static void salvarDatosSuperficie(Context context, CrearDatosSuperficie superficie,
                                             SharedPreferences.Editor editor, SharedPreferences preferences){

        if(superficie!=null){
            editor.putString("usuario", superficie.getUsuarioid());
            editor.putString("mdId", superficie.getMdId());
            editor.putString("frente", superficie.getFrente());
            editor.putString("fondo", superficie.getFondo());
            editor.putString("imgLateral1Id", superficie.getImgLateral1());
            editor.putString("imgLateral2Id", superficie.getImgLateral2());
            editor.putString("imgFrenteId", superficie.getImgFrenteId());
            editor.putString("latitud", superficie.getLatitud());
            editor.putString("longitud", superficie.getLongitud());
            editor.putString("versionApp", superficie.getVersionApp());
            editor.putString("numTelefono", superficie.getNumTelefono());
            editor.putString("fechaFrente", superficie.getFechaFrente());
            editor.putString("fechaLateral1", superficie.getFechaLateral1());
            editor.putString("fechaLateral2", superficie.getFechaLateral2());
            editor.putString("fechaEntorno1", superficie.getFechaEntorno1());
            editor.putString("fechaEntorno2", superficie.getFechaEntorno2());
            editor.putString("fechaEntorno3", superficie.getFechaEntorno3());
            editor.putString("esquina", superficie.getEsquina());
            editor.putString("imgPredial", superficie.getImgPredial());
            editor.putString("fechaPredial", superficie.getFechaPredial());
            editor.putString("imgReciboAgua", superficie.getImgPredial());
            editor.putString("fechaReciboAgua", superficie.getFechaPredial());
            editor.putString("imgReciboLuz", superficie.getImgPredial());
            editor.putString("fechaReciboLuz", superficie.getFechaPredial());

            editor.apply();
        }

    }

    public static CrearDatosSuperficie obtenerSuperficie(Context context){
        CrearDatosSuperficie crearDatosSuperficie = new CrearDatosSuperficie();
        SharedPreferences preferences = context.getSharedPreferences("datosSuperficieCrear", Context.MODE_PRIVATE);
        crearDatosSuperficie.setUsuarioid(preferences.getString("usuario", ""));
        crearDatosSuperficie.setMdId(preferences.getString("mdId", ""));
        crearDatosSuperficie.setFrente(preferences.getString("frente", ""));
        crearDatosSuperficie.setFondo(preferences.getString("fondo", ""));
        crearDatosSuperficie.setImgLateral1(preferences.getString("imgLateral1Id", ""));
        crearDatosSuperficie.setImgLateral2(preferences.getString("imgLateral2Id", ""));
        crearDatosSuperficie.setImgFrenteId(preferences.getString("imgFrenteId", ""));
        crearDatosSuperficie.setLatitud(preferences.getString("latitud", ""));
        crearDatosSuperficie.setLongitud(preferences.getString("longitud", ""));
        crearDatosSuperficie.setVersionApp(preferences.getString("versionApp", ""));
        crearDatosSuperficie.setNumTelefono(preferences.getString("numTelefono", ""));
        crearDatosSuperficie.setFechaFrente(preferences.getString("fechaFrente", ""));
        crearDatosSuperficie.setFechaLateral1(preferences.getString("fechaLateral1", ""));
        crearDatosSuperficie.setFechaLateral2(preferences.getString("fechaLateral2", ""));
        crearDatosSuperficie.setFechaEntorno1(preferences.getString("fechaEntorno1", ""));
        crearDatosSuperficie.setFechaEntorno2(preferences.getString("fechaEntorno2", ""));
        crearDatosSuperficie.setFechaEntorno3(preferences.getString("fechaEntorno3", ""));
        crearDatosSuperficie.setEsquina(preferences.getString("esquina", ""));
        crearDatosSuperficie.setImgPredial(preferences.getString("imgPredial", ""));
        crearDatosSuperficie.setFechaPredial(preferences.getString("fechaPredial", ""));
        crearDatosSuperficie.setImgReciboAgua(preferences.getString("imgReciboAgua", ""));
        crearDatosSuperficie.setFechaReciboAgua(preferences.getString("fechaReciboAgua", ""));
        crearDatosSuperficie.setImgReciboLuz(preferences.getString("imgReciboLuz", ""));
        crearDatosSuperficie.setFechaReciboLuz(preferences.getString("fechaReciboLuz", ""));

        return crearDatosSuperficie;
    }



}
