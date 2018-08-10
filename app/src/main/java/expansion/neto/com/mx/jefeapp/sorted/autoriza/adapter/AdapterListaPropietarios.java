package expansion.neto.com.mx.jefeapp.sorted.autoriza.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import expansion.neto.com.mx.jefeapp.R;
import expansion.neto.com.mx.jefeapp.databinding.FragmentAutoriza6Binding;
import expansion.neto.com.mx.jefeapp.modelView.crearModel.PropietarioBusqueda;

/**
 * Created by Kevin on 26/6/2017.
 */

public class AdapterListaPropietarios extends RecyclerView.Adapter<AdapterListaPropietarios.ListaviewHolder> {

    private List<PropietarioBusqueda.ListaPropietario> listas_im;
    Context fm;
    private AdapterListaPropietarios.OnItemClick mCallback;

    public AdapterListaPropietarios(List<PropietarioBusqueda.ListaPropietario> listas_im, Context fm,
                                    AdapterListaPropietarios.OnItemClick listener){
        this.listas_im = listas_im;
        this.fm = fm;
        this.mCallback = listener;
    }

    public class ListaviewHolder extends RecyclerView.ViewHolder {
        TextView txtHoras;
        ImageView imgRenta;
        public ListaviewHolder(View itemView) {
            super(itemView);
            txtHoras = (TextView) itemView.findViewById(R.id.nombre);
            imgRenta = (ImageView) itemView.findViewById(R.id.yarenta);

        }
    }

    @Override
    public ListaviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ListaviewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_propietario_pricker, parent, false));
    }

    @Override
    public void onBindViewHolder(final ListaviewHolder holder, final int position) {
      holder.txtHoras.setText(listas_im.get(position).getNombrePropietario().toString()+" "+
              listas_im.get(position).getAPaternoPropietario().toString()+" "+
              listas_im.get(position).getAMaternoPropietario().toString());

      if(listas_im.get(position).getYaRenta().equals("1")){
          holder.imgRenta.setVisibility(View.VISIBLE);
          holder.imgRenta.setBackgroundResource(R.drawable.palomita_azul);
      }else{
          holder.imgRenta.setVisibility(View.VISIBLE);
          holder.imgRenta.setBackgroundResource(R.drawable.tache_cancel_rojo);
      }

      holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mCallback.onClick(
                        listas_im.get(position).getNombrePropietario(),
                        listas_im.get(position).getAPaternoPropietario(),
                        listas_im.get(position).getAMaternoPropietario(),
                        listas_im.get(position).getTelefono(),
                        listas_im.get(position).getMail()
                        );
                selectedPosition=position;
                notifyDataSetChanged();

            }
        });

        if(selectedPosition==position) {
            holder.txtHoras.setTypeface(null, Typeface.BOLD);
        }else {
            holder.txtHoras.setTypeface(null, Typeface.NORMAL);
        }
    }

    int selectedPosition = -1;

    public interface OnItemClick {
        void onClick (String nombre, String apellido, String apellidoM, String telefono, String email);
    }

    @Override
    public int getItemCount() {
        return listas_im.size();
    }

}
