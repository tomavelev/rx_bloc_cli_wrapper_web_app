This is a simple Vaadin Web App that facilitates creation of rx_bloc_cli projects.
It is not coded with the idea of public hosting, but as internal tool.

* It stores the data in a file - h2 database
* It has hard-coded login with commented code - to replace it with db authentication

After creation of an app - that usually takes 30s to minute - the app refreshes tha page and allows you to download a
zip of the project.

Because it has several requirements for iOS development 
 - ruby,
 - pods, cocoapods,
 - dart, flutter, rx_bloc_cli
it needs to be executed from a macOS environment. I've tried it - running it on Windows (failing) and in Docker.

You need to update the application property settings for where is flutter and where the projects will be generated.
Mine are:<br>
flutter_directory=$HOME/Downloads/flutter/bin<br>
building_directory=/Users/tomavelev/apps/fff

To build it - you need Java 21 and Maven. Once you have those installed. You could 
- build it with 'mvn clean package -Pproduction -Dvaadin.force.production.build=true'
- run it with java -jar target/rx_bloc_cli_wrapper_web_app-1.0-SNAPSHOT.jar