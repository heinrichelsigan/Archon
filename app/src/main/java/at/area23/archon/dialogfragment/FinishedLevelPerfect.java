/**
 * @author           <a href="mailto:heinrich.elsigan@area23.at">Heinrich Elsigan</a>
 * @version          V 1.0.1
 * @since            API 27 Oreo 8.1
 *
 * <p>Archon android</p>
 *
 * <P>Coded 2023 by <a href="mailto:heinrich.elsigan@area23.at">Heinrich Elsigan</a>
 */

package at.area23.archon.dialogfragment;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.utils.widget.MotionLabel;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import org.jetbrains.annotations.NotNull;

import at.area23.archon.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FinishedLevelPerfect#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FinishedLevelPerfect extends DialogFragment {

    /* The activity that creates an instance of this dialog fragment must
     * implement this interface in order to receive event callbacks.
     * Each method passes the DialogFragment in case the host needs to query it. */
    public interface NoticeDialogListener {
        public void onDialogPositiveClick(DialogFragment dialog);
        public void onDialogNegativeClick(DialogFragment dialog);
        // public void onDialogCancel(DialogFragment dialog);
    }

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static String ARG_PARAM1 = "perfect";
    private static String ARG_PARAM2 = "currentLevel";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private static int aPerfect = 1;
    private static int aCurrentLevel = 1;
    public int perfectGame = 1;

    Context context;
    // Video view
    ImageView imgViewHeader, imgViewLevelCompleted, imgViewPerfect, imgViewNextLevel;
    // MotionLabel mlabel_frogA_level;

    // Use this instance of the interface to deliver action events
    NoticeDialogListener listener;

    public FinishedLevelPerfect() {
        // Required empty public constructor

    }


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param perfect Parameter 1.
     * @param currentLevel Parameter 2.
     * @return A new instance of fragment FinishedLevelDialogFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FinishedLevelPerfect newInstance(int perfect, int currentLevel) {
        FinishedLevelPerfect fragment = new FinishedLevelPerfect();
        Bundle args = new Bundle();

        args.putInt(ARG_PARAM1, perfect);
        args.putInt(ARG_PARAM2, currentLevel);
        fragment.setArguments(args);
        fragment.perfectGame = perfect;

        aPerfect = perfect;
        aCurrentLevel = currentLevel;

        return fragment;
    }

    // Override the Fragment.onAttach() method to instantiate the NoticeDialogListener
    @Override
    public void onAttach(Context c) {
        super.onAttach(c);
        context = c;

        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            listener = (NoticeDialogListener) context;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException("You must implement NoticeDialogListener" + e.getMessage());
        }
    }

    @Override
    public @NotNull Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        final DialogFragment dialogFragment = this;

        if (savedInstanceState != null) {
            perfectGame = savedInstanceState.getInt(getString(R.string.msg_perfect), 1);
        }

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(inflater.inflate(R.layout.fragment_finished_level_perfect_dialog, null))
                // Add action buttons
                .setPositiveButton(R.string.msg_level_switch, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        listener.onDialogPositiveClick(dialogFragment);
                        dialogFragment.getDialog().cancel();
                    }
                })
                // .setOnCancelListener(new DialogInterface.OnCancelListener() {
                //     @Override
                //     public void onCancel(DialogInterface dialog) {
                //         listener.onDialogCancel(dialogFragment);
                //         dialogFragment.getDialog().cancel();
                //     }
                // })
                .setNegativeButton(R.string.msg_replay, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        listener.onDialogNegativeClick(dialogFragment);
                        dialogFragment.getDialog().cancel();
                    }
                });

        // getArguments
        Bundle bundle = getArguments();
        if (bundle != null) {
            try {
                aPerfect =  bundle.getInt(getString(R.string.msg_perfect));
            } catch (Exception exBundle) { }
            try {
                aCurrentLevel = bundle.getInt(getString(R.string.msg_level_white));
            } catch (Exception exBundle) { }
        }

        return builder.create();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_finished_level_perfect_dialog, container, false);
    }


    @Override
    public void show(@NonNull FragmentManager manager, @Nullable String tag) {
        super.show(manager, tag);

        // startVideo(manager);
    }

    @Override
    public void onStart() {
        super.onStart();

        View view = null;
        VideoView vView = null;
        try {
            view = getView();
            // imgViewHeader = (ImageView) view.findViewById(R.id.imgViewHeader);
            imgViewLevelCompleted = (ImageView) view.findViewById(R.id.imgViewLevelCompleted);
            imgViewPerfect = (ImageView) view.findViewById(R.id.imgViewPerfect);
            imgViewPerfect.setVisibility(View.VISIBLE);
            switch (aCurrentLevel) {
                case 1:
                    Drawable drawable1 = getResources().getDrawable(R.drawable.message_level1completed, null);
                    imgViewLevelCompleted.setImageDrawable(drawable1);
                    break;
                case 2:
                    Drawable drawable2 = getResources().getDrawable(R.drawable.message_level2completed, null);
                    imgViewLevelCompleted.setImageDrawable(drawable2);
                    break;
                case 3:
                    Drawable drawable3 = getResources().getDrawable(R.drawable.message_level3completed, null);
                    imgViewLevelCompleted.setImageDrawable(drawable3);
                    break;
                default: Drawable drawable4 = getResources().getDrawable(R.drawable.message_levelcompleted, null);
                    imgViewLevelCompleted.setImageDrawable(drawable4);
                    break;
            }
            imgViewLevelCompleted.setVisibility(View.VISIBLE);
            // mlabel_frogA_level = (MotionLabel) view.findViewById(R.id.labelFrogLevel);
            // mlabel_frogA_level.setText(String.valueOf(aCurrentLevel));

        } catch (Exception exi) {
            exi.printStackTrace();
        }
    }


}