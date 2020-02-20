package com.example.androidtask.view.alert;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;

import androidx.databinding.DataBindingUtil;

import com.example.androidtask.R;
import com.example.androidtask.databinding.FragmentAlertBottomSheetBinding;
import com.example.androidtask.util.Constants;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class AlertBottomSheetFragment extends BottomSheetDialogFragment {

    private FragmentAlertBottomSheetBinding binding;
    private BottomSheetBehavior bottomSheetBehavior;
    private String type;
    private String message;

    public AlertBottomSheetFragment() {
        // Required empty public constructor
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        BottomSheetDialog bottomSheet = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);

        //inflating layout
        View view = View.inflate(getContext(), R.layout.fragment_alert_bottom_sheet, null);
        //binding views to data binding.
        binding = DataBindingUtil.bind(view);
        //setting layout with bottom sheet
        bottomSheet.setContentView(view);
        bottomSheetBehavior = BottomSheetBehavior.from((View) (view.getParent()));

        type = getArguments().getString(Constants.TYPE);
        message = getArguments().getString(Constants.MESSAGE);

        binding.alertMessage.setText(message);

        if (type != null) {
            if (type.equals(Constants.ERROR)) {
                binding.alertImage.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.error));
            } else if (type.equals(Constants.WARNING)) {
                binding.alertImage.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.warning));
            } else if (type.equals(Constants.SUCCESS)) {
                binding.alertImage.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.success));
            }

        }

        return bottomSheet;
    }

    @Override
    public void onStart() {
        super.onStart();
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
    }

}
