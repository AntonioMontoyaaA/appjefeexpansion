package expansion.neto.com.mx.jefeapp.fragment.fragmentCreacion;

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
import expansion.neto.com.mx.jefeapp.databinding.FragmentDialogGuardarMdBinding;

/**
 * Created by Kevin on 26/6/2017.
 */

public class FragmentDialogGuardar extends DialogFragment {

    FragmentDialogGuardarMdBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_dialog_guardar_md,container,false);
        View view = binding.getRoot();

        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.aceptars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().dismiss();
            }
        });

    }

    public void onResume() {
        super.onResume();
        Window window = getDialog().getWindow();
        window.setLayout(1064, 420);
        window.setGravity(Gravity.CENTER);
    }
}
