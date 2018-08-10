package expansion.neto.com.mx.jefeapp.fragment.fragmentCreacion;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import expansion.neto.com.mx.jefeapp.R;
import expansion.neto.com.mx.jefeapp.databinding.FragmentCalificaBinding;
import expansion.neto.com.mx.jefeapp.ui.dashboard.ActivityMain;

/**
 * Created by marcosmarroquin on 28/03/18.
 */

public class FragmentCalifica extends DialogFragment {

    FragmentCalificaBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_califica,container,false);
        View view = binding.getRoot();

        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.calificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent main = new Intent(getActivity(), ActivityMain.class);
                getActivity().startActivity(main);
            }
        });
    }


}



