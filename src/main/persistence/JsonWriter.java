package persistence;

import model.GameManager;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

/*
 Represents the writer for writing a janggi game manager to file
 */
public class JsonWriter {
    private String path;
    private PrintWriter writer;

    // EFFECTS: constructs a JSON Writer that writes data
    //          with a path and writer
    public JsonWriter(String path) {
        this.path = path;
    }

    // EFFECTS: writes a game manager to file
    public void writeGameManager(GameManager gameManager) throws FileNotFoundException {
        JSONObject json = gameManager.toJson();
        open();
        saveToFile(json.toString());
        close();
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    private void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: opens a writer that writes to file at path; throws
    //          a FileNotFoundException if destination file
    //          cannot be opened for writing
    private void open() throws FileNotFoundException {
        this.writer = new PrintWriter(new File(path));
    }

    // MODIFIES: this (why?) - TO FIGURE OUT
    // EFFECTS: writes string to file at PATH
    private void saveToFile(String json) {
        writer.print(json);
    }

    public String getPath() {
        return path;
    }
}

/*
 structure inspiration for persistence package: https://github.students.cs.ubc.ca/CPSC210/C3-LectureLabStarter
 */
