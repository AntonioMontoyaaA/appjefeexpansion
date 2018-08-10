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
            editor.putString("imgEntorno2Id", superficie.getImgEntorno2Id());
            editor.putString("imgEntorno1Id", superficie.getImgEntorno1Id());
            editor.putString("imgFrenteId", superficie.getImgFrenteId());
            editor.putString("latitud", superficie.getLatitud());
            editor.putString("longitud", superficie.getLongitud());
            editor.putString("versionApp", superficie.getVersionApp());
            editor.putString("numTelefono", superficie.getNumTelefono());
            editor.putString("fechaFrente", superficie.getFechaFrente());
            editor.putString("fechaEntorno1", superficie.getFechaEntorno1());
            editor.putString("fechaEntorno2", superficie.getFechaEntorno2());
            editor.putString("esquina", superficie.getEsquina());

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
        crearDatosSuperficie.setImgEntorno2Id(preferences.getString("imgEntorno2Id", ""));
        crearDatosSuperficie.setImgEntorno1Id(preferences.getString("imgEntorno1Id", ""));
        crearDatosSuperficie.setImgFrenteId(preferences.getString("imgFrenteId", ""));
        crearDatosSuperficie.setLatitud(preferences.getString("latitud", ""));
        crearDatosSuperficie.setLongitud(preferences.getString("longitud", ""));
        crearDatosSuperficie.setVersionApp(preferences.getString("versionApp", ""));
        crearDatosSuperficie.setNumTelefono(preferences.getString("numTelefono", ""));
        crearDatosSuperficie.setFechaFrente(preferences.getString("fechaFrente", ""));
        crearDatosSuperficie.setFechaEntorno1(preferences.getString("fechaEntorno1", ""));
        crearDatosSuperficie.setFechaEntorno2(preferences.getString("fechaEntorno2", ""));
        crearDatosSuperficie.setEsquina(preferences.getString("esquina", ""));

        return crearDatosSuperficie;
    }



}
