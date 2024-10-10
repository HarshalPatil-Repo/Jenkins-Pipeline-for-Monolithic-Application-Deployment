# Jenkins Pipeline for Monolithic Application Deployment

In this project I have deployed simple java based application into Tomcat server using Jenkins pipeline. Below tools are integrated with this pipeline:
- Git: Application code repository
- SonarQube: Static Code Analysis
- Maven: To build code into package and create artifact
- Nexus: Backup repository for release candidate and artifact
- Tomcat: Deployments server for realease candidate or artifact

## Steps
1. Create total 4 EC2 instances of Amazon Linux 2 with t2.medium

2. On 1st server: (Jenkins)
- Install jenkins (with java 17)
- Install Git
- Install maven (with java 11)
- Configure jenkins

3. On 2nd Server: (SonarQube)
- Install sonarqube with java 11
- Configure sonarqube

4. On 3rd server: (Nexus)
- Install nexus with java 11
- Configure nexus

5. On 4th Sever: (Tomcat)
- Install tomcat with java 11
- configure tomcat
- **tomup** and **tomdown** soft link for starting and stoping tomcat server

6. Add jenkins user to in tomcat server and give sudo privileges to jenkins user

7. Configure passwordless authentication from jenkins to tomcat server

8. On Github modify pom.xml for SonarQube and Nexus 



## Jenkins Pipeline
 * ![image](https://github.com/user-attachments/assets/4c0c26db-ed89-492b-9c7a-14a69ea95b99)
 * ![image](https://github.com/user-attachments/assets/fb28679c-e063-4375-82ac-bd6f6818f08a)

## SonarQube Static Code Analysis
  * ![image](https://github.com/user-attachments/assets/86c6c612-1205-41a3-910c-281da0db0458)

## Nexus Artifact Upload
  * ![image](https://github.com/user-attachments/assets/841af9a4-b02a-4b7e-bf09-883924e61e38)

## Deployment in Tomcat Server
  * ![image](https://github.com/user-attachments/assets/f456993c-b7b2-4399-8283-3769f3864003)

## Web Application
  * ![image](https://github.com/user-attachments/assets/0e81533f-885b-40c8-8a0b-b7ed35af23a2)






