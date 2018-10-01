package expansion.neto.com.mx.jefeapp.ui.dashboard;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.lang.reflect.Type;
import java.util.ArrayList;

import expansion.neto.com.mx.jefeapp.R;
import expansion.neto.com.mx.jefeapp.fragment.fragmentAgenda.FragmentAgendaNotificaciones;
import expansion.neto.com.mx.jefeapp.fragment.fragmentAgenda.FragmentInicioAgenda;
import expansion.neto.com.mx.jefeapp.fragment.fragmentAutorizadas.FragmentInicioAutorizadas;
import expansion.neto.com.mx.jefeapp.fragment.fragmentCreacion.FragmentDialogCancelarMd;
import expansion.neto.com.mx.jefeapp.fragment.fragmentCreacion.FragmentInicioAutoriza;
import expansion.neto.com.mx.jefeapp.fragment.fragmentDashboard.FragmentDashboard;
import expansion.neto.com.mx.jefeapp.fragment.fragmentProceso.FragmentInicioProceso;
import expansion.neto.com.mx.jefeapp.fragment.fragmentRechazadas.FragmentInicioRechazadas;
import expansion.neto.com.mx.jefeapp.fragment.fragmentTerminar.FragmentCardTerminar;
import expansion.neto.com.mx.jefeapp.fragment.fragmentTerminar.FragmentTerminar;
import expansion.neto.com.mx.jefeapp.modelView.agendaModel.Notificaciones;
import expansion.neto.com.mx.jefeapp.modelView.loginModel.Permiso;
import expansion.neto.com.mx.jefeapp.provider.agendaProvider.ProviderObtieneNotificaciones;
import expansion.neto.com.mx.jefeapp.radios.ui.radios.ActivityRadios;
import expansion.neto.com.mx.jefeapp.ui.agenda.ActivityNotificaciones;
import expansion.neto.com.mx.jefeapp.ui.autoriza.ActivityAutorizar;
import expansion.neto.com.mx.jefeapp.utils.Util;
import expansion.neto.com.mx.jefeapp.utils.desing.CustomTypefaceSpan;

import static expansion.neto.com.mx.jefeapp.constantes.RestUrl.TIPO_NOTIFICACION;


/**
 * Created by Kevin on 24/6/2017.
 * Clase que hace la primera vista de la app, en esta se encuentran los metodos para los efectos del NavigationView
 *
 */
public class ActivityMain extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private static final String SELECTED_ITEM_ID = "SELECTED_ITEM_ID";
    private final Handler mDrawerHandler = new Handler();

    private int mPrevSelectedId;
    private int mSelectedId;
    DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;
    Intent main;
    LinearLayout notificacion;
    TextView numNotificaciones;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mNavigationView = findViewById(R.id.navigation_view);
        Toolbar mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        notificacion = (LinearLayout) findViewById(R.id.linearNotificacion);
        numNotificaciones = (TextView) findViewById(R.id.numNotificacion);

        SharedPreferences preferences = getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
        //startService(new Intent(getApplicationContext(), ServicioRutas.class));

        notificacion.setVisibility(View.VISIBLE);
        getNotificaciones(numNotificaciones);

        notificacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                main = new Intent(getApplicationContext(), ActivityNotificaciones.class);
                startActivity(main);

            }
        });


        Gson gson = new Gson();
        String json = preferences.getString("permisos", null);
        Type type = new TypeToken<ArrayList<Permiso>>() {}.getType();
        ArrayList<Permiso> permisos = gson.fromJson(json, type);

        getPermisos(permisos);

        assert mNavigationView != null;
        mNavigationView.setNavigationItemSelectedListener(this);
        setUserDataUI();

        mDrawerLayout = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this,
                mDrawerLayout, mToolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                super.onDrawerSlide(drawerView, 0);

            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, 0);
            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        mSelectedId = mNavigationView.getMenu().getItem(prefs.getInt("default_view", 0)).getItemId();
        mSelectedId = savedInstanceState == null ? mSelectedId : savedInstanceState.getInt(SELECTED_ITEM_ID);
        mPrevSelectedId = mSelectedId;
        mNavigationView.getMenu().findItem(mSelectedId).setChecked(true);

        if (savedInstanceState == null) {
            mDrawerHandler.removeCallbacksAndMessages(null);
            mDrawerHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    navigate(mSelectedId);
                }
            }, 250);
            boolean openDrawer = prefs.getBoolean("open_drawer", false);
            if (openDrawer)
                mDrawerLayout.openDrawer(GravityCompat.START);
            else
                mDrawerLayout.closeDrawers();
        }

        ImageLoader imageLoader = ImageLoader.getInstance();
        if (!imageLoader.isInited()) {
            imageLoader.init(ImageLoaderConfiguration.createDefault(this));
        }

        Menu m = mNavigationView.getMenu();
        for (int i=0;i<m.size();i++) {
            MenuItem mi = m.getItem(i);
            SubMenu subMenu = mi.getSubMenu();
            if (subMenu!=null && subMenu.size() >0 ) {
                for (int j=0; j <subMenu.size();j++) {
                    MenuItem subMenuItem = subMenu.getItem(j);
                    applyFontToMenuItem(subMenuItem);
                }
            }
            applyFontToMenuItem(mi);
        }

    }

    public void getNotificaciones(final TextView numNotificaciones){
        SharedPreferences preferences = getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
        String usuarioId = preferences.getString("usuario", "");
        ProviderObtieneNotificaciones.getInstance(this).obtenerNotificaciones(usuarioId, TIPO_NOTIFICACION, new ProviderObtieneNotificaciones.InterfaceObtieneNotificaciones() {
            @Override
            public void resolve(Notificaciones eventos) {
                if(eventos!=null){
                    if (eventos.getCodigo()==200){
                        int totalNotificaciones = 0;
                        for(int i=0;i<eventos.getNotificaciones().size();i++){
                            if(eventos.getNotificaciones().get(i).getEstatus().equals("0") ||
                                    eventos.getNotificaciones().get(i).getEstatus().equals("") ||
                                    eventos.getNotificaciones().get(i).getEstatus().equals(null)){
                                totalNotificaciones++;
                            }
                        }
                        numNotificaciones.setText(totalNotificaciones+"");
                    }
                }
            }
            @Override
            public void reject(Exception e) { }
        });
    }

    private void navigate(int itemId) {
        final View elevation = findViewById(R.id.elevation);
        Fragment navFragment = null;

        switch (itemId) {
            case R.id.txt_inicio:
                mPrevSelectedId = itemId;
                setTitle("");
                navFragment = new FragmentDashboard();
                break;

            case R.id.txt_crear:
                mPrevSelectedId = itemId;


                main = new Intent(this, ActivityAutorizar.class);
                startActivity(main);


                break;

            case R.id.txt_terminar:


                main = new Intent(this, FragmentInicioAutoriza.class);
                startActivity(main);


                mPrevSelectedId = itemId;

                break;

            case R.id.txt_autorizadas:

                if(permisoAutorizadas){
                    main = new Intent(this, FragmentInicioRechazadas.class);
                    startActivity(main);
                }

                mPrevSelectedId = itemId;
                break;

            case R.id.txt_proceso:
                if(permisoProceso){
                    main = new Intent(this, FragmentInicioProceso.class);
                    startActivity(main);
                }
                mPrevSelectedId = itemId;

                break;
            case R.id.txt_rechazadas:
                if(permisoRechazadas){
                    Intent main = new Intent(this, FragmentInicioAutorizadas.class);
                    startActivity(main);
                }
                mPrevSelectedId = itemId;

                break;
            case R.id.txt_agenda:
                if(permisoAgenda){
                    main = new Intent(this, FragmentInicioAgenda.class);
                    startActivity(main);
                }
                mPrevSelectedId = itemId;
                break;
            case R.id.txt_misradios:
                main = new Intent(this, ActivityRadios.class);
                startActivity(main);
                mPrevSelectedId = itemId;
                break;
            case R.id.txt_sesion:
                mPrevSelectedId = itemId;
                Util.cerrarSesion(this);
                break;
        }




        final LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, dp(4));

        if (navFragment != null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
            try {
                transaction.replace(R.id.content_frame, navFragment).commit();

                if (elevation != null) {
                    params.topMargin = navFragment instanceof FragmentDashboard ? dp(48) : 0;

                    Animation a = new Animation() {
                        @Override
                        protected void applyTransformation(float interpolatedTime, Transformation t) {
                            elevation.setLayoutParams(params);
                        }
                    };
                    a.setDuration(150);
                    elevation.startAnimation(a);
                }
            } catch (IllegalStateException ignored) {
            }
        }
    }


    public int dp(float value) {
        float density = getApplicationContext().getResources().getDisplayMetrics().density;

        if (value == 0) {
            return 0;
        }
        return (int) Math.ceil(density * value);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        menuItem.setChecked(true);

        mSelectedId = menuItem.getItemId();
        mDrawerHandler.removeCallbacksAndMessages(null);
        mDrawerHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                navigate(mSelectedId);
            }
        }, 250);
        mDrawerLayout.closeDrawers();
        return true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SELECTED_ITEM_ID, mSelectedId);
    }

    private void applyFontToMenuItem(MenuItem mi) {
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/HelveticaNeue.ttf");
        SpannableString mNewTitle = new SpannableString(mi.getTitle());
        mNewTitle.setSpan(new CustomTypefaceSpan("" , font), 0 , mNewTitle.length(),  Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        mi.setTitle(mNewTitle);
    }


    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            //super.onBackPressed();
        }
    }

    /**
     * Se encarga de llenar la información del Usuario
     * en la cabecera del menu
     */
    private void setUserDataUI() {
        View vista = mNavigationView.getHeaderView(0);
    }


    ArrayList<Permiso> permisosGerente;
    public void getPermisos(ArrayList<Permiso> permiso) {
        if(permiso!=null){

            permisosGerente = new ArrayList<>();
            for(int i=0;i<permiso.size();i++){
                if(permiso.get(i).getFimoduloid() == MODULO_DASHBOARD) {
                    permisosGerente.add(permiso.get(i));
                }
            }

            for(int j=0;j< permisosGerente.size();j++){
                int valor = permisosGerente.get(j).getFisubmodulo();

                switch (valor){
                    case MODULO_POR_AUTORIZAR_ID:
                        if(permisosGerente.get(j).getFiestatus()==1){
                            permisoAutorizar = true;
                        }else{
                            permisoAutorizar = false;
                        }
                        break;
                    case MODULO_EN_PROCESO_ID:
                        if(permisosGerente.get(j).getFiestatus()==1){
                            permisoProceso = true;
                        }else{
                            permisoProceso = false;
                        }
                        break;
                    case MODULO_AUTORIZADAS_ID:
                        if(permisosGerente.get(j).getFiestatus()==1){
                            permisoAutorizadas = true;
                        }else{
                            permisoAutorizadas = false;
                        }
                        break;
                    case MODULO_RECHAZADAS_ID:
                        if(permisosGerente.get(j).getFiestatus()==1){
                            permisoRechazadas = true;
                        }else{
                            permisoRechazadas = false;
                        }
                        break;
                    case MODULO_AGENDA_ID:
                        if(permisosGerente.get(j).getFiestatus()==1){
                            permisoAgenda = true;
                        }else{
                            permisoAgenda = false;
                        }
                        break;
                    case MODULO_COLABORADORES_ID:
                        if(permisosGerente.get(j).getFiestatus()==1){
                            permisoColaboradores = true;
                        }else{
                            permisoColaboradores = false;
                        }
                        break;
                }
            }
        }
    }

    private static final int MODULO_DASHBOARD = 7;
    private static final int MODULO_POR_AUTORIZAR_ID = 7;
    private static final int MODULO_EN_PROCESO_ID = 3;
    private static final int MODULO_AUTORIZADAS_ID = 5;
    private static final int MODULO_RECHAZADAS_ID = 4;
    private static final int MODULO_AGENDA_ID = 6;
    private static final int MODULO_COLABORADORES_ID = 8;


    Boolean permisoAutorizar;
    Boolean permisoProceso;
    Boolean permisoAutorizadas;
    Boolean permisoRechazadas;
    Boolean permisoAgenda;
    Boolean permisoColaboradores;

}
