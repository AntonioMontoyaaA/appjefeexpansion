package expansion.neto.com.mx.jefeapp.sorted.autorizadas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.Comparator;

import expansion.neto.com.mx.jefeapp.databinding.ItemAutorizadasPickerBinding;
import expansion.neto.com.mx.jefeapp.modelView.autorizaModel.Autorizadas;
import expansion.neto.com.mx.jefeapp.sorted.SortedListAdapter;

public class AdapterAutorizadas extends SortedListAdapter<Autorizadas.Autorizada> {

    AutorizadasHolder.Listener listener;

    Context context;

    public AdapterAutorizadas(Context context, Comparator<Autorizadas.Autorizada> comparator, AutorizadasHolder.Listener listener) {
        super(context, Autorizadas.Autorizada.class, comparator);
        this.listener = listener;
        this.context = context;
    }

    @Override
    protected ViewHolder<? extends Autorizadas.Autorizada> onCreateViewHolder(LayoutInflater inflater, ViewGroup parent, int viewType) {
        final ItemAutorizadasPickerBinding binding = ItemAutorizadasPickerBinding.inflate(inflater, parent, false);
        binding.setListener(listener);
        binding.setTipoCard(1);
        return new AutorizadasHolder(binding, listener);
    }

    @Override
    protected boolean areItemsTheSame(Autorizadas.Autorizada item1, Autorizadas.Autorizada item2) {
        return false;
    }

    @Override
    protected boolean areItemContentsTheSame(Autorizadas.Autorizada oldItem, Autorizadas.Autorizada newItem) {
        return false;
    }

}
