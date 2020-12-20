package main.main.professor101;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SuggestionAdapter extends RecyclerView.Adapter<SuggestionAdapter.SuggestionViewHolder> {

    private ArrayList<Suggestion> arrayList;
    private Context context;

    public SuggestionAdapter(ArrayList<Suggestion> arrayList, Context context) {
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
    public interface OnItemClickListener {
        void onItemClick(View v, int position);
    }

    private OnItemClickListener mListener1 = null;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener1 = listener;
    }

    @NonNull
    @Override
    public SuggestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_suggestion,parent,false);
        SuggestionViewHolder holder = new SuggestionViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull SuggestionViewHolder holder, int position) {
        holder.tv_content.setText(arrayList.get(position).getContent());
        holder.tv_title.setText(arrayList.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return (arrayList != null ? arrayList.size() : 0);
    }

    public class SuggestionViewHolder extends RecyclerView.ViewHolder {
        TextView tv_title;
        TextView tv_content;

        public SuggestionViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tv_content = itemView.findViewById(R.id.sugContent);
            this.tv_title = itemView.findViewById(R.id.sugTitle);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        if (mListener != null) {
                            mListener.onItemLongClick(view, pos);
                        }
                    }
                    return true;
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        if (mListener1 != null) {
                            mListener1.onItemClick(view, pos);
                        }
                    }
                }
            });
        }
    }
}
