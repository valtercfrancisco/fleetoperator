#! /bin/bash

SCRIPT=$(readlink "$0")
SCRIPT_PATH=$(dirname "$SCRIPT")

mongoimport --type csv --db fleet-operator --collection gpsdata \
  --columnsHaveTypes \
  --fields="timestamp.auto(),lineId.auto(),direction.auto(),journeyPatternId.string(),timeFrame.date(2006-01-02),vehicleJourneyId.auto(),operator.auto(),congestion.boolean(),longitude.double(),latitude.auto(),delay.auto(),blockId.auto(),vehicleId.auto(),stopId.auto(),atStop.auto()" \
  --file "$SCRIPT_PATH"/siri.20121106.csv
