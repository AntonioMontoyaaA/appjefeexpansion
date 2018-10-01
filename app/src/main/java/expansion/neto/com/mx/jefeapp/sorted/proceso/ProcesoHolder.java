package expansion.neto.com.mx.jefeapp.sorted.proceso;

import expansion.neto.com.mx.jefeapp.databinding.ItemProcesoPickerBinding;
import expansion.neto.com.mx.jefeapp.modelView.procesoModel.Proceso;
import expansion.neto.com.mx.jefeapp.sorted.SortedListAdapter;

public class ProcesoHolder extends SortedListAdapter.ViewHolder<Proceso.Memoria> {

    ItemProcesoPickerBinding itemProcesoPickerBinding;

    public ProcesoHolder(final ItemProcesoPickerBinding itemProcesoPickerBinding, final ProcesoHolder.Listener listener) {
        super(itemProcesoPickerBinding.getRoot());
        this.itemProcesoPickerBinding = itemProcesoPickerBinding;
    }

    @Override
    protected void performBind(Proceso.Memoria item) {
        itemProcesoPickerBinding.setProcesoModel(item);
    }

    public interface Listener {
        void onProcesoSelect(Proceso.Memoria model);
    }

}
