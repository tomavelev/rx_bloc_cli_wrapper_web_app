mvn clean package -Pproduction -Dvaadin.force.production.build=true

move /y target\rx_bloc_cli_wrapper_web_app-1.0-SNAPSHOT.jar host_folder\app.jar

docker build -t rx_bloc_cli_wrapper_web_app .

#docker-compose up --force-recreate
#https://github.com/openai/whisper
#https://platform.openai.com/docs/guides/text-generation/parameter-details




#$ docker-compose build --no-cache
#$ docker-compose up --force-recreate