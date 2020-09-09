package dk.easj.anbo.recyclerviewteacherexample;

class Teachers {
    private static final Teacher[] teachers = new Teacher[]{
            new Teacher(1, "Anders", "anbo@zealand.dk", 1000),
            new Teacher(2, "Peter", "pele@zealand.dk", 1100),
            new Teacher(3, "Michael", "micl@zealand.dk", 1200)
    };

    public static Teacher[] getAllTeachers() {
        return teachers;
    }

    public static Teacher getTeacherById(int id) {
        for (Teacher t : teachers) {
            if (t.getId() == id) return t;
        }
        return null;
    }
}
