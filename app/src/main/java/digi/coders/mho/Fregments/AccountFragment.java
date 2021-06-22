package digi.coders.mho.Fregments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.squareup.picasso.Picasso;
import digi.coders.mho.Activity.EditProfileActivty;
import digi.coders.mho.Helper.Constant;
import digi.coders.mho.Helper.PrefrenceManager;
import digi.coders.mho.Model.UserDetailsModel;
import digi.coders.mho.R;

public class AccountFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    TextView country;
    TextView dateofbirth;
    TextView daycount;
    TextView edit_profile;
    TextView language;
    private String mParam1;
    private String mParam2;
    ImageView porfile;
    TextView tag;
    TextView userid;
    TextView username;
    View view;

    public static AccountFragment newInstance(String param1, String param2) {
        AccountFragment fragment = new AccountFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.mParam1 = getArguments().getString(ARG_PARAM1);
            this.mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_account, container, false);
        this.view = inflate;
        this.dateofbirth = (TextView) inflate.findViewById(R.id.dateofbirth);
        this.language = (TextView) this.view.findViewById(R.id.language);
        this.tag = (TextView) this.view.findViewById(R.id.tag);
        this.daycount = (TextView) this.view.findViewById(R.id.daycount);
        this.country = (TextView) this.view.findViewById(R.id.country);
        this.username = (TextView) this.view.findViewById(R.id.username);
        this.userid = (TextView) this.view.findViewById(R.id.userid);
        this.porfile = (ImageView) this.view.findViewById(R.id.porfile);
        this.edit_profile = (TextView) this.view.findViewById(R.id.edit_profile);
        UserDetailsModel userDetailsModel = new PrefrenceManager(getContext()).getuserdetails();
        this.username.setText(userDetailsModel.getUser_name());
        this.dateofbirth.setText(userDetailsModel.getDateofbirth());
        this.tag.setText(userDetailsModel.getTag());
        this.daycount.setText("6 Days");
        this.country.setText(userDetailsModel.getCountry());
        this.userid.setText(userDetailsModel.getUser_id());
        Picasso.get().load(Constant.PROFILE_Url + "" + userDetailsModel.getPhoto()).placeholder(R.drawable.profile).error(R.drawable.profile).into(this.porfile);
        this.edit_profile.setOnClickListener(new View.OnClickListener() {
            /* class digi.coders.mho.Fregments.AccountFragment.AnonymousClass1 */

            public void onClick(View view) {
                AccountFragment.this.startActivity(new Intent(AccountFragment.this.getContext(), EditProfileActivty.class));
            }
        });
        return this.view;
    }
}
