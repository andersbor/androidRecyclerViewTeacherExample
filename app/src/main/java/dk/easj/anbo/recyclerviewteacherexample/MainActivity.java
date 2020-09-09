package dk.easj.anbo.recyclerviewteacherexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.Arrays;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG = "TEACHERS";
    private RecyclerViewSimpleAdapter<Teacher> adapter;
    private final Teacher[] allTeachers = Teachers.getAllTeachers();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Want to update the Adapters data when coming back from the child activity
        RecyclerView recyclerView = findViewById(R.id.mainTeachersRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecyclerViewSimpleAdapter<>(Arrays.asList(allTeachers));
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new RecyclerViewSimpleAdapter.OnItemClickListener<Teacher>() {

            //@Override
            public void onItemClick(View view, int position, Teacher teacher) {
                Log.d(LOG_TAG, "Clicked " + position + " " + adapter.getItemId(position) + " " + teacher);
                Intent intent = new Intent(getBaseContext(), TeacherDetailActivity.class);
                intent.putExtra(TeacherDetailActivity.TEACHER, teacher);
                startActivity(intent);
            }
        });
    }

    public void orderByNameClicked(View view) {
        Arrays.sort(allTeachers, new Comparator<Teacher>() {
            // modern Java does have Lambda expressions
            // https://developer.android.com/studio/write/java8-support
            @Override
            public int compare(Teacher t1, Teacher t2) {
                return t1.getName().compareToIgnoreCase(t2.getName());
            }
        });
        adapter.notifyDataSetChanged();
    }

    public void orderByEmailClicked(View view) {
        Arrays.sort(allTeachers, new Comparator<Teacher>() {
            @Override
            public int compare(Teacher t1, Teacher t2) {
                return t1.getEmail().compareToIgnoreCase(t2.getEmail());
            }
        });
        adapter.notifyDataSetChanged();
    }

    public void orderBySalaryClicked(View view) {
        Arrays.sort(allTeachers, new Comparator<Teacher>() {
            @Override
            public int compare(Teacher t1, Teacher t2) {
                return t1.getSalary() - t2.getSalary();
            }
        });
        adapter.notifyDataSetChanged();
    }
}