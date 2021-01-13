package expansion.neto.com.mx.jefeapp.ui.agenda;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.jaredrummler.materialspinner.MaterialSpinner;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import expansion.neto.com.mx.jefeapp.R;
import expansion.neto.com.mx.jefeapp.databinding.ActivityAddEventBinding;
import expansion.neto.com.mx.jefeapp.fragment.fragmentAgenda.FragmentDialogAgendaDia;
import expansion.neto.com.mx.jefeapp.fragment.fragmentAgenda.FragmentInicioAgenda;
import expansion.neto.com.mx.jefeapp.modelView.Ubicacion;
import expansion.neto.com.mx.jefeapp.modelView.agendaModel.Eventos;
import expansion.neto.com.mx.jefeapp.modelView.agendaModel.GuardarEvento;
import expansion.neto.com.mx.jefeapp.modelView.crearModel.Codigos;
import expansion.neto.com.mx.jefeapp.provider.agendaProvider.ProviderAgendaTareas;
import expansion.neto.com.mx.jefeapp.provider.agendaProvider.ProviderCrearEvento;
import expansion.neto.com.mx.jefeapp.utils.ServicioGPS;
import expansion.neto.com.mx.jefeapp.utils.Util;

public class ActivityAddEvento extends AppCompatActivity {

    private ActivityAddEventBinding binding;
    private GuardarEvento evento;
    String fechaAgenda;
    String fechaFinProgramada;
    String horaInicio;
    String horaFinal;
    String tareaxId = "5";
    ArrayList<String> nombresEventos;
    String usuarioId;

    String direccionF = "";
    Ubicacion gpsUbica;
    String fechaAgendaFinal;
    String fechaAgendaF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        initDataBinding();
        binding.toolbar.nombreTitulo.setText(getString(R.string.nuevoevento));
        binding.inicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getSupportFragmentManager();
                FragmentDialogAgendaDia dFragment = new FragmentDialogAgendaDia("inicio");
                dFragment.show(fm, "Dialog Fragment");
            }
        });

        binding.toolbar.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityAddEvento.super.onBackPressed();
            }
        });




        binding.fina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getSupportFragmentManager();
                FragmentDialogAgendaDia dFragment = new FragmentDialogAgendaDia("fin");
                dFragment.show(fm, "Dialog Fragment");
            }
        });


        binding.toolbar.guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               
                String usuario = null;
                SharedPreferences preferences = getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);

                if(todoDia==true){

                }else{
                    fechaAgenda = preferences.getString("fechaAgenda", "");
                    fechaFinProgramada = preferences.getString("fechaFinProgramada", "");
                    horaInicio = preferences.getString("horaIni", "");
                    horaFinal = preferences.getString("horaFi", "");
                    usuario = preferences.getString("usuario", "");


                    if(horaInicio.equals("") && horaFinal.equals("")){
                        horaInicio = "08:00";
                        horaFinal = "20:00";
                        fechaAgenda = preferences.getString("fechaSeleccionada", "");
                        fechaFinProgramada = fechaAgenda;
                    }

                    horaInicio = horaInicio.substring(0, 5);
                    horaFinal = horaFinal.substring(0, 5);



                    fechaAgendaFinal = fechaAgenda + " " + horaInicio+":00";
                    fechaAgendaF = fechaFinProgramada + " " + horaFinal+":00";
                }

                usuario = preferences.getString("usuario", "");

                String observaciones = binding.descripcion.getText().toString();

                String porAsignar = "[{\"entidadId\":"+usuario+"}]";

                evento = new GuardarEvento(usuario,
                        tareaxId,
                        fechaAgendaFinal,
                        fechaAgendaF,
                        observaciones,
                        direccionF,
                        String.valueOf(gpsUbica.lat),
                        String.valueOf(gpsUbica.lng),
                        "3",
                        porAsignar
                );

                ProviderCrearEvento.getInstance(getApplicationContext()).guardarEvento(evento, new ProviderCrearEvento.InterfaceCrearEvento() {
                    @Override
                    public void resolve(Codigos codigo) {
                        if(codigo!=null){
                            if(codigo.getCodigo()==200){
                                Toast.makeText(getApplicationContext(), codigo.getMensaje(), Toast.LENGTH_SHORT).show();
                                Intent main = new Intent(getApplicationContext(), FragmentInicioAgenda.class);
                                startActivity(main);
                            }else{
                                Toast.makeText(getApplicationContext(), codigo.getMensaje(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                    @Override
                    public void reject(Exception e) {

                    }
                });
            }
        });
    }

    private static final DateFormat TWELVE_TF = new SimpleDateFormat("hh:mm a");
    private static final DateFormat TWENTY_FOUR_TF = new SimpleDateFormat("HH:mm");

    public static String convertirHora(String horaDoce) throws ParseException {
            return TWENTY_FOUR_TF.format(TWELVE_TF.parse(horaDoce));
    }

    private void initDataBinding() {

        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_event);
        final SharedPreferences preferences = getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
        String usuarioId = preferences.getString("usuario", "");

        final Date hoy = Calendar.getInstance().getTime();
        long timeInMillis = System.currentTimeMillis();
        Calendar cal1 = Calendar.getInstance();
        cal1.setTimeInMillis(timeInMillis);
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "hh:mm");
        final String hora = dateFormat.format(cal1.getTime());



        String fecha = preferences.getString("fechaSeleccionada", "");
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("fechaAgenda", fecha);
        editor.putString("fechaFinProgramada", fecha);
        editor.putString("horaIni", "08:00");
        editor.putString("horaFi", "20:00");
        editor.apply();

        gpsUbica = gps();
        setDireccion(gpsUbica);

        binding.inicioFecha.setText(Util.getFechaFormat(hoy)+" "+hora);
        binding.finFecha.setText(Util.getFechaFormat(hoy)+" "+hora);

        ProviderAgendaTareas.getInstance(this).obtenerEventos(usuarioId, new ProviderAgendaTareas.InterfaceObtieneEventos() {
            @Override
            public void resolve(Eventos eventos) {
                if(eventos!=null && eventos.getEventos()!=null){
                    nombresEventos = new ArrayList<>();
                    if(eventos.getEventos().size()>0){
                        for(int i=0;i<eventos.getEventos().size();i++){
                            nombresEventos.add(eventos.getEventos().get(i).getNombre());
                        }
                        binding.spinnerHora.setItems(nombresEventos);

                        binding.spinnerHora.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {
                            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {

                                switch (position){
                                    case 0:
                                        tareaxId = "5";
                                        break;
                                    case 1:
                                        tareaxId = "4";
                                        break;
                                    case 2:
                                        tareaxId = "3";
                                        break;
                                    case 3:
                                        tareaxId = "2";
                                        break;
                                    case 4:
                                        tareaxId = "1";
                                        break;

                                }
                            }
                        });



                        if(binding.buscarNombre.isChecked()){

                            String fechaSeleccionada = preferences.getString("fechaSeleccionada", "");
                            if(!fechaSeleccionada.equals("")){
                                Date date = StringToDate(fechaSeleccionada);

                                binding.inicioFecha.setText(Util.getFechaFormat(date)+" ~ 08:00");
                                binding.finFecha.setText(Util.getFechaFormat(date)+" ~ 20:00");
                                binding.inicioFecha.setEnabled(false);
                                binding.finFecha.setEnabled(false);

                                SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                                String formattedDate = df.format(date);

                                fechaAgendaFinal = formattedDate + " 08:00:00";
                                fechaAgendaF = formattedDate + " 20:00:00";
                                todoDia = true;
                            }else{
                                binding.inicioFecha.setText(Util.getFechaFormat(hoy)+" ~ 08:00");
                                binding.finFecha.setText(Util.getFechaFormat(hoy)+" ~ 20:00");
                                binding.inicioFecha.setEnabled(false);
                                binding.finFecha.setEnabled(false);

                                Date c = Calendar.getInstance().getTime();

                                SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                                String formattedDate = df.format(c);

                                fechaAgendaFinal = formattedDate + " 08:00:00";
                                fechaAgendaF = formattedDate + " 20:00:00";
                                todoDia = true;
                            }


                        }else{

                            String fechaSeleccionada = preferences.getString("fechaSeleccionada", "");
                            Date date = StringToDate(fechaSeleccionada);

                            binding.inicioFecha.setText(Util.getFechaFormat(date)+" ~ 08:00");
                            binding.finFecha.setText(Util.getFechaFormat(date)+" ~ 20:00");

                        }

                        binding.buscarNombre.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                if(isChecked){

                                    binding.inicioFecha.setText(Util.getFechaFormat(hoy)+" ~ 08:00");
                                    binding.finFecha.setText(Util.getFechaFormat(hoy)+" ~ 20:00");
                                    binding.inicioFecha.setEnabled(false);
                                    binding.finFecha.setEnabled(false);

                                    Date c = Calendar.getInstance().getTime();

                                    SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                                    String formattedDate = df.format(c);

                                    fechaAgendaFinal = formattedDate + " 08:00:00";
                                    fechaAgendaF = formattedDate + " 20:00:00";
                                    todoDia = true;

                                }else{
                                    binding.inicioFecha.setEnabled(true);
                                    binding.finFecha.setEnabled(true);
                                    todoDia = false;
                                }
                            }
                        });


                    }
                }
            }

            @Override
            public void reject(Exception e) {

            }
        });

    }


    public static Date StringToDate(String dtStart){
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date date = format.parse(dtStart);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
       return null;
    }

    public void setFecha(String fecha, String tipo, String fechaFin){
        if(tipo.equals("inicio")){
            binding.inicioFecha.setText(fecha);
            binding.finFecha.setText(fechaFin);

        }else{
            binding.finFecha.setText(fecha);
        }
    }

    public void setDireccion(Ubicacion gpsUbica){
        Geocoder geocoder;
        List<Address> addresses = null;
        geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
        try {
            addresses = geocoder.getFromLocation(gpsUbica.lat, gpsUbica.lng, 1);
            direccionF = addresses.get(0).getAddressLine(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /******** gps **************/
    ServicioGPS gpsUbicas;
    Boolean todoDia = false;
    Double latitude, longitude, latitudeLast = 0.0, longitudeLast = 0.0;

    public Ubicacion gps() {
        Ubicacion ubicacion;
        latitudeLast = latitude;
        longitudeLast = longitude;
        gpsUbicas = new ServicioGPS(this);
        if (gpsUbicas.canGetLocation()) {
            latitude = gpsUbicas.getLatitude();
            longitude = gpsUbicas.getLongitude();
            ubicacion = new Ubicacion(latitude, longitude, true);
        } else {
            if(latitudeLast!=null){
                ubicacion = new Ubicacion(latitudeLast, longitudeLast, false);
            }else{
                ubicacion = new Ubicacion(0.0, 0.0, false);
            }
        }
        return ubicacion;
    }

}
