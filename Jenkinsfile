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
        sh 'docker build -t achat:2.0 . '
      }
    }
stage('PUSH IMAGE') {
    steps {
        withCredentials([string(credentialsId: 'Dockerhub', variable: 'Dockerhub')]) {
            sh "echo ${Dockerhub} | docker login -u jihedmelki -p ${Dockerhub}"
            sh "docker tag achat:2.0 jihedmelki/springimageee1"
            sh "docker push jihedmelki/springimageee1"
        }
    }
}
stage('DOCKER COMPOSE'){
    steps{
        sh 'docker compose up -d '
      }
    }
        stage('PROMETHEUS GRAFANA'){
    steps{
        echo 'done !'
      }
    }
    }
    post {
            always{
                
                
                emailext to: "melkijihed1@gmail.com",
                subject: "jenkins build:${currentBuild.currentResult}: ${env.JOB_NAME}",
                body: "${currentBuild.currentResult}: Job ${env.JOB_NAME}\nMore Info can be found here: ${env.BUILD_URL}"

            }
        }
}
