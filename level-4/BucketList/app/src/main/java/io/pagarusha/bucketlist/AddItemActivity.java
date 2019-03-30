package io.pagarusha.bucketlist;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AddItemActivity extends AppCompatActivity {

    private TextInputEditText etTitle, etDescription;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_list_item_view);

        etTitle = findViewById(R.id.editText_title);
        etDescription = findViewById(R.id.editText_description);
        button = findViewById(R.id.btn_add_item);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String title = etTitle.getText().toString();
                String description = etDescription.getText().toString();
//                BucketListItem item = new BucketListItem(title, description, false);

                Intent intent = new Intent(AddItemActivity.this, MainActivity.class);
                intent.putExtra("title", title);
                intent.putExtra("description", description);
                setResult(Activity.RESULT_OK, intent);
                finish();

            }
        });

    }
}
