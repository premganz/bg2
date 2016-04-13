#!/bin/bash
#rm -rf  master.zip.2
#rm -rf  bg2-master/
#wget  https://github.com/premganz/bg2/archive/master.zip
#unzip master.zip.2
#cd bg2-master/
if [[ -s deploy.flag ]] ; then
echo "$FILE has data."
mvn clean install -P fatjar
java -cp /home/ec2-user/jetty/bg1-master/target/bg1-1.0.0.jar:/home/ec2-user/jetty/bg1-master/src/main/resources/*:/home/ec2-user/jetty/bg1-master/target/lib/* org.spo.ifs.template.Main
else
echo "deployflag is empty."
fi ;
