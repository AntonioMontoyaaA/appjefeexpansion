package expansion.neto.com.mx.jefeapp.ui.autoriza;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.Comparator;

import expansion.neto.com.mx.jefeapp.R;
import expansion.neto.com.mx.jefeapp.databinding.ActivityAutorizaListBinding;
import expansion.neto.com.mx.jefeapp.modelView.autorizaModel.Autoriza;
import expansion.neto.com.mx.jefeapp.modelView.autorizaModel.PorTerminar;
import expansion.neto.com.mx.jefeapp.modelView.loginModel.UsuarioLogin;
import expansion.neto.com.mx.jefeapp.provider.dashboardProvider.ExpansionGerenteProviderAutoriza;
import expansion.neto.com.mx.jefeapp.sorted.autoriza.AdapterAutoriza;
import expansion.neto.com.mx.jefeapp.sorted.autoriza.AutorizaHolder;


/**
 * Created by marcosmarroquin on 23/03/18.
 */

public class ActivityListaAutoriza extends AppCompatActivity
        implements AutorizaHolder.Listener{



    private ActivityAutorizaListBinding binding;
    UsuarioLogin.Perfil perfil;
    AdapterAutoriza adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        initDataBinding();
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        getListaAutoriza(perfil);
    }

    private static final Comparator<PorTerminar> ALPHABETICAL_COMPARATOR = new Comparator<PorTerminar>() {
        @Override
        public int compare(PorTerminar a, PorTerminar b) {
            return a.getCodigo().compareTo(b.getCodigo());
        }
    };

    /**
     * Método que setea la vista con el binding
     */
    private void initDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_autoriza_list);
    }

    public void getListaAutoriza(UsuarioLogin.Perfil perfil){

        //binding.prog.setVisibility(View.VISIBLE);

        ExpansionGerenteProviderAutoriza.getInstance(this).compruebaAutoriza(new ExpansionGerenteProviderAutoriza.ConsultaAutoriza() {
            @Override
            public void resolve(ArrayList<Autoriza> autoriza) {
                if(autoriza!=null){ }
            }

            @Override
            public void reject(Exception e) {

            }
        });
    }

    @Override
    public void onAutorizaSelect(PorTerminar.Memoria model) {
        Intent main = new Intent(ActivityListaAutoriza.this, ActivityAutorizar.class);
        ActivityListaAutoriza.this.startActivity(main);
        ActivityListaAutoriza.this.finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


}
