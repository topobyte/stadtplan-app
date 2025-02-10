// Copyright 2021 Sebastian Kuerten
//
// This file is part of stadtplan-app.
//
// stadtplan-app is free software: you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
// stadtplan-app is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
// GNU General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with stadtplan-app. If not, see <http://www.gnu.org/licenses/>.

package de.topobyte.apps.viewer.activities;

import static de.topobyte.android.common.resources.DonateDialog.donateDialog;

import android.content.Intent;
import android.os.Bundle;
import android.text.util.Linkify;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.topobyte.android.common.resources.CommonIcons;
import de.topobyte.android.common.resources.DonateDialog;
import de.topobyte.android.intent.utils.IntentFactory;
import de.topobyte.android.intent.utils.ThankOption;
import de.topobyte.apps.maps.stadtplan.BuildConfig;
import de.topobyte.apps.maps.stadtplan.R;
import de.topobyte.apps.viewer.FeedbackUtil;

public class AboutActivity extends PlainActivity
{

  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);

    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    setContentView(R.layout.activity_about);

    TextView appVersion = findViewById(R.id.textViewAppVersion);
    String appVersionTemplate = getString(R.string.app_version);
    String versionName = BuildConfig.VERSION_NAME;
    appVersion.setText(String.format(appVersionTemplate, versionName));

    setupFeedback();
    setupDonate();

    TextView website = findViewById(R.id.textViewWebsite);
    TextView repo = findViewById(R.id.textViewRepo);
    TextView license = findViewById(R.id.textViewLicense);

    license.setText(
        String.format("%s: %s", getString(R.string.about_license), getString(R.string.gpl_3_0)));

    Linkify.addLinks(website, Pattern.compile("www.topobyte.de"), "", allFilter,
        new StaticUrlTransformer("https://www.topobyte.de"));
    Linkify.addLinks(repo, Pattern.compile("topobyte/stadtplan-app"), "", allFilter,
        new StaticUrlTransformer("https://github.com/topobyte/stadtplan-app"));
    Linkify.addLinks(license, Pattern.compile(getString(R.string.gpl_3_0)), "", allFilter,
        new StaticUrlTransformer("https://www.gnu.org/licenses/gpl-3.0.en.html"));

    TextView mapCopyright = findViewById(R.id.textViewMapCopyright);
    TextView mapLicense = findViewById(R.id.textViewMapLicense);

    Linkify.addLinks(mapCopyright, Pattern.compile("OpenStreetMap"), "", allFilter,
        new StaticUrlTransformer("http://openstreetmap.org/about"));
    Linkify.addLinks(mapLicense, Pattern.compile("Open Database License"), "", allFilter,
        new StaticUrlTransformer("http://opendatacommons.org/licenses/odbl"));
  }

  private void setupFeedback()
  {
    Button buttonRate = findViewById(R.id.buttonRate);
    Button buttonMail = findViewById(R.id.buttonMail);
    Button buttonShare = findViewById(R.id.buttonShare);
    Button buttonCommunity = findViewById(R.id.buttonCommunity);

    CommonIcons commonIcons = new CommonIcons(this, 36);

    commonIcons.setRate(buttonRate);
    commonIcons.setEmail(buttonMail);
    commonIcons.setShare(buttonShare);
    commonIcons.setGroup(buttonCommunity);

    buttonRate.setOnClickListener(view -> {
      Intent intent = IntentFactory.createRateAppIntent(AboutActivity.this);
      startActivity(intent);
    });

    buttonMail.setOnClickListener(view -> FeedbackUtil.sendFeedbackMail(AboutActivity.this));

    buttonShare.setOnClickListener(view -> FeedbackUtil.share(AboutActivity.this));

    buttonCommunity.setOnClickListener(view -> {
      Locale locale = Locale.getDefault();
      String lang = locale.getLanguage();
      String url = "https://www.topobyte.de/stadtplan-app/community";
      if (lang.equals("de")) {
        url = "https://www.topobyte.de/de/stadtplan-app/community";
      }
      startActivity(IntentFactory.createUrlIntent(url));
    });
  }

  private void setupDonate()
  {
    Button buttonDonate1 = findViewById(R.id.buttonDonate1);
    Button buttonDonate2 = findViewById(R.id.buttonDonate2);
    Button buttonDonate5 = findViewById(R.id.buttonDonate5);
    Button buttonDonate10 = findViewById(R.id.buttonDonate10);

    CommonIcons commonIcons = new CommonIcons(this, 36);

    commonIcons.setCafe(buttonDonate1);
    commonIcons.setBeer(buttonDonate2);
    commonIcons.setCinema(buttonDonate5);
    commonIcons.setRestaurant(buttonDonate10);

    buttonDonate1.setOnClickListener(view -> {
      DonateDialog dialog = donateDialog(ThankOption.THANK_1);
      dialog.show(getSupportFragmentManager(), null);
    });

    buttonDonate2.setOnClickListener(view -> {
      DonateDialog dialog = donateDialog(ThankOption.THANK_2);
      dialog.show(getSupportFragmentManager(), null);
    });

    buttonDonate5.setOnClickListener(view -> {
      DonateDialog dialog = donateDialog(ThankOption.THANK_5);
      dialog.show(getSupportFragmentManager(), null);
    });

    buttonDonate10.setOnClickListener(view -> {
      DonateDialog dialog = donateDialog(ThankOption.THANK_10);
      dialog.show(getSupportFragmentManager(), null);
    });
  }

  static final Linkify.MatchFilter allFilter = (s, start, end) -> true;

  static class StaticUrlTransformer implements Linkify.TransformFilter
  {

    private final String link;

    public StaticUrlTransformer(String link)
    {
      this.link = link;
    }

    @Override
    public String transformUrl(Matcher match, String url)
    {
      return link;
    }
  }

}
