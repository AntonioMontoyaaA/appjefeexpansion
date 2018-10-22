package expansion.neto.com.mx.jefeapp.sorted.proceso;

import expansion.neto.com.mx.jefeapp.databinding.ItemNumMensajesPrickerBinding;
import expansion.neto.com.mx.jefeapp.databinding.ItemTiemposPrickerBinding;
import expansion.neto.com.mx.jefeapp.modelView.procesoModel.ChatNumMensajes;
import expansion.neto.com.mx.jefeapp.modelView.procesoModel.TiemposProceso;
import expansion.neto.com.mx.jefeapp.sorted.SortedListAdapter;

public class NumMensajesHolder extends SortedListAdapter.ViewHolder<ChatNumMensajes.Comentario> {

    ItemNumMensajesPrickerBinding itemTiemposPrickerBinding;

    public NumMensajesHolder(final ItemNumMensajesPrickerBinding itemTiemposPrickerBinding, final Listener listener) {
        super(itemTiemposPrickerBinding.getRoot());
        this.itemTiemposPrickerBinding = itemTiemposPrickerBinding;
    }

    @Override
    protected void performBind(ChatNumMensajes.Comentario item) {

        itemTiemposPrickerBinding.setAutorizaModel(item);

    }

    public interface Listener {
        void onAutorizaSelect(ChatNumMensajes.Comentario model);
    }

}
