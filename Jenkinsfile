pipeline {
  agent any 

  triggers {
        githubPush()
    }
  
  stages {
    stage('git checkout') {
      steps {
        git branch : 'fournisseurYoussef',
        url: 'https://github.com/JihedMelki7/Groupe3_Achat.git',
        credentialsId: '17737efd-7305-407e-8c90-ace550bbcdb7';
        echo 'checkout stage'
           }
  }
     stage('Affichage de la date système') {
            steps {
                sh 'date'
            }
        }
    
  
    stage ('MVN BUILD') {
      steps {
        sh 'mvn clean package'
        echo 'Build stage done'
      }
    }
  
    stage ('MVN COMPILE') {
      steps {
        sh 'mvn compile'
        echo 'compile stage done'

      }
    }
     stage ('MVN TEST') {
      steps {
        sh 'mvn test'
      }
    }
       stage ('STATIC TEST WITH SONAR') {
       steps {
        withCredentials([usernamePassword(credentialsId: 'a8b63053-9fcf-41e8-b155-8c14dcc9117d', passwordVariable: 'sonar', usernameVariable: 'admin')]) {
                    sh "mvn sonar:sonar -Dsonar.login=$admin -Dsonar.password=$sonar"
                }
      }
    }
    stage ('NEXUS DEPLOY') {
       steps {
       sh 'mvn deploy -DskipTests'
        
      }
    }
    stage('build image docker'){
      steps{
        sh 'docker build -t achat:1-0 . '
      }
    }
        stage('build push image'){
      steps{
        sh "docker login -u youssefhadiji956 -p Hadijiyoussef@1998 "
        sh " docker tag achat:1-0 youssefhadiji956/achat:1-0"
        sh " docker push youssefhadiji956/achat:1-0"
      }
    }
  
 }
}
