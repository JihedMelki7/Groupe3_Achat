pipeline {
    agent any

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
        withCredentials([usernamePassword(credentialsId: 'b29c8358-2305-401b-b2f3-834f94c1d8a8', passwordVariable: 'sonar', usernameVariable: 'admin')]) {
                    sh "mvn sonar:sonar -Dsonar.login=$admin -Dsonar.password=$sonar"
                }
      }
    }

       stage('Unit Tests') {
          steps {
          sh 'mvn test'  
                }
        }
    }
}
