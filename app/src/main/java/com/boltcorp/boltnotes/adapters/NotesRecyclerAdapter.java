package com.boltcorp.boltnotes.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.boltcorp.boltnotes.R;
import com.boltcorp.boltnotes.models.Note;
import com.boltcorp.boltnotes.util.Utility;

import java.util.ArrayList;

public class NotesRecyclerAdapter extends RecyclerView.Adapter<NotesRecyclerAdapter.ViewHolder> {

    private static final String TAG = "NotesRecyclerAdapter";

    private ArrayList<Note> mNotes = new ArrayList<>();
    private OnNoteListener mOnNoteListener;

    public NotesRecyclerAdapter(ArrayList<Note> notes, OnNoteListener onNoteListener) {
        this.mNotes = notes;
        this.mOnNoteListener = onNoteListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_note_list_item, parent, false);
        return new ViewHolder(view, mOnNoteListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        try {
            String month = mNotes.get(position).getNoteTime().substring(0, 2);
            month = Utility.getMonthFromNumber(month);
            String year = mNotes.get(position).getNoteTime().substring(3);
            String timeStamp = month + " " + year;

            holder.noteTime.setText(timeStamp);
            holder.noteTitle.setText(mNotes.get(position).getNoteTitle());
        }
        catch (Exception e) {
            Log.d(TAG, "onBindViewHolder: "+e);
        }
    }

    @Override
    public int getItemCount() {
        return mNotes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView noteTitle, noteTime;
        OnNoteListener onNoteListener;

        public ViewHolder(@NonNull View itemView, OnNoteListener onNoteListener) {
            super(itemView);
            noteTitle = itemView.findViewById(R.id.note_title);
            noteTime = itemView.findViewById(R.id.note_time);
            this.onNoteListener = onNoteListener;

            itemView.setOnClickListener(this);
        }

        public void onClick(View view){
            onNoteListener.onNoteClick(getAdapterPosition());
        }
    }

    public interface OnNoteListener{
        void onNoteClick(int position);
    }
}
