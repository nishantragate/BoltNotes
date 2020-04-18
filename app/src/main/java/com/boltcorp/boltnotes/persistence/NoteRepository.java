package com.boltcorp.boltnotes.persistence;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.boltcorp.boltnotes.async.DeleteAsyncTask;
import com.boltcorp.boltnotes.async.InsertAsyncTask;
import com.boltcorp.boltnotes.async.UpdateAsyncTask;
import com.boltcorp.boltnotes.models.Note;

import java.util.List;

public class NoteRepository {

    private NoteDatabase mNoteDatabase;

    public NoteRepository(Context context) {
        mNoteDatabase = NoteDatabase.getInstance(context);
    }

    public void insertNoteTask(Note note) {
        new InsertAsyncTask(mNoteDatabase.getNoteDao()).execute(note);
    }

    public void updateNoteTask(Note note) {
        new UpdateAsyncTask(mNoteDatabase.getNoteDao()).execute(note);
    }

    public LiveData<List<Note>> retrieveNotesTask() {
        return mNoteDatabase.getNoteDao().getNotes();
    }

    public void deleteNoteTask(Note note) {
        new DeleteAsyncTask(mNoteDatabase.getNoteDao()).execute(note);
    }
}
