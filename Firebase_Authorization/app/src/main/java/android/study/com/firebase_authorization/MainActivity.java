package android.study.com.firebase_authorization;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    Button btnSignIn;
    Button btnSignUp;
    Button btnSignOut;

    EditText etEmail;
    EditText etPw;

    ListView listView;

    FirebaseDatabase database;
    DatabaseReference userRef;

    ListAdapter adapter;
    ValueEventListener valueEventListener;

    ArrayList<Map<String, User>> datas = new ArrayList<>();

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etEmail = (EditText) findViewById(R.id.etEmail);
        etPw = (EditText) findViewById(R.id.etPw);

        mAuth = FirebaseAuth.getInstance();
        // 데이터베이스 커넥션
        database = FirebaseDatabase.getInstance();
        listView = (ListView) findViewById(R.id.listView);

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d("TAG", "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d("TAG", "onAuthStateChanged:signed_out");
                }
            }
        };

        btnSignUp = (Button) findViewById(R.id.btnSignUp);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString();
                String pw = etPw.getText().toString();

                if (!"".equals(email) && !"".equals(pw)) {
                    addUser(email, pw);
                } else {
                    Toast.makeText(MainActivity.this, "Email과 PW를 올바르게 입력 해 주십시오", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnSignIn = (Button) findViewById(R.id.btnSignIn);
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString();
                String pw = etPw.getText().toString();

                if (!"".equals(email) && !"".equals(pw)) {
                    signIn(email, pw);
                } else {
                    Toast.makeText(MainActivity.this, "Email과 PW를 올바르게 입력 해 주십시오", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnSignOut = (Button) findViewById(R.id.btnSignOut);
        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                Toast.makeText(MainActivity.this, "로그 아웃!!", Toast.LENGTH_SHORT).show();


            }
        });
    }

    public void signIn(String email, String pw) {
        mAuth.signInWithEmailAndPassword(email, pw)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful())
                            Toast.makeText(MainActivity.this, "로그인 실패!!", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(MainActivity.this, "로그인 성공!!", Toast.LENGTH_SHORT).show();

                        adapter = new ListAdapter();
                        listView.setAdapter(adapter);
                    }
                });

        // 참조포인트
        userRef = database.getReference("users");
        valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot users) {
                Log.e("FireBase", "snapshot=" + users.getValue());

                datas = new ArrayList<>();
                for (DataSnapshot userData : users.getChildren()) {
                    try {
                        Map<String, User> data = new HashMap<>();
                        String userId = userData.getKey();
                        User user = userData.getValue(User.class);
                        data.put(userId, user);
                        datas.add(data);
                    } catch (Exception e) {
                        // 데이터 구조가 달라서 매핑이 안될경우 예외처리
                        e.printStackTrace();
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        userRef.addValueEventListener(valueEventListener);
    }

    public void addUser(String email, String pw) {
        mAuth.createUserWithEmailAndPassword(email, pw)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful())
                            Toast.makeText(MainActivity.this, "생성 실패!!", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(MainActivity.this, "생성 성공!!", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });

    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    class ListAdapter extends BaseAdapter {

        LayoutInflater inflater;

        public ListAdapter() {
            inflater = getLayoutInflater();
        }

        @Override
        public int getCount() {
            return datas.size();
        }

        @Override
        public Object getItem(int position) {
            return datas.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null)
                convertView = inflater.inflate(R.layout.list_item, null);

            TextView tvName = (TextView) convertView.findViewById(R.id.tvName);
            TextView tvUid = (TextView) convertView.findViewById(R.id.tvUid);
            TextView tvEmail = (TextView) convertView.findViewById(R.id.tvEmail);

            Map<String, User> data = datas.get(position);
            String uid = data.keySet().iterator().next();
            User user = data.get(uid);

            tvUid.setText(uid);
            tvName.setText(user.username);
            tvEmail.setText(user.email);

            return convertView;
        }
    }
}
