package expansion.neto.com.mx.jefeapp.sorted.autoriza;


import expansion.neto.com.mx.jefeapp.databinding.ItemAutorizaPeatonalPrickerBinding;
import expansion.neto.com.mx.jefeapp.modelView.autorizaModel.Peatonal;
import expansion.neto.com.mx.jefeapp.sorted.SortedListAdapter;

public class AutorizaHolderPeatonal extends SortedListAdapter.ViewHolder<Peatonal> {

    ItemAutorizaPeatonalPrickerBinding itemAutorizaPeatonalPrickerBinding;

    public AutorizaHolderPeatonal(final ItemAutorizaPeatonalPrickerBinding itemAutorizaPeatonalPrickerBinding, final Listener listener) {
        super(itemAutorizaPeatonalPrickerBinding.getRoot());
        this.itemAutorizaPeatonalPrickerBinding = itemAutorizaPeatonalPrickerBinding;
    }

    @Override
    protected void performBind(Peatonal item) {
        itemAutorizaPeatonalPrickerBinding.setPeatonal(item);
    }

    public interface Listener {
        void onAutorizaSelect(Peatonal model);
    }

}
