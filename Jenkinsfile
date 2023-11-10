pipeline {
  agent any 

  environment {
    DOCKERHUB_CREDENTIALS = credentials('dockerHub')
  }
  
  stages {
    stage('GIT') {
      steps {
        git branch : 'fournisseurYoussef',
        url: 'https://github.com/JihedMelki7/Groupe3_Achat.git',
        credentialsId: '17737efd-7305-407e-8c90-ace550bbcdb7';
        echo 'checkout stage'
           }
  }

    
    stage ('MVN BUILD') {
      steps {
        sh 'mvn clean install'
        echo 'Build stage done'
      }
    }
  

     stage ('MVN TEST') {
      steps {
        sh 'mvn test'
      }
    }
       stage ('SONAR') {
       steps {
        withCredentials([usernamePassword(credentialsId: 'a8b63053-9fcf-41e8-b155-8c14dcc9117d', passwordVariable: 'sonar', usernameVariable: 'admin')]) {
                    sh "mvn sonar:sonar -Dsonar.login=$admin -Dsonar.password=$sonar"
                }
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
        stage('Login') {
      steps {
        sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
      }
    }
        stage('PUSH IMAGE'){
      steps{
        sh " docker tag achat:6-0 youssefhadiji956/achat:6-0"
        sh " docker push youssefhadiji956/achat:6-0"
      }
    }

        stage('START APP'){
      steps{
        sh 'docker compose up -d '
      }
    }
  
 }
        post {
            always{
                
                
                emailext to: "hadijiyoussef@gmail.com",
                subject: "jenkins build:${currentBuild.currentResult}: ${env.JOB_NAME}",
                body: "${currentBuild.currentResult}: Job ${env.JOB_NAME}\nMore Info can be found here: ${env.BUILD_URL}"

                
            cleanWs()
            }
        }
}
