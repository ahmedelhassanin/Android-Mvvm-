package com.example.mvvmex;

import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.net.InterfaceAddress;
import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends ListAdapter<Note,NoteAdapter.NoteHolder> {

    private OnItemClickLisner onItemClickLisner;

    public NoteAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<Note> DIFF_CALLBACK = new DiffUtil.ItemCallback<Note>() {
        @Override
        public boolean areItemsTheSame(Note oldItem, Note newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(Note oldItem, Note newItem) {
            return oldItem.getTitle().equals(newItem.getTitle()) &&
                    oldItem.getDes().equals(newItem.getDes()) &&
                    oldItem.getPriority() == newItem.getPriority();
        }
    };



    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.note_item,viewGroup,false);


        return new NoteHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder noteHolder, int i) {
        Note currentnote=getItem(i);
        noteHolder.text_tittle.setText(currentnote.getTitle());
        noteHolder.text_des.setText(currentnote.getDes());
        noteHolder.text_priority.setText(String.valueOf(currentnote.getPriority()));

    }





    public Note getNoteAt(int position){
        return getItem(position);
    }

    class NoteHolder extends RecyclerView.ViewHolder{
        private TextView text_tittle,text_des,text_priority;

        public NoteHolder(@NonNull View itemView) {
            super(itemView);
            text_tittle=itemView.findViewById(R.id.text_tittle);
            text_des=itemView.findViewById(R.id.text_des);
            text_priority=itemView.findViewById(R.id.text_priority);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position=getAdapterPosition();
                    if (onItemClickLisner !=null &&position !=RecyclerView.NO_POSITION) {
                        onItemClickLisner.OnItemClick(getItem(position));
                    }
                }
            });

        }
    }

    public interface OnItemClickLisner{
        void OnItemClick(Note note);
    }
    public void setOnItemClickLisner(OnItemClickLisner onItemClickLisner){
      this.onItemClickLisner=onItemClickLisner;
    }
}
