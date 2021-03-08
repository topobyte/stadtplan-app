#!/bin/bash

DIR=$(dirname $0)

OUT="$DIR/../app/src/main/assets"
MAP="$OUT/map.xmap.jet"
DB="$OUT/map.sqlite.jet"

curl http://osmtestdata.topobyte.de/stadtplan/191007/Cambridge.xmap > "$MAP"
curl http://osmtestdata.topobyte.de/stadtplan/191007/Cambridge.sqlite | gzip > "$DB"
