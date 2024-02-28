# apt-get update &&  apt-get install apt-transport-https
#wget -qO- https://dl-ssl.google.com/linux/linux_signing_key.pub |  gpg  --dearmor -o /usr/share/keyrings/dart.gpg
#
#echo 'deb [signed-by=/usr/share/keyrings/dart.gpg arch=amd64] https://storage.googleapis.com/download.dartlang.org/linux/debian stable main' |  tee /etc/apt/sources.list.d/dart_stable.list
#
# apt-get update &&  apt-get install dart
#dpkg -i dart_3.2.6-1_amd64.deb

echo 'xxx6'


##RUN mkdir /mnt/host_folder/testtt
#RUN mkdir -p /mnt/host_folder
#RUN mkdir -p /mnt/host_folder/testtt
#
#
## Define working directory
#RUN rx_bloc_cli create /mnt/host_folder/testtt --project-name xxx
#FROM ubuntu:latest

# Create a directory on the host machine
#RUN mkdir -p /mnt/host_folder/1
#FROM google/dart
#RUN dart pub global activate rx_bloc_cli
#CMD ["./script.sh"]
#RUN apt-get update
#RUN apt-get install -y software-properties-common python-software-properties
#RUN apt-add-repository ppa:hachre/dart
#RUN apt-get -y update
#RUN apt-get install -y dartsdk
#FROM ubuntu:latest
export PATH=$PATH:/mnt/flutter/bin

#C:\Users\progr\dev\flutter_linux_3.19.1-stable\flutter\bin
#not working
#apt-get -y update
#apt-get -y install git
#
#wget https://storage.googleapis.com/dart-archive/channels/stable/release/latest/linux_packages/dart_3.2.5-1_amd64.deb
#dpkg -i dart_3.2.5-1_amd64.deb

#not working in docker
#wget -qO- https://dl-ssl.google.com/linux/linux_signing_key.pub |  gpg --dearmor -o /usr/share/keyrings/dart.gpg
#echo 'deb [signed-by=/usr/share/keyrings/dart.gpg arch=amd64] https://storage.googleapis.com/download.dartlang.org/linux/debian stable main' |  tee /etc/apt/sources.list.d/dart_stable.list


#^[[A^[[A^[[B^[[B^C
 ## find / -name git
 #/var/lib/git
 #/usr/share/lintian/overrides/git
 #/usr/share/doc/git
 #/usr/share/bash-completion/completions/git
 #/usr/bin/git
 #/usr/lib/git-core/git
 ## ^C
 ##

dart pub global activate rx_bloc_cli
export PATH=/mnt/flutter/bin:${PATH}
apt update
apt install ruby-full

java -jar /mnt/host_folder/rx_bloc_cli_wrapper_web_app-1.0-SNAPSHOT.jar


# /root/.pub-cache/bin/rx_bloc_cli create . --project-name asds --organisation com.programtom --no-enable-analytics --no-enable-feature-counter --no-enable-feature-deeplinks --no-enable-feature-widget-toolkit --no-enable-login --no-enable-social-logins --no-enable-auth-matrix --no-enable-pin-code --no-enable-otp --no-enable-dev-menu --realtime-communication none --cicd none --no-enable-patrol --no-enable-change-language --no-interactive