package com.example.androidtask.view.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.androidtask.R;
import com.example.androidtask.callback.OnItemClickListner;
import com.example.androidtask.data.model.ItemHome;
import com.example.androidtask.databinding.FragmentHomeBinding;
import com.example.androidtask.repository.HomeRepository;
import com.example.androidtask.util.Constants;
import com.example.androidtask.viewmodel.HomeViewModel;


public class HomeFragment extends Fragment implements OnItemClickListner {

    private FragmentHomeBinding binding;
    private HomeRepository repository;
    private HomeViewModel viewModel;

    private HomeAdapter homeAdapter;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        repository = HomeRepository.getInstance(getActivity().getApplication());
        viewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        viewModel.setRepository(repository);
        initUI();
        observe();
        return binding.getRoot();
    }

    private void initUI() {
        binding.hotspotsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        binding.eventsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        binding.attractionRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
    }

    private void observe() {
        viewModel.getHomeData().observe(this, apiResponse -> {
            if (apiResponse != null) {
                homeAdapter = new HomeAdapter(apiResponse.getData().getHotspots(), this);
                binding.hotspotsRecyclerView.setAdapter(homeAdapter);

                homeAdapter = new HomeAdapter(apiResponse.getData().getEvents(), this);
                binding.eventsRecyclerView.setAdapter(homeAdapter);

                homeAdapter = new HomeAdapter(apiResponse.getData().getAttractions(), this);
                binding.attractionRecyclerView.setAdapter(homeAdapter);
            }
        });
        viewModel.getLoading().observe(this, isLoading -> {
            if (isLoading) {
                binding.setIsLoading(true);
            } else {
                binding.setIsLoading(false);
            }
        });

        viewModel.getErrorMessage().observe(this, message -> {
            if (!message.isEmpty()) {
                Bundle bundle = new Bundle();
                bundle.putString(Constants.TYPE, Constants.ERROR);
                bundle.putString(Constants.MESSAGE, message);
                Navigation.findNavController(getActivity(), R.id.navHostFragment).navigate(R.id.alertBottomSheetFragment, bundle);
            }
        });
    }

    @Override
    public void onItemClick(ItemHome item) {
        Toast.makeText(getContext(), item.getName().substring(0, 5), Toast.LENGTH_SHORT).show();
    }
}
