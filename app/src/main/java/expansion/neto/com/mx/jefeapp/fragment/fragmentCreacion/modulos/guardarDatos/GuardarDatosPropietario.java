package expansion.neto.com.mx.jefeapp.fragment.fragmentCreacion.modulos.guardarDatos;

import android.content.Context;
import android.content.SharedPreferences;

import expansion.neto.com.mx.jefeapp.modelView.crearModel.CrearDatosPropietario;

public class GuardarDatosPropietario {

    public static void salvarDatosPropietario(Context context, CrearDatosPropietario propietario,
                                              SharedPreferences.Editor editor, SharedPreferences preferences){

        if(propietario!=null){

            editor.putString("usuario", propietario.getUsuarioId());
            editor.putString("mdId", propietario.getMdId());
            editor.putString("nombrePropietario", propietario.getNombrePropietario());
            editor.putString("apaternoPropietario", propietario.getApaternoPropietario());
            editor.putString("amaternoPropietario", propietario.getAmaternoPropietario());
            editor.putString("telefono", propietario.getTelefono());
            editor.putString("latitud", propietario.getLatitud());
            editor.putString("email", propietario.getEmail());
            editor.putString("longitud", propietario.getLongitud());
            editor.apply();
        }

    }

    public static CrearDatosPropietario obtenerPropietario(Context context){
        CrearDatosPropietario crearDatosPropietario = new CrearDatosPropietario();
        SharedPreferences preferences = context.getSharedPreferences("datosPropietario", Context.MODE_PRIVATE);
        crearDatosPropietario.setUsuarioId(preferences.getString("usuario", ""));
        crearDatosPropietario.setMdId(preferences.getString("mdId", ""));
        crearDatosPropietario.setNombrePropietario(preferences.getString("nombrePropietario", ""));
        crearDatosPropietario.setApaternoPropietario(preferences.getString("apaternoPropietario", ""));
        crearDatosPropietario.setAmaternoPropietario(preferences.getString("amaternoPropietario", ""));
        crearDatosPropietario.setTelefono(preferences.getString("telefono", ""));
        crearDatosPropietario.setLatitud(preferences.getString("latitud", ""));
        crearDatosPropietario.setLongitud(preferences.getString("longitud", ""));
        crearDatosPropietario.setEmail(preferences.getString("email", ""));

        return crearDatosPropietario;

    }



}
