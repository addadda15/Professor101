package main.main.professor101;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentViewHolder> {

    private ArrayList<Comment> arrayList;
    private Context context;

    public CommentAdapter(ArrayList<Comment> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    public interface OnItemLongClickListener {
        void onItemLongClick(View v, int position);
    }

    private OnItemLongClickListener mListener = null;

    public void setOnItemLongClickListener(OnItemLongClickListener listener) {
        this.mListener = listener;
    }

    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_comment, parent, false);
        CommentViewHolder holder = new CommentViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {
        holder.tvId.setText(arrayList.get(position).getId());
        holder.tvCmt.setText(arrayList.get(position).getComment());
        holder.tvScore.setText(String.valueOf(arrayList.get(position).getRatingScore()));
    }

    @Override
    public int getItemCount() {
        return (arrayList != null ? arrayList.size() : 0);
    }

    public class CommentViewHolder extends RecyclerView.ViewHolder {
        TextView tvId;
        TextView tvCmt;
        TextView tvScore;

        public CommentViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tvCmt = itemView.findViewById(R.id.comment);
            this.tvId = itemView.findViewById(R.id.cmtId);
            this.tvScore = itemView.findViewById(R.id.ratingScore);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        if (mListener != null) {
                            mListener.onItemLongClick(view, pos);
                        }
                    }
                    return false;
                }
            });
        }
    }
}
