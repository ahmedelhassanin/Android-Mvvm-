package com.example.mvvmex;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {

    public NoteRepository noteRepository;
    public LiveData<List<Note>> allnote;

    public NoteViewModel(@NonNull Application application) {
        super(application);

        noteRepository=new NoteRepository(application);
        allnote=noteRepository.getAllNotes();

    }

    public void insert(Note note){

        noteRepository.insert(note);
    }

    public void update(Note note){
        noteRepository.update(note);

    }

    public void delete(Note note){
        noteRepository.delete(note);

    }

    public void deleteAllNotes(){
        noteRepository.deleteAllNotes();

    }

    public LiveData<List<Note>> getAllNotes(){
        return allnote;
    }

}
