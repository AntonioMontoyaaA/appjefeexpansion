package expansion.neto.com.mx.jefeapp.fragment.fragmentCreacion.modulos.mandarDatos;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import expansion.neto.com.mx.jefeapp.modelView.crearModel.Codigos;
import expansion.neto.com.mx.jefeapp.modelView.crearModel.CrearDatosSitio;
import expansion.neto.com.mx.jefeapp.provider.crearProvider.ProviderCrearDatosSitio;

public class FragmentDatosSitio {

    Context context;

    public FragmentDatosSitio(Context context) {
        this.context = context;
    }

    public void mandarDatos(final CrearDatosSitio crearsitio) {

        ProviderCrearDatosSitio.getInstance(context).guardarMd(crearsitio,
                new ProviderCrearDatosSitio.InterfaceCrearDatosSitio() {
                    @Override
                    public void resolve(Codigos codigo) {
                        if (codigo != null && codigo.getCodigo()!=null) {
                            if (codigo.getCodigo() == 200) {
                                SharedPreferences preferences = context.getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = preferences.edit();
                                editor.putFloat("latMd", Float.parseFloat(crearsitio.getLatitud()));
                                editor.putFloat("lotMd", Float.parseFloat(crearsitio.getLongitud()));
                                editor.putString("nombreSitio", crearsitio.getNombreSitio());
                                editor.putLong("mdId", codigo.getMdId());
                                editor.apply();

                            } else {
                                final SharedPreferences preferences = context.getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
                                String nombreMd = preferences.getString("nombreSitio", "");

                                if (nombreMd.length() > 0) {

                                } else {
                                    Toast.makeText(context, codigo.getMensaje(),
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        }

                    }

                    @Override
                    public void reject(Exception e) {
                    }
                });
    }
}