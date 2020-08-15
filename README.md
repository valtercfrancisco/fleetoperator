# tb.lx Backend Challenge 

By [Valter Francisco](mailto:valter_321@hotmail.com)

## Instructions

1. Navigate to [repo](https://github.com/valter321/fleetoperator)

2. Before cloning repo make sure you have Git Large File Storage installed. This will allow you to download the large cvs file
 with the gps data. You can install it following these instructions: `https://docs.github.com/en/github/managing-large-files/installing-git-large-file-storage`

3. Clone repo locally using: `https://github.com/valter321/fleetoperator.git`, 
alternatively download the zip from my github page (Code -> Download ZIP) and unzip to folder.

4. Before running application, we need to make sure we have a MongoDB process running and configure
it for the application. If you don't have MongoDB, download it from here: 
`https://www.mongodb.com/try/download/community` and follow the instructions here to install: 
`https://docs.mongodb.com/manual/tutorial/`. Finally, run mongodb service.

5. Configure the mongodb process by adding the cvs data to it. To do this, open a terminal window, 
cd into fleetoperator folder and type `artifacts/database/database-loader.sh` to run the database
loader and populate your mongodb with gps data. You can also do this from the database folder 
using: `./database-loader.sh`

6. Finally, from the same terminal window we run the application by using
`java -jar artifacts/fleetoperator-0.0.1-SNAPSHOT.jar`. Alternatively, you can simply open the folder in an
IDE (I used IntelliJ), build and run application from there.

7. The server runs by default in port 8080, so make sure you have that port free, and looks to connect to
the default port of mongodb, 27017. You can change these settings in the `application.properties` file. To
change mongodb port simply add `spring.data.mongodb.port=mongoport` to this file. If you change the mongodb
port though, you'll have to stop the mongodb server before running tests, or an exception will be thrown. 
This happens because the application uses mongobd embedded process for tests and when adding this property
both processes will use this port. 

8. The server is running by default on `http://localhost:8080/`, now you can use Postman or your browser to send api requests.

## Tech Stack Discussion

I used the following technologies: 
- Kotlin 
- SpringBoot 
- Gradle 
- Mockk (testing)
- MongoDB.

I used [spring initializr](https://start.spring.io/) to generate the scaffolding for this app.

## Requirements

Build a web service that exposes Vehicle (Bus), Fleet (Operator), and 
Activity (Stop) data, for a given time frame.

The service exposes a RESTful API to answer the following questions:

1.    Given a time frame [start-time, end-time], what is the list of running operators?

2.    Given a time frame [start-time, end-time] and an Operator, what is the list of vehicle IDs?

3.    Given a time frame [start-time, end-time] and a fleet, which vehicles are at a stop?

4.    Given a time frame [start-time, end-time] and a vehicle, return the trace of that vehicle (GPS entries, ordered by timestamp).

## Solution

The server has these 4 endpoints to answer the 4 questions asked above:

1. List running operators: `GET /operators?startDate=2012-11-05&endDate=2012-11-06`  
  
2. List vehicle Ids for an operator: `GET /vehicles?startDate=2012-11-05&endDate=2012-11-06&operator=PO`  
  
3. List vehicle Ids at stop for an operator: `GET /vehicles/atStop?startDate=2012-11-05&endDate=2012-11-06&operator=PO`  
  
4. List of traces of a given vehicle id: `GET /trace?startDate=2012-11-05&endDate=2012-11-06&vehicleId=33228`