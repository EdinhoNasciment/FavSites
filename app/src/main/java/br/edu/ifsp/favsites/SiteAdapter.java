package br.edu.ifsp.favsites;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SiteAdapter extends RecyclerView.Adapter<SiteAdapter.ViewHolder> {

    private Context context;
    private List<Site> dataset;
    private SiteItemClick listener;

    public SiteAdapter(Context context, List<Site> dataset, SiteItemClick listener){

        this.context = context;
        this.dataset = dataset;
        this.listener = listener;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){

        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.site_item,parent, false)
        RecyclerView.ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position){

        Site site = dataset.get(position);
        holder.apelido.setText(site.getApelido());
        holder.url.setText(site.getUrl());
        if (site.isFavorito()){
            holder.estrela.setColorFilter(context.getColor(R.color.red));
        }else{
            holder.estrela.setColorFilter(context.getColor(R.color.gray));
        }
        holder.estrela.setOnClickListener(v -> listener.clickEstrelaSiteItem(position));

        holder.itemView.setOnClickListener(v -> listener.clickSiteItem(position));

    }

    @Override
    public int getItem(){
        return dataset.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView apelido;
        public TextView url;
        ImageView coracao;

        public ViewHolder(@NonNull View itemView){

            super(itemView);
            apelido = itemView.findViewById(R.id.textview_apelido);
            url = itemView.findViewById((R.id.edittext_url));
            estrela = itemView.findViewById(R.id.img_estrela);

        }

    }
}
