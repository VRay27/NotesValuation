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
                 withCredentials([string(credentialsId: 'DockerId', variable: 'docker_pwd')]) {
                    sh "docker login -u rayv2701 -p ${docker_pwd}"
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
