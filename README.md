# farm-data
Displaying data from different farms.

## Table of contents
* [General info](#general-info)
* [Technologies](#technologies)
* [Prerequisites](#prerequisites)
* [Run](#run)

## General info
This project aims to upload CSV files and filter with different granularities for processing. Filtered and validated data uploads to MySQL database.
Endpoints to fetch data from farms with different granularities (by month, by metric) were considered. Pagination is considered inside all endpoints that fetches data from DB for making client side easier. 
Testing is considered in controller and service layer.
	
## Technologies
Project is created with:
* JDK: 8
* Gradle
* Spring Boot

## Prerequisites
At first, JDK8 (Java Development Kit) should be installed.After that,MySQL server should be locally installed and the parameters in application.yml file must be the same.
The project may be run either on Windows or Linux. Project has tests that is no configuration needed to run. 

## Run
To run this project, install it locally using git clone:
cd to project directory then run following
```
./gradlew bootRun

