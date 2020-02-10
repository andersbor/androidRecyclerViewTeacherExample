package dk.easj.anbo.recyclerviewteacherexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class TeacherDetailActivity extends AppCompatActivity {
    public static final String TEACHER = "teacher";
    private static final String LOG_TAG = "TEACHERS";
    private int teacherId;
    private EditText nameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_detail);
        Intent intent = getIntent();
        Teacher teacher = (Teacher) intent.getSerializableExtra(TEACHER);
        Log.d(LOG_TAG, teacher.toString());

        teacherId = teacher.getId();

        TextView textView = findViewById(R.id.teacherDetailIdTextView);
        textView.setText(String.format("Id: %d", teacher.getId()));

        TextView emailView = findViewById(R.id.teacherDetailEmailTextView);
        emailView.setText("Email " + teacher.getEmail());

        TextView salaryView = findViewById(R.id.teacherDetailSalaryTextView);
        salaryView.setText(String.format("Salary %d", teacher.getSalary()));

        nameView = findViewById(R.id.teacherDetailNameEditTExt);
        nameView.setText(teacher.getName());
    }

    public void teacherDetailUpdateButtonClicked(View view) {
        Teacher teacher = Teachers.getTeacherById(teacherId);
        if (teacher != null) {
            String newName = nameView.getText().toString();
            teacher.setName(newName);
        }
    }

    public void teacherDetailBackButtonClicked(View view) {
        finish();
    }
}
