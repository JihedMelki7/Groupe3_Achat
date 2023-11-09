pipeline {
    agent any

    environment {
    DOCKERHUB_CREDENTIALS = credentials('DockerHub Credentials')
  }

    stages {
        
        stage('GIT') {
            steps {
                git branch : 'SecteurActivite-MohamedJihed',
                url: 'https://github.com/JihedMelki7/Groupe3_Achat.git',
                credentialsId: 'b29c8358-2305-401b-b2f3-834f94c1d8a8';
                echo 'checkout stage'
            }
        }
        

        stage('Clean') {
            steps {
                script {
                    // Clean the project
                    sh 'mvn clean'
                }
            }
        }

        stage('Compile') {
            steps {
                script {
                    // Compile the project
                    sh 'mvn compile'
                }
            }
        }

        stage ('SONAR') {
           steps {
                withCredentials([usernamePassword(credentialsId: 'f6ef2775-c9c0-41a2-a557-feee36341238', passwordVariable: 'sonar', usernameVariable: 'admin')]) {
                    sh "mvn sonar:sonar -Dsonar.login=$admin -Dsonar.password=$sonar"
                }
      }
    }

       stage('Unit Tests') {
          steps {
              sh 'mvn test'  
                }
        }
        
        stage ('NEXUS ') {
            steps {
               sh 'mvn deploy -DskipTests'
                  }
        }
        
        stage('BUILD IMAGE'){
            steps{
                sh 'docker build -t achat:6-0 . '
                  }
        }
    }
}
