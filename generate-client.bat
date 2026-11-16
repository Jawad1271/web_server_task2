@echo off
echo =========================================================
echo Make sure the Spring Boot application is RUNNING 
echo before executing this script!
echo =========================================================
echo Generating Web Service Client from WSDL...

if not exist "src\main\java" mkdir src\main\java

wsimport -keep -p lt.viko.eif.ihasan.Task2.client.generated -s src\main\java http://localhost:8081/ws/student?wsdl

echo =========================================================
echo Done! Client files generated in:
echo src\main\java\lt\viko\eif\ihasan\Task2\client\generated
echo =========================================================
pause
