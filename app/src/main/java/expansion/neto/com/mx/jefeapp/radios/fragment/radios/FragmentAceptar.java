package expansion.neto.com.mx.jefeapp.radios.fragment.radios;


import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import expansion.neto.com.mx.jefeapp.R;
import expansion.neto.com.mx.jefeapp.databinding.FragmentFragmentAceptarBinding;
import expansion.neto.com.mx.jefeapp.radios.ui.radios.ActivityRadios;


public class FragmentAceptar extends DialogFragment {

    FragmentFragmentAceptarBinding binding;

    public String mensaje = "";

    public FragmentAceptar() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_fragment_aceptar,container,false);
        View view = binding.getRoot();

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().dismiss();

                if (mensaje.equals("sin_radios")){
                    getActivity().finish();
                    Intent main = new Intent(getContext(), ActivityRadios.class);
                    startActivity(main);
                }else if (mensaje.equals("sin_radios_asignados")){
                    getActivity().finish();
                }
            }
        });

        if (mensaje.equals("sin_radios")){
            binding.textoMensaje.setText(getString(R.string.sin_radios));
        }else if (mensaje.equals("sin_radios_asignados")){
            binding.textoMensaje.setText(getString(R.string.sin_radios_asignados));
        }
    }

    public void onResume() {
        super.onResume();
        Window window = getDialog().getWindow();
        window.setLayout(1064, 420);
        window.setGravity(Gravity.CENTER);
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}