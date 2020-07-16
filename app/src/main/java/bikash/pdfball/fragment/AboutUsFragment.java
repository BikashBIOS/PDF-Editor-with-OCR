package bikash.pdfball.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.OnClick;
import bikash.pdfball.R;
import bikash.pdfball.util.FeedbackUtils;

public class AboutUsFragment extends Fragment {

    private Activity mActivity;
    private FeedbackUtils mFeedbackUtils;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_about_us, container, false);
        ButterKnife.bind(this, rootView);
        try {
            PackageInfo packageInfo = mActivity.getPackageManager().getPackageInfo(mActivity.getPackageName(), 0);
            TextView versionText = rootView.findViewById(R.id.version_value);
            String version = versionText.getText().toString() + " " + packageInfo.versionName;
            versionText.setText(version);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        mFeedbackUtils = new FeedbackUtils(mActivity);
        return rootView;
    }

    @OnClick(R.id.layout_email)
    public void sendmail() {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"bikash.ultracures@gmail.com"});
        intent.putExtra(Intent.EXTRA_SUBJECT, mActivity.getResources().getString(R.string.feedback_subject));
        intent.putExtra(Intent.EXTRA_TEXT, mActivity.getResources().getString(R.string.feedback_text));
        mFeedbackUtils.openMailIntent(intent);
    }

    @OnClick(R.id.layout_website)
    void openWeb() {
        mFeedbackUtils.openWebPage("");
    }

    @OnClick(R.id.layout_slack)
    void joinSlack() {
        mFeedbackUtils.openWebPage("" );
    }

    @OnClick(R.id.layout_github)
    void githubRepo() {
        mFeedbackUtils.openWebPage("https://github.com/BikashRanjanOjha/PDF-Editor");
    }

    @OnClick(R.id.layout_contri)
    void contributorsList() {
        mFeedbackUtils.openWebPage("");
    }

    @OnClick(R.id.layout_playstore)
    void openPlaystore() {
        mFeedbackUtils.openWebPage("");
    }

    @OnClick(R.id.layout_privacy)
    void privacyPolicy() {
        mFeedbackUtils.openWebPage("");
    }

    @OnClick(R.id.layout_license)
    void license() {
        mFeedbackUtils.openWebPage("");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
    }
}
