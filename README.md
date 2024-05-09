This file can be used as a template for initializing and running spring projects.

What's included:

1. Gradle file created from start.spring.io
2. Other dependencies like MySQL.
3. Spring Tests
4. CRUD operation on data

Usage -

1. To build the repository -

From the repository root,

1. run `./gradlew clean build -x test`run the build
2. run `./gradlew bootjar` to create executable jar. The jar will be located inside build directories.

## Authentication with Spring Security

This project utilizes Spring Security to handle authentication and authorization.

### API for Auth:

- /auth/login
- /auth/register

#### Public APIS

- /auth/login
- /auth/register
- /hotels
- /hotels/{hotelId}
- /swagger-ui.html : for API documentation

## If you want to run in local

- ### Need to be added:

Since this application uses cloud MySQL server from [Aiven](https://aiven.io) as database and I have removed mysql-config.properties file which contain hostname, portname, username, password, etc. of database, if you want to use mysql cloud you can use [Aiven](https://aiven.io) add the file in main/resources
FileName should be exact same as I have provided this filename to LmsApplication.java as property file, if you wish to change it change in LmsApplication.java too

- #### mysql-config.properties

  - mysql.host=hostname
  - mysql.port=portname
  - mysql.database=dbname
  - mysql.username=username
  - mysql.password=password

- ### application.properties

  inside application.properties add your own secret key

  - application.security.jwt.secret-key=YourSecretKey

## If you want to host this application

You can use [Render](https://render.com/) to host this application

- At first you must have a cloud-based Database in this case MYSQL to host this
- Also you need to push .jar file in your /build/libs/\*\*\*.jar to Github repo

  - For this after cloning repo run `./gradlew clean build -x test`

- Clone this repo
- In [Render](https://render.com/) go to Web Service hosting
- Authorize using Github Repo
- Add fllowing to your environment variables in Render
  - spring.datasource.url=URL
  - spring.datasource.username=username
  - spring.datasource.password=password
  - application.security.jwt.secret-key=your-256-bit-generated-hex-code
- Check your logs in log tab to see furthur error and resolve accrodingly
- Also You can check "/swagger-ui/index.html" for API documentation

## PostMan Collection

- [PostManCollection.json](https://github.com/kunaljs-sudo/HotelManagementAggregatorApp/blob/main/HotelBookingManagementAggregator.postman_collection.json)
