package expansion.neto.com.mx.jefeapp.radios.sorted.radios.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import expansion.neto.com.mx.jefeapp.R;
import expansion.neto.com.mx.jefeapp.radios.modelView.radiosModel.DetalleRadio;
import expansion.neto.com.mx.jefeapp.radios.ui.radios.ActivityDetalleRadios;

import static expansion.neto.com.mx.jefeapp.radios.ui.radios.ActivityDetalleRadios.NOMBRERADIO;

public class AdapterRadios extends RecyclerView.Adapter<AdapterRadios.ViewHolder>{

    private Context context;
    private List<DetalleRadio> listDetalleRadio;
    private LayoutInflater layoutInflater;

    public AdapterRadios(Context context, List<DetalleRadio> listDetalleRadio){
        this.context = context;
        this.listDetalleRadio = listDetalleRadio;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(layoutInflater.inflate(R.layout.item_radios,parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final DetalleRadio detalleRadio = listDetalleRadio.get(position);
        holder.setData(detalleRadio);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //adapter radios
                ((Activity)context).finish();
                Intent intent = new Intent(context, ActivityDetalleRadios.class);
                intent.putExtra(NOMBRERADIO, detalleRadio.getNombreRadio());
                intent.putExtra(ActivityDetalleRadios.FECHAASIGANDO, detalleRadio.getFechaAsignado());
                intent.putExtra(ActivityDetalleRadios.TIPO, detalleRadio.getTipoEstrategia());
                intent.putExtra(ActivityDetalleRadios.STATUS, detalleRadio.getEstatusRadio());
                intent.putExtra(ActivityDetalleRadios.POBLACION, detalleRadio.getPoblacion().get(0).getPoblacion());
                intent.putExtra(ActivityDetalleRadios.PEA, detalleRadio.getPoblacion().get(0).getPea());
                intent.putExtra(ActivityDetalleRadios.VIVIENDAS, detalleRadio.getPoblacion().get(0).getViviendas());
                intent.putExtra(ActivityDetalleRadios.NSE, detalleRadio.getPoblacion().get(0).getNse());
                intent.putExtra(ActivityDetalleRadios.MERCADOS, detalleRadio.getGeneradores().get(0).getMercados());
                intent.putExtra(ActivityDetalleRadios.ESCUELA, detalleRadio.getGeneradores().get(0).getEscuelas());
                intent.putExtra(ActivityDetalleRadios.HOSPITALES, detalleRadio.getGeneradores().get(0).getHospitales());
                intent.putExtra(ActivityDetalleRadios.TEMPLOS, detalleRadio.getGeneradores().get(0).getTemplos());
                intent.putExtra(ActivityDetalleRadios.CALLE, detalleRadio.getUbicacion().get(0).getCallePrincipal());
                intent.putExtra(ActivityDetalleRadios.CALLE1, detalleRadio.getUbicacion().get(0).getEntreCalle1());
                intent.putExtra(ActivityDetalleRadios.CALLE2, detalleRadio.getUbicacion().get(0).getEntreCalle2());
                intent.putExtra(ActivityDetalleRadios.LATITUD, detalleRadio.getLatitud());
                intent.putExtra(ActivityDetalleRadios.LONGITUD, detalleRadio.getLongitud());
                intent.putExtra(ActivityDetalleRadios.RADIOID, detalleRadio.getRadioId());
                view.getContext().startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return listDetalleRadio.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvNombreRadio, tvFechaAsignado, tvTipo,tvStatus;
        public ViewHolder(View itemView) {
            super(itemView);
            tvNombreRadio = itemView.findViewById(R.id.tvNombreRadio);
            tvFechaAsignado = itemView.findViewById(R.id.tvFechaAsignado);
            tvTipo = itemView.findViewById(R.id.tvTipo);
            tvStatus = itemView.findViewById(R.id.tvStatus);
        }
        public void setData (DetalleRadio detalleradio){
            DetalleRadio radios1 = detalleradio;
            tvNombreRadio.setText(radios1.getNombreRadio());
            tvFechaAsignado.setText(radios1.getFechaAsignado());
            tvTipo.setText(radios1.getTipoEstrategia());
            tvStatus.setText(radios1.getEstatusRadio());
        }
    }
}
