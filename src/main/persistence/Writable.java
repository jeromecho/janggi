package persistence;

import org.json.JSONObject;

public interface Writable {
    // EFFECTS: returns a JSON object representation of the given
    //          Java class
    public JSONObject toJson();
}

/*
 structure inspiration for persistence package: https://github.students.cs.ubc.ca/CPSC210/C3-LectureLabStarter
 */
