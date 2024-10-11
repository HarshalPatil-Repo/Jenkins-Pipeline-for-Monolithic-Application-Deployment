pipeline {
    agent any

    stages {
        stage('Git Checkout') {
            steps {
                git 'https://github.com/HarshalPatil-Repo/Jenkins-Pipeline-for-Monolithic-Application-Deployment.git'
            }
        }
        stage('Sonar Code Testing') {
            steps {
                sh 'mvn sonar:sonar'
            }
        }
        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }
        stage('Nexus Artifact Upload') {
            steps {
                nexusArtifactUploader artifacts: [[artifactId: 'myweb', classifier: '', file: 'target/myweb-8.2.17-SNAPSHOT.war', type: 'war']], credentialsId: 'nexus3', groupId: 'in.javahome', nexusUrl: '172.31.86.205:8081', nexusVersion: 'nexus3', protocol: 'http', repository: 'maven-snapshots', version: '8.2.17-SNAPSHOT'
            }
        }
        stage('Tomcat Deployemnt') {
            steps {
                sh """
                  ssh jenkins@172.31.86.100 sudo rm -rf /var/tmp/*.war
                  ssh jenkins@172.31.86.100 sudo rm -rf /opt/apache-tomcat-9.0.54/webapps/*.war
                  scp /var/lib/jenkins/workspace/MonoliticApplicatonDeployment/target/*.war jenkins@172.31.86.100:/var/tmp
                  ssh jenkins@172.31.86.100 sudo cp /var/tmp/*.war /opt/apache-tomcat-9.0.54/webapps/
                  ssh jenkins@172.31.86.100 sudo tomdown
                  ssh jenkins@172.31.86.100 sudo tomup
                """
            }
        }
    }
}
