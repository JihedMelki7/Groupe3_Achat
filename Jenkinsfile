pipeline {
  agent any 

  triggers {
        githubPush()
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
        sh 'docker build -t achat:2-0 . '
      }
    }
        stage('PUSH IMAGE'){
      steps{
        sh "docker login -u youssefhadiji956 -p Hadijiyoussef@1998 "
        sh " docker tag achat:2-0 youssefhadiji956/achat:2-0"
        sh " docker push youssefhadiji956/achat:2-0"
      }
    }
       stage('DOCKER down'){
      steps{
        sh 'docker compose down '
      }
    }
        stage('DOCKER COMPOSE'){
      steps{
        sh 'docker compose up -d '
      }
    }
  
 }
}
