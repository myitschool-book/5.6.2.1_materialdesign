package ru.samsung.itschool.mdev.materialdesign;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class TileContentFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RecyclerView recyclerView = (RecyclerView) inflater.inflate(
                R.layout.rv_item, container, false);
        MyContentAdapter adapter = new MyContentAdapter(recyclerView.getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        // отступ для плитки
        int tilePadding = getResources().getDimensionPixelSize(R.dimen.tile_padding);
        recyclerView.setPadding(tilePadding, tilePadding, tilePadding, tilePadding);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        return recyclerView;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView picture;
        public TextView name;
        public MyViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.fragment_tile_content, parent, false));
            picture = itemView.findViewById(R.id.tile_picture);
            name = itemView.findViewById(R.id.tile_title);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, DetailActivity.class);
                    context.startActivity(intent);
                }
            });
        }
    }
    /**
     * Adapter для отображения в recycler view.
     */
    public static class MyContentAdapter extends RecyclerView.Adapter<MyViewHolder> {
        // Кол-во RecyclerView.
        private static final int LENGTH = 18;
        private final String[] mProjects;
        private final Drawable[] mProjectPictures;
        public MyContentAdapter(Context context) {
            Resources resources = context.getResources();
            mProjects = resources.getStringArray(R.array.projects);
            TypedArray a = resources.obtainTypedArray(R.array.projects_picture);
            mProjectPictures = new Drawable[a.length()];
            for (int i = 0; i < mProjectPictures.length; i++) {
                mProjectPictures[i] = a.getDrawable(i);
            }
            a.recycle();
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyViewHolder(LayoutInflater.from(parent.getContext()), parent);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.picture.setImageDrawable(mProjectPictures[position % mProjectPictures.length]);
            holder.name.setText(mProjects[position % mProjects.length]);
        }

        @Override
        public int getItemCount() {
            return LENGTH;
        }
    }
}