# CruiseCompany
## DESCRIPTION OF THE PROJECT
Cruise Company system. Company has several Ships. Ships have passenger - capacity, route, number of visited ports, one cruise duration, staff. Client chooses and pays cruise. Chooses Excursions upon arrival at the port for extra paying. Ship Administrator indicates bonuses for passengers, considering ticket class (pool, gym, cinema, beauty salon, etc…).
## INSTRUCTION FOR THE INSTALLATION OF THE PROJECT
1. Download and unzip the archive with the project or download using Git client (e.g. git clone https://github.com/Vyshnyak/CruiseCompany)
2. Connect to MySQL Server using MySQL Workbench.
3. Create a database by importing the database dump (file "dbdumps" at the root of the repository):
   - In MySQL Server, go to - Server -> Data Import
   - In the Import From Dump Project Folder option, select the path to the database dump file (file "dbdumps" at the root of the repository)
   - Push the button Start Import
## INSTRUCTION FOR STARTING THE PROJECT
1. Istall maven http://www.apache-maven.ru/install.html
2. In project directory open command line
3. Enter command "mvn tomcat7:run"
4. Open browser and follow the link http://localhost:8080/
