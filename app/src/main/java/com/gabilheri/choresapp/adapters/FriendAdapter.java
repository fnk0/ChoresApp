package com.gabilheri.choresapp.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gabilheri.choresapp.R;
import com.gabilheri.choresapp.models.User;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 7/20/15.
 */
public class FriendAdapter extends RecyclerView.Adapter<FriendAdapter.ViewHolder> {

    List<User> users;

    ItemCallback callback;

    public FriendAdapter(List<User> users) {
        this.users = users;
    }

    public FriendAdapter(List<User> users, ItemCallback callback) {
        this.users = users;
        this.callback = callback;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_friend, parent, false);
        if(callback == null) {
            return new ViewHolder(view);
        } else {
            return new ViewHolder(view, callback);
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        User user = users.get(position);
        Glide.with(holder.itemView.getContext())
                .load(user.getUserPicture())
                .centerCrop()
                .crossFade()
                .into(holder.userPicture);

        holder.userName.setText(user.getName());
        Glide.with(holder.itemView.getContext())
                .load(user.getSocialMedia())
                .centerCrop()
                .crossFade()
                .into(holder.socialMedia);

        holder.itemView.setTag(user.getName());
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    /**
     * Interfce to handle the ItemClick callback
     */
    public interface ItemCallback {
        void onItemClick(View v);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @Bind(R.id.userPicture)
        public CircleImageView userPicture;

        @Bind(R.id.userName)
        public TextView userName;

        @Bind(R.id.socialMedia)
        public ImageView socialMedia;

        private ItemCallback callback;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public ViewHolder(View itemView, ItemCallback callback) {
            super(itemView);
            this.callback = callback;
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            callback.onItemClick(v);
        }
    }
}
