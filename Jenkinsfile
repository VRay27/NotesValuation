pipeline {
    agent any 
    stages {
               stage('Compile and Clean') { 
                 steps {
                   sh "printenv"
                   sh "mvn clean compile"
                        }
                     }
               stage('Test') { 
                 steps {
                   sh "mvn test"
                        }
                     }  
               stage('deploy') { 
                 steps {
                   sh "mvn package"
                       }
                     }     
        stage('Build Docker image'){
            steps {
                sh 'docker build -t rayv2701/cicd27:${BUILD_NUMBER} .'
            }
        }

        stage('Docker Login'){
            
            steps {
                 withCredentials([string(credentialsId: 'Dockerid', variable: 'Dockerpwd')]) {
                    sh "docker login -u rayv2701 -p ${Dockerpwd}"
                }
            }                
        }

        stage('Docker Push'){
            steps {
                sh 'docker push rayv2701/cicd27:${BUILD_NUMBER}'
            }
        }
        
       

               stage('Archving') { 
                 steps {
                   archiveArtifacts '**/target/*.war'
            }
        }
  }
}
