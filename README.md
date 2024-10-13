# Jenkins Pipeline for Monolithic Application Deployment

In this project I have deployed simple java based application into Tomcat server using Jenkins pipeline. Below tools are integrated with this pipeline:
- Git: Application code repository
- SonarQube: Static Code Analysis
- Maven: To build code into package and create artifact
- Nexus: Backup repository for release candidate and artifact
- Tomcat: Deployments server for realease candidate or artifact

## Steps
1.Create total 4 EC2 instances of Amazon Linux 2 with t2.medium

2.On 1st server: (Jenkins)
- Install jenkins (with java 17)
- Install Git
- Install maven (with java 11)
- Configure jenkins

3.On 2nd Server: (SonarQube)
- Install sonarqube with java 11
- Configure sonarqube

4.On 3rd server: (Nexus)
- Install nexus with java 11
- Configure nexus

5.On 4th Sever: (Tomcat)
- Install tomcat with java 11
- configure tomcat
- **tomup** and **tomdown** soft link for starting and stoping tomcat server

6.Add jenkins user to in tomcat server and give sudo privileges to jenkins user

7.Configure passwordless authentication from jenkins to tomcat server

8.On Github modify pom.xml for SonarQube and Nexus 



## Jenkins Pipeline
 * ![image](https://github.com/user-attachments/assets/ad166cf1-79ed-4c12-9934-c054c5053e09)
 * ![image](https://github.com/user-attachments/assets/f48d8209-a9de-4da3-ae8c-52fc0fdb3a41)
 * ![image](https://github.com/user-attachments/assets/353653f6-9d82-48bb-ae6d-a18a0c9db923)

## SonarQube Static Code Analysis
  * ![image](https://github.com/user-attachments/assets/a8bac607-cebf-4c78-b041-2765734bf039)

## Nexus Artifact Upload
  * ![image](https://github.com/user-attachments/assets/a18f2634-d4d9-4628-b9a2-453b14846016)

## Deployment in Tomcat Server
  * ![image](https://github.com/user-attachments/assets/7cffee3b-6abc-46bb-8f78-621499ad5696)

## Web Application
  * ![image](https://github.com/user-attachments/assets/0d5d22ce-14ae-46ee-af4f-f4cf3d950fca)







