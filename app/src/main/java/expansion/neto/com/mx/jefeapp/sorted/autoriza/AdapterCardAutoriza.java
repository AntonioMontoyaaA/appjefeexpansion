package expansion.neto.com.mx.jefeapp.sorted.autoriza;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import expansion.neto.com.mx.jefeapp.R;
import expansion.neto.com.mx.jefeapp.modelView.DummyModel;
import expansion.neto.com.mx.jefeapp.modelView.autorizaModel.Autoriza;
import expansion.neto.com.mx.jefeapp.modelView.autorizaModel.AutorizaModel;
import expansion.neto.com.mx.jefeapp.utils.ImageUtil;

public class AdapterCardAutoriza extends ArrayAdapter<AutorizaModel> implements View.OnClickListener {

	private LayoutInflater mInflater;

	public AdapterCardAutoriza(Context context, List<AutorizaModel> items) {
		super(context, 0, items);
		mInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public long getItemId(int position) {
		return getItem(position).getmId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final ViewHolder holder;
		if (convertView == null) {
			convertView = mInflater.inflate(
					R.layout.list_item_google_cards_media, parent, false);
			holder = new ViewHolder();
			holder.image = (ImageView) convertView
					.findViewById(R.id.list_item_google_cards_media_image);
//			holder.artistName = (TextView) convertView
//					.findViewById(R.id.list_item_google_cards_media_artist_name);
//			holder.year = (TextView) convertView
//					.findViewById(R.id.list_item_google_cards_media_year);
//			holder.text = (TextView) convertView
//					.findViewById(R.id.list_item_google_cards_media_text);
//			holder.country = (TextView) convertView
//					.findViewById(R.id.list_item_google_cards_media_country);
//			holder.like = (TextView) convertView
//					.findViewById(R.id.list_item_google_cards_media_like);
//			holder.favorite = (TextView) convertView
//					.findViewById(R.id.list_item_google_cards_media_favorite);
//			holder.share = (TextView) convertView
//					.findViewById(R.id.list_item_google_cards_media_share);
//			holder.like.setOnClickListener(this);
//			holder.favorite.setOnClickListener(this);
//			holder.share.setOnClickListener(this);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

//		holder.like.setTag(position);
//		holder.favorite.setTag(position);
//		holder.share.setTag(position);
		AutorizaModel item = getItem(position);
		ImageUtil.displayImage(holder.image, item.getmImageURL(), null);

		return convertView;
	}

	private static class ViewHolder {
		public ImageView image;
		public TextView artistName;
		public TextView year;
		public TextView text;
		public TextView country;
		public TextView like;
		public TextView favorite;
		public TextView share;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int possition = (Integer) v.getTag();
		switch (v.getId()) {
//			case R.id.list_item_google_cards_media_like:
//				// click on share button
//				Toast.makeText(getContext(), "Like " + possition, Toast.LENGTH_SHORT).show();
//				break;
//			case R.id.list_item_google_cards_media_favorite:
//				// click on share button
//				Toast.makeText(getContext(), "Favorite " + possition, Toast.LENGTH_SHORT).show();
//				break;
//			case R.id.list_item_google_cards_media_share:
//				// click on share button
//				Toast.makeText(getContext(), "Share " + possition, Toast.LENGTH_SHORT).show();
//				break;
		}
	}



}
