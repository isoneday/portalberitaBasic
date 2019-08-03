package com.imastudio.portalberita.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.imastudio.portalberita.MainActivity;
import com.imastudio.portalberita.R;
import com.imastudio.portalberita.model.BeritaItem;
import com.squareup.picasso.Picasso;

import java.util.List;

import static com.imastudio.portalberita.helper.MyConstants.BASEURL;

public class PortalBeritaAdapter extends
        RecyclerView.Adapter<PortalBeritaAdapter.ViewHolder> {


    MainActivity mainActivity;
    List<BeritaItem> dataBerita;

    public PortalBeritaAdapter(MainActivity mainActivity, List<BeritaItem> dataBerita) {
        this.mainActivity=mainActivity;
        this.dataBerita=dataBerita;
    }

    @NonNull
    @Override
    public PortalBeritaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = (LayoutInflater)
                mainActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v=inflater.inflate(R.layout.listitem, null,false);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull PortalBeritaAdapter.ViewHolder viewHolder, int i) {
        viewHolder.txtjudul.setText(dataBerita.get(i).getJudulBerita());
        viewHolder.txtpenulis.setText(dataBerita.get(i).getPenulis());
        viewHolder.txttanggal.setText(dataBerita.get(i).getTanggalPosting());
        Picasso.get().load(BASEURL+"images/"+dataBerita.get(i).getFoto())
                .placeholder(R.drawable.ic_android_black_24dp)
                .error(R.drawable.ic_android_black_24dp)
                .into(viewHolder.imgberita);

    }

    @Override
    public int getItemCount() {
        return dataBerita.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtjudul,txtpenulis,txttanggal;
        ImageView imgberita;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtjudul = itemView.findViewById(R.id.item_judul);
            txtpenulis = itemView.findViewById(R.id.item_penulis);
            txttanggal = itemView.findViewById(R.id.item_tanggal);
            imgberita =itemView.findViewById(R.id.item_image);
        }
    }
}
