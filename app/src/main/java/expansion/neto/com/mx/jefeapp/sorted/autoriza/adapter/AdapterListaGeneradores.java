package expansion.neto.com.mx.jefeapp.sorted.autoriza.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import expansion.neto.com.mx.jefeapp.R;
import expansion.neto.com.mx.jefeapp.modelView.crearModel.CompetenciasGeneradores;
import expansion.neto.com.mx.jefeapp.modelView.crearModel.CompetenciasGeneradoresV2;

/**
 * Created by Kevin on 26/6/2017.
 */

public class AdapterListaGeneradores extends RecyclerView.Adapter<AdapterListaGeneradores.ListaviewHolder> {

    private List<CompetenciasGeneradoresV2.OtrosGeneradore> listas_im;
    Context context;
    private AdapterListaGeneradores.OnItemClick mCallback;

    public AdapterListaGeneradores(List<CompetenciasGeneradoresV2.OtrosGeneradore> listas_im, Context fm
            , AdapterListaGeneradores.OnItemClick listener){
        this.listas_im = listas_im;
        this.context = fm;
        this.mCallback = listener;

    }

    public class ListaviewHolder extends RecyclerView.ViewHolder {
        TextView txtNombre;
        ImageView imagen;

        public ListaviewHolder(View itemView) {
            super(itemView);

            txtNombre = (TextView) itemView.findViewById(R.id.nombre);
            imagen = (ImageView) itemView.findViewById(R.id.imagen);

        }

    }

    @Override
    public ListaviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ListaviewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_autoriza_competencia_pricker, parent, false));
    }

    @Override
    public void onBindViewHolder(final ListaviewHolder holder, final int position) {

        holder.txtNombre.setText(listas_im.get(position).getNombreGenerador());

        if(listas_im.get(position).getGeneradorId()==5){
            String uri = "@drawable/g_iglesia";
            int imageResource = context.getResources().getIdentifier(uri, null, context.getPackageName());
            Drawable res = context.getResources().getDrawable(imageResource);
            holder.imagen.setImageDrawable(res);
        }else if(listas_im.get(position).getGeneradorId()==6){
            String uri = "@drawable/g_mercado";
            int imageResource = context.getResources().getIdentifier(uri, null, context.getPackageName());
            Drawable res = context.getResources().getDrawable(imageResource);
            holder.imagen.setImageDrawable(res);
        }else if(listas_im.get(position).getGeneradorId()==7){
            String uri = "@drawable/escuela";
            int imageResource = context.getResources().getIdentifier(uri, null, context.getPackageName());
            Drawable res = context.getResources().getDrawable(imageResource);
            holder.imagen.setImageDrawable(res);
        }else if(listas_im.get(position).getGeneradorId()==8){
            String uri = "@drawable/g_busstop";
            int imageResource = context.getResources().getIdentifier(uri, null, context.getPackageName());
            Drawable res = context.getResources().getDrawable(imageResource);
            holder.imagen.setImageDrawable(res);
        }else if(listas_im.get(position).getGeneradorId()==9){
            String uri = "@drawable/otros";
            int imageResource = context.getResources().getIdentifier(uri, null, context.getPackageName());
            Drawable res = context.getResources().getDrawable(imageResource);
            holder.imagen.setImageDrawable(res);
        }else if(listas_im.get(position).getGeneradorId()==10){
            String uri = "@drawable/g_recauderia";
            int imageResource = context.getResources().getIdentifier(uri, null, context.getPackageName());
            Drawable res = context.getResources().getDrawable(imageResource);
            holder.imagen.setImageDrawable(res);
        }else if(listas_im.get(position).getGeneradorId()==11){
            String uri = "@drawable/g_recauderia";
            int imageResource = context.getResources().getIdentifier(uri, null, context.getPackageName());
            Drawable res = context.getResources().getDrawable(imageResource);
            holder.imagen.setImageDrawable(res);
        }else if(listas_im.get(position).getGeneradorId()==12){
            String uri = "@drawable/g_comida";
            int imageResource = context.getResources().getIdentifier(uri, null, context.getPackageName());
            Drawable res = context.getResources().getDrawable(imageResource);
            holder.imagen.setImageDrawable(res);
        }else if(listas_im.get(position).getGeneradorId()==13){
            String uri = "@drawable/g_mercado";
            int imageResource = context.getResources().getIdentifier(uri, null, context.getPackageName());
            Drawable res = context.getResources().getDrawable(imageResource);
            holder.imagen.setImageDrawable(res);
        }else if(listas_im.get(position).getGeneradorId()==14){
            String uri = "@drawable/g_tianguis";
            int imageResource = context.getResources().getIdentifier(uri, null, context.getPackageName());
            Drawable res = context.getResources().getDrawable(imageResource);
            holder.imagen.setImageDrawable(res);
        }else if(listas_im.get(position).getGeneradorId()==15){
            String uri = "@drawable/g_tortilleria";
            int imageResource = context.getResources().getIdentifier(uri, null, context.getPackageName());
            Drawable res = context.getResources().getDrawable(imageResource);
            holder.imagen.setImageDrawable(res);
        }else if(listas_im.get(position).getGeneradorId()==16){
            String uri = "@drawable/g_carniceria";
            int imageResource = context.getResources().getIdentifier(uri, null, context.getPackageName());
            Drawable res = context.getResources().getDrawable(imageResource);
            holder.imagen.setImageDrawable(res);
        }else if(listas_im.get(position).getGeneradorId()==17){
            String uri = "@drawable/metro";
            int imageResource = context.getResources().getIdentifier(uri, null, context.getPackageName());
            Drawable res = context.getResources().getDrawable(imageResource);
            holder.imagen.setImageDrawable(res);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mCallback.onClick(listas_im.get(position).getGeneradorId(), listas_im.get(position).getNombreGenerador(),
                        listas_im.get(position).getNivelId());
                selectedPosition=position;
                notifyDataSetChanged();

            }
        });

        holder.txtNombre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mCallback.onClick(listas_im.get(position).getGeneradorId(), listas_im.get(position).getNombreGenerador(),
                        listas_im.get(position).getNivelId());
                selectedPosition=position;
                notifyDataSetChanged();

            }
        });

        if(selectedPosition==position) {
            holder.txtNombre.setTypeface(null, Typeface.BOLD);
        }else {
            holder.txtNombre.setTypeface(null, Typeface.NORMAL);
        }

    }

    int selectedPosition = -1;

    public interface OnItemClick {
        void onClick (int value, String nombre, int generadorId);
    }

    @Override
    public int getItemCount() {
        return listas_im.size();
    }

}
