package org.example.spring;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@RequiredArgsConstructor
@Service
public class NoteService {

    private static final Logger LOGGER = LoggerFactory.getLogger(NoteService.class);
   private final Map<Long, Note> noteMap = new HashMap<>();
   private final AtomicLong idGenerator = new AtomicLong();


    public List<Note> listAll() {
        return new ArrayList<>(noteMap.values());
    }

    public Note add(Note note) {
        long id = idGenerator.incrementAndGet();
        note.setId(id);
        noteMap.put(id, note);
        return note;
    }

    public void deleteById(long id) {
        if (noteMap.containsKey(id)) {
            noteMap.remove(id);
        } else {
            throw new RuntimeException("There is no note with such ID");
        }
    }

    public void update(Note note) {
        if (noteMap.containsKey(note.getId())) {
            Note oldNote = noteMap.get(note.getId());
            oldNote.setContent(note.getContent());
            oldNote.setTitle(note.getTitle());
        } else {
            throw new RuntimeException("There is no note with such ID");
        }
    }

    public Note getById(long id) {
        if (noteMap.containsKey(id)) {
            return noteMap.get(id);
        } else {
            throw new RuntimeException("There is no note with such ID");
        }
    }

    @PostConstruct
    public void postConstruct() {
        LOGGER.info("Bean created: {}", this.getClass().getName());
    }

}
