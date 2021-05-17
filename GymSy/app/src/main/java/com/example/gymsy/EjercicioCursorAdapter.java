package com.example.gymsy;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;

import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import java.time.Instant;

public class EjercicioCursorAdapter extends CursorAdapter {

    public EjercicioCursorAdapter(Context context, Cursor c) {
        super(context, c,0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        return inflater.inflate(R.layout.list_item_ejercicio, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        ImageView avatarImage = (ImageView) view.findViewById(R.id.iv_avatar);
        String avatarUri = cursor.getString(cursor.getColumnIndex(TablasBD.EjercicioEntry.IMAGEN_URI));

        Glide
                .with(context)
                .load(Uri.parse("file:///" + avatarUri))
                .asBitmap()
                .centerCrop()
                .into(new BitmapImageViewTarget(avatarImage) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable drawable
                                = RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                        avatarImage.setImageDrawable(drawable);
                    }
                });

    }
}
