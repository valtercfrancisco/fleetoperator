#! /bin/bash

SOURCE="${BASH_SOURCE[0]}"
while [ -h "$SOURCE" ]; do # resolve $SOURCE until the file is no longer a symlink
  DIR="$( cd -P "$( dirname "$SOURCE" )" >/dev/null 2>&1 && pwd )"
  SOURCE="$(readlink "$SOURCE")"
  [[ $SOURCE != /* ]] && SOURCE="$DIR/$SOURCE" # if $SOURCE was a relative symlink, we need to resolve it relative to the path where the symlink file was located
done
SCRIPT_PATH="$( cd -P "$( dirname "$SOURCE" )" >/dev/null 2>&1 && pwd )"

mongoimport --type csv --db fleet-operator --collection gpsdata \
  --columnsHaveTypes \
  --fields="timestamp.auto(),lineId.auto(),direction.auto(),journeyPatternId.string(),timeFrame.date(2006-01-02),vehicleJourneyId.auto(),operator.auto(),congestion.boolean(),longitude.double(),latitude.auto(),delay.auto(),blockId.auto(),vehicleId.auto(),stopId.auto(),atStop.auto()" \
  --file "$SCRIPT_PATH"/siri.20121106.csv
