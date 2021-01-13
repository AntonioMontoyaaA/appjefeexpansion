package expansion.neto.com.mx.jefeapp.sorted.agenda;


import expansion.neto.com.mx.jefeapp.databinding.ItemAgendaEventosPrickerBinding;
import expansion.neto.com.mx.jefeapp.modelView.agendaModel.ConsultaEvento;
import expansion.neto.com.mx.jefeapp.sorted.SortedListAdapter;

public class AgendaEventosHolder extends SortedListAdapter.ViewHolder<ConsultaEvento.Agenda> {

    ItemAgendaEventosPrickerBinding itemAgendaEventosPrickerBinding;

    public AgendaEventosHolder(final ItemAgendaEventosPrickerBinding itemAgendaHorasPrickerBinding, final Listener listener) {
        super(itemAgendaHorasPrickerBinding.getRoot());
        this.itemAgendaEventosPrickerBinding = itemAgendaHorasPrickerBinding;
    }

    @Override
    protected void performBind(ConsultaEvento.Agenda item) {
        itemAgendaEventosPrickerBinding.setAgenda(item);
    }

    public interface Listener {
        void onAgendaSelect(ConsultaEvento.Agenda model);
    }

}
