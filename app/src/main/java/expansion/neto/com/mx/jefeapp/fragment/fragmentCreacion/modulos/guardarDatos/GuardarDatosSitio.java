package expansion.neto.com.mx.jefeapp.fragment.fragmentCreacion.modulos.guardarDatos;

import android.content.Context;
import android.content.SharedPreferences;

import expansion.neto.com.mx.jefeapp.modelView.crearModel.CrearDatosSitio;

public class GuardarDatosSitio {

    public static void salvarDatosSitio( CrearDatosSitio crearDatosSitio, SharedPreferences.Editor editor   ){
        if(crearDatosSitio.getNombreSitio()!=null && !crearDatosSitio.getNombreSitio().equals("")){
            editor.putString("usuarioId", crearDatosSitio.getUsuarioId());
            editor.putString("nombreSitio", crearDatosSitio.getNombreSitio());
            editor.putString("codigoPostal", crearDatosSitio.getCodigoPostal());
            editor.putString("direccion", crearDatosSitio.getDireccion());
            editor.putString("estado", crearDatosSitio.getEstado());
            editor.putString("municipio", crearDatosSitio.getMunicipio());
            editor.putString("ciudad", crearDatosSitio.getCiudad());
            editor.putString("latitud", crearDatosSitio.getLatitud());
            editor.putString("longitud", crearDatosSitio.getLongitud());
            editor.putString("pais", crearDatosSitio.getPais());
            editor.putString("proximidadCentro", crearDatosSitio.getTipoubicacion());
            editor.putString("numtelefono", crearDatosSitio.getNumtelefono());
            editor.putString("versionapp", crearDatosSitio.getVersionapp());
            editor.putString("mdId", crearDatosSitio.getMdId() );
            editor.apply();
        }
    }

    public static CrearDatosSitio obtenerSitio(Context context){

        CrearDatosSitio crearSitio = new CrearDatosSitio();
        SharedPreferences preferences = context.getSharedPreferences("datosSitio", Context.MODE_PRIVATE);

        crearSitio.setUsuarioId(preferences.getString("usuarioId", ""));
        crearSitio.setNombreSitio(preferences.getString("nombreSitio", ""));
        crearSitio.setCodigoPostal(preferences.getString("codigoPostal", ""));
        crearSitio.setDireccion(preferences.getString("direccion", ""));
        crearSitio.setEstado(preferences.getString("estado", ""));
        crearSitio.setMunicipio(preferences.getString("municipio", ""));
        crearSitio.setCiudad(preferences.getString("ciudad", ""));
        crearSitio.setLatitud(preferences.getString("latitud", ""));
        crearSitio.setLongitud(preferences.getString("longitud", ""));
        crearSitio.setPais(preferences.getString("pais", ""));
        crearSitio.setTipoubicacion(preferences.getString("proximidadCentro", ""));
        crearSitio.setNumtelefono(preferences.getString("numtelefono", ""));
        crearSitio.setVersionapp(preferences.getString("versionapp", ""));
        crearSitio.setMdId(preferences.getString("mdId", ""));

        return crearSitio;

    }



}
