package expansion.neto.com.mx.jefeapp.fragment.fragmentCreacion.modulos.mandarDatos;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import expansion.neto.com.mx.jefeapp.modelView.crearModel.Codigos;
import expansion.neto.com.mx.jefeapp.modelView.crearModel.CrearGeneralidades;
import expansion.neto.com.mx.jefeapp.provider.crearProvider.ProviderCrearGeneralidades;

public class FragmentDatosGeneralidades {

    Context context;
    public FragmentDatosGeneralidades(Context context){
        this.context = context;
    }

    public void mandarDatosGeneralidades(CrearGeneralidades generalidades){
        ProviderCrearGeneralidades.getInstance(context).guardarGeneralidades(generalidades,
                new ProviderCrearGeneralidades.InterfaceCrearDatosGeneralidades() {
                    @Override
                    public void resolve(Codigos codigo) {
                        if(codigo.getCodigo()==200){

                            SharedPreferences preferencesGeneralidades = context.getApplicationContext().getSharedPreferences("datosGeneralidades", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editorGeneralidades = preferencesGeneralidades.edit();
                            editorGeneralidades.clear();
                            editorGeneralidades.commit();
                            editorGeneralidades.apply();
                            context.getSharedPreferences("datosGeneralidades", 0).edit().clear().commit();



                        }else{
                            Toast.makeText(context, codigo.getMensaje(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void reject(Exception e) {

                    }
        });
    }
}
