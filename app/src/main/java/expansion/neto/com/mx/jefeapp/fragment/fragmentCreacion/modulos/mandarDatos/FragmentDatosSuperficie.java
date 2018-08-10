package expansion.neto.com.mx.jefeapp.fragment.fragmentCreacion.modulos.mandarDatos;

import android.content.Context;
import android.widget.Toast;

import expansion.neto.com.mx.jefeapp.modelView.crearModel.Codigos;
import expansion.neto.com.mx.jefeapp.modelView.crearModel.CrearDatosSuperficie;
import expansion.neto.com.mx.jefeapp.provider.crearProvider.ProviderCrearSuperficie;

public class FragmentDatosSuperficie {
    Context context;
    public FragmentDatosSuperficie(Context context){
        this.context = context;
    }

    public void mandarDatosSuperficie(CrearDatosSuperficie crearDatosSuperficie){
        ProviderCrearSuperficie.getInstance(context).guardarSuperficie(crearDatosSuperficie,
                new ProviderCrearSuperficie.InterfaceCrearDatosSuperficie() {
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
