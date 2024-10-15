
pipeline {
    agent any

    environment {
        // Define version using BUILD_NUMBER and an optional custom suffix
        NEW_VERSION = "8.2.${BUILD_NUMBER}-SNAPSHOT"
    }


    stages {
        stage('Git Checkout') {
            steps {
                git 'https://github.com/HarshalPatil-Repo/Jenkins-Pipeline-for-Monolithic-Application-Deployment.git'
            }
        }
        stage('Version Change') {
            steps {
                sh """
                        mvn versions:set -DnewVersion=${NEW_VERSION}
                    """
                // Confirm the version has been updated
                sh "cat pom.xml"
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
                nexusArtifactUploader artifacts: [[artifactId: 'myweb', classifier: '', file: "target/myweb-${NEW_VERSION}.war", type: 'war']], credentialsId: 'nexus3', groupId: 'in.javahome', nexusUrl: '44.211.139.20:8081', nexusVersion: 'nexus3', protocol: 'http', repository: 'maven-snapshots', version: "${NEW_VERSION}"
            }
        }
        stage('Tomcat Deployemnt') {
            steps {
                sh """
                  ssh jenkins@172.31.91.73 sudo rm -rf /var/tmp/*.war
                  ssh jenkins@172.31.91.73 sudo rm -rf /opt/apache-tomcat-9.0.96/webapps/myweb-8.2.*
                  scp /var/lib/jenkins/workspace/MonoliticApplicatonDeployment/target/*.war jenkins@172.31.91.73:/var/tmp
                  ssh jenkins@172.31.91.73 sudo cp /var/tmp/*.war /opt/apache-tomcat-9.0.96/webapps/
                  ssh jenkins@172.31.91.73 sudo tomdown
                  ssh jenkins@172.31.91.73 sudo tomup
                """
            }
        }
    }
    post {
        success {
            script {
                // Push updatedd pom.xml to git repository
                withCredentials([sshUserPrivateKey(credentialsId: 'github-ssh-key', keyFileVariable: 'SSH_KEY')]) {
                    sh """
                        git config user.email "hpharshalmech@gmail.com"
                        git config user.name "HarshalPatil"
                        git add pom.xml
                        git commit -m "Update version to ${NEW_VERSION} [Jenkins Build #${BUILD_NUMBER}]"
                        git push git@github.com:HarshalPatil-Repo/Jenkins-Pipeline-for-Monolithic-Application-Deployment.git HEAD:master
                    """
               }
          }
    }
    always {
        cleanWs()
      }
}
