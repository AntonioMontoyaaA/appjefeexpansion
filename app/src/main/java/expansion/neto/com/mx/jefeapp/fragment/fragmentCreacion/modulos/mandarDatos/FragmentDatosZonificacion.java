package expansion.neto.com.mx.jefeapp.fragment.fragmentCreacion.modulos.mandarDatos;

import android.content.Context;
import android.widget.Toast;

import expansion.neto.com.mx.jefeapp.modelView.crearModel.Codigos;
import expansion.neto.com.mx.jefeapp.provider.crearProvider.ProviderCrearZonificacion;

public class FragmentDatosZonificacion {
    Context context;
    public FragmentDatosZonificacion(Context context){
        this.context = context;
    }

    public void mandarDatosZonificacion(String json){
        ProviderCrearZonificacion.getInstance(context).crearDatosZonificacion(json,
                new ProviderCrearZonificacion.InterfaceCrearDatosZonificacion() {
                    @Override
                    public void resolve(Codigos codigo) {
                        if(codigo.getCodigo()==200){

                        }else{
                            Toast.makeText(context.getApplicationContext(), codigo.getMensaje(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void reject(Exception e) {

                    }
        });
    }
}
