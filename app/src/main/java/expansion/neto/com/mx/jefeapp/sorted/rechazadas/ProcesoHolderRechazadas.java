package expansion.neto.com.mx.jefeapp.sorted.rechazadas;

import expansion.neto.com.mx.jefeapp.databinding.ItemProcesoPickerBinding;
import expansion.neto.com.mx.jefeapp.modelView.procesoModel.Proceso;
import expansion.neto.com.mx.jefeapp.sorted.SortedListAdapter;

public class ProcesoHolderRechazadas extends SortedListAdapter.ViewHolder<Proceso.Memoria> {

    ItemProcesoPickerBinding itemProcesoPickerBinding;

    public ProcesoHolderRechazadas(final ItemProcesoPickerBinding itemProcesoPickerBinding, final ProcesoHolderRechazadas.Listener listener) {
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
