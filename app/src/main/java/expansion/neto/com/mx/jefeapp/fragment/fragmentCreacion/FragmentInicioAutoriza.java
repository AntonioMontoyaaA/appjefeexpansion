package expansion.neto.com.mx.jefeapp.fragment.fragmentCreacion;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import expansion.neto.com.mx.jefeapp.R;
import expansion.neto.com.mx.jefeapp.fragment.fragmentTerminar.FragmentCardTerminar;
import expansion.neto.com.mx.jefeapp.fragment.fragmentTerminar.FragmentDialogCancelarMdTerminar;
import expansion.neto.com.mx.jefeapp.ui.dashboard.ActivityMain;

/**
 * Clase la cual es encargada de manejar correctamente los TAB LAYOUTS de la pantalla de IMPULSO, este verifica si eres
 * director entonces se agrega un solo tab de lo contrario se agrega uno segundo con los indicadores de la sucursal
 * correspondiente.
 */
public class FragmentInicioAutoriza extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private LinearLayout back;
    private TextView titulo;
    private LinearLayout guardar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.index_layout);

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        viewPager = (ViewPager) findViewById(R.id.pager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
        back = (LinearLayout) findViewById(R.id.back);

        titulo = (TextView) findViewById(R.id.nombre_titulo);
        guardar = (LinearLayout) findViewById(R.id.guardar);
        guardar.setVisibility(View.GONE);
        titulo.setText(getString(R.string.terminar));

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent main = new Intent(getApplicationContext(), ActivityMain.class);
                startActivity(main);
            }
        });


        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);



    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new FragmentCardTerminar(), getString(R.string.md));
        //adapter.addFragment(new FragmentCardTerminar(), getString(R.string.area));
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {


        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }


    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            getSupportFragmentManager().popBackStack();
        } else {
            Intent main = new Intent(getApplicationContext(), ActivityMain.class);
            startActivity(main);
        }
    }

}