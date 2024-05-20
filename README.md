# WGPlanner-DDD
This project is a university project. 
It was created as part of the Advanced Software Engineering lecture in the 6th semester and should include the basics and principles of **Domain Driven Design** and **Clean Code**.



## How to start the application
In a first step you have to run the database. Make sure to install Docker before trying to start the database. To start it database, simply run the following command in the root-directory of the project.
```console
docker-compose up -d
```
After this, a docker image of MongoDB should be downloaded and a local MongoDB instance should run via Docker. Now you can start the application normally and the data will be stored in the local instance of MongoDB :)



## How can I see data in the database?
To see your data in the database, you can simply choose a tool of your choice to connect to your local MongoDB instance. I recommend the usage of [MongoDB Compass](https://www.mongodb.com/try/download/compass). It's a free GUI of MongoDB itself to access their databases. 
To connect to the database, you have to copy the following URI:
```console
mongodb://localhost:27017
```
Then click on "Advanced Connection Options" and choose the Authentication section. In this section, you have to choose "Username/Password". Enter the here displayed credentials to the text areas:
```
username: root
password: password
```
(These credentials are also in the docker-compose.yml. If you want to make your database more secure, you can edit the docker-compose.yml and change the username and password to your owns.)

For IntelliJ users': You can also connect with the database by using IntelliJ's in-house database connection interface. Simply click "New Database" -> "Data Source - MongoDB" -> add the given credentials.

**Hint:**
If you use MongoDB Compass, you logged into your database and sent some data, but the database/data isn't displayed. Simply reload the database. To do this, click on the two arrows next to the section named "Databases".
Now the database named "wgplanner" should be displayed and all your data is stored in it.
