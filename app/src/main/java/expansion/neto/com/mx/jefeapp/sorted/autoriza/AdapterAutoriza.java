package expansion.neto.com.mx.jefeapp.sorted.autoriza;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.Comparator;

import expansion.neto.com.mx.jefeapp.databinding.ItemAutorizaPrickerBinding;
import expansion.neto.com.mx.jefeapp.modelView.autorizaModel.PorTerminar;
import expansion.neto.com.mx.jefeapp.sorted.SortedListAdapter;

/**
 * Clase que implementa el adaptador de los Recycler View
 * Created by Kevin on 26/6/2017.
 */
public class AdapterAutoriza extends SortedListAdapter<PorTerminar.Memoria> {

    AutorizaHolder.Listener listener;

    Context context;

    public AdapterAutoriza(Context context, Comparator<PorTerminar.Memoria> comparator, AutorizaHolder.Listener listener) {
        super(context, PorTerminar.Memoria.class, comparator);
        this.listener = listener;
        this.context = context;
    }

    @Override
    protected SortedListAdapter.ViewHolder<? extends PorTerminar.Memoria> onCreateViewHolder(LayoutInflater inflater, ViewGroup parent, int viewType) {
        final ItemAutorizaPrickerBinding binding = ItemAutorizaPrickerBinding.inflate(inflater, parent, false);
        binding.setListener(listener);
        return new AutorizaHolder(binding, listener);
    }

    @Override
    protected boolean areItemsTheSame(PorTerminar.Memoria item1, PorTerminar.Memoria item2) {
        return false;
    }

    @Override
    protected boolean areItemContentsTheSame(PorTerminar.Memoria oldItem, PorTerminar.Memoria newItem) {
        return false;
    }
}