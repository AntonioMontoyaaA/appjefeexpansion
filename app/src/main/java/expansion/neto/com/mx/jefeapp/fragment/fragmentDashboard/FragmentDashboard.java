package expansion.neto.com.mx.jefeapp.fragment.fragmentDashboard;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Toast;

import com.eralp.circleprogressview.ProgressAnimationListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import expansion.neto.com.mx.jefeapp.R;
import expansion.neto.com.mx.jefeapp.cron.Cron;
import expansion.neto.com.mx.jefeapp.cron.CronJob;
import expansion.neto.com.mx.jefeapp.cron.ReminderUtilities;
import expansion.neto.com.mx.jefeapp.cron.ReminderUtilitiesJob;
import expansion.neto.com.mx.jefeapp.databinding.FragmentDashboardBinding;
import expansion.neto.com.mx.jefeapp.fragment.fragmentAgenda.FragmentInicioAgenda;
import expansion.neto.com.mx.jefeapp.fragment.fragmentAutorizadas.FragmentInicioAutorizadas;
import expansion.neto.com.mx.jefeapp.fragment.fragmentCreacion.FragmentInicioAutoriza;
import expansion.neto.com.mx.jefeapp.fragment.fragmentProceso.FragmentInicioProceso;
import expansion.neto.com.mx.jefeapp.fragment.fragmentRechazadas.FragmentInicioRechazadas;
import expansion.neto.com.mx.jefeapp.modelView.dashboardModel.Dashboard;
import expansion.neto.com.mx.jefeapp.modelView.loginModel.Permiso;
import expansion.neto.com.mx.jefeapp.modelView.loginModel.Usuario;
import expansion.neto.com.mx.jefeapp.modelView.loginModel.UsuarioLogin;
import expansion.neto.com.mx.jefeapp.provider.dashboardProvider.ProviderDatosDashboard;
import expansion.neto.com.mx.jefeapp.radios.ui.radios.ActivityRadios;
import expansion.neto.com.mx.jefeapp.ui.autoriza.ActivityAutorizar;
import expansion.neto.com.mx.jefeapp.utils.Util;

public class FragmentDashboard extends Fragment {

    FragmentDashboardBinding binding;
    String mes;
    String semana;
    UsuarioLogin.Perfil perfil = new UsuarioLogin.Perfil();
    int anio = Calendar.getInstance().get(Calendar.YEAR) ;
    String anioString;
    int totalDaysInYear;
    int totalWeeks;

    int semanaRestaInt = 1;
    String semanaResta;
    int mesRestaInt = 0;
    String mesResta;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_dashboard,container,false);
        View v = binding.getRoot();


        Calendar mCalendar = new GregorianCalendar(TimeZone.getDefault());
        mCalendar.setFirstDayOfWeek(Calendar.MONDAY);
        mCalendar.set(anio,Calendar.DECEMBER,31);
        totalDaysInYear = mCalendar.get(Calendar.DAY_OF_YEAR);
        totalWeeks = totalDaysInYear / 7;

        Date hoy = Calendar.getInstance().getTime();
        String upperString = Util.getFechaDay(hoy).substring(0,1).toUpperCase() + Util.getFechaDay(hoy).substring(1);
        binding.dia.setText("Hoy " + upperString + ", " + Util.getFechaFormat(hoy));

        getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
        Calendar fecha = Calendar.getInstance();
        final int meses = fecha.get(Calendar.MONTH) + 1;
        getContext().getSharedPreferences("datosSuperficie", 0).edit().clear().apply();
        SharedPreferences preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editorExpansion = preferences.edit();
        editorExpansion.putString("anioConsulta", String.valueOf(anio));
        editorExpansion.putString("mesDasbord", String.valueOf(meses-1));
        editorExpansion.putString("mesTaco", String.valueOf(meses));
        editorExpansion.putString("tipoSitio", "");
        editorExpansion.apply();
        binding.nombreJefe.setVisibility(View.INVISIBLE);
        getDatos(String.valueOf(meses), "0");
        getMesSemana();
        String mese = getMes(meses);
        binding.setSemana("Semana "+semana);
        binding.setMes(mese);
        binding.setAnioString(" "+anioString);

        binding.derMes.setEnabled(false);
        binding.derSemana.setEnabled(false);

        binding.crearMD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getContext().getSharedPreferences("datosSuperficie", 0).edit().clear().apply();
                SharedPreferences preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
                SharedPreferences.Editor editorExpansion = preferences.edit();
                editorExpansion.putString("mdIdterminar", "");
                editorExpansion.apply();
                Intent main = new Intent(getContext(), ActivityAutorizar.class);
                startActivity(main);
            }
        });

        binding.autorizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent main = new Intent(getContext(), FragmentInicioAutoriza.class);
                startActivity(main);
            }
        });

        binding.proceso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent main = new Intent(getContext(), FragmentInicioProceso.class);
                startActivity(main);

            }
        });

        binding.agenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent main = new Intent(getContext(), FragmentInicioAgenda.class);
                startActivity(main);

            }
        });

        binding.rechazadas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent main = new Intent(getContext(), FragmentInicioRechazadas.class);
                startActivity(main);
            }
        });

        binding.autorizadas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent main = new Intent(getContext(), FragmentInicioAutorizadas.class);
                startActivity(main);

            }
        });

        binding.izqSemana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.derSemana.setEnabled(true);
                binding.derSemana.setAlpha(1.0f);
                if(semanaRestaInt!=0){
                    binding.izqSemana.setEnabled(true);
                    semanaRestaInt--;
                    semanaResta = String.valueOf(semanaRestaInt);
                    getDatos("0", semanaResta);
                    //binding.setSemana("Semana "+semanaResta);
                }else{
                    binding.izqSemana.setEnabled(false);
                }
            }
        });

        binding.clradio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent main = new Intent(getContext(), ActivityRadios.class);
                startActivity(main);

            }
        });

        binding.derSemana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.izqSemana.setEnabled(true);

                semanaRestaInt = (semanaRestaInt)+1;
                int semanaActual = Integer.parseInt(getMesSemana());
                if(semanaRestaInt==semanaActual){
                    getDatos("0", String.valueOf(semanaRestaInt));
                    //binding.setSemana("Semana "+String.valueOf(semanaRestaInt));
                    binding.derSemana.setAlpha(0.0f);
                    binding.derSemana.setEnabled(false);

                }else {
                    getDatos("0", String.valueOf(semanaRestaInt));
                    //binding.setSemana("Semana "+String.valueOf(semanaRestaInt));
                    binding.derSemana.setAlpha(1.0f);
                }
            }
        });

        binding.izqMes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.derMes.setEnabled(true);
                binding.derMes.setAlpha(1.0f);
                binding.izqMes.setEnabled(true);
                mesRestaInt--;
                mesResta = String.valueOf(mesRestaInt);
                getDatos(mesResta, "0");
                //String nombreMes = getMes(mesRestaInt);
                //binding.setMes(nombreMes);
                editorExpansion.putString("mesTaco", mesResta);
                editorExpansion.apply();

            }
        });

        binding.derMes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.izqMes.setEnabled(true);

                mesRestaInt = (mesRestaInt)+1;

                if(mesRestaInt==13){
                    mesRestaInt=1;
                    anio++;
                    anioString = String.valueOf(anio);
                    binding.setAnioString(" "+anioString);
                }
                editorExpansion.putString("anioConsulta", String.valueOf(anio));
                Calendar fecha = Calendar.getInstance();
                int mesActual = fecha.get(Calendar.MONTH) + 1;
                if(mesRestaInt==mesActual && anio == Calendar.getInstance().get(Calendar.YEAR)){
                    getDatos(String.valueOf(mesRestaInt), "0");
                    String nombreMes = getMes(mesRestaInt);
                    binding.setMes(nombreMes);
                    binding.derMes.setAlpha(0.0f);
                    binding.derMes.setEnabled(false);

                    editorExpansion.putString("mesDasbord", String.valueOf(mesRestaInt));
                    editorExpansion.putString("mesTaco", String.valueOf(mesRestaInt));
                    editorExpansion.apply();

                }else {
                    getDatos(String.valueOf(mesRestaInt), "0");
                    String mesNombre = getMes(mesRestaInt);
                    binding.setMes(mesNombre);
                    binding.derMes.setAlpha(1.0f);
                    binding.derMes.setEnabled(true);
                    editorExpansion.putString("mesDasbord", String.valueOf(mesRestaInt));
                    editorExpansion.putString("mesTaco", String.valueOf(mesRestaInt));
                    editorExpansion.apply();
                }
            }
        });

        ReminderUtilities.scheduleCronReminder(getContext());
        ReminderUtilitiesJob.scheduleCronReminder(getContext());

        return v;
    }


    public String getMesSemana(){
        Date date = new Date();
        anioString = String.valueOf(anio);
        mes  = (String)
                DateFormat.format("MMMM",  date); // Jun
        mes = mes.substring(0,1).toUpperCase() + mes.substring(1).toLowerCase();
        Calendar now = Calendar.getInstance();
        now.setTime(date);
        int year = now.get(Calendar.YEAR);
        int month = now.get(Calendar.MONTH);
        int day = now.get(Calendar.DAY_OF_MONTH);
        now.set(Calendar.YEAR, year);
        now.set(Calendar.MONTH, month);
        now.set(Calendar.DATE, day);
        semana = String.valueOf(now.get(Calendar.WEEK_OF_YEAR));
        return semana;
    }

    public String getMes(int mesNum){

        if(mesNum==1){
            mes = "Enero";
        }if(mesNum==2){
            mes = "Febrero";
        }if(mesNum==3){
            mes = "Marzo";
        }if(mesNum==4){
            mes = "Abril";
        }if(mesNum==5){
            mes = "Mayo";
        }if(mesNum==6){
            mes = "Junio";
        }if(mesNum==7){
            mes = "Julio";
        }if(mesNum==8){
            mes = "Agosto";
        }if(mesNum==9){
            mes = "Septiembre";
        }if(mesNum==10){
            mes = "Octubre";
        }if(mesNum==11){
            mes = "Noviembre";
        }if(mesNum==12){
            mes = "Diciembre";
        }
        return mes;
    }

    public void setTacometros(int planMes, int realMes, int planSemana, int realSemana){

        if(planSemana==0 || planMes==0 ){

        }else{
            binding.tacometroMes.setTextEnabled(false);
            binding.tacometroMes.setInterpolator(new AccelerateDecelerateInterpolator());
            binding.tacometroMes.setStartAngle(270);
            binding.tacometroMes.setProgressWithAnimation((100/planMes*realMes), 2000);
            binding.tacometroMes.addAnimationListener(new ProgressAnimationListener() {
                @Override
                public void onValueChanged(float value) { }

                @Override
                public void onAnimationEnd() { }
            });

            binding.tacometroSemana.setTextEnabled(false);
            binding.tacometroSemana.setInterpolator(new AccelerateDecelerateInterpolator());
            binding.tacometroSemana.setStartAngle(270);
            binding.tacometroSemana.setProgressWithAnimation((100/planSemana*realSemana), 2000);

            binding.tacometroSemana.addAnimationListener(new ProgressAnimationListener() {
                @Override
                public void onValueChanged(float value) {

                }

                @Override
                public void onAnimationEnd() { }
            });
        }
    }
    public void getDatos(String buscaMes, String semanas){
        SharedPreferences preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
        SharedPreferences.Editor editorExpansion = preferences.edit();
        String usuarioId = preferences.getString("usuario","");
        String area = preferences.getString("areaxpuesto","");
        //blockUI();

        Gson gson = new Gson();
        String json = preferences.getString("permisos", null);
        Type type = new TypeToken<ArrayList<Permiso>>() {}.getType();
        ArrayList<Permiso> permisos = gson.fromJson(json, type);

        getPermisos(permisos, binding);

        int semana = Integer.valueOf(semanas);
        int mesSolicitud = Integer.valueOf(buscaMes);
        if(mesSolicitud > 0 ){
            //buscaMes = String.valueOf(--mesSolicitud);
            editorExpansion.putString("mesDasbord", String.valueOf(mesSolicitud));
        }else if(semana > totalWeeks){
            semanas = "1";
            anio++;
            anioString = String.valueOf(anio);
            binding.setAnioString(" "+anioString);
            editorExpansion.putString("anioConsulta", String.valueOf(anio));
            editorExpansion.putString("mesDasbord", "1");
        }else if (semana == 0){
            anio--;
            anioString = String.valueOf(anio);
            binding.setAnioString(" "+anioString);
            Calendar mCalendar = new GregorianCalendar(TimeZone.getDefault());
            mCalendar.setFirstDayOfWeek(Calendar.MONDAY);
            mCalendar.set(anio,Calendar.DECEMBER,31);
            totalDaysInYear = mCalendar.get(Calendar.DAY_OF_YEAR);
            totalWeeks = totalDaysInYear / 7;
            semanas = String.valueOf(totalWeeks);
            editorExpansion.putString("anioConsulta", String.valueOf(anio));
            editorExpansion.putString("mesDasbord", "12");
        }
        editorExpansion.apply();
        String anio2 = String.valueOf(anio);


        ProviderDatosDashboard.getInstance(getContext()).obtenerDatosAutorizadas(semanas, buscaMes, anio2, usuarioId, area, new ProviderDatosDashboard.ConsultaDatosDashboard() {
            @Override
            public void resolve(Dashboard dashboard) {
                if(dashboard!=null){
                    if(dashboard.getCodigo()==200 && dashboard!=null){
                        for(int i=0;i<dashboard.getTotales().size();i++){
                            binding.izqSemana.setEnabled(true);
                            binding.derMes.setEnabled(true);
                            binding.derSemana.setEnabled(true);
                            binding.izqMes.setEnabled(true);
                            if(dashboard.getTotales().get(i).getEstatusid()==2){
                                binding.totalProceso.setText(dashboard.getTotales().get(i).getTotal()+"");
                            }
                            if(dashboard.getTotales().get(i).getEstatusid()==3){
                                binding.totalRechazados.setText(dashboard.getTotales().get(i).getTotal()+"");
                            }
                            if(dashboard.getTotales().get(i).getEstatusid()==1){
                                binding.totalTerminar.setText(dashboard.getTotales().get(i).getTotal()+"");
                            }

                            if(dashboard.getTotales().get(i).getEstatusid()==4){
                                binding.totalAutorizadas.setText(dashboard.getTotales().get(i).getTotal()+"");
                            }
                        }

                        int planMes;
                        int realMes;
                        int planSem;
                        int realSem;

                        if(dashboard.getProductividad().get(0).getPlanMes()==null){
                            planMes = 0;
                        }else{
                            planMes = dashboard.getProductividad().get(0).getPlanMes();
                        }

                        if(dashboard.getProductividad().get(0).getRealMes()==null){
                            realMes = 0;
                        }else{
                            realMes = dashboard.getProductividad().get(0).getRealMes();
                        }

                        if(dashboard.getProductividad().get(0).getPlanSem()==null){
                            planSem = 0;
                        }else{
                            planSem = dashboard.getProductividad().get(0).getPlanSem();
                        }

                        if(dashboard.getProductividad().get(0).getRealSem()==null){
                            realSem = 0;
                        }else{
                            realSem = dashboard.getProductividad().get(0).getRealSem();
                        }

                        setTacometros(planMes,
                                realMes,
                                planSem,
                                realSem);

                        String mese = getMes(dashboard.getProductividad().get(0).getMes());
                        binding.setMes(mese);

                        binding.setSemana("Semana "+ dashboard.getProductividad().get(0).getSemana());

                        semanaRestaInt =  dashboard.getProductividad().get(0).getSemana();
                        mesRestaInt = dashboard.getProductividad().get(0).getMes();


                        Calendar fecha = Calendar.getInstance();
                        final int meses = fecha.get(Calendar.MONTH) + 1;

                        if(meses==mesRestaInt){
                            binding.derMes.setEnabled(false);
                            binding.derMes.setAlpha(0.0f);
                        }else{
                            binding.derMes.setEnabled(true);
                            binding.derMes.setAlpha(1.0f);
                        }

                        if(Integer.valueOf(getMesSemana())==semanaRestaInt){
                            binding.derSemana.setEnabled(false);
                            binding.derSemana.setAlpha(0.0f);
                        }else{
                            binding.derSemana.setEnabled(true);
                            binding.derSemana.setAlpha(1.0f);
                        }

                        perfil = Usuario.sharedGet(getContext());
                        perfil.setPlanMes(planMes);
                        perfil.setRealMes(realMes);
                        perfil.setPlanSemana(planSem);
                        perfil.setRealSemana(realSem);
                        binding.setPerfil(perfil);
                        binding.nombreJefe.setVisibility(View.VISIBLE);


                        SharedPreferences preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
                        final SharedPreferences.Editor editorExpansion = preferences.edit();
                        editorExpansion.putString("mesDasbord", String.valueOf(dashboard.getProductividad().get(0).getMes()));
                        editorExpansion.putString("mesTaco", String.valueOf(dashboard.getProductividad().get(0).getMes()));
                        editorExpansion.apply();
                    }
                 //   unblockUI();
                }else{
                    Toast.makeText(getContext(), "Error al cargar los datos", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void reject(Exception e) {

            }
        });
    }

    private void blockUI(){
        Util.addProgressBar(getContext(), binding.container,binding.container.getChildCount()-0 );
    }

    private void unblockUI(){
        binding.container.removeViewAt(binding.container.getChildCount()-1);
    }

    ArrayList<Permiso> permisosJefe;
    public void getPermisos(ArrayList<Permiso> permiso, FragmentDashboardBinding binding){
        permisosJefe = new ArrayList<>();
        if(permiso!=null && permiso.size()>0){
            for(int i=0;i<permiso.size();i++){
                if(permiso.get(i).getFimoduloid()==7){
                    permisosJefe.add(permiso.get(i));
                }
            }

            for(int j=0;j<permisosJefe.size();j++){
                int valor = permisosJefe.get(j).getFisubmodulo();
                switch (valor){
                    case 1:
                        if(permisosJefe.get(j).getFiestatus()==1){
                            binding.crearMD.setVisibility(View.VISIBLE);
                        }else{
                            binding.crearMD.setVisibility(View.GONE);
                        }
                        break;
                    case 2:
                        if(permisosJefe.get(j).getFiestatus()==1){
                            binding.autorizar.setVisibility(View.VISIBLE);
                        }else{
                            binding.autorizar.setVisibility(View.GONE);
                        }
                        break;
                    case 3:
                        if(permisosJefe.get(j).getFiestatus()==1){
                            binding.proceso.setVisibility(View.VISIBLE);
                        }else{
                            binding.proceso.setVisibility(View.GONE);
                        }
                        break;
                    case 4:
                        if(permisosJefe.get(j).getFiestatus()==1){
                            binding.rechazadas.setVisibility(View.VISIBLE);
                        }else{
                            binding.rechazadas.setVisibility(View.GONE);
                        }
                        break;
                    case 5:
                        if(permisosJefe.get(j).getFiestatus()==1){
                            binding.autorizadas.setVisibility(View.VISIBLE);
                        }else{
                            binding.autorizadas.setVisibility(View.GONE);
                        }
                        break;
                    case 6:
                        if(permisosJefe.get(j).getFiestatus()==1){
                            binding.agenda.setVisibility(View.VISIBLE);
                        }else{
                            binding.agenda.setVisibility(View.INVISIBLE);
                        }
                        break;
                }
            }
        }
    }

}

