package Services;

import java.util.Comparator;
import Entities.*;

public class FacultyComparator implements Comparator<Faculty> {
    public int compare(Faculty a, Faculty b) {
        return a.getFacultyName().compareTo(b.getFacultyName());
    }
}
