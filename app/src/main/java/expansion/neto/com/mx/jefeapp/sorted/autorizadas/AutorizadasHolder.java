package expansion.neto.com.mx.jefeapp.sorted.autorizadas;

import expansion.neto.com.mx.jefeapp.databinding.ItemAutorizadasPickerBinding;
import expansion.neto.com.mx.jefeapp.modelView.autorizaModel.Autorizadas;
import expansion.neto.com.mx.jefeapp.sorted.SortedListAdapter;

public class AutorizadasHolder extends SortedListAdapter.ViewHolder<Autorizadas.Autorizada> {

    ItemAutorizadasPickerBinding itemProcesoPickerBinding;

    public AutorizadasHolder(final ItemAutorizadasPickerBinding itemProcesoPickerBinding, final AutorizadasHolder.Listener listener) {
        super(itemProcesoPickerBinding.getRoot());
        this.itemProcesoPickerBinding = itemProcesoPickerBinding;
    }

    @Override
    protected void performBind(Autorizadas.Autorizada item) {
        itemProcesoPickerBinding.setProcesoModel(item);
    }

    public interface Listener {
        void onProcesoSelect(Autorizadas.Autorizada model);
    }
}
