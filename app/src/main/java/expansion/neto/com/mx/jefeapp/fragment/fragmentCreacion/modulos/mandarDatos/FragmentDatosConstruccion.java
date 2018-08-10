package expansion.neto.com.mx.jefeapp.fragment.fragmentCreacion.modulos.mandarDatos;

import android.content.Context;
import android.widget.Toast;

import expansion.neto.com.mx.jefeapp.modelView.crearModel.Codigos;
import expansion.neto.com.mx.jefeapp.provider.crearProvider.ProviderCrearConstruccion;

public class FragmentDatosConstruccion {
    Context context;
    public FragmentDatosConstruccion(Context context){
        this.context = context;
    }

    public void mandarDatosConstruccion(String json){
        ProviderCrearConstruccion.getInstance(context).crearDatosConstruccion(json,
                new ProviderCrearConstruccion.InterfaceCrearDatosConstruccion() {
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
