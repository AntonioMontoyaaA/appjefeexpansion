package expansion.neto.com.mx.jefeapp.sorted.autoriza.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import expansion.neto.com.mx.jefeapp.R;
import expansion.neto.com.mx.jefeapp.databinding.FragmentAutoriza6Binding;
import expansion.neto.com.mx.jefeapp.modelView.crearModel.CompetenciasGeneradores;
import expansion.neto.com.mx.jefeapp.modelView.crearModel.HorasPeatonales;

;

/**
 * Created by Kevin on 26/6/2017.
 */

public class AdapterListaHoras extends RecyclerView.Adapter<AdapterListaHoras.ListaviewHolder> {

    private List<HorasPeatonales.Detalle> listas_im;
    Context fm;
    private AdapterListaHoras.OnItemClick mCallback;
    FragmentAutoriza6Binding binding;
    String where;

    public AdapterListaHoras(List<HorasPeatonales.Detalle> listas_im,
                             Context fm, AdapterListaHoras.OnItemClick listener,
                             FragmentAutoriza6Binding binding, String where){

        this.listas_im = listas_im;
        this.fm = fm;
        this.binding = binding;
        this.mCallback = listener;
        this.where = where;
    }

    public class ListaviewHolder extends RecyclerView.ViewHolder {
        TextView txtHoras;
        LinearLayout borrar;
        public ListaviewHolder(View itemView) {
            super(itemView);
            txtHoras = (TextView) itemView.findViewById(R.id.nombre);
            borrar = (LinearLayout) itemView.findViewById(R.id.cancelar);
        }

    }

    @Override
    public ListaviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ListaviewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_autoriza_hora_pricker, parent, false));
    }

    @Override
    public void onBindViewHolder(final ListaviewHolder holder, final int position) {

        if(!where.equals("t")){
            holder.borrar.setVisibility(View.GONE);
        }else{
            holder.borrar.setVisibility(View.GONE);
        }

        holder.txtHoras.setText(listas_im.get(position).getHoraMin() + " - "+listas_im.get(position).getHoraMax());

        holder.borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mCallback!=null){
                    if(where.equals("t")){
                        mCallback.onClick(
                                listas_im.get(position).getHoraMin(),
                                listas_im.get(position).getHoraMax(),
                                binding
                        );
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listas_im.size();
    }

    public interface OnItemClick {
        void onClick (String horaI, String horaF, FragmentAutoriza6Binding binding);
    }

}
