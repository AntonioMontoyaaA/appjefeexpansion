/*
 * Copyright 2014 Niek Haarman
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package expansion.neto.com.mx.jefeapp.fragment.fragmentTerminar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.databinding.DataBindingUtil;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import expansion.neto.com.mx.jefeapp.R;
import expansion.neto.com.mx.jefeapp.databinding.ActivityAutorizaListBinding;
import expansion.neto.com.mx.jefeapp.modelView.autorizaModel.PorTerminar;
import expansion.neto.com.mx.jefeapp.modelView.loginModel.UsuarioLogin;
import expansion.neto.com.mx.jefeapp.provider.autorizaProvider.ProviderDatosPorTerminar;
import expansion.neto.com.mx.jefeapp.sorted.autoriza.AdapterAutoriza;
import expansion.neto.com.mx.jefeapp.sorted.autoriza.AutorizaHolder;
import expansion.neto.com.mx.jefeapp.ui.porterminar.ActivityPorTerminar;


public class FragmentCardTerminar extends Fragment implements AutorizaHolder.Listener{

	private ActivityAutorizaListBinding binding;
	AdapterAutoriza adapter;
	List<PorTerminar.Memoria> listaMemorias = null;

	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
							 @Nullable Bundle savedInstanceState) {

		binding = DataBindingUtil.inflate(inflater, R.layout.activity_autoriza_list,container,false);
		View view = binding.getRoot();

		getListaAutoriza();
		getContext().getSharedPreferences("datosSuperficie", 0).edit().clear().apply();
		adapter = new AdapterAutoriza(getContext(),ALPHABETICAL_COMPARATOR, this);
		binding.recyclerAutoriza.setLayoutManager(new LinearLayoutManager(getContext()));
		binding.recyclerAutoriza.setAdapter(adapter);

		RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 1);
		binding.recyclerAutoriza.setLayoutManager(mLayoutManager);
		binding.recyclerAutoriza.addItemDecoration(new GridSpacingItemDecoration(1, dpToPx(1), true));
		binding.recyclerAutoriza.setItemAnimator(new DefaultItemAnimator());

		binding.buscar.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
									  int count) {
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
										  int after) {
			}

			@SuppressLint("DefaultLocale")
			@Override
			public void afterTextChanged(Editable editable) {
				if(listaMemorias!=null){
					String texto = binding.buscar.getText().toString();
					List<PorTerminar.Memoria> listaTemporal = new ArrayList<PorTerminar.Memoria>();

					binding.recyclerAutoriza.removeAllViews();
					adapter.edit().removeAll().commit();

					if(listaMemorias!=null){
						if (texto.equals("")) {
							adapter.edit().replaceAll(listaMemorias).commit();
							adapter.notifyItemRangeRemoved(0, adapter.getItemCount());
						} else {
							for(PorTerminar.Memoria memoria : listaMemorias) {
								if(memoria.getCreador().toLowerCase().contains(texto.toLowerCase())
										|| memoria.getNombresitio().toLowerCase().contains(texto.toLowerCase())) {
									listaTemporal.add(memoria);
								}
							}
							adapter.edit().replaceAll(listaTemporal).commit();
							adapter.notifyItemRangeRemoved(0, adapter.getItemCount());
						}
					}
				}
			}
		});
		return view;
	}


	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
	}

	private static final Comparator<PorTerminar.Memoria> ALPHABETICAL_COMPARATOR = new Comparator<PorTerminar.Memoria>() {
		@Override
		public int compare(PorTerminar.Memoria a, PorTerminar.Memoria b) {
			return a.getParciales().compareTo(b.getParciales());
		}
	};

	public void getListaAutoriza(){

		binding.prog.setVisibility(View.VISIBLE);

		SharedPreferences preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
		String usuarioId = preferences.getString("usuario","");
		String area = preferences.getString("areaxpuesto","");
		String meses = preferences.getString("mesDasbord","");
		String anio = preferences.getString("anioConsulta","");

		ProviderDatosPorTerminar.getInstance(getContext()).obtenerDatosAutorizadas(
				usuarioId, area, meses, anio, new ProviderDatosPorTerminar.ConsultaDatosAutorizadas() {
			@Override
			public void resolve(PorTerminar datosSitio) {
				if(datosSitio!=null){
					if(datosSitio.getCodigo()==200 && datosSitio.getMemorias()!=null){
						if(datosSitio.getCodigo()!=404){
							listaMemorias = datosSitio.getMemorias();
							adapter.edit().replaceAll(datosSitio.getMemorias()).commit();
							adapter.notifyItemRangeRemoved(0, adapter.getItemCount());
							binding.prog.setVisibility(View.GONE);
						}else{
							binding.prog.setVisibility(View.GONE);
							Snackbar snackbar = Snackbar.make(binding.layout,
									Html.fromHtml("<b><font color=\"#254581\">" +
											getContext().getString(R.string.mdnT) +
											"</font></b>"), Snackbar.LENGTH_SHORT);
							View snackBarView = snackbar.getView();
							snackBarView.setBackgroundColor(getContext().getResources().getColor(R.color.snackBar));
							snackbar.show();
						}
					}else{
						binding.prog.setVisibility(View.GONE);
						Snackbar snackbar = Snackbar.make(binding.layout,
								Html.fromHtml("<b><font color=\"#254581\">" +
										datosSitio.getMensaje() +
										"</font></b>"), Snackbar.LENGTH_SHORT);
						View snackBarView = snackbar.getView();
						snackBarView.setBackgroundColor(getContext().getResources().getColor(R.color.snackBar));
						snackbar.show();
					}
				}



			}

			@Override
			public void reject(Exception e) {

			}
		});
	}

	@Override
	public void onAutorizaSelect(PorTerminar.Memoria model) {

	//	Log.e("*****", model.getMemoriaid()+"");

		SharedPreferences preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = preferences.edit();
		editor.putString("mdIdterminar", "");
		editor.apply();
		String md = model.getMemoriaid();
		editor.putString("mdIdterminar", md);
		editor.putString("banderaMapa", "1");

		editor.apply();
		getContext().getSharedPreferences("datosSuperficie", 0).edit().clear().apply();
		Intent main = new Intent(getContext(), ActivityPorTerminar.class);
		getContext().startActivity(main);
		getActivity().finish();
	}

	/**
	 * RecyclerView item decoration - give equal margin around grid item
	 */
	public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

		private int spanCount;
		private int spacing;
		private boolean includeEdge;

		public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
			this.spanCount = spanCount;
			this.spacing = spacing;
			this.includeEdge = includeEdge;
		}

		@Override
		public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
			int position = parent.getChildAdapterPosition(view); // item position
			int column = position % spanCount; // item column

			if (includeEdge) {
				outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
				outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

				if (position < spanCount) { // top edge
					outRect.top = spacing;
				}
				outRect.bottom = spacing; // item bottom
			} else {
				outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
				outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
				if (position >= spanCount) {
					outRect.top = spacing; // item top
				}
			}
		}
	}

	/**
	 * Converting dp to pixel
	 */
	private int dpToPx(int dp) {
		Resources r = getResources();
		return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
	}

}
