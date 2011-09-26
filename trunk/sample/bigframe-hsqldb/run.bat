cd bigframe-biz
call mvn clean install
cd ../bigframe-webapp
call mvn clean package -Dmaven.test.skip=true
call mvn jetty:run