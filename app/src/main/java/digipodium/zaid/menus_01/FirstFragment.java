package digipodium.zaid.menus_01;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ShareCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

public class FirstFragment extends Fragment implements View.OnClickListener {

    private TextView textNote1,textNote2;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textNote1 = view.findViewById(R.id.textNote1);
        textNote2 = view.findViewById(R.id.textNote2);
        ImageView imgMenu = view.findViewById(R.id.imgMenu);
        ImageView imgMenu2 = view.findViewById(R.id.imgMenu2);
        imgMenu.setOnClickListener(this);
        imgMenu2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        PopupMenu pop = new PopupMenu(getActivity(), v);
        pop.getMenuInflater().inflate(R.menu.menu_notes_popup, pop.getMenu());
        switch (v.getId()){
            case R.id.imgMenu:
                pop.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.note_pop_copy:
                                String data = textNote1.getText().toString();
                                ClipboardManager manager = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                                ClipData clipData = ClipData.newPlainText("note1",data);
                                manager.setPrimaryClip(clipData);
                                Toast.makeText(getActivity(), "copied to Clipboard", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.note_pop_edit:
                                break;
                            case R.id.note_pop_share:
                                String notes = textNote1.getText().toString();
                                Intent intent = ShareCompat.IntentBuilder.from(getActivity()).setSubject("note 1").setText(notes).createChooserIntent();
                                startActivity(intent);
                                break;
                        }
                        return true;
                    }
                });
                break;
            case R.id.imgMenu2:
                pop.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.note_pop_copy:
                                String data = textNote2.getText().toString();
                                ClipboardManager manager = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                                ClipData clipData = ClipData.newPlainText("note2",data);
                                manager.setPrimaryClip(clipData);
                                Toast.makeText(getActivity(), "copied to Clipboard", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.note_pop_edit:
                                break;
                            case R.id.note_pop_share:
                                String notes = textNote2.getText().toString();
                                Intent intent = ShareCompat.IntentBuilder.from(getActivity()).setSubject("note 2").setText(notes).createChooserIntent();
                                startActivity(intent);
                                break;
                        }
                        return true;
                    }
                });
                break;
        }
        pop.show();
    }
}