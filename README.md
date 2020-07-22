# car-tracker-seed
-----------------------------------------------------------------------
-----------------------------------------------------------------------
Following are the commands and the instructions to run the project.

-----------------------------------------------------------------------
## Clone the project from the git repository in the local directory

####Command: git clone "url"

Description: Now, you have the project on the local directory

-----------------------------------------------------------------------
## Instruction to import:

#### File --> Import --> Existing Maven Projects --> browse the project

Description: Now, you have the project in your IDE

-----------------------------------------------------------------------
## Instruction to run: 

###Note: Change the application.properties file with your local MySQL DB configuration
####Command: Build the project --> Right Click --> Maven --> Update Projects
			 Right Click on Application Class --> Run As --> Java Application

-----------------------------------------------------------------------
## Description:
1) PUT /api/vehicles and include the Vehicle JSON in the body
 --> Create/Update vehicles in the db
2) POST /api/readings and include the Reading JSON in the body
 --> create the entry in reading, tire and alert database
3) GET /api/vehicles
 --> Fetch all the vehicles from the db
4) GET /api/alerts/high
 --> Will fetch all the HIGH alerts within last two hours
5) GET /api/alerts/historical/vin
 --> Will fetch all the historical alerts for the given id
6) GET /api/vehicles/geolocation/vin
 --> Will fetch the geolocation of the vehicle in last 30 mins

-----------------------------------------------------------------------
## UNIT TESTING
1) Created JUNIT Test class for AlertService, VehicleService and ReadingService
2) Created JUNIT Suite class and included all the JUNIT class into it

## Integration TESTING
1) Created Test Class for AlertController, ReadingController and VehicleController

-----------------------------------------------------------------------

Date: [07/22/2020]
Signature: Kaustubh Singh

