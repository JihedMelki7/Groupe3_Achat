pipeline {
  agent any 
  stages {
    stage('git checkout') {
      steps {
        git branch : 'fournisseurYoussef',
        url: 'https://github.com/JihedMelki7/Groupe3_Achat.git',
        credentialsId: '17737efd-7305-407e-8c90-ace550bbcdb7';
        echo 'checkout stage'
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
    
  
  
 }
}
