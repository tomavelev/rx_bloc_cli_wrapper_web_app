
FROM eclipse-temurin:21-jdk
WORKDIR /mnt/host_folder

#RUN apt-get update
#RUN apt-get install git -y
#RUN apt-get install -y software-properties-common python-software-properties
#RUN apt-add-repository ppa:hachre/dart
#RUN apt-get -y update
#RUN apt-get install -y dartsdk
#RUN apt-get install libdart6-dev
#RUN dart pub global activate rx_bloc_cli

#RUN echo $PATH
#ENV PATH="$PATH:/etc/profile"
#ENV PATH=/mnt/flutter/bin:${PATH}
#RUN echo $PATH
#sudo apt update
 #
 #2. Use the following command to install Ruby:
 #
 #sudo apt install ruby-full
CMD ["./script.sh"]
