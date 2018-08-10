package expansion.neto.com.mx.jefeapp.fragment.fragmentCreacion.modulos.mandarDatos;

import android.content.Context;
import android.widget.Toast;

import expansion.neto.com.mx.jefeapp.modelView.crearModel.Codigos;
import expansion.neto.com.mx.jefeapp.modelView.crearModel.CrearDatosPropietario;
import expansion.neto.com.mx.jefeapp.provider.crearProvider.ProviderCrearDatosPropietario;

public class FragmentDatosPropietario {
    Context context;
    public FragmentDatosPropietario(Context context){
        this.context = context;
    }

    public void mandarDatosPropietario(CrearDatosPropietario crearDatosPropietario){
        ProviderCrearDatosPropietario.getInstance(context).guardarPropietario(crearDatosPropietario,
                new ProviderCrearDatosPropietario.InterfaceCrearDatosPropietario() {
                    @Override
                    public void resolve(Codigos codigo) {
                        if(codigo.getCodigo()==200){

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
