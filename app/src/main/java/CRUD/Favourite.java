package CRUD;

import com.google.firebase.database.IgnoreExtraProperties;


@IgnoreExtraProperties
public class Favourite {
    private String exerciseId;
    private String exerciseName;
    private String exerciseGenre;

    public Favourite(){

    }

    public Favourite(String exerciseId, String exerciseName, String exerciseGenre) {
        this.exerciseId = exerciseId;
        this.exerciseName = exerciseName;
        this.exerciseGenre = exerciseGenre;
    }

    public String getExerciseId() {
        return exerciseId;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public String getExerciseGenre() {
        return exerciseGenre;
    }
}
