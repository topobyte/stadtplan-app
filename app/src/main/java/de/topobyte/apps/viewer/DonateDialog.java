// Copyright 2023 Sebastian Kuerten
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

package de.topobyte.apps.viewer;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import de.topobyte.android.intent.utils.ThankOption;
import de.topobyte.android.intent.utils.TopobyteIntentFactory;
import de.topobyte.apps.maps.atestcity.R;

public class DonateDialog extends DialogFragment
{

  public static final String ARG_VALUE = "value";

  private ThankOption value;

  @Override
  @SuppressLint("InflateParams")
  public Dialog onCreateDialog(@Nullable Bundle savedInstanceState)
  {
    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

    LayoutInflater inflater = getActivity().getLayoutInflater();

    builder.setTitle(R.string.cr_support_us);
    View view = inflater.inflate(R.layout.dialog_donate, null);
    builder.setView(view);

    Bundle arguments = getArguments();
    value = (ThankOption) arguments.get(ARG_VALUE);

    builder.setNegativeButton(android.R.string.cancel,
        (dialog, id) -> {
          // ignore
        });

    Button button = view.findViewById(R.id.button);
    button.setOnClickListener(v -> {
      openGooglePlay();
      dismiss();
    });

    return builder.create();
  }

  private void openGooglePlay()
  {
    Intent intent = TopobyteIntentFactory.createThanksAppDetailIntent(value);
    startActivity(intent);
  }
}
