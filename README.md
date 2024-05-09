## Here the Drive Link for Jar File

### [Jar](https://drive.google.com/drive/folders/1RDgtIy9zG0qTLmgX1E7SnUSDH3GEB7ql?usp=drive_link)

---

This file can be used as a template for initializing and running spring projects.

What's included:

1. Gradle file created from start.spring.io
2. Other dependencies like MySQL.
3. Spring Tests
4. CRUD operation on data

---

Usage -

- To build the repository -

  From the repository root,

  - run command in terminal `./gradlew clean build -x test` to create executable jar. The jar will be located inside build directories

## Port Used

To run the server defined port is **8081**

---

## Authentication with Spring Security

This project utilizes Spring Security to handle authentication and authorization.

- ### API for Auth:

  (http://localhost:8081)

  - /auth/login
  - /auth/register

---

- #### Public APIS

  (http://localhost:8081)

  - /auth/login
  - /auth/register
  - /hotels
  - /hotels/{hotelId}
  - /swagger-ui.html : for API documentation

---

### Need to be added:

- #### mysql-config.properties

  - mysql.host=hostname
  - mysql.port=portname
  - mysql.database=dbname
  - mysql.username=username
  - mysql.password=password

- #### application.properties

  inside application.properties add your own secret key

  - application.security.jwt.secret-key=YourSecretKey(256Bit Hashed Key)

---

- ## If you want to run in local

  Since this application uses cloud MySQL server from [Aiven](https://aiven.io) as database and I have removed mysql-config.properties file which contain hostname, portname, username, password, etc. of database, if you want to use mysql cloud you can use [Aiven](https://aiven.io) add the file in main/resources
  FileName should be exact same as I have provided this filename to LmsApplication.java as property file, if you wish to change it change in HotelMangmentAggregatorAppApplication.java too by provding as `@PropertySource("classpath:mysql-config.properties")` on class level.

---

- ## If you want to host this application

  You can use [Render](https://render.com/) to host this application

  - Clone or Fork this repo
  - You must have a cloud-based Database in this case MYSQL to host this
  - By default the build directory is ignored, forcefully add the jar files using the command:
    `git add -f build/libs`
  - Also you need to push .jar file in your /build/libs/\*\*\*.jar to Github repo using `git lfs track "*.jar"` and `git add .gitattributes` before adding to git as .jar files are large.

    - For this in local run `./gradlew clean build -x test`

  - Add, Commit and Push your changes to GitHub make sure /build/libs/\*\*\*.jar is present.

  - In [Render](https://render.com/) go to Web Service hosting
  - Authorize using Github Repo
  - Add following to your environment variables in Render
    - spring.datasource.url=URL
    - spring.datasource.username=username
    - spring.datasource.password=password
    - application.security.jwt.secret-key=your-256-bit-generated-hex-code
  - Check your logs in log tab to see furthur error and resolve accrodingly
  - Also You can check "/swagger-ui/index.html" for API documentation

---

- ## PostMan Collection

  - [PostManCollection.json](https://github.com/kunaljs-sudo/HotelManagementAggregatorApp/blob/main/HotelBookingManagementAggregator.postman_collection.json)
