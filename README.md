# RestSqlProject
Team members: 
Zacky Kharboutli
Tim Larsson 
Pär Söderlund 
Alexandra Bjäremo

Spring boot application that handles queries for SQL database from web application 

Website interface:

localhost:8080/person             	lists all the persons in the databse
localhost:8080/person/delete      	lets you delete a person
localhost:8080/create		  	create person
localhost:8080/person/name/<name> 	search by <name>
localhost:8080/person/id/<id>		search by <id>
localhost:8080/person/number/<number>	search by <number>
localhost:8080/person/update/<id>	update by <id>


Apis:

localhost:8080/api/people         	list all persons as JSON
localhost:8080/api/emails        	list all emails as JSON
localhost:8080/api/phones        	list all phones as JSON
localhost:8080/api/addresses         	list all addresses as JSON
localhost:8080/api/relations         	list all relations as JSON

Heroku link
https://personsdb4task17.herokuapp.com/ 
