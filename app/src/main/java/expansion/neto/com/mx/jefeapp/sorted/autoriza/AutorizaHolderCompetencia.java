package expansion.neto.com.mx.jefeapp.sorted.autoriza;


import expansion.neto.com.mx.jefeapp.databinding.ItemAutorizaCompetenciaPrickerBinding;
import expansion.neto.com.mx.jefeapp.databinding.ItemAutorizaPeatonalPrickerBinding;
import expansion.neto.com.mx.jefeapp.modelView.autorizaModel.Peatonal;
import expansion.neto.com.mx.jefeapp.modelView.autorizaModel.Zonificacion;
import expansion.neto.com.mx.jefeapp.sorted.SortedListAdapter;

public class AutorizaHolderCompetencia extends SortedListAdapter.ViewHolder<Zonificacion.Competencium> {

    ItemAutorizaCompetenciaPrickerBinding itemAutorizaCompetenciaPrickerBinding;

    public AutorizaHolderCompetencia(final ItemAutorizaCompetenciaPrickerBinding itemAutorizaCompetenciaPrickerBinding, final Listener listener) {
        super(itemAutorizaCompetenciaPrickerBinding.getRoot());
        this.itemAutorizaCompetenciaPrickerBinding = itemAutorizaCompetenciaPrickerBinding;
    }


    @Override
    protected void performBind(Zonificacion.Competencium item) {

    }

    public interface Listener {
        void onAutorizaSelect(Zonificacion.Competencium model);
    }

}
