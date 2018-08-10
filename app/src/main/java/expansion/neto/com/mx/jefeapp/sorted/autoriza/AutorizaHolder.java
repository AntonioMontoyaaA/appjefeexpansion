package expansion.neto.com.mx.jefeapp.sorted.autoriza;


import expansion.neto.com.mx.jefeapp.databinding.ItemAutorizaPrickerBinding;
import expansion.neto.com.mx.jefeapp.modelView.autorizaModel.PorTerminar;
import expansion.neto.com.mx.jefeapp.sorted.SortedListAdapter;

public class AutorizaHolder extends SortedListAdapter.ViewHolder<PorTerminar.Memoria> {

    ItemAutorizaPrickerBinding itemAutorizaPrickerBinding;


    public AutorizaHolder(final ItemAutorizaPrickerBinding itemAutorizaPrickerBinding, final Listener listener) {
        super(itemAutorizaPrickerBinding.getRoot());
        this.itemAutorizaPrickerBinding = itemAutorizaPrickerBinding;
    }

    @Override
    protected void performBind(PorTerminar.Memoria item) {
        itemAutorizaPrickerBinding.setAutorizaModel(item);
    }

    public interface Listener {
        void onAutorizaSelect(PorTerminar.Memoria model);
    }

}
