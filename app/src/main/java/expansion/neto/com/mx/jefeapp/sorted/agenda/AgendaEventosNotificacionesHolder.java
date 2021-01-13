package expansion.neto.com.mx.jefeapp.sorted.agenda;


import expansion.neto.com.mx.jefeapp.databinding.ItemAgendaNotificacionesPrickerBinding;
import expansion.neto.com.mx.jefeapp.modelView.agendaModel.Notificaciones;
import expansion.neto.com.mx.jefeapp.sorted.SortedListAdapter;

public class AgendaEventosNotificacionesHolder extends SortedListAdapter.ViewHolder<Notificaciones.Notificacione> {

    ItemAgendaNotificacionesPrickerBinding itemAgendaNotificacionesPrickerBinding;

    public AgendaEventosNotificacionesHolder(final ItemAgendaNotificacionesPrickerBinding itemAgendaNotificacionesPrickerBinding, final Listener listener) {
        super(itemAgendaNotificacionesPrickerBinding.getRoot());
        this.itemAgendaNotificacionesPrickerBinding = itemAgendaNotificacionesPrickerBinding;
    }

    @Override
    protected void performBind(Notificaciones.Notificacione item) {
        itemAgendaNotificacionesPrickerBinding.setAgenda(item);
    }

    public interface Listener {
        void onAgendaSelect(Notificaciones.Notificacione model);
    }

}
