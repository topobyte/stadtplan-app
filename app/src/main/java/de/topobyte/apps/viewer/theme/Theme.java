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

package de.topobyte.apps.viewer.theme;

public class Theme
{
  private final String name;
  private final String key;
  private final boolean premium;

  public Theme(String name, String key, boolean premium)
  {
    this.name = name;
    this.key = key;
    this.premium = premium;
  }

  public String getName()
  {
    return name;
  }

  public String getKey()
  {
    return key;
  }

  public boolean isPremium()
  {
    return premium;
  }

}
