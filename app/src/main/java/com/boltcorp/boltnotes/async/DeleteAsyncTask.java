package com.boltcorp.boltnotes.async;

import android.os.AsyncTask;
import android.util.Log;

import com.boltcorp.boltnotes.models.Note;
import com.boltcorp.boltnotes.persistence.NoteDao;

public class DeleteAsyncTask extends AsyncTask<Note, Void, Void> {
    private static final String TAG = "InsertAsyncTask";

    private NoteDao mNoteDao;

    public DeleteAsyncTask(NoteDao dao) {
        mNoteDao = dao;
    }

    @Override
    protected Void doInBackground(Note... notes) {
        Log.d(TAG, "doInBackground: thread: "+Thread.currentThread().getName());
        mNoteDao.deleteNotes(notes);
        return null;
    }
}
